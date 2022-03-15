package com.galosanchez.model;

import com.galosanchez.domain.Book;
import com.galosanchez.domain.CopyBook;
import com.galosanchez.domain.Loan;
import com.galosanchez.domain.Student;
import com.galosanchez.domain.User;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Esta clase que permite seleccionar, crear, actualiza filas de la tabla Loans en la base de datos
 * @author Galo
 */
public class SqlLoan {
    private Connection conexionTransaccional;

    public SqlLoan(Connection conexionTransaccional) {
        this.conexionTransaccional = conexionTransaccional;
    }

    public SqlLoan() {

    }
    
        /**
     * Método que inserta un nuevo prestamo de libro
     * @param loan
     * @return 
     */
    public int insertLoan(Loan loan) {
        Connection conn = null;
        PreparedStatement ps = null;

        String sql = "INSERT INTO loans (date_prestamo, date_devolucion, detail, id_user, id_student, id_copy_book) VALUES (?,?,?,?,?,?)";
        try {
            conn = this.conexionTransaccional != null ? this.conexionTransaccional : Conexion.getConexion();
            ps = conn.prepareStatement(sql);
            ps.setDate(1, loan.getDate_prestamo());
            
//            java.sql.Date l = loan.getDate_devolucion();
//            loan.getDate_devolucion().setDate(l.getDate()+1);
            ps.setDate(2, loan.getDate_devolucion());
            
            ps.setString(3, loan.getDetail());
            ps.setInt(4, loan.getUser().getId_user());
            ps.setInt(5, loan.getStudent().getId_student());
            ps.setInt(6, loan.getCopyBook().getId());
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
     * Método que selecciona los prestamos con estado prestado ordenados descendentemente
     * @return 
     */
    public ArrayList<Loan> selectLoanPending() {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

         String sql = "SELECT loans.id_loans, loans.date_prestamo, loans.date_devolucion, loans.state, loans.detail, users.id_user, users.user, students.id_student,\n" +
                    "students.cedula, students.name, students.last_name, copy_books.id as id_copy_book, copy_books.code, books.title\n" +
                    "FROM loans\n" +
                    "INNER JOIN users ON loans.id_user = users.id_user\n" +
                    "INNER JOIN students ON loans.id_student = students.id_student\n" +
                    "INNER JOIN copy_books ON loans.id_copy_book = copy_books.id\n" +
                    "INNER JOIN books ON copy_books.id_book = books.id_book\n" +
                    "WHERE loans.state = 'L'\n" +
                    "ORDER BY loans.id_loans DESC;";

        try {
            conn = this.conexionTransaccional != null ? this.conexionTransaccional : Conexion.getConexion();
            ps = conn.prepareStatement(sql);
            rs = ps.executeQuery();
            ArrayList<Loan> lista = new ArrayList<>();
            while(rs.next()){
                lista.add(new Loan(rs.getInt("id_loans"), new java.sql.Date(rs.getDate("date_prestamo").getTime() + 86400000),new java.sql.Date(rs.getDate("date_devolucion").getTime() + 86400000),
                        rs.getString("state").charAt(0),rs.getString("detail"), 
                            new User(rs.getInt("id_user"), rs.getString("user")), 
                                new Student(rs.getInt("id_student"), rs.getString("cedula"), rs.getString("name"), rs.getString("last_name")), 
                                    new CopyBook(rs.getInt("id_copy_book"), rs.getString("code"), 
                                        new Book(rs.getString("title")))));
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
     * Método que selecciona los prestamos con estado devuelto ordenados descendentemente
     * @return 
     */
    public ArrayList<Loan> selectLoanReturned() {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

         String sql = "SELECT loans.id_loans, loans.date_prestamo, loans.date_devolucion, loans.state, loans.detail, users.id_user, users.user, students.id_student,\n" +
                    "students.cedula, students.name, students.last_name, copy_books.id as id_copy_book, copy_books.code, books.title\n" +
                    "FROM loans\n" +
                    "INNER JOIN users ON loans.id_user = users.id_user\n" +
                    "INNER JOIN students ON loans.id_student = students.id_student\n" +
                    "INNER JOIN copy_books ON loans.id_copy_book = copy_books.id\n" +
                    "INNER JOIN books ON copy_books.id_book = books.id_book\n" +
                    "WHERE loans.state = 'D'\n" +
                    "ORDER BY loans.id_loans DESC;";

        try {
            conn = this.conexionTransaccional != null ? this.conexionTransaccional : Conexion.getConexion();
            ps = conn.prepareStatement(sql);
            rs = ps.executeQuery();
            ArrayList<Loan> lista = new ArrayList<>();
            while(rs.next()){
                lista.add(new Loan(rs.getInt("id_loans"), new java.sql.Date(rs.getDate("date_prestamo").getTime() + 86400000),new java.sql.Date(rs.getDate("date_devolucion").getTime() + 86400000),
                        rs.getString("state").charAt(0),rs.getString("detail"), 
                            new User(rs.getInt("id_user"), rs.getString("user")), 
                                new Student(rs.getInt("id_student"), rs.getString("cedula"), rs.getString("name"), rs.getString("last_name")), 
                                    new CopyBook(rs.getInt("id_copy_book"), rs.getString("code"), 
                                        new Book(rs.getString("title")))));
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
     * Método que selecciona todos los prestamos ordenados de forma decendente
     * @return 
     */
    public ArrayList<Loan> selectLoadAll() {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

         String sql = "SELECT loans.id_loans, loans.date_prestamo, loans.date_devolucion, loans.state, loans.detail, users.id_user, users.user, students.id_student,\n" +
                    "students.cedula, students.name, students.last_name, copy_books.id as id_copy_book, copy_books.code, books.title\n" +
                    "FROM loans\n" +
                    "INNER JOIN users ON loans.id_user = users.id_user\n" +
                    "INNER JOIN students ON loans.id_student = students.id_student\n" +
                    "INNER JOIN copy_books ON loans.id_copy_book = copy_books.id\n" +
                    "INNER JOIN books ON copy_books.id_book = books.id_book\n" +
                    "ORDER BY loans.id_loans DESC;";

        try {
            conn = this.conexionTransaccional != null ? this.conexionTransaccional : Conexion.getConexion();
            ps = conn.prepareStatement(sql);
            rs = ps.executeQuery();
            ArrayList<Loan> lista = new ArrayList<>();
            while(rs.next()){
                lista.add(new Loan(rs.getInt("id_loans"), new java.sql.Date(rs.getDate("date_prestamo").getTime() + 86400000),new java.sql.Date(rs.getDate("date_devolucion").getTime() + 86400000),
                        rs.getString("state").charAt(0),rs.getString("detail"),  
                            new User(rs.getInt("id_user"), rs.getString("user")), 
                                new Student(rs.getInt("id_student"), rs.getString("cedula"), rs.getString("name"), rs.getString("last_name")), 
                                    new CopyBook(rs.getInt("id_copy_book"), rs.getString("code"), 
                                        new Book(rs.getString("title")))));
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
     * Método que selecciona los prestamos con estado prestado y que comiencen con el codigo que ingrese,
     * ordenados descendentemente
     * @param code
     * @return 
     */
    public ArrayList<Loan> selectLoanPendingCode(String code) {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

         String sql = "SELECT loans.id_loans, loans.date_prestamo, loans.date_devolucion, loans.state, loans.detail, users.id_user, users.user, students.id_student,\n" +
                    "students.cedula, students.name, students.last_name, copy_books.id as id_copy_book, copy_books.code, books.title\n" +
                    "FROM loans\n" +
                    "INNER JOIN users ON loans.id_user = users.id_user\n" +
                    "INNER JOIN students ON loans.id_student = students.id_student\n" +
                    "INNER JOIN copy_books ON loans.id_copy_book = copy_books.id\n" +
                    "INNER JOIN books ON copy_books.id_book = books.id_book\n" +
                    "WHERE loans.state = 'L' AND copy_books.code LIKE ? \n" +
                    "ORDER BY loans.id_loans DESC;";

        try {
            conn = this.conexionTransaccional != null ? this.conexionTransaccional : Conexion.getConexion();
            ps = conn.prepareStatement(sql);
            ps.setString(1, code+"%");
            rs = ps.executeQuery();
            ArrayList<Loan> lista = new ArrayList<>();
            while(rs.next()){
                lista.add(new Loan(rs.getInt("id_loans"), new java.sql.Date(rs.getDate("date_prestamo").getTime() + 86400000),new java.sql.Date(rs.getDate("date_devolucion").getTime() + 86400000),
                        rs.getString("state").charAt(0),rs.getString("detail"), 
                            new User(rs.getInt("id_user"), rs.getString("user")), 
                                new Student(rs.getInt("id_student"), rs.getString("cedula"), rs.getString("name"), rs.getString("last_name")), 
                                    new CopyBook(rs.getInt("id_copy_book"), rs.getString("code"), 
                                        new Book(rs.getString("title")))));
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
     * Método que selecciona los prestamos con estado devuelto y que comiencen con el codigo que ingrese,
     * ordenados descendentemente
     * @param code
     * @return 
     */
    public ArrayList<Loan> selectLoanReturnedCode(String code) {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

         String sql = "SELECT loans.id_loans, loans.date_prestamo, loans.date_devolucion, loans.state, loans.detail, users.id_user, users.user, students.id_student,\n" +
                    "students.cedula, students.name, students.last_name, copy_books.id as id_copy_book, copy_books.code, books.title\n" +
                    "FROM loans\n" +
                    "INNER JOIN users ON loans.id_user = users.id_user\n" +
                    "INNER JOIN students ON loans.id_student = students.id_student\n" +
                    "INNER JOIN copy_books ON loans.id_copy_book = copy_books.id\n" +
                    "INNER JOIN books ON copy_books.id_book = books.id_book\n" +
                    "WHERE loans.state = 'D' AND copy_books.code LIKE ? \n" +
                    "ORDER BY loans.id_loans DESC;";

        try {
            conn = this.conexionTransaccional != null ? this.conexionTransaccional : Conexion.getConexion();
            ps = conn.prepareStatement(sql);
            ps.setString(1, code+"%");
            rs = ps.executeQuery();
            ArrayList<Loan> lista = new ArrayList<>();
            while(rs.next()){
                lista.add(new Loan(rs.getInt("id_loans"), new java.sql.Date(rs.getDate("date_prestamo").getTime() + 86400000),new java.sql.Date(rs.getDate("date_devolucion").getTime() + 86400000),
                        rs.getString("state").charAt(0),rs.getString("detail"), 
                            new User(rs.getInt("id_user"), rs.getString("user")), 
                                new Student(rs.getInt("id_student"), rs.getString("cedula"), rs.getString("name"), rs.getString("last_name")), 
                                    new CopyBook(rs.getInt("id_copy_book"), rs.getString("code"), 
                                        new Book(rs.getString("title")))));
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
     * Método que selecciona los prestamos que comiencen con el codigo que ingrese,
     * ordenados descendentemente
     * @param code
     * @return 
     */
    public ArrayList<Loan> selectLoadAllCode(String code) {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

         String sql = "SELECT loans.id_loans, loans.date_prestamo, loans.date_devolucion, loans.state, loans.detail, users.id_user, users.user, students.id_student,\n" +
                    "students.cedula, students.name, students.last_name, copy_books.id as id_copy_book, copy_books.code, books.title\n" +
                    "FROM loans\n" +
                    "INNER JOIN users ON loans.id_user = users.id_user\n" +
                    "INNER JOIN students ON loans.id_student = students.id_student\n" +
                    "INNER JOIN copy_books ON loans.id_copy_book = copy_books.id\n" +
                    "INNER JOIN books ON copy_books.id_book = books.id_book\n" +
                    "WHERE copy_books.code LIKE ? \n" +
                    "ORDER BY loans.id_loans DESC;";

        try {
            conn = this.conexionTransaccional != null ? this.conexionTransaccional : Conexion.getConexion();
            ps = conn.prepareStatement(sql);
            ps.setString(1, code+"%");
            rs = ps.executeQuery();
            ArrayList<Loan> lista = new ArrayList<>();
            while(rs.next()){
                lista.add(new Loan(rs.getInt("id_loans"), new java.sql.Date(rs.getDate("date_prestamo").getTime() + 86400000),new java.sql.Date(rs.getDate("date_devolucion").getTime() + 86400000),
                        rs.getString("state").charAt(0),rs.getString("detail"), 
                            new User(rs.getInt("id_user"), rs.getString("user")), 
                                new Student(rs.getInt("id_student"), rs.getString("cedula"), rs.getString("name"), rs.getString("last_name")), 
                                    new CopyBook(rs.getInt("id_copy_book"), rs.getString("code"), 
                                        new Book(rs.getString("title")))));
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
     * Método que actualice el detalle y el estado al préstamo con el id que ingresa
     * @param loan
     * @return 
     */
    public int updateLoan(Loan loan) {
        Connection conn = null;
        PreparedStatement ps = null;

        String sql = "UPDATE loans SET detail = ?, state = ? WHERE id_loans = ?";
        try {
            conn = this.conexionTransaccional != null ? this.conexionTransaccional : Conexion.getConexion();
            ps = conn.prepareStatement(sql);
            ps.setString(1, loan.getDetail());
            ps.setString(2, String.valueOf(loan.getState()) );
            ps.setInt(3, loan.getId_loan());
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
     * Método que consulta el numero de prestamos totales
     * @return 
     */
    public int numberLoans() {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        String sql = "SELECT COUNT(id_loans) FROM loans;";

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

    /**
     * Método que consulta el numero de prestamos que se encuentren pendiente por devolver
     * @return 
     */
    public int numberLoansPending() {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        String sql = "SELECT COUNT(id_loans) FROM loans where state = 'L';";

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
