package com.galosanchez.domain;

import java.sql.Timestamp;
import java.util.Objects;

/**
 *
 * @author Galo Sánchez
 */
public class Student {
    private int id_student;
    private String cedula;
    private String name;
    private String lastName;
    private String phone;
    private String email;
    private Timestamp createdAt;

    public Student (){
    }

    public Student( String cedula, String name, String lastName, String phone, String email, Timestamp createdAt) {
        this.cedula = cedula;
        this.name = name;
        this.lastName = lastName;
        this.phone = phone;
        this.email = email;
        this.createdAt = createdAt;
    }

    public Student(int id, String cedula, String name, String lastName, String phone, String email, Timestamp createdAt) {
        this.id_student = id;
        this.cedula = cedula;
        this.name = name;
        this.lastName = lastName;
        this.phone = phone;
        this.email = email;
        this.createdAt = createdAt;
    }

    public Student(int id, String cedula, String name, String lastName, String phone, String email) {
        this.id_student = id;
        this.cedula = cedula;
        this.name = name;
        this.lastName = lastName;
        this.phone = phone;
        this.email = email;
    }

    public Student(int id_student, String cedula, String name, String lastName) {
        this.id_student = id_student;
        this.cedula = cedula;
        this.name = name;
        this.lastName = lastName;
    }
    
    public int getId_student() {
        return id_student;
    }

    public void setId_student(int id_student) {
        this.id_student = id_student;
    }

    public String getCedula() {
        return cedula;
    }

    public void setCedula(String cedula) {
        this.cedula = cedula;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
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
        final Student other = (Student) obj;
        if (this.id_student != other.id_student) {
            return false;
        }
        if (!Objects.equals(this.cedula, other.cedula)) {
            return false;
        }
        if (!Objects.equals(this.name, other.name)) {
            return false;
        }
        if (!Objects.equals(this.lastName, other.lastName)) {
            return false;
        }
        if (!Objects.equals(this.phone, other.phone)) {
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
        return "Student{" + "id=" + id_student + ", cedula=" + cedula + ", name=" + name + ", last_name=" + lastName + ", phone=" + phone + ", email=" + email + ", created_at=" + createdAt + '}';
    }
    
}
