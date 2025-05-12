package Tests;

import Colecoes.ColecaoAluno;
import Entidades.Aluno;
import Entidades.Disciplina;
import Excecoes.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class
ColecaoAlunoTest {

    private ColecaoAluno colecao;
    private Aluno aluno = new Aluno("Italo", "italo@gmail.com", "SI");
    private List<Aluno> colecaoteste;
    private Aluno alunoadicionado;

    ColecaoAlunoTest() throws EntidadeNullException, DadoInvalidoException {
    }

    @BeforeEach
    void setUp() {
        colecao = new ColecaoAluno();
        colecaoteste = new ArrayList<>();
    }


    @Test
    void adicionar() throws EntidadeCadastradaException, EntidadeNullException, EntidadeDesativadaException, DadoInvalidoException, EntidadeNaoCadastradaException {
        colecao.adicionar(aluno);
        alunoadicionado = colecao.buscar(aluno.getId());
        assertEquals(aluno, alunoadicionado);

    }


    @Test
    void remover() throws EntidadeCadastradaException, EntidadeDesativadaException, DadoInvalidoException, EntidadeNullException, EntidadeNaoCadastradaException {
        colecao.adicionar(aluno);
        colecao.remover(aluno.getId());
        assertEquals(false,aluno.isAtivo());

    }

    @Test
    void buscar() throws EntidadeDesativadaException, DadoInvalidoException, EntidadeCadastradaException, EntidadeNullException, EntidadeNaoCadastradaException {
        colecao.adicionar(aluno);
        alunoadicionado = colecao.buscar(aluno.getId());
        assertEquals(aluno, alunoadicionado);
    }

    @Test
    void getTodos() throws EntidadeCadastradaException, EntidadeNullException, EntidadeNaoCadastradaException {
        colecao.adicionar(aluno);
        colecaoteste.add(aluno);
        assertEquals(colecao.getTodos(),colecaoteste);

    }

    @Nested
    class DisciplinaTest {

        DisciplinaTest() throws DadoInvalidoException {
        }

        private Disciplina disciplina;
        private List<Disciplina> listaDeDisciplinas;

        @BeforeEach
        void setUp() throws DadoInvalidoException {
            disciplina = new Disciplina("Cálculo", 1, 60);
            listaDeDisciplinas = new ArrayList<>();
        }

        @Test
        void desativar() {
            disciplina.desativar();
            assertEquals(disciplina.isAtiva(), false);

        }

        @Test
        void isAtiva() {
            assertEquals(disciplina.isAtiva(), true);
        }

        @Test
        void getNome() {
            assertEquals(disciplina.getNome(),"Cálculo");
        }

        @Test
        void setNome() throws DadoInvalidoException {
            disciplina.setNome("Java");
            assertEquals(disciplina.getNome(),"Java");
        }

        @Test
        void getCodigo() {
            assertEquals(disciplina.getCodigo(),1);
        }

        @Test
        void setCodigo() throws Exception {
            disciplina.setCodigo(24);
            assertEquals(disciplina.getCodigo(),24);
        }

        @Test
        void getCargaHoraria() {
            assertEquals(disciplina.getCargaHoraria(),60);
        }

        @Test
        void setCargaHoraria() throws DadoInvalidoException {
            disciplina.setCargaHoraria(240);
            assertEquals(disciplina.getCargaHoraria(),240);
        }
    }
}