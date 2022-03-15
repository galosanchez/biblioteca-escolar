package com.galosanchez.model;

import com.galosanchez.domain.Author;
import com.galosanchez.domain.Book;
import com.galosanchez.domain.CopyBook;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Clase que permite seleccionar, crear, actualiza filas de la tabla Book y CopyBook en la base de datos
 * @author Galo
 */
public class SqlBook {
    
    private Connection conexionTransaccional;

    public SqlBook(Connection conexionTransaccional) {
        this.conexionTransaccional = conexionTransaccional;
    }

    public SqlBook() {

    }

    /**
     * Método que consulta a la base todos los libos
     * @return lista de tipo book
     */
    public ArrayList<Book> bookList() {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        String sql = "SELECT books.id_book, books.editorial, books.title, books.year, authors.id_author, authors.name\n" +
                     "FROM books\n" +
                     "INNER JOIN authors ON books.id_author = authors.id_author\n" +
                     "ORDER BY books.title ASC;";
        try {
            conn = this.conexionTransaccional != null ? this.conexionTransaccional : Conexion.getConexion();
            ps = conn.prepareStatement(sql);
            rs = ps.executeQuery();
            ArrayList<Book> lista = new ArrayList<>();
            while(rs.next()){
                lista.add(new Book(rs.getInt("id_book"), rs.getString("editorial"), rs.getString("title"),
                        rs.getString("year"),  new Author(rs.getInt("id_author"), rs.getString("name"))));
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

    /**
     * Método que verifica si existe un ejemplar de un libro a partir del código
     * @param code
     * @return 
     */
    public int copyBookCodeExist(String code) {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        String sql = "SELECT COUNT(id) FROM copy_books WHERE code = ?";

        try {
            conn = this.conexionTransaccional != null ? this.conexionTransaccional : Conexion.getConexion();
            ps = conn.prepareStatement(sql);
            ps.setString(1, code);
            rs = ps.executeQuery();
            if (rs.next()) {
                return rs.getInt(1);
            }
            return 1;
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

    /**
     * Método que inserta un ejemplar de un libro
     * @param copyBook
     * @return 
     */
    public int insertCopyBook(CopyBook copyBook) {
        Connection conn = null;
        PreparedStatement ps = null;

        String sql = "INSERT INTO copy_books (code, state, id_book) VALUES (?,?,?)";
        try {
            conn = this.conexionTransaccional != null ? this.conexionTransaccional : Conexion.getConexion();
            ps = conn.prepareStatement(sql);
            ps.setString(1, copyBook.getCode());
            ps.setString(2, copyBook.getState());
            ps.setInt(3, copyBook.getBook().getId_book());
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
     * Método que inserta un libro
     * @param book
     * @return
     * @throws SQLException 
     */
    public int insertBook(Book book) throws SQLException {
        Connection conn = null;
        PreparedStatement ps = null;

        String sql = "INSERT INTO books (editorial, title, year, id_author) VALUES (?,?,?,?)";
        try {
            conn = this.conexionTransaccional != null ? this.conexionTransaccional : Conexion.getConexion();
            ps = conn.prepareStatement(sql,Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, book.getEditorial());
            ps.setString(2, book.getTitle());
            ps.setString(3, book.getYear());
            ps.setInt(4, book.getAuthor().getId_author());
            if(ps.executeUpdate() == 0)
                return 0;
            ResultSet generatedKeys = ps.getGeneratedKeys();
            if (generatedKeys.next()) {
                return generatedKeys.getInt(1);
            }else{
                return 0;
            }
        }finally {
            Conexion.close(ps);
            if (this.conexionTransaccional == null) {
                Conexion.close(conn);
            }
        }
    }

    /**
     * Método que obtiene una lista con todos los libros con las columnas
     * título, author, editorial, año y cantidad
     * @return 
     */
    public ArrayList<Book> queryBookList() {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        String sql = "SELECT books.id_book, books.title, authors.name author, books.editorial, books.year, COUNT(*) cantidad\n" +
                     "FROM copy_books\n" +
                     "INNER JOIN books ON copy_books.id_book = books.id_book\n" +
                     "INNER JOIN authors ON books.id_author = authors.id_author\n" +
                     "GROUP BY copy_books.id_book;";
        try {
            conn = this.conexionTransaccional != null ? this.conexionTransaccional : Conexion.getConexion();
            ps = conn.prepareStatement(sql);
            rs = ps.executeQuery();
            ArrayList<Book> lista = new ArrayList<>();
            while(rs.next()){
                lista.add(new Book(rs.getInt("id_book"), rs.getString("editorial"), rs.getString("title"),
                        rs.getString("year"),  new Author(rs.getInt("cantidad"), rs.getString("author"))));
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

    /**
     * Método que permite obtener un alista de busqueda de libros a partir del título,
     * autor, editorial y año
     * @param search
     * @param cadena
     * @return 
     */
    public ArrayList<Book> searchBook(String search, String cadena) {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        String consulta ="";
        switch (search) {
            case "title":
                consulta = "books.title";
                break;
            case "author":
                consulta = "authors.name";
                break;
            case "editorial":
                consulta = "books.editorial";
                break;
            case "year":
                consulta = "books.year";
                break;
            default:
        }

        String sql = "SELECT books.id_book, books.title, authors.name author, books.editorial, books.year, COUNT(*) cantidad\n" +
                     "FROM copy_books\n" +
                     "INNER JOIN books ON copy_books.id_book = books.id_book\n" +
                     "INNER JOIN authors ON books.id_author = authors.id_author\n" +
                     "WHERE "+consulta+" LIKE ? \n" +
                     "GROUP BY copy_books.id_book;";
        try {
            conn = this.conexionTransaccional != null ? this.conexionTransaccional : Conexion.getConexion();
            ps = conn.prepareStatement(sql);
            ps.setString(1, cadena+"%");
            rs = ps.executeQuery();
            ArrayList<Book> lista = new ArrayList<>();
            while(rs.next()){
                lista.add(new Book(rs.getInt("id_book"), rs.getString("editorial"), rs.getString("title"),
                        rs.getString("year"),  new Author(rs.getInt("cantidad"), rs.getString("author"))));
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

    /**
     * Métdo que verifica si un ejemplar de libro esta ocupado
     * @param code
     * @return 
     */
    public CopyBook copyBookBusy(String code) {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        String sql = "SELECT * FROM copy_books WHERE code = ? AND active = 'F';";

        try {
            conn = this.conexionTransaccional != null ? this.conexionTransaccional : Conexion.getConexion();
            ps = conn.prepareStatement(sql);
            ps.setString(1, code);
            rs = ps.executeQuery();
            CopyBook copyBook = null;
            while(rs.next()){
                copyBook = new CopyBook(rs.getInt("id"), rs.getString("code"), rs.getString("state"), new Book(rs.getInt("id_book")));
            }
            return copyBook;
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

    /**
     * Método que actualiza el valor de active para poner en ocupado a un ejemplar de libro
     * @param id_copy_book
     * @return
     * @throws SQLException 
     */
    public int updateCopyBook(int id_copy_book) throws SQLException {
        Connection conn = null;
        PreparedStatement ps = null;

        String sql = "UPDATE copy_books SET active = 'B'WHERE id = ? ;";
        try {
            conn = this.conexionTransaccional != null ? this.conexionTransaccional : Conexion.getConexion();
            ps = conn.prepareStatement(sql);
            ps.setInt(1, id_copy_book);
            return ps.executeUpdate();
        }finally {
            Conexion.close(ps);
            if (this.conexionTransaccional == null) {
                Conexion.close(conn);
            }
        }
    }

    /**
     * Método que consulta el numero de ejemplares de libros
     * @return 
     */
    public int numberBooks() {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        String sql = "SELECT count(id) FROM copy_books;";

        try {
            conn = this.conexionTransaccional != null ? this.conexionTransaccional : Conexion.getConexion();
            ps = conn.prepareStatement(sql);
            rs = ps.executeQuery();
            if (rs.next()) {
                return rs.getInt(1);
            }
            return 0;
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