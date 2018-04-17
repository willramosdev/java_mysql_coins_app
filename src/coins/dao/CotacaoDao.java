/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package coins.dao;

import app.dao.GenericDAO;
import coins.model.Cotacao;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author Will
 */
public class CotacaoDao extends GenericDAO {

    public CotacaoDao() {
    }
    
    public Cotacao findCotacao(String nome) {
        String select = "SELECT * FROM cotacao WHERE nome = ?";
        Cotacao ct = null;
        try {
            try (PreparedStatement stmt = getConnection().prepareStatement(select)) {
                stmt.setString(1, nome);
                try (ResultSet rs = stmt.executeQuery()) {
                    while (rs.next()) {
                        //int id, int moedaId, double valor, double corretagem, double imposto
                        ct = new Cotacao(
                                rs.getString("nome"),
                                rs.getDouble("valor"),
                                rs.getDouble("corretagem"),
                                rs.getDouble("imposto")
                        );
                    }
                }
            }
        } catch (SQLException e){
            System.out.println("Erro: " + e);
        }
        
        return ct;
    }
}
