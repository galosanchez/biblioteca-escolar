package com.galosanchez.controller;

import com.galosanchez.domain.Loan;
import com.galosanchez.model.Conexion;
import com.galosanchez.model.SqlBook;
import com.galosanchez.model.SqlLoan;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Clase que permite obtener y asignar datos de prestamos
 * @author Galo
 */
public class LoanController {
    
    /**
     * Método que crea un nuevo préstamo y actualiza al ejemplar de libro el valor de active para poner en ocupado
     * @param loan
     * @return 
     */
    public static int createLoan(Loan loan) {
        Connection conexion = null;
        try {
            conexion = Conexion.getConexion();
            if (conexion.getAutoCommit()) {
                conexion.setAutoCommit(false);            
            }
            
            SqlBook sqlBook = new SqlBook(conexion);
            SqlLoan sqlLoan = new SqlLoan(conexion);
            int loanVal = sqlLoan.insertLoan(loan);
            int bookBal = sqlBook.updateCopyBook(loan.getCopyBook().getId());

            if(loanVal > 0 && bookBal > 0){
                conexion.commit();
                return 1;
            }
            conexion.rollback();
            return 0;
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
     * Método que obtiene la lista de prestamos pendientes por devolver
     * @return 
     */
    public static ArrayList<Loan> listLoanPendient() {
        SqlLoan sqlLoan = new SqlLoan();
        return sqlLoan.selectLoanPending();
    }

    /**
     * Método que obtiene los prestamos pendientes, devueltos y todos dependiento de la cadena tipo que entre
     * @param tipo
     * @return 
     */
    public static ArrayList<Loan> searchLoan(String tipo) {
        SqlLoan sqlLoan = new SqlLoan();
        ArrayList<Loan> lista = new ArrayList<>();
        switch (tipo) {
            case "pendiente":
                lista = sqlLoan.selectLoanPending();
                break;
            case "prestamo":
                lista = sqlLoan.selectLoanReturned();
                break;
            case "todo":
                lista = sqlLoan.selectLoadAll();
                break;
            default:
        }
        return lista;
    }

    /**
     * Método que obtiene los prestamos pendientes, devueltos, todos; y que comiencen con el codigo que ingrese dependiento de la cadena tipo que entre
     * @param tipo
     * @param code
     * @return 
     */
    public static ArrayList<Loan> searchLoan(String tipo, String code) {
        SqlLoan sqlLoan = new SqlLoan();
        ArrayList<Loan> lista = new ArrayList<>();
        switch (tipo) {
            case "pendiente":
                lista = sqlLoan.selectLoanPendingCode(code);
                break;
            case "prestamo":
                lista = sqlLoan.selectLoanReturnedCode(code);
                break;
            case "todo":
                lista = sqlLoan.selectLoadAllCode(code);
                break;
            default:
        }
        return lista;
    }

    /**
     * Método que actualiza un prestamo
     * @param loan
     * @return 
     */
    public static int actualizarPrestamo(Loan loan) {
        SqlLoan sqlLoan = new SqlLoan();
        return sqlLoan.updateLoan(loan);
    }
    
    
    
}
