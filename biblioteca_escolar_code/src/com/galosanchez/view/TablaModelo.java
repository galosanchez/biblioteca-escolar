package com.galosanchez.view;

import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Galo Sánchez
 */
public class TablaModelo extends DefaultTableModel {

    String[] titulos;
    Object[][] datos;

    /**
     * Determina el modelo con el que se va a construir la tabla
     *
     * @param datos
     * @param titulos
     */
    public TablaModelo(Object[][] datos, String[] titulos) {
        super();
        this.titulos = titulos;
        this.datos = datos;
        setDataVector(datos, titulos);
    }

    public TablaModelo() {

    }

}
