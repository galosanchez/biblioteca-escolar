package com.galosanchez.model;

import com.galosanchez.domain.Student;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Esta clase permite consultar, insertar, atualizar o eliminar estudiantes
 * directamente con la base de datos
 * @author Galo Sánchez
 */
public class SqlStudent {

    private Connection conexionTransaccional;

    public SqlStudent(Connection conexionTransaccional) {
        this.conexionTransaccional = conexionTransaccional;
    }

    public SqlStudent() {

    }

    /**
     * Este método permite insertar un nuevo estudiante
     * @param student
     * @return
     */
    public  int insertStudent(Student student) {
        Connection conn = null;
        PreparedStatement ps = null;

        String sql = "INSERT INTO students (cedula, name, last_name, phone, email, created_at) VALUES (?,?,?,?,?,?)";
        try {
            conn = this.conexionTransaccional != null ? this.conexionTransaccional : Conexion.getConexion();
            ps = conn.prepareStatement(sql);
            ps.setString(1, student.getCedula());
            ps.setString(2, student.getName());
            ps.setString(3, student.getLastName());
            ps.setString(4, student.getPhone());
            ps.setString(5, student.getEmail());
            ps.setTimestamp(6, student.getCreatedAt());
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
     * Método que verifica si existe un estudiante con la cédula que pasamos como parametro
     * @param cedula
     * @return 
     */
    public int existCedula(String cedula) {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        String sql = "SELECT count(id_student) FROM students WHERE cedula = ?";

        try {
            conn = this.conexionTransaccional != null ? this.conexionTransaccional : Conexion.getConexion();
            ps = conn.prepareStatement(sql);
            ps.setString(1, cedula);
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
     * Método que obtiene estudiante con la cédula
     * @param cedula
     * @return 
     */
    public Student getStudentCedula(String cedula) {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        String sql = "SELECT * FROM students WHERE cedula = ?";

        try {
            conn = this.conexionTransaccional != null ? this.conexionTransaccional : Conexion.getConexion();
            ps = conn.prepareStatement(sql);
            ps.setString(1, cedula);
            rs = ps.executeQuery();
            Student student = null;
            while(rs.next()){
                student = new Student(rs.getInt("id_student"), rs.getString("cedula"), rs.getString("name"), 
                        rs.getString("last_name"), rs.getString("phone"), rs.getString("email"));
            }
            return student;
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
     * Método que verifica si existe un estudiante con el coreo que pasamos como parametro
     * @param correo
     * @return 
     */
    public int existEmail(String correo) {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        String sql = "SELECT count(id_student) FROM students WHERE email = ?";

        try {
            conn = this.conexionTransaccional != null ? this.conexionTransaccional : Conexion.getConexion();
            ps = conn.prepareStatement(sql);
            ps.setString(1, correo);
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
     * Metodo que consulta a la base todos los estudiantes para retornar la lista
     * @return 
     */
    public ArrayList<Student> listStudents() {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        String sql = "select * from students ORDER BY id_student DESC;";

        try {
            conn = this.conexionTransaccional != null ? this.conexionTransaccional : Conexion.getConexion();
            ps = conn.prepareStatement(sql);
            rs = ps.executeQuery();
            ArrayList<Student> lista = new ArrayList<>();
            while(rs.next()){
                lista.add(new Student(rs.getInt("id_student"), rs.getString("cedula"), rs.getString("name"),
                        rs.getString("last_name"), rs.getString("phone"), rs.getString("email"), rs.getTimestamp("created_at")));
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
     * Metodo que consulta a la base todos para ontener los estudiantes que con la cedula
     * comience con el parametro que recibe.
     * @param cadena
     * @return 
     */
    public ArrayList<Student> searchCedula(String cadena) {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        String sql = "SELECT * FROM students WHERE cedula LIKE ? ORDER BY id_student DESC;";

        try {
            conn = this.conexionTransaccional != null ? this.conexionTransaccional : Conexion.getConexion();
            ps = conn.prepareStatement(sql);
            ps.setString(1, cadena+"%");
            rs = ps.executeQuery();
            ArrayList<Student> lista = new ArrayList<>();
            while(rs.next()){
                lista.add(new Student(rs.getInt("id_student"), rs.getString("cedula"), rs.getString("name"),
                        rs.getString("last_name"), rs.getString("phone"), rs.getString("email"), rs.getTimestamp("created_at")));
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
     * Metodo que consulta a la base todos para ontener los estudiantes que con el nombre
     * comience con el parametro que recibe.
     * @param cadena
     * @return 
     */
    public ArrayList<Student> searchName(String cadena) {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        String sql = "SELECT * FROM students WHERE name LIKE ? ORDER BY id_student DESC;";

        try {
            conn = this.conexionTransaccional != null ? this.conexionTransaccional : Conexion.getConexion();
            ps = conn.prepareStatement(sql);
            ps.setString(1, cadena+"%");
            rs = ps.executeQuery();
            ArrayList<Student> lista = new ArrayList<>();
            while(rs.next()){
                lista.add(new Student(rs.getInt("id_student"), rs.getString("cedula"), rs.getString("name"),
                        rs.getString("last_name"), rs.getString("phone"), rs.getString("email"), rs.getTimestamp("created_at")));
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
     * Metodo que consulta a la base todos para ontener los estudiantes que con el apellido
     * comience con el parametro que recibe.
     * @param cadena
     * @return 
     */
    public ArrayList<Student> searchLastName(String cadena) {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        String sql = "SELECT * FROM students WHERE last_name LIKE ? ORDER BY id_student DESC;";

        try {
            conn = this.conexionTransaccional != null ? this.conexionTransaccional : Conexion.getConexion();
            ps = conn.prepareStatement(sql);
            ps.setString(1, cadena+"%");
            rs = ps.executeQuery();
            ArrayList<Student> lista = new ArrayList<>();
            while(rs.next()){
                lista.add(new Student(rs.getInt("id_student"), rs.getString("cedula"), rs.getString("name"),
                        rs.getString("last_name"), rs.getString("phone"), rs.getString("email"), rs.getTimestamp("created_at")));
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
     * Metodo que consulta a la base todos para ontener los estudiantes que con el correo
     * comience con el parametro que recibe.
     * @param cadena
     * @return 
     */
    public ArrayList<Student> searchEmail(String cadena) {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        String sql = "SELECT * FROM students WHERE email LIKE ? ORDER BY id_student DESC;";

        try {
            conn = this.conexionTransaccional != null ? this.conexionTransaccional : Conexion.getConexion();
            ps = conn.prepareStatement(sql);
            ps.setString(1, cadena+"%");
            rs = ps.executeQuery();
            ArrayList<Student> lista = new ArrayList<>();
            while(rs.next()){
                lista.add(new Student(rs.getInt("id_student"), rs.getString("cedula"), rs.getString("name"),
                        rs.getString("last_name"), rs.getString("phone"), rs.getString("email"), rs.getTimestamp("created_at")));
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
     * Método que actualiza información de un estudiente de la base de datos
     * @param student
     * @return 
     */
    public int editStudent(Student student) {
        Connection conn = null;
        PreparedStatement ps = null;

        String sql = "UPDATE students SET name = ?, last_name = ?, phone = ?, email = ? WHERE cedula = ?";
        try {
            conn = this.conexionTransaccional != null ? this.conexionTransaccional : Conexion.getConexion();
            ps = conn.prepareStatement(sql);
            ps.setString(1, student.getName());
            ps.setString(2, student.getLastName());
            ps.setString(3, student.getPhone());
            ps.setString(4, student.getEmail());
            ps.setString(5, student.getCedula());
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
     * Método que consulta el numero de estudiantes
     * @return 
     */
    public int numberStudents() {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        String sql = "SELECT count(id_student) FROM students;";

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
