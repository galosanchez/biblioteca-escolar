package com.galosanchez.controller;

import com.galosanchez.domain.User;
import com.galosanchez.model.Hash;
import com.galosanchez.model.SqlBook;
import com.galosanchez.model.SqlLoan;
import com.galosanchez.model.SqlStudent;
import com.galosanchez.model.SqlUser;
import com.galosanchez.model.Conexion;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Clase que permite obtener y asignar datos de usuarios
 * @author Galo Sánchez
 */
public class UserController {

    /**
     * Verifica si el usuario y la contraseña estén correctos
     * @param user
     * @return 
     */
    public static User login(User user) {
            SqlUser sqlUser = new SqlUser();
            String passHash = Hash.sha1(user.getPassword());
            user.setPassword(passHash);
            return sqlUser.login(user);
    }

    /**
     * Método que obtiene el número existente de estudiantes, ejemplares de libros, prestamos, prestamos pendientes
     * @return 
     */
    public static int[] listDataHome() {
        Connection conexion = null;
        try {
            conexion = Conexion.getConexion();
            if (conexion.getAutoCommit()) {
                conexion.setAutoCommit(false);            
            }
            int[] arrayData = new int[4];
            SqlStudent sqlStudent = new SqlStudent(conexion);
            SqlBook sqlBook = new SqlBook(conexion);
            SqlLoan sqlLoan = new SqlLoan(conexion);
            arrayData[0] = sqlStudent.numberStudents();
            arrayData[1] = sqlBook.numberBooks();
            arrayData[2] = sqlLoan.numberLoans();
            arrayData[3] = sqlLoan.numberLoansPending();
            
            conexion.commit();
            return arrayData;
        } catch (SQLException ex) {
            try {
                conexion.rollback();
            } catch (SQLException ex1) {
                Logger.getLogger(BookController.class.getName()).log(Level.SEVERE, null, ex1);
                throw new RuntimeException(ex);
            }
            Logger.getLogger(BookController.class.getName()).log(Level.SEVERE, null, ex);
            throw new RuntimeException("3012", ex);
            
        }finally{
            Conexion.close(conexion);
        } 
        
    }

    /**
     * Método que obtiene una lista de usuarios excepto al administrador
     * @return 
     */
    public static ArrayList<User> listAllUsers() {
        SqlUser sqlUser = new SqlUser();
        return sqlUser.selectAllUsers();
    }
}
