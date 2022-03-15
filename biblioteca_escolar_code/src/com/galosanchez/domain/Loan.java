package com.galosanchez.domain;

import java.sql.Date;

/**
 *
 * @author Galo
 */
public class Loan {
    private int id_loan;
    private Date date_prestamo;
    private Date date_devolucion;
    private char state;
    private String detail;
    private User user;
    private Student student;
    private CopyBook copyBook;
    
    public Loan(){
        
    }

    public Loan(int id_loan, Date date_prestamo, Date date_devolucion, char state, String detail, User user, Student student, CopyBook copyBook) {
        this.id_loan = id_loan;
        this.date_prestamo = date_prestamo;
        this.date_devolucion = date_devolucion;
        this.state = state;
        this.detail = detail;
        this.user = user;
        this.student = student;
        this.copyBook = copyBook;
    }
    
    public int getId_loan() {
        return id_loan;
    }

    public void setId_loan(int id_loan) {
        this.id_loan = id_loan;
    }

    public Date getDate_prestamo() {
        return date_prestamo;
    }

    public void setDate_prestamo(Date date_prestamo) {
        this.date_prestamo = date_prestamo;
    }

    public Date getDate_devolucion() {
        return date_devolucion;
    }

    public void setDate_devolucion(Date date_devolucion) {
        this.date_devolucion = date_devolucion;
    }

    public char getState() {
        return state;
    }

    public void setState(char state) {
        this.state = state;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public CopyBook getCopyBook() {
        return copyBook;
    }

    public void setCopyBook(CopyBook copyBook) {
        this.copyBook = copyBook;
    }
    
    
    
}
