package aplicacao;

import entidades.Livro;
import entidades.Cliente;
import dao.LivroDAO;
import dao.ClienteDAO;

import java.util.List;
import java.util.Scanner;

public class Programa {
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        menu();
    }

    public static void menu() {
        int opcao;
        do {
            System.out.println("\n=== Sistema de Biblioteca ===");
            System.out.println("1. Cadastrar Livro");
            System.out.println("2. Cadastrar Cliente");
            System.out.println("3. Listar Livros Cadastrados");
            System.out.println("4. Listar Clientes Cadastrados");
            System.out.println("5. Deletar Livro");
            System.out.println("6. Deletar Cliente");
            System.out.println("7. Realizar Empréstimo de Livro");
            System.out.println("8. Listar Empréstimos");
            System.out.println("9. Realizar Devolução de Livro");
            System.out.println("0. Sair");
            System.out.print("Escolha uma opção: ");
            opcao = scanner.nextInt();
            scanner.nextLine(); // limpar buffer

            switch (opcao) {
                case 1 -> cadastrarLivro();
                case 2 -> cadastrarCliente();
                case 3 -> listarLivrosCadastrados();
                case 4 -> listarClientesCadastrados();
                case 5 -> deletarLivro();
                case 6 -> deletarCliente();
                case 7 -> realizarEmprestimoLivro();
                case 8 -> listarEmprestimos();
                case 9 -> realizarDevolucaoLivro();
                case 0 -> System.out.println("Saindo...");
                default -> System.out.println("Opção inválida!");
            }
        } while (opcao != 0);
    }

    // ===================== LIVROS =====================
    public static void cadastrarLivro() {
        System.out.print("Digite o Título do Livro: ");
        String titulo = scanner.nextLine();
        System.out.print("Digite o Autor do Livro: ");
        String autor = scanner.nextLine();
        System.out.print("Digite a Quantidade de Exemplares: ");
        int quantidade = scanner.nextInt();
        scanner.nextLine();
        Livro livro = new Livro(0, titulo, autor, quantidade);
        LivroDAO livroDAO = new LivroDAO();
        livroDAO.adicionar(livro);
        System.out.println("Livro cadastrado com sucesso!");
    }

    public static void listarLivrosCadastrados() {
        LivroDAO livroDAO = new LivroDAO();
        List<Livro> livros = livroDAO.listarTodos();

        System.out.println("\n=== Livros Cadastrados ===");
        for (Livro livro : livros) {
            System.out.println(livro);
        }
    }

    public static void deletarLivro() {
        System.out.print("Digite o ID do Livro a ser deletado: ");
        int id = scanner.nextInt();
        scanner.nextLine();

        LivroDAO livroDAO = new LivroDAO();
        boolean sucesso = livroDAO.deletar(id);

        if (sucesso) System.out.println("Livro deletado com sucesso!");
        else System.out.println("Livro não encontrado ou erro ao deletar.");
    }

    // ===================== CLIENTES =====================
    public static void cadastrarCliente() {
        System.out.print("Digite o Nome do Cliente: ");
        String nome = scanner.nextLine();

        Cliente cliente = new Cliente(0, nome);
        ClienteDAO clienteDAO = new ClienteDAO();
        clienteDAO.adicionar(cliente);

        System.out.println("Cliente cadastrado com sucesso!");
    }

    public static void listarClientesCadastrados() {
        ClienteDAO clienteDAO = new ClienteDAO();
        List<Cliente> clientes = clienteDAO.listarTodos();

        System.out.println("\n=== Clientes Cadastrados ===");
        for (Cliente cliente : clientes) {
            System.out.println(cliente);
        }
    }

    public static void deletarCliente() {
        System.out.print("Digite o ID do Cliente a ser deletado: ");
        int id = scanner.nextInt();
        scanner.nextLine();

        ClienteDAO clienteDAO = new ClienteDAO();
        boolean sucesso = clienteDAO.deletar(id);

        if (sucesso) System.out.println("Cliente deletado com sucesso!");
        else System.out.println("Cliente não encontrado ou erro ao deletar.");
    }

    // ===================== EMPRÉSTIMOS =====================
    public static void realizarEmprestimoLivro() {
        System.out.print("Digite o ID do Livro: ");
        int idLivro = scanner.nextInt();
        System.out.print("Digite o ID do Cliente: ");
        int idCliente = scanner.nextInt();
        scanner.nextLine();
        LivroDAO livroDAO = new LivroDAO();
        ClienteDAO clienteDAO = new ClienteDAO();
        dao.EmprestimoDAO emprestimoDAO = new dao.EmprestimoDAO();
        entidades.Livro livro = livroDAO.buscarPorId(idLivro);
        entidades.Cliente cliente = clienteDAO.buscarPorId(idCliente);
        if (livro == null) {
            System.out.println("Livro não encontrado!");
            return;
        }
        if (cliente == null) {
            System.out.println("Cliente não encontrado!");
            return;
        }
        if (livro.getQuantidade() <= 0) {
            System.out.println("Não há exemplares disponíveis para empréstimo!");
            return;
        }
        entidades.Emprestimo emprestimo = new entidades.Emprestimo(livro, cliente, java.time.LocalDateTime.now());
        emprestimoDAO.adicionar(emprestimo);
        livroDAO.decrementarQuantidade(livro.getId());
        System.out.println("Empréstimo realizado com sucesso!");
    }

    public static void listarEmprestimos() {
        dao.EmprestimoDAO emprestimoDAO = new dao.EmprestimoDAO();
        java.util.List<entidades.Emprestimo> lista = emprestimoDAO.listarTodos();
        System.out.println("\n=== Empréstimos ===");
        for (entidades.Emprestimo e : lista) {
            e.exibirDetalhes();
            System.out.println("----------------------");
        }
    }

    public static void realizarDevolucaoLivro() {
        System.out.print("Digite o ID do Empréstimo: ");
        int idEmprestimo = scanner.nextInt();
        scanner.nextLine();
        dao.EmprestimoDAO emprestimoDAO = new dao.EmprestimoDAO();
        entidades.Emprestimo emprestimo = null;
        for (entidades.Emprestimo e : emprestimoDAO.listarTodos()) {
            if (e.getId() == idEmprestimo) {
                emprestimo = e;
                break;
            }
        }
        if (emprestimo == null) {
            System.out.println("Empréstimo não encontrado!");
            return;
        }
        emprestimoDAO.marcarDevolucao(idEmprestimo);
        LivroDAO livroDAO = new LivroDAO();
        livroDAO.incrementarQuantidade(emprestimo.getLivro().getId());
        System.out.println("Devolução realizada com sucesso!");
    }
}
