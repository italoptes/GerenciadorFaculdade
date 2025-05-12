package Entidades;

import Excecoes.DadoInvalidoException;

import java.io.Serializable;
import java.util.Objects;

public class Disciplina implements Serializable {

    private String nome;
    private int codigo;
    private int cargaHoraria;
    private boolean isAtiva = true;

    public Disciplina(String nome, int codigo, int cargaHoraria) throws DadoInvalidoException {
        if (nome == null || nome.trim().isEmpty()) {
            throw new DadoInvalidoException("Nome");
        }
        if (codigo <= 0 ) {
            throw new DadoInvalidoException("Código");
        }
        if (cargaHoraria <= 0 ) {
            throw new DadoInvalidoException("Carga Horária");
        }
        this.nome = nome;
        this.codigo = codigo;
        this.cargaHoraria = cargaHoraria;
    }

    public void desativar(){
        isAtiva = false;
    }

    public boolean isAtiva() {
        return isAtiva;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) throws DadoInvalidoException {
        if (nome == null || nome.trim().isEmpty()) {
            throw new DadoInvalidoException("Nome");
        }
        this.nome = nome;
    }

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) throws Exception {
        if (codigo <= 0 ) {
            throw new DadoInvalidoException("Código");
        }
        this.codigo = codigo;
    }

    public int getCargaHoraria() {
        return cargaHoraria;
    }

    public void setCargaHoraria(int cargaHoraria) throws DadoInvalidoException {
        if (cargaHoraria <= 0 ) {
            throw new DadoInvalidoException("Carga Horária");
        }
        this.cargaHoraria = cargaHoraria;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Disciplina that = (Disciplina) o;
        return codigo == that.codigo && cargaHoraria == that.cargaHoraria && Objects.equals(nome, that.nome);
    }

    @Override
    public int hashCode() {
        return Objects.hash(nome, codigo, cargaHoraria);
    }

    @Override
    public String toString() {
        return "\n" +
                "  nome = '" + nome + "',\n" +
                "  codigo = " + codigo + ",\n" +
                "  cargaHoraria = " + cargaHoraria + "\n" ;
    }
}
