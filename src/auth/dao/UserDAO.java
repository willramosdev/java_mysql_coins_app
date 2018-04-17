/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package auth.dao;

import auth.model.User;
import app.dao.GenericDAO;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.ResultSet;

/**
 *
 * @author Will
 */
public class UserDAO extends GenericDAO {

    public User findUser(String login) throws SQLException {
        String select = "SELECT * FROM users WHERE login = ?";
        User user = null;
        
        PreparedStatement stmt = getConnection().prepareStatement(select);
        stmt.setString(1, login);
        ResultSet rs = stmt.executeQuery();
        
        while (rs.next()) {
            user = new User(
                rs.getInt("id"),
                rs.getString("nome"),
                rs.getString("login"),
                rs.getString("senha")
            );
        }

        rs.close();
        stmt.close();
        return user;
    }

}


