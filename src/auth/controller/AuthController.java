/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package auth.controller;

import auth.dao.UserDAO;
import auth.model.User;
import java.sql.SQLException;
import javax.swing.JOptionPane;


/**
 *
 * @author Will
 */
public class AuthController {
    
    private final UserDAO dao = new UserDAO();
    private User user = null;

    
    public AuthController() {
    }

    public User getUser() {
        return user;
    }
    
    public static void Sair() {
        System.exit(0);
    }
    
    public boolean Login(String login, String senha) throws SQLException {
        
        User logUser = dao.findUser(login);
        
        if (logUser != null) {
            if ( login.equals(logUser.getLogin())) {
                if (senha.equals(logUser.getSenha())) {
                    this.user = logUser;
                    return true;
                } else {
                    JOptionPane.showMessageDialog(
                        null, 
                        "Senha incorreta"
                    );
                }
            }
        } else {
            JOptionPane.showMessageDialog(
                null, 
                "Login incorreto"
            );
        }
        return false;
    }
    
}
