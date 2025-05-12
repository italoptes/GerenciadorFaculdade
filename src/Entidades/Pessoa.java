package Entidades;

import Excecoes.DadoInvalidoException;

import java.io.Serializable;
import java.time.Year;
import java.util.Objects;

public abstract class Pessoa<Tipox> implements Serializable {

    private String nome;
    private String email;
    private boolean isAtivo = true;
    private int id;
    private static int contador = 0;

    private int geradorId(){
        int ano = Year.now().getValue();
        contador++;
        String id = ano + String.format("%04d", contador); // vai deixar o id no formato 20250001 e depois converte p int
        return Integer.parseInt(id);
    }

    public Pessoa(String nome, String email) throws DadoInvalidoException {
        if ( nome == null || nome.trim().isEmpty() ) {
            throw new DadoInvalidoException("Nome");
        }
        if ( email == null || email.trim().isEmpty() ) {
            throw new DadoInvalidoException("E-mail");
        }

        this.nome = nome;
        this.email = email;
        this.id = geradorId();
    }

    public void desativar(){
        isAtivo = false;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) throws DadoInvalidoException {
        if ( nome == null || nome.trim().isEmpty() ) {
            throw new DadoInvalidoException("Nome");
        }
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) throws DadoInvalidoException {
        if ( email == null || email.trim().isEmpty() ) {
            throw new DadoInvalidoException("E-mail");
        }
        this.email = email;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public boolean isAtivo() {
        return isAtivo;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Pessoa<?> pessoa = (Pessoa<?>) o;
        return isAtivo == pessoa.isAtivo && id == pessoa.id && Objects.equals(nome, pessoa.nome) && Objects.equals(email, pessoa.email);
    }

    @Override
    public int hashCode() {
        return Objects.hash(nome, email, isAtivo, id);
    }

    @Override
    public String toString() {
        return "Pessoa {" +
                "nome = '" + nome + '\'' +
                ", email = '" + email + '\'' +
                ", isAtivo = " + isAtivo +
                ", id = " + id +
                '}';
    }
}
