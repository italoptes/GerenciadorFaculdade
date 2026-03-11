package Arquivos;

import java.io.BufferedWriter;
import java.io.BufferedReader;
import java.io.FileWriter;
import java.io.FileReader;
import java.io.IOException;

public class PersistenciaTXT<T> {

    public void salvarEmArquivo(T objeto, String nomeArquivo) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(nomeArquivo))) {
            writer.write(objeto.toString());
            System.out.println("Objeto salvo com sucesso em " + nomeArquivo);
        } catch (IOException e) {
            System.err.println("Erro ao salvar: " + e.getMessage());
        }
    }

    public String carregarDeArquivo(String nomeArquivo) {
        StringBuilder conteudo = new StringBuilder();

        try (BufferedReader reader = new BufferedReader(new FileReader(nomeArquivo))) {
            String linha;

            while ((linha = reader.readLine()) != null) {
                conteudo.append(linha).append("\n");
            }

        } catch (IOException e) {
            System.err.println("Erro ao carregar: " + e.getMessage());
        }

        return conteudo.toString();
    }
}