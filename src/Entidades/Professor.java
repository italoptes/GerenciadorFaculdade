package Entidades;

import Excecoes.DadoInvalidoException;
import Excecoes.EntidadeNullException;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Professor extends Pessoa implements Serializable {
    private List<Disciplina> disciplinasMinistradas = new ArrayList<>();

    public Professor(String nome, String email) throws DadoInvalidoException {
        super(nome, email);
    }

    public void associarDisciplina(Disciplina disciplina) throws EntidadeNullException {
        if (disciplina == null){
            throw new EntidadeNullException("Disciplina");
        }
        disciplinasMinistradas.add(disciplina);
    }

    public List<Disciplina> getDisciplinasMinistradas() {
        return disciplinasMinistradas;
    }

    public void setDisciplinasMinistradas(List<Disciplina> disciplinasMinistradas) {
        this.disciplinasMinistradas = disciplinasMinistradas;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Professor professor = (Professor) o;
        return Objects.equals(disciplinasMinistradas, professor.disciplinasMinistradas);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), disciplinasMinistradas);
    }

    @Override
    public String toString() {
        return super.toString().replace("Entidades.Pessoa", "Entidades.Professor") +
                ", disciplinasMinistradas=" + disciplinasMinistradas +
                '}';
    }


}
