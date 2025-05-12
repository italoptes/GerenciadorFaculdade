package View;

import Arquivos.Persistencia;
import Colecoes.ColecaoAluno;
import Colecoes.ColecaoDisciplina;
import Colecoes.ColecaoProfessores;
import Colecoes.ColecaoTurma;
import Entidades.Aluno;
import Entidades.Disciplina;
import Entidades.Professor;
import Entidades.Turma;
import Excecoes.*;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Faculdade implements Serializable {
    private ColecaoAluno colecaoAluno;
    private ColecaoDisciplina colecaoDisciplina;
    private ColecaoProfessores colecaoProfessores;
    private ColecaoTurma colecaoTurma;
    private static final long serialVersionUID = 1L; //para ser compatível com futuras versões
    private static Persistencia<Faculdade> persistencia = new Persistencia<>();

    public Faculdade() {
        colecaoAluno = new ColecaoAluno();
        colecaoDisciplina = new ColecaoDisciplina();
        colecaoProfessores = new ColecaoProfessores();
        colecaoTurma = new ColecaoTurma();
    }

    public Aluno cadastraAlunoFaculdade(String nome, String email, String curso) throws EntidadeCadastradaException, EntidadeNullException, DadoInvalidoException {
        Aluno aluno = new Aluno(nome, email, curso);
        colecaoAluno.adicionar(aluno);
        return aluno;
    }

    public void desativarAlunoFaculdade(int matriculaAluno) throws EntidadeNaoEncontradaException, EntidadeDesativadaException, DadoInvalidoException, EntidadeNaoCadastradaException {
        colecaoAluno.remover(matriculaAluno);
    }

    public Aluno buscarAlunoFaculdade(int matricula) throws EntidadeDesativadaException, DadoInvalidoException, EntidadeNaoCadastradaException {
        return colecaoAluno.buscar(matricula);
    }

    public Aluno encontrarAluno(String nomeAluno) throws EntidadeNaoCadastradaException {
        return colecaoAluno.encontrarAluno(nomeAluno);
    }

    public List<Aluno> listarAlunosFaculdade() throws EntidadeNaoEncontradaException, EntidadeNaoCadastradaException {
        return colecaoAluno.getTodos();
    }

    public Disciplina criarDisciplinaFaculdade(String nome, int codigo, int cargaHoraria) throws EntidadeNullException, DadoInvalidoException {
        Disciplina disciplina = new Disciplina(nome, codigo, cargaHoraria);
        colecaoDisciplina.adicionarDisciplina(disciplina);
        return disciplina;
    }

    public void desativarDisciplinaFaculdade(Disciplina disciplina) throws EntidadeDesativadaException, DadoInvalidoException, EntidadeNaoCadastradaException {
        colecaoDisciplina.removerDisciplina(disciplina.getCodigo());
    }

    public String listarDisciplinasFaculdade() throws EntidadeNaoCadastradaException {
        return colecaoDisciplina.listarDisciplinas();
    }

    public Professor cadastrarProfessorFaculdade(String nome, String email) throws EntidadeNullException, DadoInvalidoException {
        Professor professor = new Professor(nome, email);
        colecaoProfessores.adicionarProfessor(professor);
        return professor;
    }

    public void associarProfessorDisciplina(Professor professor, Disciplina disciplina) throws EntidadeNullException {
        professor.associarDisciplina(disciplina);
    }

    public Professor encontrarProfessor(String nomeProfessor) throws EntidadeNaoCadastradaException {
        return colecaoProfessores.encontrarProfessor(nomeProfessor);
    }

    public Disciplina encontrarDisciplina(String nomeDisciplina) throws EntidadeNaoCadastradaException {
        return colecaoDisciplina.encontrarDisciplina(nomeDisciplina);
    }

    public void desativarProfessorFaculdade(Professor professor) throws EntidadeDesativadaException, DadoInvalidoException, EntidadeNaoEncontradaException, EntidadeNaoCadastradaException {
        colecaoProfessores.removerProfessor(professor.getId());
    }

    public String listarProfessoresFaculdade() throws EntidadeCadastradaException, EntidadeDesativadaException, EntidadeNaoCadastradaException {
        return colecaoProfessores.listarProfessores();
    }

    public void adicionarAlunoTurma(Turma turma, Aluno aluno) throws EntidadeCadastradaException, EntidadeNullException, DadoInvalidoException {
        turma.adicionaAluno(aluno);
    }

    public void removerAlunoTurma(Turma turma, Aluno aluno) throws EntidadeNaoEncontradaException, EntidadeDesativadaException, DadoInvalidoException, EntidadeNaoCadastradaException {
        turma.removeAlunoId(aluno.getId());
    }

    public ArrayList<Aluno> listarAlunosTurma(Turma turma) throws EntidadeNaoEncontradaException, EntidadeNaoCadastradaException {
        return turma.listarAlunos();
    }

    public void defineQuantUnidadadeTurma(Turma turma, int quantUnidade) throws DadoInvalidoException {
        turma.defineQuantUnidades(quantUnidade);
    }

    public void adicionaNotaTurma(Turma turma, Aluno aluno, int unidade, double valor) throws EntidadeNaoEncontradaException, EntidadeDesativadaException, DadoInvalidoException, EntidadeNaoCadastradaException {
        turma.adicionarNota(aluno.getId(), unidade, valor);
    }

    public List<Double> listarNotasAlunoTurma(Turma turma, Aluno aluno) throws EntidadeNaoEncontradaException, EntidadeDesativadaException, DadoInvalidoException, EntidadeNaoCadastradaException {
        return turma.listarNotasAluno(aluno.getId());
    }

    public List<List<Double>> listarNotasTurma(Turma turma) throws EntidadeNaoEncontradaException, EntidadeNaoCadastradaException {
        return turma.listarNotasTurma(turma);
    }

    public Map<Integer, Double> calcularMediasTurma(Turma turma) throws EntidadeNullException {
        return turma.calcularMedias();
    }

    public void defineTipoMediaTurma(int tipoMedia, Turma turma) throws DadoInvalidoException {
        turma.defineTipoMedia(tipoMedia);
    }

    public Turma criarTurmaFaculdade(Disciplina disciplina, Professor professor) throws EntidadeCadastradaException, DadoInvalidoException {
        Turma turma = new Turma(disciplina, professor);
        colecaoTurma.adicionarTurmas(turma);
        return turma;
    }

    public void desativaTurmaFaculdade(Turma turma) throws EntidadeCadastradaException, EntidadeDesativadaException, EntidadeNaoCadastradaException {
        colecaoTurma.desativaTurmaId(turma.getIdTurma());
    }

    public String buscarTurmaFaculdade(String idTurma) throws EntidadeDesativadaException, DadoInvalidoException, EntidadeNaoCadastradaException {
        return colecaoTurma.buscarTurmaId(idTurma);
    }

    public Turma encontrarTurma(String codTurma) throws EntidadeNaoCadastradaException {
        return colecaoTurma.encontrarTurma(codTurma);
    }

    public List<Turma> listarTurmasFaculdade() throws EntidadeNaoEncontradaException, EntidadeNaoCadastradaException {
        return colecaoTurma.listarTurmas();
    }

    public List<Turma> listarTurmasAssociadasAProfessor(Professor professor) throws EntidadeNaoCadastradaException {
        return colecaoTurma.listarTurmasAssociadasAProfessor(professor);
    }

    public void salvarFaculdade(String nomeArquivo) throws EntidadeNaoEncontradaException, EntidadeNaoCadastradaException {
        if (colecaoAluno.getTodos().isEmpty() && colecaoProfessores.listarProfessores().isEmpty() && colecaoTurma.getTurmas().isEmpty()) {
            throw new EntidadeNaoEncontradaException("Todas as coleções estão vazias, nada para salvar.");
        }
        persistencia.salvarEmArquivo(this, nomeArquivo);
    }

    public static Faculdade carregarFaculdade(String nomeArquivo) {
        return persistencia.carregarDeArquivo(nomeArquivo);
    }


}
