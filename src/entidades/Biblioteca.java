package entidades;

import java.util.ArrayList;
import java.util.List;

public class Biblioteca {
    private List<Livro> livros;
    private List<Cliente> clientes;
    private List<Emprestimo> emprestimos;  // Lista de empréstimos realizados

    public Biblioteca() {
        livros = new ArrayList<>();
        clientes = new ArrayList<>();
        emprestimos = new ArrayList<>();
    }

    public void adicionarLivro(Livro livro) {
        livros.add(livro);
    }

    public void adicionarCliente(Cliente cliente) {
        clientes.add(cliente);
    }

    public void adicionarEmprestimo(Emprestimo emprestimo) {
        emprestimos.add(emprestimo);
    }

    public List<Livro> getLivros() {
        return livros;
    }

    public List<Cliente> getClientes() {
        return clientes;
    }

    public List<Emprestimo> getEmprestimos() {
        return emprestimos;
    }

    public Object buscarPorId(int id) {
        for (Livro livro : livros) {
            if (livro.getId() == id) return livro;
        }
        for (Cliente cliente : clientes) {
            if (cliente.getId() == id) return cliente;
        }
        return null;
    }
}
