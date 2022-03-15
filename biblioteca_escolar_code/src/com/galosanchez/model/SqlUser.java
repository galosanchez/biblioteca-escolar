package com.galosanchez.model;

import com.galosanchez.domain.User;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Esta clase permite consultar, insertar, atualizar o eliminar Usuarios
 * directamente con la base de datos
 * @author Galo Sánchez
 */
public class SqlUser {

    private Connection conexionTransaccional;

    public SqlUser() {

    }

    public SqlUser(Connection conexionTransaccional) {
        this.conexionTransaccional = conexionTransaccional;
    }

    /**
     * Verifica si el usuario y la contraseña estén correctos
     * @param user
     * @return 
     */
    public User login(User user) {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        String sql = "SELECT id_user, user, name, email, created_at, type FROM users WHERE user = ? AND password = ?";
        try {
            conn = this.conexionTransaccional != null ? this.conexionTransaccional : Conexion.getConexion();
            ps = conn.prepareStatement(sql);
            ps.setString(1, user.getUser());
            ps.setString(2, user.getPassword());
            rs = ps.executeQuery();
            User u = null;
            if (rs.next()) {
                u = new User(rs.getInt("id_user"), rs.getString("user"), rs.getString("name"), 
                        rs.getString("email"), rs.getTimestamp("created_at"), rs.getString("type").charAt(0));
            }
            return u;
        } catch (SQLException ex) {
            throw new RuntimeException("3012", ex);
        } finally {
            Conexion.close(rs);
            Conexion.close(ps);
            if (this.conexionTransaccional == null) {
                Conexion.close(conn);
            }
        }
    }

    /**
     * Método que selecciona todos los usuarios excepto al administrador
     * @return 
     */
    public ArrayList<User> selectAllUsers() {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        String sql = "select id_user, user, name, email, created_at, type FROM users where user != 'admin';";

        try {
            conn = this.conexionTransaccional != null ? this.conexionTransaccional : Conexion.getConexion();
            ps = conn.prepareStatement(sql);
            rs = ps.executeQuery();
            ArrayList<User> lista = new ArrayList<>();
            while(rs.next()){
            lista.add(new User(rs.getInt("id_user"), rs.getString("user"), rs.getString("name"), rs.getString("email"), rs.getTimestamp("created_at"), rs.getString("type").charAt(0) ));
                
            }
            return lista;
        } catch (SQLException ex) {
            Logger.getLogger(SqlStudent.class.getName()).log(Level.SEVERE, null, ex);
            throw new RuntimeException("3012", ex);
        } finally {
            Conexion.close(rs);
            Conexion.close(ps);
            if (this.conexionTransaccional == null) {
                Conexion.close(conn);
            }
        }
    }

}
