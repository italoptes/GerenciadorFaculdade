package View;

import Entidades.Aluno;
import Entidades.Disciplina;
import Entidades.Professor;
import Entidades.Turma;
import Excecoes.*;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Faculdade faculdade = Faculdade.carregarFaculdade("faculdade.txt");
        String menu = """
                \n----- MENU DA FACULDADE -----
                1. Cadastrar Aluno
                2. Cadastrar Professor
                3. Criar Disciplina
                4. Criar Turma
                5. Matricular Aluno em Turma
                6. Listar Alunos por Turma
                7. Configurar Média
                8. Cadastrar Notas
                9. Desativar aluno
                10. Calcular Média da Turma
                11. Encerrar Turma
                12. Salvar Faculdade
                13. Sair
                Escolha uma opção:\s""";

        if (faculdade == null) {
            faculdade = new Faculdade();
        }
        boolean sair = false;

        while (!sair) {
            System.out.println(menu);
            String input = scanner.nextLine().trim();
            int opcao;
                try {
                    if (input.isEmpty()) {
                        System.out.println("Entrada VAZIA\n" +
                                "tente novamente");
                        continue;
                    }
                    opcao = Integer.parseInt(input);
                } catch (NumberFormatException e) {
                    System.out.println("Entrada INVÁLIDA\n" +
                            "tente novamente");
                    continue;
                }

            switch (opcao) {
                case 1:
                    cadastrarAluno(scanner, faculdade);
                    break;

                case 2:
                    cadastrarProfessor(scanner, faculdade);
                    break;

                case 3:
                    cadastrarDisciplina(scanner, faculdade);
                    break;

                case 4:
                    criarTurma(scanner, faculdade);
                    break;

                case 5:
                    matricularAlunoEmTurma(scanner, faculdade);
                    break;

                case 6:
                    listarAlunosPorTurma(scanner, faculdade);
                    break;

                case 7:
                    configurarMedia(scanner, faculdade);
                    break;

                case 8:
                    adicionarNota(scanner, faculdade);
                    break;

                case 9:
                    desativarIdAluno(scanner, faculdade);
                    break;

                case 10:
                    calcularMediaTurma(scanner, faculdade);
                    break;

                case 11:
                    encerrarTurma(scanner, faculdade);
                    break;

                case 12:
                    salvarFaculdade(faculdade);
                    break;

                case 13: // Sair
                    sair = isSair();
                    break;

                default:
                    System.out.println("Opção inválida. Tente novamente.");
                    break;
            }
        }
        scanner.close();
    }

    private static boolean isSair() {
        System.out.println("Saindo...");
        return true;
    }

    public static void salvarFaculdade(Faculdade faculdade) {
        try {
            faculdade.salvarFaculdade("faculdade.txt");
            System.out.println("Faculdade salva COM SUCESSO.");
        } catch (Exception e) {
            System.out.println("ERRO ao salvar faculdade: " + e.getMessage());
        }
    }

    public static void calcularMediaTurma(Scanner scanner, Faculdade faculdade) {
        try {
            faculdade.listarTurmasFaculdade();
            boolean continuar = true;
            Turma turmaCalcularMedia;
            while (continuar) {
                System.out.println("Turmas ativas --- ");
                System.out.println(faculdade.listarTurmasFaculdade());

                turmaCalcularMedia = buscaTurma(scanner, faculdade);
                if (turmaCalcularMedia == null) {
                    if (!tenteNovamente(scanner)) {
                        return;
                    }
                } else {
                    System.out.println("Média da turma: " + faculdade.calcularMediasTurma(turmaCalcularMedia));
                    continuar = false;
                }
            }
        } catch (EntidadeNaoEncontradaException | EntidadeNullException | EntidadeNaoCadastradaException e) {
            System.out.println("Erro ao calcular média da turma: " + e.getMessage());
        }
    }

    public static void encerrarTurma(Scanner scanner, Faculdade faculdade) {
        try {
            faculdade.listarTurmasFaculdade();
            boolean continuar = true;
            while (continuar) {
                System.out.println("Turmas ativas --- ");
                System.out.println(faculdade.listarTurmasFaculdade());
                System.out.println("Digite o nome da turma que você deseja encerrar ---");
                Turma turmaEncerrada = null;
                while (turmaEncerrada == null) {
                    turmaEncerrada = buscaTurma(scanner, faculdade);
                    if (turmaEncerrada == null && tenteNovamente(scanner)) {
                        return;
                    }
                }
                faculdade.desativaTurmaFaculdade(turmaEncerrada);
                System.out.println("Turma encerrada COM SUCESSO");
                continuar = false;
            }


        } catch (EntidadeNaoEncontradaException | EntidadeCadastradaException | EntidadeDesativadaException |
                 EntidadeNaoCadastradaException e) {
            System.out.println("ERRO ao encerrar turma: " + e.getMessage());
        }
    }

    public static void desativarIdAluno(Scanner scanner, Faculdade faculdade) {
        try {
            faculdade.listarAlunosFaculdade();
            boolean continuar = true;
            while (continuar) {
                System.out.println("Alunos ativos --- ");
                System.out.println(faculdade.listarAlunosFaculdade());
                int idAluno = 0;
                while (idAluno == 0) {
                    idAluno = validarIdAluno(scanner, faculdade);
                    if (idAluno == 0 && tenteNovamente(scanner)) {
                        return;
                    }
                }
                faculdade.desativarAlunoFaculdade(idAluno);
                System.out.println("Aluno desativado COM SUCESSO.");
                continuar = false;
            }
        } catch (EntidadeNaoEncontradaException | EntidadeDesativadaException | DadoInvalidoException |
                 EntidadeNaoCadastradaException e) {
            System.out.println("ERRO ao desativar aluno: " + e.getMessage());
        }
    }

    public static void adicionarNota(Scanner scanner, Faculdade faculdade) {
        try {
            faculdade.listarTurmasFaculdade();
            faculdade.listarAlunosFaculdade();
            faculdade.listarProfessoresFaculdade();
            boolean continuar = true;
            while (continuar) {
                Professor professorBuscado = null;
                while (professorBuscado == null) {
                    System.out.println("Professores ativos --- ");
                    System.out.println(faculdade.listarProfessoresFaculdade());
                    System.out.println("Informe um professor para encontrar as turmas associadas a ele ---");
                    professorBuscado = buscaProfessor(scanner, faculdade);
                    if (professorBuscado == null && tenteNovamente(scanner)) {
                        return;
                    }
                }
                faculdade.listarTurmasAssociadasAProfessor(professorBuscado);
                Turma turmaAssociada = null;
                while (turmaAssociada == null) {
                    System.out.println("Turmas associadas a(o) professor(a) " + professorBuscado.getNome() + " --- ");
                    System.out.println(faculdade.listarTurmasAssociadasAProfessor(professorBuscado));
                    turmaAssociada = buscaTurma(scanner, faculdade);
                    if (turmaAssociada == null && tenteNovamente(scanner)) {
                        return;
                    }
                }
                faculdade.listarAlunosTurma(turmaAssociada);
                Aluno alunoBuscado = null;
                while (alunoBuscado == null) {
                    System.out.println("Alunos ativos na Turma " + turmaAssociada.getIdTurma() + " --- ");
                    System.out.println(faculdade.listarAlunosTurma(turmaAssociada));
                    alunoBuscado = buscaAluno(scanner, faculdade);
                    if (alunoBuscado == null && tenteNovamente(scanner)) {
                        return;
                    }
                }
                double nota = 0;
                while (nota == 0) {
                    nota = validarNota(scanner);
                    if (nota == 0 && tenteNovamente(scanner)) {
                        System.out.println("Nota INVÁLIDA.");
                        return;
                    }
                }
                int unidade = 0;
                while (unidade == 0) {
                    System.out.println("Digite uma unidade --- ");
                    unidade = validarQuantUnidades(scanner);
                    if (unidade == 0 && tenteNovamente(scanner)) {
                        System.out.println("Unidade INVÁLIDA.");
                        return;
                    }
                }
                faculdade.adicionaNotaTurma(turmaAssociada, alunoBuscado, unidade, nota);
                System.out.println("Nota cadastrada COM SUCESSO.");
                continuar = false;
            }

        } catch (DadoInvalidoException | EntidadeNaoEncontradaException | EntidadeDesativadaException |
                 EntidadeNaoCadastradaException | EntidadeCadastradaException e) {
            System.out.println("ERRO ao cadastrar nota: " + e.getMessage());
        }
    }

    public static void configurarMedia(Scanner scanner, Faculdade faculdade) {
        try {
            faculdade.listarTurmasFaculdade();
            boolean continuar = true;
            while (continuar) {
                Turma turmaConfigurar = null;
                while (turmaConfigurar == null) {
                    System.out.println("Turmas ativas --- ");
                    System.out.println(faculdade.listarTurmasFaculdade());
                    turmaConfigurar = buscaTurma(scanner, faculdade);
                    if (turmaConfigurar == null && tenteNovamente(scanner)) {
                        return;
                    }
                }
                int mediaConfigurada = 0;
                while (mediaConfigurada == 0) {
                    mediaConfigurada = validarEscolhaMedia(scanner);
                    if (mediaConfigurada == 0 && tenteNovamente(scanner)) {
                        return;
                    }
                }
                int quantUnidades = 0;
                while (quantUnidades == 0) {
                    quantUnidades = validarQuantUnidades(scanner);
                    if (quantUnidades == 0 && tenteNovamente(scanner)) {
                        return;
                    }
                }
                faculdade.defineTipoMediaTurma(mediaConfigurada, turmaConfigurar);
                faculdade.defineQuantUnidadadeTurma(turmaConfigurar, quantUnidades);
                System.out.println("Configuração de média realizada COM SUCESSO.");
                continuar = false;
            }

        } catch (DadoInvalidoException | EntidadeNaoEncontradaException | EntidadeNaoCadastradaException e) {
            System.out.println("ERRO ao configurar média: " + e.getMessage());
        }
    }

    public static void listarAlunosPorTurma(Scanner scanner, Faculdade faculdade) {
        try {
            faculdade.listarTurmasFaculdade();
            faculdade.listarAlunosFaculdade();
            boolean continuar = true;
            while (continuar) {
                System.out.println("Turmas ativas --- ");
                System.out.println(faculdade.listarTurmasFaculdade());
                Turma turmaListar = null;
                while (turmaListar == null) {
                    turmaListar = buscaTurma(scanner, faculdade);
                    if (turmaListar == null && tenteNovamente(scanner)) {
                        return;
                    }

                }
                System.out.println(faculdade.listarAlunosTurma(turmaListar));
                System.out.println("Alunos da turma " + turmaListar.getIdTurma() + " listados COM SUCESSO.");
                continuar = false;
            }

        } catch (EntidadeNaoEncontradaException | EntidadeNaoCadastradaException e) {
            System.out.println("ERRO ao listar alunos por turma: " + e.getMessage());
        }
    }

    private static void matricularAlunoEmTurma(Scanner scanner, Faculdade faculdade) {
        try {
            faculdade.listarTurmasFaculdade();
            faculdade.listarAlunosFaculdade();
            boolean continuar = true;
            while (continuar) {
                System.out.println("Turmas ativas --- ");
                System.out.println(faculdade.listarTurmasFaculdade());
                Turma turmaBuscada = null;
                while (turmaBuscada == null) {
                    turmaBuscada = buscaTurma(scanner, faculdade);
                    if (turmaBuscada == null && tenteNovamente(scanner)) {
                        return;
                    }
                }
                Aluno alunoBuscado = null;
                while (alunoBuscado == null) {
                    System.out.println("Alunos ativos --- ");
                    System.out.println(faculdade.listarAlunosFaculdade());
                    System.out.println("Digite o nome do aluno ---");
                    alunoBuscado = buscaAluno(scanner, faculdade);
                    if (alunoBuscado == null && tenteNovamente(scanner)) {
                        return;
                    }
                }
                faculdade.adicionarAlunoTurma(turmaBuscada, alunoBuscado);
                System.out.println("Aluno : " + alunoBuscado.getNome() + " - " + alunoBuscado.getId() + " matriculado em turma : " + turmaBuscada.getIdTurma() + " COM SUCESSO");
                continuar = false;
            }
        } catch (DadoInvalidoException | EntidadeCadastradaException | EntidadeNaoCadastradaException |
                 EntidadeDesativadaException | EntidadeNullException | EntidadeNaoEncontradaException e) {
            System.out.println("ERRO ao matricular aluno em turma " + e.getMessage());
        }
    }

    private static void criarTurma(Scanner scanner, Faculdade faculdade) {
        try {
            faculdade.listarDisciplinasFaculdade();
            faculdade.listarProfessoresFaculdade();
            boolean continuar = true;
            while (continuar) {
                System.out.println("Disciplinas ativas --- ");
                System.out.println(faculdade.listarDisciplinasFaculdade());
                System.out.println("Digite a disciplina da turma --- ");
                Disciplina disciplinaBuscada = null;
                while (disciplinaBuscada == null) {
                    disciplinaBuscada = buscaDisciplina(scanner, faculdade);
                    if (disciplinaBuscada == null && tenteNovamente(scanner)) {
                        return;
                    }
                }
                System.out.println("Professores ativos --- ");
                System.out.println(faculdade.listarProfessoresFaculdade());
                System.out.println("Digite o nome do professor da turma --- ");
                Professor professorBuscado = null;
                while (professorBuscado == null) {
                    professorBuscado = buscaProfessor(scanner, faculdade);
                    if (professorBuscado == null && tenteNovamente(scanner)) {
                        return;
                    }
                }
                faculdade.associarProfessorDisciplina(professorBuscado, disciplinaBuscada);
                Turma turma = faculdade.criarTurmaFaculdade(disciplinaBuscada, professorBuscado);
                System.out.println(turma.toString() + " criada COM SUCESSO.");
                continuar = false;
            }
        } catch (DadoInvalidoException | EntidadeCadastradaException | EntidadeNaoCadastradaException |
                 EntidadeDesativadaException | EntidadeNullException e) {
            System.out.println("ERRO ao criar turma: " + e.getMessage());
        }
    }

    private static void cadastrarDisciplina(Scanner scanner, Faculdade faculdade) {
        try {
            boolean continuar = true;
            while (continuar) {
                System.out.println("Digite o nome da disciplina --- ");
                String nomeDisciplina = null;
                while (nomeDisciplina == null) {
                    nomeDisciplina = validarNome(scanner);
                    if (nomeDisciplina == null && tenteNovamente(scanner)) {
                        return;
                    }
                }
                int semestreDisciplina = 0;
                while (semestreDisciplina == 0) {
                    semestreDisciplina = validarCodigoSemestre(scanner);
                    if (semestreDisciplina == 0 && tenteNovamente(scanner)) {
                        return;
                    }
                }
                int cargaHoraria = 0;
                while (cargaHoraria == 0) {
                    cargaHoraria = validarCargaHorariaDisciplina(scanner);
                    if (cargaHoraria == 0 && tenteNovamente(scanner)) {
                        return;
                    }
                }
                Disciplina disciplina = faculdade.criarDisciplinaFaculdade(nomeDisciplina, semestreDisciplina, cargaHoraria);
                System.out.println("Disciplina : " + disciplina.toString() +  " criada COM SUCESSO.");
                continuar = false;
            }
        } catch (EntidadeNullException | DadoInvalidoException e) {
            System.out.println("ERRO ao criar disciplina: " + e.getMessage());
        }
    }

    private static void cadastrarProfessor(Scanner scanner, Faculdade faculdade) {
        try {
            boolean continuar = true;
            while (continuar) {
                System.out.println("Digite o nome do professor --- ");
                String nomeProf = null;
                while (nomeProf == null) {
                    nomeProf = validarNome(scanner);
                    if (nomeProf == null && tenteNovamente(scanner)) {
                        return;
                    }
                }
                System.out.println("Digite o email do professor --- ");
                String emailProf = null;
                while (emailProf == null) {
                    emailProf = validarEmail(scanner);
                    if (emailProf == null && tenteNovamente(scanner)) {
                        return;
                    }
                }
                Professor professor = faculdade.cadastrarProfessorFaculdade(nomeProf, emailProf);
                System.out.println("Professor : " + professor.getNome() + " (" + professor.getId() + ") cadastrado COM SUCESSO.");
                continuar = false;
            }

        } catch (DadoInvalidoException |
                 EntidadeNullException e) {
            System.out.println("ERRO ao cadastrar professor: " + e.getMessage());
        }
    }

    private static void cadastrarAluno(Scanner scanner, Faculdade faculdade) {
        try {
            boolean continuar = true;
            while (continuar) {
                System.out.println("Digite o nome do aluno --- ");
                String nomeAluno = null;
                while (nomeAluno == null) {
                    nomeAluno = validarNome(scanner);
                    if (nomeAluno == null && tenteNovamente(scanner)) {
                        return;
                    }
                }
                System.out.println("Digite o email do aluno --- ");
                String emailAluno = null;
                while (emailAluno == null) {
                    emailAluno = validarEmail(scanner);
                    if (emailAluno == null && tenteNovamente(scanner)) {
                        return;
                    }
                }
                System.out.println("Digite o curso do aluno --- ");
                String cursoAluno = null;
                while (cursoAluno == null) {
                    cursoAluno = validarCurso(scanner);
                    if (cursoAluno == null && tenteNovamente(scanner)) {
                        return;
                    }
                }
                Aluno aluno = faculdade.cadastraAlunoFaculdade(nomeAluno, emailAluno, cursoAluno);
                System.out.println("Aluno : " + aluno.getNome() + " - " + aluno.getCurso() + " (" + aluno.getId() + ") cadstrado COM SUCESSO.");
                continuar = false;
            }


        } catch (EntidadeCadastradaException | DadoInvalidoException |
                 EntidadeNullException e) {
            System.out.println("ERRO ao cadastrar aluno: " + e.getMessage());
        }
    }

    private static boolean tenteNovamente(Scanner scanner) {
        while (true) {
            System.out.println("Deseja tentar novamente? (s/n)");
            String opcao = scanner.nextLine().trim().toLowerCase();
            if (opcao.equals("n")) {
                return true;
            } else if (opcao.equals("s")) {
                return false;
            } else {
                System.out.println("Opção INVÁLIDA. Digite apenas 's' ou 'n'.");
            }
        }
    }

    public static String validarNome(Scanner scanner) {
        System.out.print("Digite o nome: ");
        String nome = scanner.nextLine().trim();
        if (!nome.isEmpty() && nome.matches("[\\p{L}\\s]+")) {
            return nome;
        }
        System.out.println("Nome INVÁLIDO.");
        return null;
    }

    public static String validarEmail(Scanner scanner) {
        System.out.print("Digite o email: ");
        String email = scanner.nextLine().trim();
        if (!email.isEmpty() && email.matches("^[\\w.-]+@[\\w.-]+\\.\\w{2,}$")) {
            return email;
        }
        System.out.println("Email INVÁLIDO.");
        return null;
    }

    public static String validarCurso(Scanner scanner) {
        System.out.print("Digite o curso: ");
        String curso = scanner.nextLine().trim();
        if (!curso.isEmpty() && curso.matches("[\\p{L}\\s]+")) {
            return curso;
        }
        System.out.println("Curso INVÁLIDO.");
        return null;
    }

    public static int validarCodigoSemestre(Scanner scanner) {
        System.out.print("Digite o código semestre: ");
        String input = scanner.nextLine().trim();
        if (!input.isEmpty() && input.matches("\\d+")) {
            return Integer.parseInt(input);
        }
        System.out.println("Código de semestre INVÁLIDO.");
        return 0;
    }

    public static int validarCargaHorariaDisciplina(Scanner scanner) {
        System.out.print("Digite a carga horária da disciplina: ");
        String input = scanner.nextLine().trim();
        if (!input.isEmpty() && input.matches("\\d+")) {
            return Integer.parseInt(input);
        }
        System.out.println("Carga horária INVÁLIDA.");
        return 0;
    }

    public static Professor buscaProfessor(Scanner scanner, Faculdade faculdade) throws EntidadeNaoCadastradaException {
        String nomeProfessor = validarNome(scanner);
        Professor professorBuscado = faculdade.encontrarProfessor(nomeProfessor);
        if (professorBuscado == null) {
            System.out.println("Professor NÃO ENCONTRADO");
        }
        return professorBuscado;
    }

    public static Disciplina buscaDisciplina(Scanner scanner, Faculdade faculdade) throws EntidadeNaoCadastradaException {
        String nomeDisciplina = validarNome(scanner);
        Disciplina disciplinaBuscada = faculdade.encontrarDisciplina(nomeDisciplina);
        if (disciplinaBuscada == null) {
            System.out.println("Disciplina NÃO ENCONTRADA");
        }
        return disciplinaBuscada;
    }

    public static Turma buscaTurma(Scanner scanner, Faculdade faculdade) throws EntidadeNaoCadastradaException {
        String codigoTurma = validarCodigoTurma(scanner);
        Turma turma = faculdade.encontrarTurma(codigoTurma);
        if (turma == null) {
            System.out.println("Turma NÃO ENCONTRADA.");
        }
        return turma;
    }

    public static Aluno buscaAluno(Scanner scanner, Faculdade faculdade) throws EntidadeNaoCadastradaException, EntidadeDesativadaException, DadoInvalidoException {
        String nomeAluno = validarNome(scanner);
        Aluno alunoBuscado = faculdade.encontrarAluno(nomeAluno);
        if (alunoBuscado == null) {
            System.out.println("Aluno NÃO ENCONTRADO");
        }
        return alunoBuscado;
    }

    private static int validarIdAluno(Scanner scanner, Faculdade faculdade) throws EntidadeNaoCadastradaException, EntidadeNaoEncontradaException {
        while (true) {
            System.out.print("Digite o ID do aluno: ");
            String input = scanner.nextLine().trim();
            if (!input.isEmpty() && input.matches("\\d+")) {
                int idAluno = Integer.parseInt(input);
                if (idAluno > 0) {
                    boolean alunoExiste = false;
                    for (Aluno aluno : faculdade.listarAlunosFaculdade()) {
                        if (aluno.getId() == idAluno) {
                            alunoExiste = true;
                            break;
                        }
                    }
                    if (!alunoExiste) {
                        System.out.println("ID não encontrado. Tente novamente.");
                    } else {
                        return idAluno;
                    }
                } else {
                    System.out.println("ID INVÁLIDO. O ID deve ser um número positivo.");
                }
            } else {
                System.out.println("ID INVÁLIDO. O ID deve ser um número.");
            }
            System.out.println("Tente novamente.");
        }
    }

    public static int validarEscolhaMedia(Scanner scanner) {
        while (true) {
            String menuMedia = """
                    Digite o tipo de cálculo de média:
                    1. Média Aritmética
                    2. Última nota tem peso maior
                    3. Remove a menor nota
                    Escolha uma opção:""";
            System.out.println(menuMedia);
            if (!scanner.hasNextInt()) {
                scanner.nextLine();
                System.out.println("Entrada INVÁLIDA.");
                continue;
            }
            int opcao = scanner.nextInt();
            scanner.nextLine();
            switch (opcao) {
                case 1:
                    System.out.println("Média Aritmética selecionada.");
                    return 1;
                case 2:
                    System.out.println("Última nota com peso maior selecionada.");
                    return 2;
                case 3:
                    System.out.println("Remover menor nota selecionado.");
                    return 3;
                default:
                    System.out.println("Opção INVÁLIDA.");
                    break;
            }
        }
    }

    public static int validarQuantUnidades(Scanner scanner) {
        while (true) {
            System.out.print("Digite a quantidade de unidades de avaliação da turma: ");
            if (!scanner.hasNextInt()) {
                scanner.nextLine();
                System.out.println("Entrada INVÁLIDA.");
                return 0;
            }
            int quantUnidades = scanner.nextInt();
            scanner.nextLine();
            if (quantUnidades > 0) {
                return quantUnidades;
            } else {
                System.out.println("Quantidade INVÁLIDA.");
            }
        }
    }

    public static double validarNota(Scanner scanner) {
        while (true) {
            System.out.print("Digite a nota: ");
            String entrada = scanner.nextLine().trim();
            if (!entrada.matches("^\\d+(\\.\\d+)?$")) {
                return 0;
            } else {
                if (entrada.matches("^\\d+(\\.\\d+)?$")) {
                    double nota = Double.parseDouble(entrada);
                    if (nota >= 0.0 && nota <= 10.0) {
                        return nota;
                    } else {
                        return 0;
                    }
                }
            }
        }
    }

    public static String validarCodigoTurma(Scanner scanner) {
        while (true) {
            System.out.print("Digite o código da turma: ");
            String codigoTurma = scanner.nextLine().trim();
            if (!codigoTurma.isEmpty() && codigoTurma.matches("[a-zA-Z0-9\\s]+")) {
                return codigoTurma;
            }
            System.out.println("Formato de código da turma INVÁLIDO");
        }
    }

}





