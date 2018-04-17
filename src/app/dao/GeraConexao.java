/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package app.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class GeraConexao {
    
    private static final String DRIVER_CLASS = "com.mysql.jdbc.Driver";
    private static final String URL = "jdbc:mysql://localhost/estacionamento_db";
    private static final String USER = "admindb";
    private static final String PASS = "admindb";

    public static Connection GeraConexao() {
        Connection Conexao;
        Conexao = null;
        
        try {
            Conexao = DriverManager.getConnection(URL, USER, PASS);
        } catch (SQLException e) {
            System.out.println("erro sql de conexao: " + e.getMessage());
            Conexao = null;
        }
        return Conexao;
    }
    
    public static Connection getConnection() {
		System.out.println("Conectando ao Banco de Dados");
		try {
			Class.forName(DRIVER_CLASS);
			return DriverManager.getConnection(URL, USER, PASS);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
		return null;
	}
}
