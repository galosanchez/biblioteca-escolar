package com.galosanchez.domain;

import java.util.Objects;

/**
 *
 * @author Galo Sánchez
 */
public class Author {
    private int id_author;
    private String name;
    
    public Author(){
    }

    public Author(int id_author, String name) {
        this.id_author = id_author;
        this.name = name;
    }

    public int getId_author() {
        return id_author;
    }

    public void setId_author(int id_author) {
        this.id_author = id_author;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Author other = (Author) obj;
        if (this.id_author != other.id_author) {
            return false;
        }
        return Objects.equals(this.name, other.name);
    }
    
    @Override
    public String toString() {
        return name;
    }

    public String toStringAll() {
        return "Author{" + "id_author=" + id_author + ", name=" + name + '}';
    }

}
