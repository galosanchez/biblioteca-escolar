package com.galosanchez.domain;

/**
 *
 * @author Galo
 */
public class CopyBook {
    private int id;
    private String code;
    private String state;
    private String ative;
    private Book book;
    
    
    public CopyBook(){
        
    }

    public CopyBook(int id, String code, String state, Book book) {
        this.id = id;
        this.code = code;
        this.state = state;
        this.book = book;
    }

    public CopyBook(int id, String code, Book book) {
        this.id = id;
        this.code = code;
        this.book = book;
    }

    public String getAtive() {
        return ative;
    }

    public void setAtive(String ative) {
        this.ative = ative;
    }
    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }

    @Override
    public String toString() {
        return "CopyBook{" + "id=" + id + ", code=" + code + ", state=" + state + ", book=" + book.toStringAll() + '}';
    }
    
}
