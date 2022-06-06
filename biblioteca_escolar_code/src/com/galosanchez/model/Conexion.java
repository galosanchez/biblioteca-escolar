package com.galosanchez.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Esta clase permite establecer la conexión con la base de datos
 * @author Galo Sánchez
 */
public class Conexion {
    private static final String DATABASE = "biblioteca_escolar";
    private static final String USER = "tuUserName";
    private static final String PASSWORD = "tuPassword";
    private static final String URL = "jdbc:mysql://localhost:3306/" + DATABASE + "?useSSL=false&useTimezone=true&serverTimezone=UTC&allowPublicKeyRetrieval=true";
        
    public static Connection getConexion() {
        try {
//            Class.forName("com.mysql.jdbc.Driver");
            return DriverManager.getConnection(URL, USER, PASSWORD);
        } catch (SQLException ex) {
//            ex.printStackTrace(System.out);
            throw new RuntimeException("3011", ex);
        }
    }
    
    public static void close(ResultSet rs){
        try {
            if (rs == null) return;
            rs.close();
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        }
    }
    
    public static void close(PreparedStatement stmt){
        try {
            if (stmt == null)return;
            stmt.close();
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        }
    }
    
    public static void close(Connection conn){
        try {
            if (conn == null) return;
            conn.close();
        } catch (SQLException ex) {
           ex.printStackTrace(System.out);
        }
    }
}


