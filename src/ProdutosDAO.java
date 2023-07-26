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
    
    
    
    public void cadastrarProduto (ProdutosDTO produto){
        
        
        
        
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
            
        }catch(SQLException ex ){
            System.out.println("Erro no acesso ao banco de dados" + ex.getMessage());
        }
        
           
              
           
    }
    
    
         public ArrayList<ProdutosDTO> listarProdutos(){
        
        return listagem;
    }
}

