/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author Adm
 */
import java.sql.PreparedStatement;
import java.sql.Connection;
import javax.swing.JOptionPane;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.sql.SQLException;
import java.util.List;

public class ProdutosDAO {

    Connection conn;
    PreparedStatement prep;
    ResultSet rs;
    ArrayList<ProdutosDTO> listagem = new ArrayList<>();

    public void cadastrarProduto(ProdutosDTO produto) {

        conn = new conectaDAO().connectDB();

        String sql = "INSERT INTO produtos VALUES (?,?,?,?)";

        try {
            prep = conn.prepareStatement(sql);

            prep.setInt(1, produto.getId());
            prep.setString(2, produto.getNome());
            prep.setInt(3, produto.getValor());
            prep.setString(4, produto.getStatus());

            prep.executeUpdate();

            JOptionPane.showMessageDialog(null, "Produto inserido com sucesso!!");

        } catch (SQLException ex) {
            System.out.println("Erro no acesso ao banco de dados" + ex.getMessage());
        }

    }

    public ArrayList<ProdutosDTO> listarProdutos() {

        String sql = "SELECT produto.id as id, nome, quantia, valor FROM produto";

        try {
            PreparedStatement stmt = this.conn.prepareStatement(sql);

            stmt.execute();

            ResultSet resultado = stmt.executeQuery();

            while (rs.next()) {
                ProdutosDTO produto = new ProdutosDTO();

                produto.setId(rs.getInt("id"));
                produto.setNome(rs.getString("nome"));
                produto.setValor(rs.getInt("valor"));
                produto.setStatus(rs.getString("status"));

                listagem.add(produto);
            }

            return listagem;

        } catch (Exception e) {
            return null;
        }

    }

    public boolean Vender(int idProduto) {
        try {
            
            conn = new conectaDAO().connectDB();

            
            String sql = "UPDATE produtos SET status = 'Vendido' WHERE id = ?";
            
            PreparedStatement prep = this.conn.prepareStatement(sql);

            
            prep.setInt(1, idProduto);
            prep.executeUpdate();
            
            
            return true;
        } catch (SQLException ex) {
            return false;
        }
    }

   
        // Configurações do banco de dados

        

        public List<ProdutosDTO> listarProdutosVendidos() {
            List<ProdutosDTO> produtosVendidos = new ArrayList<>();

            try {
                String sql = "SELECT * FROM produtos WHERE status = 'Vendido'";
                    ResultSet rs = prep.executeQuery();
                        while (rs.next()) {
                            int id = rs.getInt("id");
                            String nome = rs.getString("nome");
                            double preco = rs.getDouble("preco");
                            // Outros campos, se houver

                            ProdutosDTO produto = new ProdutosDTO();
                            produtosVendidos.add(produto);
                        }
                    
                
            } catch (SQLException e) {
                e.getMessage();
            }

            return produtosVendidos;
        }
    }
    









             
      

