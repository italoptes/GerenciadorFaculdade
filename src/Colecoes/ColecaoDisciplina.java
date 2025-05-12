package Colecoes;

import Entidades.Disciplina;
import Excecoes.*;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Objects;

public class ColecaoDisciplina implements Serializable {

    private ArrayList<Disciplina> disciplinas;

    public ColecaoDisciplina(){
        disciplinas = new ArrayList<Disciplina>();
    }

    public void adicionarDisciplina(Disciplina disciplina) throws EntidadeNullException {
        if (disciplina == null){
            throw new EntidadeNullException("Disciplina");
        }
        disciplinas.add(disciplina);
    }

    public void removerDisciplina(int codigo) throws EntidadeNaoCadastradaException, EntidadeDesativadaException, DadoInvalidoException {
        if (disciplinas.isEmpty()) {
            throw new EntidadeNaoCadastradaException("Disciplina");
        }
        if (codigo <= 0) {
            throw new DadoInvalidoException("Código da disciplina");
        }
        for (Disciplina disciplinaRemova : disciplinas){
            if (disciplinaRemova.getCodigo() == codigo) {
                if (!disciplinaRemova.isAtiva()) {
                    throw new EntidadeDesativadaException("Disciplina");
                }
                disciplinaRemova.desativar();
                return;
            }
        }
        throw new DadoInvalidoException("Código da disciplina");
    }

    public Disciplina buscarDisciplina(int codigo) throws EntidadeNaoCadastradaException, EntidadeNaoEncontradaException, EntidadeDesativadaException, DadoInvalidoException {
        if (disciplinas.isEmpty()) {
            throw new EntidadeNaoCadastradaException("Disciplina");
        }
        if (codigo <= 0) {
            throw new DadoInvalidoException("Código da disciplina");
        }
        for (Disciplina disciplina : disciplinas){
            if (disciplina.getCodigo() == codigo) {
                if (!disciplina.isAtiva()) {
                    throw new EntidadeDesativadaException("Disciplina");
                }
                return disciplina;
            }
        }
        throw new EntidadeNaoEncontradaException("Disciplina");
    }

    public Disciplina encontrarDisciplina(String busca) throws EntidadeNaoCadastradaException {
        if (disciplinas.isEmpty()) {
            throw new EntidadeNaoCadastradaException("Disciplina");
        }
        for (Disciplina disciplina : disciplinas) {
            if (disciplina.getNome().equalsIgnoreCase(busca) ||
                    Integer.toString(disciplina.getCodigo()).equals(busca)) {
                return disciplina;
            }
        }
        return null;
    }

    public String listarDisciplinas() throws EntidadeNaoCadastradaException {
        if (disciplinas.isEmpty()) {
            throw new EntidadeNaoCadastradaException("Disciplina");
        }
        String discip = "";
        for (Disciplina d : disciplinas){
            if (d.isAtiva()){
                discip += d.toString() + "\n";
            }
        }
        return discip;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        ColecaoDisciplina that = (ColecaoDisciplina) o;
        return Objects.equals(disciplinas, that.disciplinas);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(disciplinas);
    }

    @Override
    public String toString() {
        return "Colecoes.ColecaoDisciplina{" +
                "disciplinas=" + disciplinas +
                '}';
    }
}
