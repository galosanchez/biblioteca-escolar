package com.galosanchez.domain;

import java.sql.Timestamp;
import java.util.Objects;

/**
 *
 * @author Galo Sánchez
 */
public class User {

    private int id_user;
    private String user;
    private String password;
    private String name;
    private String email;
    private Timestamp createdAt;
    private char type;

    public User() {

    }

    public User(String user, String password) {
        this.user = user;
        this.password = password;
    }

    public User(int id_user) {
        this.id_user = id_user;
    }

    public User(int id_user, String user) {
        this.id_user = id_user;
        this.user = user;
    }

    public User(int id_user, String user, String password, String name, String email, Timestamp created_at) {
        this.id_user = id_user;
        this.user = user;
        this.password = password;
        this.name = name;
        this.email = email;
        this.createdAt = created_at;
    }

    public User(int id_user, String user, String name, String email, Timestamp created_at, char type) {
        this.id_user = id_user;
        this.user = user;
        this.name = name;
        this.email = email;
        this.createdAt = created_at;
        this.type = type;
    }

    public int getId_user() {
        return id_user;
    }

    public void setId_user(int id_user) {
        this.id_user = id_user;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Timestamp getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Timestamp createdAt) {
        this.createdAt = createdAt;
    }

    public char getType() {
        return type;
    }

    public void setType(char type) {
        this.type = type;
    }
    

    @Override
    public int hashCode() {
        int hash = 5;
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
        final User other = (User) obj;
        if (this.id_user != other.id_user) {
            return false;
        }
        if (!Objects.equals(this.user, other.user)) {
            return false;
        }
        if (!Objects.equals(this.password, other.password)) {
            return false;
        }
        if (!Objects.equals(this.name, other.name)) {
            return false;
        }
        if (!Objects.equals(this.email, other.email)) {
            return false;
        }
        if (!Objects.equals(this.createdAt, other.createdAt)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Bibliotecario[" + "id=" + id_user + ", user=" + user + ", password=" + password + ", name=" + name + ", email=" + email + ", created_at=" + createdAt + ']';
    }

}
