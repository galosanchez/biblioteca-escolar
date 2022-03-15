package com.galosanchez.controller;

import com.galosanchez.domain.Book;
import com.galosanchez.domain.CopyBook;
import com.galosanchez.model.Conexion;
import com.galosanchez.model.SqlBook;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Clase que permite obtener y asignar datos de libros y sus ejemplares
 * @author Galo
 */
public class BookController {
    
    /**
     * Método que obtiene una lista con todos los libros
     * @return 
     */
    public static ArrayList<Book> listBooks() {
        SqlBook sqlBook = new SqlBook();
        return sqlBook.bookList();
    }

    /**
     * Método que verifica si existe u ejemplar de libro con el código
     * @param code
     * @return 
     */
    public static int bookCodeExist(String code) {
        SqlBook sqlBook = new SqlBook();
        return sqlBook.copyBookCodeExist(code);
    }

    /**
     * Método que crea un ejemplar de libro
     * @param copyBook
     * @return 
     */
    public static int createCopyBook(CopyBook copyBook) {
        SqlBook sqlBook = new SqlBook();
        return sqlBook.insertCopyBook(copyBook);
    }

    /**
     * Método que una libro y su ejemplar
     * @param copyBook
     * @return 
     */
    public static int createAllBook(CopyBook copyBook)  {
        Connection conexion = null;
        try {
            conexion = Conexion.getConexion();
            if (conexion.getAutoCommit()) {
                conexion.setAutoCommit(false);            
            }
            SqlBook sqlBook = new SqlBook(conexion);
            int instBook = sqlBook.insertBook(copyBook.getBook());
            if(instBook == 0){
                conexion.rollback();
                return 0;
            }
            copyBook.getBook().setId_book(instBook);
            int instCopyBook = sqlBook.insertCopyBook(copyBook);
            
            if (instCopyBook > 0){
                conexion.commit();
                return instBook;
            }else{
                conexion.rollback();
                return 0;
            }
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
     * Método que obtiene una lista con todos los libros con las columnas
     * título, author, editorial, año y cantidad
     * @return 
     */
    public static ArrayList<Book> queryAllBooks() {
        SqlBook sqlBook = new SqlBook();
        return sqlBook.queryBookList();
    }

    /**
     * Método que permite obtener una busqueda de libros a partir del título,
     * autor, editorial y año
     * @param search
     * @param cadena
     * @return 
     */
    public static ArrayList<Book> searchBook(String search, String cadena) {
        SqlBook sqlBook = new SqlBook();
        return sqlBook.searchBook(search,cadena);
    }

    /**
     * Métdo que verifica si un ejemplar de libro esta ocupado
     * @param code
     * @return 
     */
    public static CopyBook copyBookBusy(String code) {
        SqlBook sqlBook = new SqlBook();
        return sqlBook.copyBookBusy(code);
    }
}
