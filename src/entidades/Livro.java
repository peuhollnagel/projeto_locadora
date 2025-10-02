package entidades;

public class Livro {
    private int id;
    private String titulo;
    private String autor;
    private int quantidade;

    public Livro(int id, String titulo, String autor, int quantidade) {
        this.id = id;
        this.titulo = titulo;
        this.autor = autor;
        this.quantidade = quantidade;
    }

    public Livro(int id, String titulo, String autor) {
        this(id, titulo, autor, 1);
    }

    public int getId() { return id; }
    public String getTitulo() { return titulo; }
    public String getAutor() { return autor; }
    public int getQuantidade() { return quantidade; }
    public void setQuantidade(int quantidade) { this.quantidade = quantidade; }

    @Override
    public String toString() {
        return "ID: " + id + ", Título: " + titulo + ", Autor: " + autor + ", Quantidade: " + quantidade;
    }
}
