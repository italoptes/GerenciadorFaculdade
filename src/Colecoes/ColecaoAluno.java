package Colecoes;

import Entidades.Aluno;
import Excecoes.*;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Objects;

public class ColecaoAluno implements Serializable {

    private ArrayList<Aluno> alunos;

    public ColecaoAluno() {
        alunos = new ArrayList<>();
    }

    public void adicionar(Aluno aluno) throws EntidadeCadastradaException, EntidadeNullException {
        if (aluno == null) {
            throw new EntidadeNullException("Aluno");
        }
        for (Aluno a : alunos) {
            if (a.getId() == aluno.getId()) {
                throw new EntidadeCadastradaException("Aluno");
            }
        }
        alunos.add(aluno);
    }

    public void remover(int matriculaAluno) throws EntidadeNaoCadastradaException, EntidadeDesativadaException, DadoInvalidoException {
        if (alunos.isEmpty()) {
            throw new EntidadeNaoCadastradaException("Aluno");
        }
        if (matriculaAluno <= 0) {
            throw new DadoInvalidoException("Matrícula do Aluno");
        }
        for (Aluno aluno : alunos) {
            if (aluno.getId() == matriculaAluno) {
                if (!aluno.isAtivo()) {
                    throw new EntidadeDesativadaException("Aluno");
                }
                aluno.desativar();
                return;
            }
        }
    }

    public Aluno encontrarAluno(String busca) throws EntidadeNaoCadastradaException {
        if (alunos.isEmpty()) {
            throw new EntidadeNaoCadastradaException("Aluno");
        }
        for (Aluno aluno : alunos) {
            if (aluno.getNome().equalsIgnoreCase(busca) ||
                    Integer.toString(aluno.getId()).equals(busca)) {
                return aluno;
            }
        }
        return null;
    }

    public Aluno buscar(int matricula) throws EntidadeNaoCadastradaException, DadoInvalidoException, EntidadeDesativadaException {
        if (alunos.isEmpty()) {
            throw new EntidadeNaoCadastradaException("Aluno");
        }
        if (matricula <= 0) {
            throw new DadoInvalidoException("Matrícula");
        }
        for (Aluno aluno : alunos) {
            if (aluno.getId() == matricula) {
                if (!aluno.isAtivo()) {
                    throw new EntidadeDesativadaException("Aluno JÁ DESATIVADO");
                }
                return aluno;
            }
        }
        throw new EntidadeNaoCadastradaException("Aluno");
    }

    public ArrayList<Aluno> getTodos() throws EntidadeNaoCadastradaException {
        if (alunos.isEmpty()) {
            throw new EntidadeNaoCadastradaException("Aluno");
        }
        ArrayList<Aluno> listaAtivos = new ArrayList<>();
        for (Aluno aluno : alunos) {
            if (aluno.isAtivo()) {
                listaAtivos.add(aluno);
            }
        }
        if (listaAtivos.isEmpty()) {
            throw new EntidadeNaoCadastradaException("NENHUM aluno cadastrado.");
        }
        return listaAtivos;
    }

    @Override
    public String toString() {
        return "ColecaoAlunos{" +
                "alunos=" + alunos +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        ColecaoAluno that = (ColecaoAluno) o;
        return Objects.equals(alunos, that.alunos);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(alunos);
    }
}