package com.galosanchez.domain;

/**
 *
 * @author Galo
 */
public class Book {
    
    private int id_book;
    private String editorial;
    private String title;
    private String year;
    private Author author;
    
    public Book(){
        
    }

    public Book(int id_book, String editorial, String title, String year, Author author) {
        this.id_book = id_book;
        this.editorial = editorial;
        this.title = title;
        this.year = year;
        this.author = author;
    }

    public Book(String title) {
        this.title = title;
    }

    public Book(int id_book) {
        this.id_book = id_book;
    }

    public int getId_book() {
        return id_book;
    }

    public void setId_book(int id_book) {
        this.id_book = id_book;
    }

    public String getEditorial() {
        return editorial;
    }

    public void setEditorial(String editorial) {
        this.editorial = editorial;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public Author getAuthor() {
        return author;
    }

    public void setAuthor(Author author) {
        this.author = author;
    }

    @Override
    public String toString() {
        return  title;
    }
   
    public String toStringAll(){
        return "Book{" + "id_book=" + id_book + ", editorial=" + editorial + ", title=" + title + ", year=" + year + ", author=" + author.toStringAll() + '}';
    }
    
}
