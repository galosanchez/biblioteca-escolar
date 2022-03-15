package com.galosanchez.controller;

import com.galosanchez.domain.Student;
import com.galosanchez.model.SqlStudent;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * EClase que permite obtener y asignar datos de estudiantes
 * @author Galo Sánchez
 */
public class StudentController {

    /**
     * Este método insertara un nuevo estudiante
     *
     * @param student
     * @return
     * @throws java.sql.SQLException
     */
    public static int createStudent(Student student) throws SQLException {
        SqlStudent sqlStudent = new SqlStudent();
        return sqlStudent.insertStudent(student);
    }
    

    /**
     * Este método verifica si existe un estudiante con una cedula que pasacomo
     * parámetro
     *
     * @param cedula
     * @return
     */
    public static int existStudentCedula(String cedula) {
        SqlStudent sqlStudent = new SqlStudent();
        return sqlStudent.existCedula(cedula);
    }
    /**
     * Este método obtiene un estudiante con una cedula que pasacomo
     * parámetro
     *
     * @param cedula
     * @return
     */
    public static Student getStudentCedula(String cedula) {
        SqlStudent sqlStudent = new SqlStudent();
        return sqlStudent.getStudentCedula(cedula);
    }

    /**
     * Este método verifica si existe un estudiante con un correo que pasa como
     * parámetro
     *
     * @param email
     * @return
     */
    public static int existStudentEmail(String email) {
        SqlStudent sqlStudent = new SqlStudent();
        return sqlStudent.existEmail(email);
    }
    
    /**
     * Este método devuelve una lista con todos los estudiantes
     * @return 
     */
    public static ArrayList<Student> listAllStudents(){
        SqlStudent sqlStudent = new SqlStudent();
        return sqlStudent.listStudents();
    }
    
    /**
     * Este método devuelve la lista con los resultados de la busqueda dependiendo el tipo
     * cedula, nombre, apellido, correo electronico
     * @param tipo
     * @param cadena
     * @return 
     */
    public static ArrayList<Student> searchStudents(String tipo, String cadena){
        SqlStudent sqlStudent = new SqlStudent();
        ArrayList<Student> lista = new ArrayList<>();
        switch (tipo) {
            case "general":
                lista = sqlStudent.searchCedula(cadena);
                break;
            case "name":
                lista = sqlStudent.searchName(cadena);
                break;
            case "lastName":
                lista = sqlStudent.searchLastName(cadena);
                break;
            case "email":
                lista = sqlStudent.searchEmail(cadena);
                break;
            default:
        }
        return lista;
        
    }
    
    /**
     * Este método edita la información de un estudiante
     * @param student
     * @return 
     */
    public static int editStudent(Student student){
        SqlStudent sqlStudent = new SqlStudent();
        return sqlStudent.editStudent(student);
    }
}
