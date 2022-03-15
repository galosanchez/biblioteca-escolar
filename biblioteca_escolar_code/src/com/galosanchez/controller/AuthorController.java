package com.galosanchez.controller;

import com.galosanchez.domain.Author;
import com.galosanchez.model.SqlAuthor;
import java.util.ArrayList;

/**
 * Clase que permite obtener y asignar datos de autores
 * @author Galo Sánchez
 */
public class AuthorController {

    /**
     * Este método permite crear un autor
     * @param author
     * @return 
     */
    public static int createAuthor(Author author) {
        SqlAuthor sqlAuthor = new SqlAuthor();
        return sqlAuthor.insertAuthor(author);
    }

    /**
     * Este método permite obtener toda la lista de autores
     * @return 
     */
    public static ArrayList<Author> authorList() {
        SqlAuthor sqlAuthor = new SqlAuthor();
        return sqlAuthor.selectAllAuthors();
    }
    
}
