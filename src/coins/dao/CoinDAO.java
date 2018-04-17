/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package coins.dao;

import app.dao.GenericDAO;
import coins.model.Coin;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Will
 */
public class CoinDAO extends GenericDAO {
    WalletDAO wdao;
    
    public List findCoins() throws SQLException {
        List Coins = new ArrayList();

        String select = "SELECT * FROM coin";

        PreparedStatement stmt = getConnection().prepareStatement(select);

        ResultSet rs = stmt.executeQuery();

        while (rs.next()) {
            Coin coin = new Coin(
                    rs.getInt("id"),
                    rs.getString("nome"),
                    rs.getDouble("cotacao"),
                    rs.getDouble("unidades"),
                    rs.getInt("wallet")
            );
            Coins.add(coin);
        }

        rs.close();
        stmt.close();

        return Coins;
    }
    public Coin findCoin(int id) throws SQLException {
        String select = "SELECT * FROM coin WHERE id = ?";
        Coin coin = null;
        
        PreparedStatement stmt = getConnection().prepareStatement(select);
        stmt.setString(1, Integer.toString(id));
        ResultSet rs = stmt.executeQuery();
        
        while (rs.next()) {
            coin = new Coin(
                rs.getInt("id"),
                rs.getString("nome"),
                rs.getDouble("cotacao"),
                rs.getDouble("unidades"),
                rs.getInt("wallet")
            );
        }

        rs.close();
        stmt.close();
        return coin;
    }
    
    public double alteraUnidadesMoeda(int id, double valor) throws SQLException {
        
        String select = "UPDATE coin SET unidades = ? WHERE coin.id = ?;";
        
        PreparedStatement stmt = getConnection().prepareStatement(select);
        
        stmt.setString(1, Double.toString(valor));
        stmt.setString(2, Integer.toString(id));
           
        stmt.executeUpdate();
        
        stmt.close();
        
        return valor;
    }
}

