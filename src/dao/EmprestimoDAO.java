package dao;

import entidades.Emprestimo;
import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class EmprestimoDAO {

    public void adicionar(Emprestimo emprestimo) {
        String sql = "INSERT INTO emprestimo (id_livro, id_cliente, data_emprestimo, data_devolucao) VALUES (?, ?, ?, ?)";

        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, emprestimo.getLivro().getId());
            stmt.setInt(2, emprestimo.getCliente().getId());
            stmt.setTimestamp(3, Timestamp.valueOf(emprestimo.getDataEmprestimo()));
            stmt.setTimestamp(4, null); // inicial, sem devolução
            stmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void marcarDevolucao(int idEmprestimo) {
        String sql = "UPDATE emprestimo SET data_devolucao = ? WHERE id = ?";

        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setTimestamp(1, Timestamp.valueOf(LocalDateTime.now()));
            stmt.setInt(2, idEmprestimo);
            stmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Emprestimo> listarTodos() {
        List<Emprestimo> lista = new ArrayList<>();
        String sql = "SELECT e.id, e.id_livro, e.id_cliente, e.data_emprestimo, e.data_devolucao, " +
                "l.titulo, l.autor, l.quantidade, c.nome " +
                "FROM emprestimo e " +
                "JOIN livro l ON e.id_livro = l.id " +
                "JOIN cliente c ON e.id_cliente = c.id";

        try (Connection conn = ConnectionFactory.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                entidades.Livro livro = new entidades.Livro(
                        rs.getInt("id_livro"),
                        rs.getString("titulo"),
                        rs.getString("autor"),
                        rs.getInt("quantidade")
                );

                entidades.Cliente cliente = new entidades.Cliente(
                        rs.getInt("id_cliente"),
                        rs.getString("nome")
                );

                LocalDateTime dataEmprestimo = rs.getTimestamp("data_emprestimo").toLocalDateTime();
                int idEmprestimo = rs.getInt("id");
                Emprestimo emprestimo = new Emprestimo(idEmprestimo, livro, cliente, dataEmprestimo);

                Timestamp tsDevolucao = rs.getTimestamp("data_devolucao");
                if (tsDevolucao != null) {
                    emprestimo.devolverLivro();
                }

                lista.add(emprestimo);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return lista;
    }
}
