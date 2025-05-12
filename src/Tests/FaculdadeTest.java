package Tests;

import Entidades.Aluno;
import Entidades.Disciplina;
import Entidades.Professor;
import Entidades.Turma;
import Excecoes.*;
import View.Faculdade;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
class FaculdadeTest {

    Faculdade faculdade;
    Aluno aluno;
    Disciplina disciplina;
    Professor professor;
    Turma turma;

    @BeforeEach
    void setUp() throws EntidadeNullException, DadoInvalidoException, EntidadeCadastradaException {
        faculdade = new Faculdade();
        aluno = faculdade.cadastraAlunoFaculdade("Ana Beatriz Correia Batista", "ana.correia@dcx.ufpb.br", "Sistemas de Informação");
        disciplina = faculdade.criarDisciplinaFaculdade("Linguagem de Programação", 24,60);
        professor = faculdade.cadastrarProfessorFaculdade("Tiburtino","tiburtino@dcx.ufpb.br");
        turma = faculdade.criarTurmaFaculdade(disciplina, professor);
    }

    @Test
    void cadastraAlunoFaculdade() throws DadoInvalidoException, EntidadeDesativadaException, EntidadeNaoCadastradaException {
        int idAluno = aluno.getId();
        Aluno buscaAluno = faculdade.buscarAlunoFaculdade(idAluno);
        assertEquals(aluno, buscaAluno);
    }

    @Test
    void desativarAlunoFaculdade() throws EntidadeDesativadaException, DadoInvalidoException, EntidadeNaoEncontradaException, EntidadeNaoCadastradaException {
        int idAluno = aluno.getId();
        faculdade.desativarAlunoFaculdade(idAluno);
        assertThrows(EntidadeDesativadaException.class, () -> {
            faculdade.buscarAlunoFaculdade(idAluno);
        });
    }

    @Test
    void buscarAlunoFaculdade() throws EntidadeDesativadaException, DadoInvalidoException, EntidadeNaoCadastradaException {
        int idAluno = aluno.getId();
        Aluno alunoBuscado = faculdade.buscarAlunoFaculdade(idAluno);
        assertEquals(aluno, alunoBuscado);
    }

    @Test
    void listarAlunosFaculdade()  {

    }

    @Test
    void criarDisciplinaFaculdade()  {
        int codigoDisciplina = 24;
        int idDisciplina = disciplina.getCodigo();
        assertEquals(codigoDisciplina, disciplina.getCodigo());
    }

    @Test
    void desativarDisciplinaFaculdade() throws EntidadeDesativadaException, DadoInvalidoException, EntidadeNaoCadastradaException {
        faculdade.desativarDisciplinaFaculdade(disciplina);
        assertThrows(EntidadeDesativadaException.class,() -> {
            faculdade.desativarDisciplinaFaculdade(disciplina);
        });
    }

    @Test
    void listarDisciplinasFaculdade() throws EntidadeNaoEncontradaException {
    }

    @Test
    void cadastrarProfessorFaculdade() throws EntidadeNullException, DadoInvalidoException {


    }

    @Test
    void associarProfessorDisciplina() throws EntidadeNullException {
    }

    @Test
    void desativarProfessorFaculdade() throws EntidadeCadastradaException, EntidadeDesativadaException, DadoInvalidoException {
    }

    @Test
    void listarProfessoresFaculdade() throws EntidadeCadastradaException, EntidadeDesativadaException {
    }

    @Test
    void adicionarAlunoTurma() throws EntidadeCadastradaException, EntidadeNullException, DadoInvalidoException {
    }

    @Test
    void removerAlunoTurma() throws EntidadeNaoEncontradaException, EntidadeDesativadaException, DadoInvalidoException{
    }

    @Test
    void listarAlunosTurma() throws EntidadeNaoEncontradaException {
    }

    @Test
    void defineQuantUnidadadeTurma() throws DadoInvalidoException {
    }

    @Test
    void adicionaNotaTurma() throws EntidadeNaoEncontradaException, EntidadeDesativadaException, DadoInvalidoException {
    }

    @Test
    void listarNotasAlunoTurma() throws EntidadeNaoEncontradaException, EntidadeDesativadaException, DadoInvalidoException {
    }

    @Test
    void listarNotasTurma() throws EntidadeNaoEncontradaException, EntidadeDesativadaException, DadoInvalidoException {
    }

    @Test
    void calcularMediasTurma() throws EntidadeNullException {
    }

    @Test
    void defineTipoMediaTurma() throws DadoInvalidoException {
    }

    @Test
    void criarTurmaFaculdade() throws EntidadeCadastradaException, DadoInvalidoException {
    }

    @Test
    void desativaTurmaFaculdade() throws EntidadeCadastradaException, EntidadeDesativadaException {
    }

    @Test
    void buscarTurmaFaculdade() throws EntidadeCadastradaException, EntidadeDesativadaException, DadoInvalidoException {

    }

    @Test
    void listarTurmasFaculdade()throws EntidadeCadastradaException, EntidadeDesativadaException {
    }

    @Test
    void salvarFaculdade() {
    }

    @Test
    void carregarFaculdade() {
    }
}