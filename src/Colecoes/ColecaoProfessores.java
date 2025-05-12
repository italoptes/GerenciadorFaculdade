package Colecoes;

import Entidades.Professor;
import Excecoes.*;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Objects;

public class ColecaoProfessores implements Serializable {

    private ArrayList<Professor> professores;

    public ColecaoProfessores() {
        professores = new ArrayList<>();
    }

    public void adicionarProfessor(Professor professor) throws EntidadeNullException {
        if (professor == null) {
            throw new EntidadeNullException("Professor");
        }
        professores.add(professor);
    }

    public void removerProfessor(int idProfessor) throws EntidadeNaoCadastradaException, EntidadeNaoEncontradaException, EntidadeDesativadaException, DadoInvalidoException {
        if (professores.isEmpty()){
            throw new EntidadeNaoCadastradaException("Professor");
        }
        for (Professor prof : professores){
            if (prof.getId() == idProfessor){
                if (!prof.isAtivo()) {
                    throw new EntidadeDesativadaException("Professor JÁ DESATIVADO");
                }
                prof.desativar();
                return;
            }
        }
        throw new EntidadeNaoEncontradaException("Professor");
    }

    public Professor buscaPorIdProfessor(int idProfessor) throws EntidadeNaoEncontradaException, EntidadeDesativadaException, DadoInvalidoException {
        if (professores.isEmpty()){
            throw new EntidadeNaoEncontradaException("Professor");
        }
        for (Professor prof : professores){
            if (prof.getId() == idProfessor){
                if (!prof.isAtivo()){
                    throw new EntidadeDesativadaException("Professor");
                }
                return prof;
            }
        }
        throw new EntidadeNaoEncontradaException("Professor");
    }

    public Professor encontrarProfessor(String busca) throws EntidadeNaoCadastradaException {
        if (professores.isEmpty()) {
            throw new EntidadeNaoCadastradaException("Professor");
        }
        for (Professor professor : professores) {
            if (professor.getNome().equalsIgnoreCase(busca) ||
                    Integer.toString(professor.getId()).equals(busca)) {
                return professor;
            }
        }
        return null;
    }

    public String listarProfessores() throws EntidadeNaoCadastradaException {
        if (professores.isEmpty()){
            throw new EntidadeNaoCadastradaException("NENHUM professor cadastrado");
        }
        String profs = "";
        for (Professor prof : professores){
            if (prof.isAtivo()){
                profs += prof.getNome() + " ";
            }
        }
        return profs;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        ColecaoProfessores that = (ColecaoProfessores) o;
        return Objects.equals(professores, that.professores);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(professores);
    }

    @Override
    public String toString() {
        return "Colecoes.ColecaoProfessores{" +
                "professores=" + professores +
                '}';
    }
}