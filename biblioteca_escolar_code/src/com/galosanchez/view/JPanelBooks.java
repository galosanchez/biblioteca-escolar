package com.galosanchez.view;

import com.galosanchez.controller.BookController;
import com.galosanchez.domain.Book;
import java.awt.Color;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import javax.swing.JFrame;
import javax.swing.JTable;
import javax.swing.table.JTableHeader;

/**
 * Esta clase crea el panel de administración de libros
 * @author Galo Sánchez
 */
public class JPanelBooks extends javax.swing.JPanel implements MouseListener {

    private final JFrame jFrameParent;
    private JTable jTable;
    private TablaModelo modelo;//modelo definido en la clase ModeloTabla
    private String search;
    private final String titulos[] = {"","Título", "Autor", "Editorial", "Año", "Cantidad", " "}; // Títilos de la tabla

    public JPanelBooks(JFrame jFrameParent) {
        this.jFrameParent = jFrameParent;
        initComponents();
        jButtonSearch.requestFocus();
        search = "title";
        /* Crear la tabla */
        newTable();
    }

    /**
     * Método qie inicializa la creación de la tabla books
     */
    private void newTable() {
        jTable = new JTable();
        jTable.setBackground(Color.white);

        jTable.setBorder(null);
        jTable.addMouseListener(this);
        //tablaSeguimiento.addKeyListener(this);
        jTable.setOpaque(false);
        jScrollPane1.getViewport().setBackground(new java.awt.Color(245, 245, 245));

        jScrollPane1.setViewportView(jTable);

//        jTable.setFillsViewportHeight(true);
        Object[][] data = obtenerMatrizDatos(titulos.length, consultarListaLibros());
        construirTabla(titulos, data);
    }

    /**
     * Método que construye la tabla
     * @param titulos
     * @param data 
     */
    private void construirTabla(String[] titulos, Object[][] data) {
        modelo = new TablaModelo(data, titulos);
        //Asigna el modelo a la tabla
        jTable.setModel(modelo);

        //Asigna el tipo de dato que tendrán las celdas de cada columna definida respectivamente para validar su personalización
        jTable.getColumnModel().getColumn(0).setCellRenderer(new TableGestionCeldas("texto"));
        jTable.getColumnModel().getColumn(1).setCellRenderer(new TableGestionCeldas("texto"));
        jTable.getColumnModel().getColumn(2).setCellRenderer(new TableGestionCeldas("texto"));
        jTable.getColumnModel().getColumn(3).setCellRenderer(new TableGestionCeldas("texto"));
        jTable.getColumnModel().getColumn(4).setCellRenderer(new TableGestionCeldas("texto"));
        jTable.getColumnModel().getColumn(5).setCellRenderer(new TableGestionCeldas("texto"));
        jTable.getColumnModel().getColumn(6).setCellRenderer(new TableGestionCeldas("icono"));

        jTable.getTableHeader().setReorderingAllowed(false);
        jTable.setRowHeight(30);//tamaño de las celdas
        jTable.setGridColor(new java.awt.Color(158, 158, 158));
        jTable.setShowVerticalLines(false);

        //Define el tamaño de largo para cada columna y su contenido
        jTable.getColumnModel().getColumn(0).setPreferredWidth(10);
        jTable.getColumnModel().getColumn(1).setPreferredWidth(339);
        jTable.getColumnModel().getColumn(2).setPreferredWidth(150);
        jTable.getColumnModel().getColumn(3).setPreferredWidth(200);
        jTable.getColumnModel().getColumn(4).setPreferredWidth(70);
        jTable.getColumnModel().getColumn(5).setPreferredWidth(80);
        jTable.getColumnModel().getColumn(6).setPreferredWidth(30);

        //personaliza el encabezado
        JTableHeader jtableHeader = jTable.getTableHeader();
        jtableHeader.setDefaultRenderer(new TableGestionEncabezado());
        jTable.setTableHeader(jtableHeader);

        //se asigna la tabla al scrollPane
        jScrollPane1.setViewportView(jTable);

    }

    /**
     * se crea la matriz donde las filas son dinamicas pues corresponde
     * a todos los usuarios, mientras que las columnas son estaticas
     * correspondiendo a las columnas definidas por defecto
     * @param sizeTitles
     * @param listaPersonas
     * @return 
     */
    private Object[][] obtenerMatrizDatos(int sizeTitles, ArrayList<Book> listaPersonas) {
        String informacion[][] = new String[listaPersonas.size()][sizeTitles];

        for (int x = 0; x < informacion.length; x++) {
            informacion[x][0] = "";
            informacion[x][1] = listaPersonas.get(x).getTitle()+ "";
            informacion[x][2] = listaPersonas.get(x).getAuthor().getName()+ "";
            informacion[x][3] = listaPersonas.get(x).getEditorial()+ "";
            informacion[x][4] = listaPersonas.get(x).getYear()+ "";
            informacion[x][5] = listaPersonas.get(x).getAuthor().getId_author()+ "";
            informacion[x][6] = "EDIT";
        }

        return informacion;
    }

    /**
     * Se obtiene la lista de libros
     * @return 
     */
    private ArrayList<Book> consultarListaLibros() {
        try {
            return BookController.queryAllBooks();
        } catch (Exception e) {
            if (e.getLocalizedMessage().equals("3011")) {
                openDialogAlert("danger", "No se puede establecer conexión con el", "servidor");
            } else {
                openDialogAlert("danger", "Error al momento de consultar al servidor", "");
            }
        }
        return new ArrayList<>();
    }

    /* Métodos para crear un alert para lanzar un mensaje */
    private void openDialogAlert(String tipo, String message1, String message2) {
        DialogAlert dialogAlert = new DialogAlert(jFrameParent, true, tipo, message1, message2);
        dialogAlert.setVisible(true);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jPanelFocus = new javax.swing.JPanel();
        jPanelHorizontal = new javax.swing.JPanel();
        jLabelAuthor = new javax.swing.JLabel();
        jLabelTitle = new javax.swing.JLabel();
        jPanelSearch = new javax.swing.JPanel();
        jTextFieldSearch = new javax.swing.JTextField();
        jPanelAddBook = new javax.swing.JPanel();
        jLabelAddBook = new javax.swing.JLabel();
        jPanelButtonSearch = new javax.swing.JPanel();
        jButtonSearch = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jLabelLastEditorial = new javax.swing.JLabel();
        jLabelYear = new javax.swing.JLabel();
        jPanelAddAuthor = new javax.swing.JPanel();
        jLabelAddAuthor = new javax.swing.JLabel();

        setBackground(new java.awt.Color(245, 245, 245));
        setMinimumSize(new java.awt.Dimension(0, 0));
        setPreferredSize(new java.awt.Dimension(919, 586));
        setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel1.setBackground(new java.awt.Color(245, 245, 245));
        jPanel1.setMinimumSize(new java.awt.Dimension(0, 0));
        jPanel1.setPreferredSize(new java.awt.Dimension(874, 568));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setFont(new java.awt.Font("Hind Siliguri Medium", 0, 24)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(33, 33, 33));
        jLabel1.setText("Control de libros");
        jPanel1.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 870, 24));

        jPanelFocus.setBackground(new java.awt.Color(123, 31, 162));

        javax.swing.GroupLayout jPanelFocusLayout = new javax.swing.GroupLayout(jPanelFocus);
        jPanelFocus.setLayout(jPanelFocusLayout);
        jPanelFocusLayout.setHorizontalGroup(
            jPanelFocusLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 40, Short.MAX_VALUE)
        );
        jPanelFocusLayout.setVerticalGroup(
            jPanelFocusLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 3, Short.MAX_VALUE)
        );

        jPanel1.add(jPanelFocus, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 61, 40, 3));

        jPanelHorizontal.setBackground(new java.awt.Color(158, 158, 158));

        javax.swing.GroupLayout jPanelHorizontalLayout = new javax.swing.GroupLayout(jPanelHorizontal);
        jPanelHorizontal.setLayout(jPanelHorizontalLayout);
        jPanelHorizontalLayout.setHorizontalGroup(
            jPanelHorizontalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 879, Short.MAX_VALUE)
        );
        jPanelHorizontalLayout.setVerticalGroup(
            jPanelHorizontalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1, Short.MAX_VALUE)
        );

        jPanel1.add(jPanelHorizontal, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 63, 879, 1));

        jLabelAuthor.setFont(new java.awt.Font("Hind Siliguri SemiBold", 0, 16)); // NOI18N
        jLabelAuthor.setForeground(new java.awt.Color(117, 117, 117));
        jLabelAuthor.setText("Autor");
        jLabelAuthor.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        jLabelAuthor.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabelAuthorMouseClicked(evt);
            }
        });
        jPanel1.add(jLabelAuthor, new org.netbeans.lib.awtextra.AbsoluteConstraints(56, 35, 40, 25));

        jLabelTitle.setFont(new java.awt.Font("Hind Siliguri SemiBold", 0, 16)); // NOI18N
        jLabelTitle.setForeground(new java.awt.Color(123, 31, 162));
        jLabelTitle.setText("Título");
        jLabelTitle.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        jLabelTitle.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabelTitleMouseClicked(evt);
            }
        });
        jPanel1.add(jLabelTitle, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 35, 40, 25));

        jPanelSearch.setBackground(new java.awt.Color(227, 213, 233));
        jPanelSearch.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jTextFieldSearch.setBackground(new java.awt.Color(227, 213, 233));
        jTextFieldSearch.setFont(new java.awt.Font("Hind Siliguri", 0, 14)); // NOI18N
        jTextFieldSearch.setForeground(new java.awt.Color(158, 158, 158));
        jTextFieldSearch.setText("Buscar por título");
        jTextFieldSearch.setBorder(null);
        jTextFieldSearch.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                jTextFieldSearchFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                jTextFieldSearchFocusLost(evt);
            }
        });
        jPanelSearch.add(jTextFieldSearch, new org.netbeans.lib.awtextra.AbsoluteConstraints(16, 0, 570, 32));

        jPanel1.add(jPanelSearch, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 84, 597, 32));

        jPanelAddBook.setBackground(new java.awt.Color(156, 39, 176));

        jLabelAddBook.setFont(new java.awt.Font("Hind Siliguri Medium", 0, 14)); // NOI18N
        jLabelAddBook.setForeground(new java.awt.Color(255, 255, 255));
        jLabelAddBook.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabelAddBook.setText("Agregar libro");
        jLabelAddBook.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabelAddBookMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jLabelAddBookMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jLabelAddBookMouseExited(evt);
            }
        });

        javax.swing.GroupLayout jPanelAddBookLayout = new javax.swing.GroupLayout(jPanelAddBook);
        jPanelAddBook.setLayout(jPanelAddBookLayout);
        jPanelAddBookLayout.setHorizontalGroup(
            jPanelAddBookLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabelAddBook, javax.swing.GroupLayout.DEFAULT_SIZE, 112, Short.MAX_VALUE)
        );
        jPanelAddBookLayout.setVerticalGroup(
            jPanelAddBookLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabelAddBook, javax.swing.GroupLayout.DEFAULT_SIZE, 32, Short.MAX_VALUE)
        );

        jPanel1.add(jPanelAddBook, new org.netbeans.lib.awtextra.AbsoluteConstraints(767, 84, 112, 32));

        jPanelButtonSearch.setBackground(new java.awt.Color(156, 39, 176));
        jPanelButtonSearch.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jButtonSearch.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jButtonSearch.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/galosanchez/images/ic_search.png"))); // NOI18N
        jButtonSearch.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jButtonSearchMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jButtonSearchMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jButtonSearchMouseExited(evt);
            }
        });
        jPanelButtonSearch.add(jButtonSearch, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 32, 32));

        jPanel1.add(jPanelButtonSearch, new org.netbeans.lib.awtextra.AbsoluteConstraints(597, 84, 32, 32));

        jScrollPane1.setBackground(new java.awt.Color(245, 245, 245));
        jScrollPane1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(245, 245, 245), 0));
        jScrollPane1.setForeground(new java.awt.Color(51, 51, 51));
        jScrollPane1.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        jPanel1.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 134, 879, 435));

        jLabelLastEditorial.setFont(new java.awt.Font("Hind Siliguri SemiBold", 0, 16)); // NOI18N
        jLabelLastEditorial.setForeground(new java.awt.Color(117, 117, 117));
        jLabelLastEditorial.setText("Editorial");
        jLabelLastEditorial.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        jLabelLastEditorial.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabelLastEditorialMouseClicked(evt);
            }
        });
        jPanel1.add(jLabelLastEditorial, new org.netbeans.lib.awtextra.AbsoluteConstraints(112, 35, 65, 25));

        jLabelYear.setFont(new java.awt.Font("Hind Siliguri SemiBold", 0, 16)); // NOI18N
        jLabelYear.setForeground(new java.awt.Color(117, 117, 117));
        jLabelYear.setText("Año");
        jLabelYear.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        jLabelYear.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabelYearMouseClicked(evt);
            }
        });
        jPanel1.add(jLabelYear, new org.netbeans.lib.awtextra.AbsoluteConstraints(193, 35, 35, 25));

        jPanelAddAuthor.setBackground(new java.awt.Color(156, 39, 176));

        jLabelAddAuthor.setFont(new java.awt.Font("Hind Siliguri Medium", 0, 14)); // NOI18N
        jLabelAddAuthor.setForeground(new java.awt.Color(255, 255, 255));
        jLabelAddAuthor.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabelAddAuthor.setText("Nuevo autor");
        jLabelAddAuthor.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabelAddAuthorMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jLabelAddAuthorMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jLabelAddAuthorMouseExited(evt);
            }
        });

        javax.swing.GroupLayout jPanelAddAuthorLayout = new javax.swing.GroupLayout(jPanelAddAuthor);
        jPanelAddAuthor.setLayout(jPanelAddAuthorLayout);
        jPanelAddAuthorLayout.setHorizontalGroup(
            jPanelAddAuthorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabelAddAuthor, javax.swing.GroupLayout.DEFAULT_SIZE, 106, Short.MAX_VALUE)
        );
        jPanelAddAuthorLayout.setVerticalGroup(
            jPanelAddAuthorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabelAddAuthor, javax.swing.GroupLayout.DEFAULT_SIZE, 32, Short.MAX_VALUE)
        );

        jPanel1.add(jPanelAddAuthor, new org.netbeans.lib.awtextra.AbsoluteConstraints(645, 84, 106, 32));

        add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 16, 879, 570));
    }// </editor-fold>//GEN-END:initComponents

    /* Eventos buttons (Entered-Exited) */
    private void jButtonSearchMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButtonSearchMouseEntered
        jPanelButtonSearch.setBackground(new java.awt.Color(123, 31, 162));
    }//GEN-LAST:event_jButtonSearchMouseEntered

    private void jButtonSearchMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButtonSearchMouseExited
        jPanelButtonSearch.setBackground(new java.awt.Color(156, 39, 176));
    }//GEN-LAST:event_jButtonSearchMouseExited

    private void jLabelAddBookMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabelAddBookMouseEntered
        jPanelAddBook.setBackground(new java.awt.Color(123, 31, 162));
    }//GEN-LAST:event_jLabelAddBookMouseEntered

    private void jLabelAddBookMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabelAddBookMouseExited
        jPanelAddBook.setBackground(new java.awt.Color(156, 39, 176));
    }//GEN-LAST:event_jLabelAddBookMouseExited

    private void jLabelAddBookMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabelAddBookMouseClicked
        DialogNewBook jdialog = new DialogNewBook(jFrameParent, true);
        jdialog.setVisible(true);
    }//GEN-LAST:event_jLabelAddBookMouseClicked

    private void jLabelTitleMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabelTitleMouseClicked
        String newSearch = "title";
        if (!search.equals(newSearch)) {
            previousSearch(search);
            nextSearch(newSearch);
            search = newSearch;
        }
    }//GEN-LAST:event_jLabelTitleMouseClicked

    private void jLabelAuthorMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabelAuthorMouseClicked
        String newSearch = "author";
        if (!search.equals(newSearch)) {
            previousSearch(search);
            nextSearch(newSearch);
            search = newSearch;
        }
    }//GEN-LAST:event_jLabelAuthorMouseClicked

    private void jLabelLastEditorialMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabelLastEditorialMouseClicked
        String newSearch = "editorial";
        if (!search.equals(newSearch)) {
            previousSearch(search);
            nextSearch(newSearch);
            search = newSearch;
        }
    }//GEN-LAST:event_jLabelLastEditorialMouseClicked

    private void jLabelYearMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabelYearMouseClicked
        String newSearch = "year";
        if (!search.equals(newSearch)) {
            previousSearch(search);
            nextSearch(newSearch);
            search = newSearch;
        }
    }//GEN-LAST:event_jLabelYearMouseClicked

    private void nextSearch(String search) {
        java.awt.Color color = new java.awt.Color(123, 31, 162);
        int ejeX = 0;
        int width = 0;
        String tipoBuqueda = "";
        switch (search) {
            case "title":
                jLabelTitle.setForeground(color);
                ejeX = 0;
                width = 40;
                tipoBuqueda = "título";
                break;
            case "author":
                jLabelAuthor.setForeground(color);
                ejeX = 56;
                width = 40;
                tipoBuqueda = "autor";
                break;
            case "editorial":
                jLabelLastEditorial.setForeground(color);
                ejeX = 112;
                width = 65;
                tipoBuqueda = "editorial";
                break;
            case "year":
                jLabelYear.setForeground(color);
                ejeX = 193;
                width = 35;
                tipoBuqueda = "año";
                break;
            default:
        }
        jPanelFocus.setLocation(ejeX, 61);
        jPanelFocus.setSize(width, 3);
        if (jTextFieldSearch.getText().length() < 1) {
            jTextFieldSearch.setText("Buscar por " + tipoBuqueda);
            jTextFieldSearch.setForeground(new java.awt.Color(158, 158, 158));
        }
    }

    private void previousSearch(String search) {
        java.awt.Color color = new java.awt.Color(117, 117, 117);
        String tipoBuqueda = "";
        switch (search) {
            case "title":
                jLabelTitle.setForeground(color);
                tipoBuqueda = "título";
                break;
            case "author":
                jLabelAuthor.setForeground(color);
                tipoBuqueda = "autor";
                break;
            case "editorial":
                jLabelLastEditorial.setForeground(color);
                tipoBuqueda = "editorial";
                break;
            case "year":
                jLabelYear.setForeground(color);
                tipoBuqueda = "año";
                break;
            default:
        }
        if (jTextFieldSearch.getText().equals("Buscar por " + tipoBuqueda)) {
            jTextFieldSearch.setText("");
        }
        jButtonSearch.requestFocus();
    }

    private void jTextFieldSearchFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jTextFieldSearchFocusGained
        String tipoBuqueda = "";
        switch (search) {
            case "title":
                tipoBuqueda = "título";
                break;
            case "author":
                tipoBuqueda = "autor";
                break;
            case "editorial":
                tipoBuqueda = "editorial";
                break;
            case "year":
                tipoBuqueda = "año";
                break;
            default:
        }
        if (jTextFieldSearch.getText().equals("Buscar por " + tipoBuqueda)) {
            jTextFieldSearch.setText("");
            jTextFieldSearch.setForeground(new java.awt.Color(33, 33, 33));
        }
    }//GEN-LAST:event_jTextFieldSearchFocusGained

    private void jTextFieldSearchFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jTextFieldSearchFocusLost
        if (jTextFieldSearch.getText().length() > 0) {
            return;
        }
        String tipoBuqueda = "";
        switch (search) {
            case "title":
                tipoBuqueda = "título";
                break;
            case "author":
                tipoBuqueda = "autor";
                break;
            case "editorial":
                tipoBuqueda = "editorial";
                break;
            case "year":
                tipoBuqueda = "año";
                break;
            default:
        }
        jTextFieldSearch.setText("Buscar por " + tipoBuqueda);
        jTextFieldSearch.setForeground(new java.awt.Color(158, 158, 158));
    }//GEN-LAST:event_jTextFieldSearchFocusLost

    private void jButtonSearchMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButtonSearchMouseClicked
        if (jTextFieldSearch.getText().length() < 1) {
            return;
        }
        String tipoBuqueda = "";
        switch (search) {
            case "title":
                tipoBuqueda = "título";
                break;
            case "author":
                tipoBuqueda = "autor";
                break;
            case "editorial":
                tipoBuqueda = "editorial";
                break;
            case "year":
                tipoBuqueda = "año";
                break;
            default:
        }
        if (!jTextFieldSearch.getText().equals("Buscar por " + tipoBuqueda)) {
            busqueda(jTextFieldSearch.getText());
//            System.out.println("Busqueda");
        }
    }//GEN-LAST:event_jButtonSearchMouseClicked

    private void jLabelAddAuthorMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabelAddAuthorMouseClicked
        DialogNewAuthor jdialog = new DialogNewAuthor(jFrameParent, true);
        jdialog.setVisible(true);
    }//GEN-LAST:event_jLabelAddAuthorMouseClicked

    private void jLabelAddAuthorMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabelAddAuthorMouseEntered
        jPanelAddAuthor.setBackground(new java.awt.Color(123, 31, 162));
    }//GEN-LAST:event_jLabelAddAuthorMouseEntered

    private void jLabelAddAuthorMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabelAddAuthorMouseExited
        jPanelAddAuthor.setBackground(new java.awt.Color(156, 39, 176));
    }//GEN-LAST:event_jLabelAddAuthorMouseExited

    private void busqueda(String cadena) {
        ArrayList<Book> lista;
        try {
            lista = BookController.searchBook(search, cadena);
            Object[][] data = obtenerMatrizDatos(titulos.length, lista);
            construirTabla(titulos, data);
        } catch (Exception e) {
            if (e.getLocalizedMessage().equals("3011")) {
                openDialogAlert("danger", "No se puede establecer conexión con el", "servidor");
            } else {
                openDialogAlert("danger", "Error al momento de consultar al servidor", "");
            }
        }
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jButtonSearch;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabelAddAuthor;
    private javax.swing.JLabel jLabelAddBook;
    private javax.swing.JLabel jLabelAuthor;
    private javax.swing.JLabel jLabelLastEditorial;
    private javax.swing.JLabel jLabelTitle;
    private javax.swing.JLabel jLabelYear;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanelAddAuthor;
    private javax.swing.JPanel jPanelAddBook;
    private javax.swing.JPanel jPanelButtonSearch;
    private javax.swing.JPanel jPanelFocus;
    private javax.swing.JPanel jPanelHorizontal;
    private javax.swing.JPanel jPanelSearch;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextField jTextFieldSearch;
    // End of variables declaration//GEN-END:variables

    @Override
    public void mouseClicked(MouseEvent e) {

        
    }

    @Override
    public void mousePressed(MouseEvent e) {
    }

    @Override
    public void mouseReleased(MouseEvent e) {
    }

    @Override
    public void mouseEntered(MouseEvent e) {
    }

    @Override
    public void mouseExited(MouseEvent e) {
    }

}
