package entidades;

import java.time.LocalDateTime;

public class Emprestimo {
    private int id;
    private Livro livro;
    private Cliente cliente;
    private LocalDateTime dataEmprestimo;
    private LocalDateTime dataDevolucao;

    public Emprestimo(int id, Livro livro, Cliente cliente, LocalDateTime dataEmprestimo) {
        this.id = id;
        this.livro = livro;
        this.cliente = cliente;
        this.dataEmprestimo = dataEmprestimo;
        this.dataDevolucao = null;
    }

    public Emprestimo(Livro livro, Cliente cliente, LocalDateTime dataEmprestimo) {
        this(0, livro, cliente, dataEmprestimo);
    }

    public int getId() { return id; }
    public Livro getLivro() { return livro; }
    public Cliente getCliente() { return cliente; }
    public LocalDateTime getDataEmprestimo() { return dataEmprestimo; }
    public LocalDateTime getDataDevolucao() { return dataDevolucao; }
    public void setId(int id) { this.id = id; }
    public void devolverLivro() {
        this.dataDevolucao = LocalDateTime.now();
    }
    public void exibirDetalhes() {
        System.out.println("ID do Empréstimo: " + id);
        System.out.println("Livro: " + livro.getTitulo());
        System.out.println("Cliente: " + cliente.getNome());
        System.out.println("Data do Empréstimo: " + dataEmprestimo);
        System.out.println("Data da Devolução: " + (dataDevolucao != null ? dataDevolucao : "Pendente"));
    }
}
