/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package coins.dao;

import coins.model.Wallet;
import app.dao.GenericDAO;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.ResultSet;

/**
 *
 * @author Will
 */
public class WalletDAO extends GenericDAO {

    public Wallet findWallet(int id) throws SQLException {
        String select = "SELECT * FROM wallet WHERE id = ?";
        Wallet wallet = null;
        
        PreparedStatement stmt = getConnection().prepareStatement(select);
        stmt.setString(1, Integer.toString(id));
        ResultSet rs = stmt.executeQuery();
        
        while (rs.next()) {
            wallet = new Wallet(
                rs.getInt("id"),
                rs.getInt("user"),
                rs.getDouble("dinheiro")
            );
        }

        rs.close();
        stmt.close();
        return wallet;
    }
    
    public double alteraDinheiro(int id, double adicionar) throws SQLException {
        
        String select = "UPDATE wallet SET dinheiro = ? WHERE wallet.id = ?;";
        
        PreparedStatement stmt = getConnection().prepareStatement(select);
        
        stmt.setString(1, Double.toString(adicionar));
        stmt.setString(2, Integer.toString(id));
           
        int rs = stmt.executeUpdate();
        System.out.println(rs);
        stmt.close();
        
        return adicionar;
    }

}


