package com.galosanchez.model;

import com.galosanchez.domain.Author;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Clase que permite seleccionar, crear, actualiza filas de la tabla Author en la base de datos
 * @author Galo Sánchez
 */
public class SqlAuthor {

    private Connection conexionTransaccional;

    public SqlAuthor(Connection conexionTransaccional) {
        this.conexionTransaccional = conexionTransaccional;
    }

    public SqlAuthor() {

    }
    
    /**
     * Este método permite insertar un nuevo author
     * @param author
     * @return
     */
    public int insertAuthor(Author author) {
        Connection conn = null;
        PreparedStatement ps = null;

        String sql = "INSERT INTO authors (name) VALUES (?)";
        try {
            conn = this.conexionTransaccional != null ? this.conexionTransaccional : Conexion.getConexion();
            ps = conn.prepareStatement(sql);
            ps.setString(1, author.getName());
            return ps.executeUpdate();

        } catch (SQLException ex) {
            Logger.getLogger(SqlStudent.class.getName()).log(Level.SEVERE, null, ex);
            throw new RuntimeException("3012", ex);
        } finally {
            Conexion.close(ps);
            if (this.conexionTransaccional == null) {
                Conexion.close(conn);
            }
        }
    }

    /**
     * Este método permite obtener una lista con todos los autores
     * @return 
     */
    public ArrayList<Author> selectAllAuthors() {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        String sql = "SELECT * FROM authors\n" +
                     "ORDER BY name ASC;";
        try {
            conn = this.conexionTransaccional != null ? this.conexionTransaccional : Conexion.getConexion();
            ps = conn.prepareStatement(sql);
            rs = ps.executeQuery();
            ArrayList<Author> lista = new ArrayList<>();
            while(rs.next()){
                lista.add(new Author(rs.getInt("id_author"), rs.getString("name")));
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
