package Entidades;

import Colecoes.ColecaoAluno;
import Excecoes.*;
import Media.MediaAritimetica;
import Media.MediaIF;
import Media.MediaMaiorUltima;
import Media.MediaMenorNota;

import java.io.Serializable;

public class Turma implements Serializable {
    private ColecaoAluno colecaoAluno;
    private Disciplina disciplina;
    private Professor professor;
    private String idTurma;
    private boolean isAtiva;
    private int quantUnidades;
    private MediaIF tipoMedia;
    private Map<Integer, Map<Integer, Double>> notas;

    private String gerarIdTurma() {
        return UUID.randomUUID().toString().substring(0, 3).toUpperCase();
    }

    public Turma(Disciplina disciplina, Professor professor) throws DadoInvalidoException {
        if (disciplina == null) {
            throw new DadoInvalidoException("Disciplina");
        }
        if (professor == null) {
            throw new DadoInvalidoException("Professor");
        }

        this.disciplina = disciplina;
        this.professor = professor;
        this.idTurma = gerarIdTurma();
        this.colecaoAluno = new ColecaoAluno();
        this.notas = new HashMap<>();
        this.isAtiva = true;
    }

    public void adicionaAluno(Aluno aluno) throws DadoInvalidoException, EntidadeCadastradaException, EntidadeNullException {
        if (aluno == null) {
            throw new DadoInvalidoException("Aluno");
        }
        colecaoAluno.adicionar(aluno);
    }

    public void removeAlunoId(int idAluno) throws EntidadeNaoEncontradaException, EntidadeDesativadaException, DadoInvalidoException, EntidadeNaoCadastradaException {
        Aluno alunoRemove = colecaoAluno.buscar(idAluno);
        if (alunoRemove == null) {
            throw new EntidadeNaoEncontradaException("Aluno");
        }
        if (!alunoRemove.isAtivo()) {
            throw new EntidadeDesativadaException("Aluno");
        }
        alunoRemove.desativar();
    }

    public ArrayList<Aluno> listarAlunos() throws EntidadeNaoCadastradaException {
        if (colecaoAluno.getTodos().isEmpty()) {
            throw new EntidadeNaoCadastradaException("Aluno");
        }
        ArrayList<Aluno> alunos = new ArrayList<>();
        for (Aluno aluno : colecaoAluno.getTodos()) {
            if (aluno.isAtivo()) {
                alunos.add(aluno);
            }
        }
        return alunos;
    }

    public String getIdTurma() {
        return idTurma;
    }

    public void setIdTurma(String idTurma) throws DadoInvalidoException {
        if (idTurma == null) {
            throw new DadoInvalidoException("id");
        }
        this.idTurma = idTurma;
    }

    public Professor getProfessor() {
        return professor;
    }

    public void setProfessor(Professor professor) {
        this.professor = professor;
    }

    public void defineQuantUnidades(final int quantUnidades) throws DadoInvalidoException {
        if (quantUnidades <= 0) {
            throw new DadoInvalidoException("Quantidade");
        }
        this.quantUnidades = quantUnidades;
    }

    public int getQuantUnidades() {
        return quantUnidades;
    }

    public boolean isAtiva() {
        return isAtiva;
    }

    public void encerraTurma() {
        this.isAtiva = false;
    }

    public void adicionarNota(int idAluno, int unidade, double valor) throws EntidadeNaoEncontradaException, EntidadeDesativadaException, DadoInvalidoException, EntidadeNaoCadastradaException {
        Aluno aluno = colecaoAluno.buscar(idAluno);
        if (aluno == null) {
            throw new EntidadeNaoEncontradaException("Aluno");
        }
        if (!aluno.isAtivo()) {
            throw new EntidadeDesativadaException("Aluno");
        }
        if (valor < 0 || valor > 10) {
            throw new DadoInvalidoException("Nota");
        }
        if (unidade <= 0 || unidade > quantUnidades) {
            throw new DadoInvalidoException("Unidade");
        }

        notas.putIfAbsent(idAluno, new HashMap<>());
        notas.get(idAluno).put(unidade, valor);
    }

    public List<Double> listarNotasAluno(int idAluno) throws EntidadeNaoEncontradaException, EntidadeDesativadaException, DadoInvalidoException, EntidadeNaoCadastradaException {
        Aluno aluno = colecaoAluno.buscar(idAluno);
        if (aluno == null) {
            throw new EntidadeNaoEncontradaException("Aluno");
        }
        if (!aluno.isAtivo()) {
            throw new EntidadeDesativadaException("Aluno");
        }

        Map<Integer, Double> notasAluno = notas.get(idAluno);
        if (notasAluno == null) return Collections.emptyList();

        List<Double> listaNotas = new ArrayList<>();
        for (int i = 1; i <= quantUnidades; i++) {
            listaNotas.add(notasAluno.getOrDefault(i, 0.0));
        }
        return listaNotas;
    }

    public List<List<Double>> listarNotasTurma(Turma turma) throws EntidadeNaoCadastradaException {
        List<List<Double>> notasTurma = new ArrayList<>();
        List<Aluno> alunos = colecaoAluno.getTodos();
        // Itera sobre os alunos da turma
        for (Aluno aluno : alunos) {
            // Buscar as notas do aluno
            Map<Integer, Double> notasAluno = notas.get(aluno.getId());
            if (notasAluno == null) {
                notasTurma.add(Collections.emptyList());
            } else {
                List<Double> listaNotas = new ArrayList<>();
                for (int i = 1; i <= quantUnidades; i++) {
                    listaNotas.add(notasAluno.getOrDefault(i, 0.0));
                }
                notasTurma.add(listaNotas);
            }
        }
        return notasTurma;
    }

    public void defineTipoMedia(int tipo) throws DadoInvalidoException {
        if (tipo <= 0 || tipo > 3) {
            throw new DadoInvalidoException("Tipo de média");
        }
        switch (tipo) {
            case 1 -> tipoMedia = new MediaAritimetica();
            case 2 -> tipoMedia = new MediaMenorNota();
            case 3 -> tipoMedia = new MediaMaiorUltima();
        }
    }

    public Map<Integer, Double> calcularMedias() throws EntidadeNullException {
        if (tipoMedia == null) {
            throw new EntidadeNullException("Tipo de média");
        }
        Map<Integer, Double> medias = new HashMap<>();
        for (Integer idAluno : notas.keySet()) {
            Map<Integer, Double> notasAluno = notas.get(idAluno);
            List<Double> valores = new ArrayList<>();
            for (int i = 1; i <= quantUnidades; i++) {
                Double nota = notasAluno.get(i);
                if (nota != null) {
                    valores.add(nota);
                }
            }
            // Calcula a média com base no tipo de média definido
            double media = tipoMedia.calculaMedia(valores);
            medias.put(idAluno, media);
        }
        return medias;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Turma turma = (Turma) o;
        return isAtiva == turma.isAtiva && quantUnidades == turma.quantUnidades && Objects.equals(colecaoAluno, turma.colecaoAluno) && Objects.equals(disciplina, turma.disciplina) && Objects.equals(professor, turma.professor) && Objects.equals(idTurma, turma.idTurma) && Objects.equals(notas, turma.notas);
    }

    @Override
    public int hashCode() {
        return Objects.hash(colecaoAluno, disciplina, professor, idTurma, isAtiva, quantUnidades, notas);
    }

    @Override
    public String toString() {
        return "Turma\n" +
                "ID da Turma: " + idTurma + "\n" +
                "Quantidade de Unidades: " + quantUnidades + "\n" +
                "Professor: " + professor.getNome() + " (" + professor.getEmail() + ")\n" +
                "Disciplina: " + disciplina.getNome() + " (Código: " + disciplina.getCodigo() + ")\n" +
                "Carga Horária: " + disciplina.getCargaHoraria() + " horas\n";
    }
}
