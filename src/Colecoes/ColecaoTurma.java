package Colecoes;

import Entidades.Professor;
import Entidades.Turma;
import Excecoes.DadoInvalidoException;
import Excecoes.EntidadeCadastradaException;
import Excecoes.EntidadeDesativadaException;
import Excecoes.EntidadeNaoCadastradaException;

import java.io.Serializable;

public class ColecaoTurma implements Serializable {
    private ArrayList<Turma> turmas;

    public ColecaoTurma() {
        this.turmas = new ArrayList<>();
    }

    public void adicionarTurmas(Turma turma) throws EntidadeCadastradaException {
        if (turma == null) {
            throw new EntidadeCadastradaException("Turma");
        }
        turmas.add(turma);
    }

    public void desativaTurmaId(String idTurma) throws EntidadeNaoCadastradaException, EntidadeDesativadaException {
        if (turmas.isEmpty()) {
            throw new EntidadeNaoCadastradaException("Turma");
        }
        for (Turma turma : turmas) {
            if (idTurma.equals(turma.getIdTurma())) {
                if (!turma.isAtiva()) {
                    throw new EntidadeDesativadaException("Turma");
                }
                turma.encerraTurma();
                return;
            }
        }
    }

    public String buscarTurmaId(String idTurma) throws EntidadeNaoCadastradaException, EntidadeDesativadaException, DadoInvalidoException {
        if (turmas.isEmpty()) {
            throw new EntidadeNaoCadastradaException("Turma");
        }
        for (Turma turma : turmas) {
            if (idTurma.equals(turma.getIdTurma())) {
                if (!turma.isAtiva()) {
                    throw new EntidadeDesativadaException("Turma");
                }
                return turma.toString();
            }
        }
        throw new DadoInvalidoException("ID");
    }

    public Turma encontrarTurma(String busca) throws EntidadeNaoCadastradaException {
        if (turmas.isEmpty()) {
            throw new EntidadeNaoCadastradaException("Turma");
        }
        for (Turma turma : turmas) {
            if (turma.getIdTurma().equalsIgnoreCase(busca) ||
                    turma.getIdTurma().equals(busca)) {
                return turma;
            }
        }
        return null;
    }

    public ArrayList<Turma> listarTurmas() throws EntidadeNaoCadastradaException {
        if (turmas.isEmpty()) {
            throw new EntidadeNaoCadastradaException("Turma");
        }
        ArrayList<Turma> turmasAtivas = new ArrayList<>();
        for (Turma turma : turmas) {
            if (turma.isAtiva()) {
                turmasAtivas.add(turma);
            }
        }
        return turmasAtivas;
    }

    public ArrayList<Turma> listarTurmasAssociadasAProfessor(Professor professor) throws EntidadeNaoCadastradaException {
        if (turmas.isEmpty()) {
            throw new EntidadeNaoCadastradaException("Turma");
        }
        ArrayList<Turma> turmasAssociadas = new ArrayList<>();
        for (Turma turma : turmas) {
            if (professor == turma.getProfessor()){
                turmasAssociadas.add(turma);
            }
        }
        return turmasAssociadas;
    }

    public List<Turma> getTurmas() {
        return turmas;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        ColecaoTurma turma = (ColecaoTurma) o;
        return Objects.equals(turmas, turma.turmas);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(turmas);
    }

    @Override
    public String toString() {
        return "Colecoes.ColecaoTurma{" +
                "turmas=" + turmas +
                '}';
    }
}
