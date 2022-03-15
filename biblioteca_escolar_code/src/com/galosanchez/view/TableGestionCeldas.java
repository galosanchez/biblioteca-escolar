package com.galosanchez.view;

import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;

/**
 *
 * @author Galo Sánchez
 */
public class TableGestionCeldas extends DefaultTableCellRenderer {

    private String tipo = "text";

    //se definen por defecto los tipos de datos a usar
    private final Font normal = new Font("Hind Siliguri", Font.PLAIN, 14);
    private final Font bold = new Font("Hind Siliguri", Font.PLAIN, 14);
    //etiqueta que almacenará el icono a mostrar
    private final JLabel label = new JLabel();
    //iconos disponibles para ser mostrados en la etiqueta dependiendo de la columna que lo contenga
    private final ImageIcon iconoEdit = new ImageIcon(getClass().getResource("/com/galosanchez/images/ic_edit.png"));
    private final ImageIcon iconoBuscar = new ImageIcon(getClass().getResource("/com/galosanchez/images/ic_search.png"));

    public TableGestionCeldas() {

    }

    /**
     * Constructor explicito con el tipo de dato que tendr� la celda
     *
     * @param tipo
     */
    public TableGestionCeldas(String tipo) {
        this.tipo = tipo;
    }

    @Override
    public Component getTableCellRendererComponent(JTable table, Object value, boolean selected, boolean focused, int row, int column) {

        Color colorFondo = null;
        Color colorFondoPorDefecto = new Color(224, 224, 224);
        Color colorFondoSeleccion = new Color(189, 189, 189);

        if (selected) {
            this.setBackground(colorFondoPorDefecto);
        } else {
            //Para las que no est�n seleccionadas se pinta el fondo de las celdas de blanco
            this.setBackground(colorFondoPorDefecto);
        }

        /*
         * Se definen los tipos de datos que contendr�n las celdas basado en la instancia que
         * se hace en la ventana de la tabla al momento de construirla
         */
        if (tipo.equals("texto")) {
            //si es tipo texto define el color de fondo del texto y de la celda as� como la alineaci�n
            if (focused) {
                colorFondo = colorFondoSeleccion;
            } else {
                colorFondo = colorFondoPorDefecto;
            }
            this.setHorizontalAlignment(JLabel.LEFT);
            this.setText((String) value);
            //this.setForeground( (selected)? new Color(255,255,255) :new Color(0,0,0) );   
            //this.setForeground( (selected)? new Color(255,255,255) :new Color(32,117,32) );
            this.setBackground((selected) ? colorFondo : new Color(245, 245, 245));
            this.setFont(normal);
            this.setForeground(new Color(33, 33, 33));
            //this.setFont(bold);
            return this;
        }

        //si el tipo es icono entonces valida cual icono asignar a la etiqueta.
        if (tipo.equals("icono")) {
            if (String.valueOf(value).equals("EDIT")) {
                label.setIcon(iconoEdit);
            } else if (String.valueOf(value).equals("EVENTO")) {
                label.setIcon(iconoBuscar);
            }
            label.setHorizontalAlignment(JLabel.CENTER);
            label.setBorder(null);
//            return boton;
            return label;
        }

        //definie si el tipo de dato el numerico para personalizarlo
        if (tipo.equals("numerico")) {
            if (focused) {
                colorFondo = colorFondoSeleccion;
            } else {
                colorFondo = colorFondoPorDefecto;
            }
            // System.out.println(value);
            this.setHorizontalAlignment(JLabel.CENTER);
            this.setText((String) value);
            this.setForeground((selected) ? new Color(255, 255, 255) : new Color(32, 117, 32));
            this.setBackground((selected) ? colorFondo : Color.WHITE);
            // this.setBackground( (selected)? colorFondo :Color.MAGENTA);
            this.setFont(bold);
            return this;
        }

        return this;

    }
}
