package Entidades;

import Excecoes.DadoInvalidoException;
import Excecoes.EntidadeNullException;

import java.io.Serializable;
import java.util.Objects;

public class Aluno extends Pessoa implements Serializable {
    private static int contador = 0;
    private String curso;

    public Aluno(String nome,String email, String curso) throws EntidadeNullException, DadoInvalidoException {
        super(nome,email);

        if ( curso == null || curso.trim().isEmpty() ) {
            throw new EntidadeNullException("Curso");
        }
        this.curso = curso;
    }

    public String getCurso() {
        return curso;
    }

    public void setCurso(String curso) throws EntidadeNullException {
        if ( curso == null || curso.trim().isEmpty() ) {
            throw new EntidadeNullException("Curso");
        }
        this.curso = curso;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Aluno aluno = (Aluno) o;
        return Objects.equals(curso, aluno.curso);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), curso);
    }

    @Override
    public String toString() {
        return "Nome: " + getNome() + ". Curso: " + getCurso() + ". Matrícula: " + getId();
    }
}
