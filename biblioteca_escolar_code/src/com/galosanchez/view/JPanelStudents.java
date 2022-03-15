package com.galosanchez.view;

import com.galosanchez.controller.StudentController;
import com.galosanchez.domain.Student;
import java.awt.Color;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import javax.swing.JFrame;
import javax.swing.JTable;
import javax.swing.table.JTableHeader;

/**
 * Esta clase crea el panel de Administración de estudiantes
 * @author Galo Sánchez
 */
public class JPanelStudents extends javax.swing.JPanel implements MouseListener {

    private final JFrame jFrameParent;
    private JTable jTable;
    private TablaModelo modelo;//modelo definido en la clase ModeloTabla
    private String search;
    private final String titulos[] = {"","Cédula", "Nombres", "Apellidos", "Teléfono", "Correo", " "};

    public JPanelStudents(JFrame jFrameParent) {
        this.jFrameParent = jFrameParent;
        initComponents();
        jButtonSearch.requestFocus();
        search = "general";
        newTable();
    }

    private void newTable() {
        jTable = new JTable();
        jTable.setBackground(Color.white);

        jTable.setBorder(null);
        jTable.addMouseListener(this);
        jTable.setOpaque(false);
        jScrollPane1.getViewport().setBackground(new java.awt.Color(245, 245, 245));

        jScrollPane1.setViewportView(jTable);

        Object[][] data = obtenerMatrizDatos(titulos.length, consultarListaEstudiantes());
        construirTabla(titulos, data);
    }

    private void construirTabla(String[] titulos, Object[][] data) {
        modelo = new TablaModelo(data, titulos);
        //se asigna el modelo a la tabla
        jTable.setModel(modelo);

        //se asigna el tipo de dato que tendrán las celdas de cada columna definida respectivamente para validar su personalización
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
//        jTable.setShowGrid(false);
//        jTable.setRowMargin(2); 
        jTable.setShowVerticalLines(false);
//        jTable.setShowHorizontalLines(false);

        //Se define el tamaño de largo para cada columna y su contenido
        jTable.getColumnModel().getColumn(0).setPreferredWidth(10);
        jTable.getColumnModel().getColumn(1).setPreferredWidth(150);
        jTable.getColumnModel().getColumn(2).setPreferredWidth(155);
        jTable.getColumnModel().getColumn(3).setPreferredWidth(155);
        jTable.getColumnModel().getColumn(4).setPreferredWidth(150);
        jTable.getColumnModel().getColumn(5).setPreferredWidth(229);
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
    private Object[][] obtenerMatrizDatos(int sizeTitles, ArrayList<Student> listaPersonas) {
        String informacion[][] = new String[listaPersonas.size()][sizeTitles];

        for (int x = 0; x < informacion.length; x++) {

            informacion[x][0] = "";
            informacion[x][1] = listaPersonas.get(x).getCedula() + "";
            informacion[x][2] = listaPersonas.get(x).getName() + "";
            informacion[x][3] = listaPersonas.get(x).getLastName() + "";
            informacion[x][4] = listaPersonas.get(x).getPhone() + "";
            informacion[x][5] = listaPersonas.get(x).getEmail() + "";
            informacion[x][6] = "EDIT";
        }

        return informacion;
    }

    private ArrayList<Student> consultarListaEstudiantes() {
        /* Validar que no exista un estudiante con el correo ingresada */
        try {
            return StudentController.listAllStudents();
        } catch (Exception e) {
            if (e.getLocalizedMessage().equals("3011")) {
                openDialogAlert("danger", "No se puede establecer conexión con el", "servidor");
            } else {
                openDialogAlert("danger", "Error al momento de consultar al servidor", "");
            }
        }
        return new ArrayList<>();
    }

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
        jLabelName = new javax.swing.JLabel();
        jLabelGenelar = new javax.swing.JLabel();
        jPanelSearch = new javax.swing.JPanel();
        jTextFieldSearch = new javax.swing.JTextField();
        jPanelAddStudent = new javax.swing.JPanel();
        jLabelAddStudents = new javax.swing.JLabel();
        jPanelButtonSearch = new javax.swing.JPanel();
        jButtonSearch = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jLabelLastName = new javax.swing.JLabel();
        jLabelEmail = new javax.swing.JLabel();

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
        jLabel1.setText("Control de estudiantes");
        jPanel1.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 870, 24));

        jPanelFocus.setBackground(new java.awt.Color(123, 31, 162));

        javax.swing.GroupLayout jPanelFocusLayout = new javax.swing.GroupLayout(jPanelFocus);
        jPanelFocus.setLayout(jPanelFocusLayout);
        jPanelFocusLayout.setHorizontalGroup(
            jPanelFocusLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 55, Short.MAX_VALUE)
        );
        jPanelFocusLayout.setVerticalGroup(
            jPanelFocusLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 3, Short.MAX_VALUE)
        );

        jPanel1.add(jPanelFocus, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 61, 55, 3));

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

        jLabelName.setFont(new java.awt.Font("Hind Siliguri SemiBold", 0, 16)); // NOI18N
        jLabelName.setForeground(new java.awt.Color(117, 117, 117));
        jLabelName.setText("Nombre");
        jLabelName.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        jLabelName.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabelNameMouseClicked(evt);
            }
        });
        jPanel1.add(jLabelName, new org.netbeans.lib.awtextra.AbsoluteConstraints(71, 35, 60, 25));

        jLabelGenelar.setFont(new java.awt.Font("Hind Siliguri SemiBold", 0, 16)); // NOI18N
        jLabelGenelar.setForeground(new java.awt.Color(123, 31, 162));
        jLabelGenelar.setText("General");
        jLabelGenelar.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        jLabelGenelar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabelGenelarMouseClicked(evt);
            }
        });
        jPanel1.add(jLabelGenelar, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 35, 55, 25));

        jPanelSearch.setBackground(new java.awt.Color(227, 213, 233));
        jPanelSearch.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jTextFieldSearch.setBackground(new java.awt.Color(227, 213, 233));
        jTextFieldSearch.setFont(new java.awt.Font("Hind Siliguri", 0, 14)); // NOI18N
        jTextFieldSearch.setForeground(new java.awt.Color(158, 158, 158));
        jTextFieldSearch.setText("Buscar por cédula");
        jTextFieldSearch.setBorder(null);
        jTextFieldSearch.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                jTextFieldSearchFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                jTextFieldSearchFocusLost(evt);
            }
        });
        jPanelSearch.add(jTextFieldSearch, new org.netbeans.lib.awtextra.AbsoluteConstraints(16, 0, 649, 32));

        jPanel1.add(jPanelSearch, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 84, 681, 32));

        jPanelAddStudent.setBackground(new java.awt.Color(156, 39, 176));

        jLabelAddStudents.setFont(new java.awt.Font("Hind Siliguri Medium", 0, 14)); // NOI18N
        jLabelAddStudents.setForeground(new java.awt.Color(255, 255, 255));
        jLabelAddStudents.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabelAddStudents.setText("Agregar estudiante");
        jLabelAddStudents.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabelAddStudentsMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jLabelAddStudentsMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jLabelAddStudentsMouseExited(evt);
            }
        });

        javax.swing.GroupLayout jPanelAddStudentLayout = new javax.swing.GroupLayout(jPanelAddStudent);
        jPanelAddStudent.setLayout(jPanelAddStudentLayout);
        jPanelAddStudentLayout.setHorizontalGroup(
            jPanelAddStudentLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabelAddStudents, javax.swing.GroupLayout.DEFAULT_SIZE, 150, Short.MAX_VALUE)
        );
        jPanelAddStudentLayout.setVerticalGroup(
            jPanelAddStudentLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabelAddStudents, javax.swing.GroupLayout.DEFAULT_SIZE, 32, Short.MAX_VALUE)
        );

        jPanel1.add(jPanelAddStudent, new org.netbeans.lib.awtextra.AbsoluteConstraints(729, 84, 150, 32));

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

        jPanel1.add(jPanelButtonSearch, new org.netbeans.lib.awtextra.AbsoluteConstraints(681, 84, 32, 32));

        jScrollPane1.setBackground(new java.awt.Color(245, 245, 245));
        jScrollPane1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(245, 245, 245), 0));
        jScrollPane1.setForeground(new java.awt.Color(51, 51, 51));
        jScrollPane1.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        jPanel1.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 134, 879, 435));

        jLabelLastName.setFont(new java.awt.Font("Hind Siliguri SemiBold", 0, 16)); // NOI18N
        jLabelLastName.setForeground(new java.awt.Color(117, 117, 117));
        jLabelLastName.setText("Apellido");
        jLabelLastName.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        jLabelLastName.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabelLastNameMouseClicked(evt);
            }
        });
        jPanel1.add(jLabelLastName, new org.netbeans.lib.awtextra.AbsoluteConstraints(147, 35, 60, 25));

        jLabelEmail.setFont(new java.awt.Font("Hind Siliguri SemiBold", 0, 16)); // NOI18N
        jLabelEmail.setForeground(new java.awt.Color(117, 117, 117));
        jLabelEmail.setText("Correo electónico");
        jLabelEmail.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        jLabelEmail.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabelEmailMouseClicked(evt);
            }
        });
        jPanel1.add(jLabelEmail, new org.netbeans.lib.awtextra.AbsoluteConstraints(223, 35, 125, 25));

        add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 16, 879, 570));
    }// </editor-fold>//GEN-END:initComponents

    /* Eventos buttons (Entered-Exited) */
    private void jButtonSearchMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButtonSearchMouseEntered
        jPanelButtonSearch.setBackground(new java.awt.Color(123, 31, 162));
    }//GEN-LAST:event_jButtonSearchMouseEntered

    private void jButtonSearchMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButtonSearchMouseExited
        jPanelButtonSearch.setBackground(new java.awt.Color(156, 39, 176));
    }//GEN-LAST:event_jButtonSearchMouseExited

    private void jLabelAddStudentsMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabelAddStudentsMouseEntered
        jPanelAddStudent.setBackground(new java.awt.Color(123, 31, 162));
    }//GEN-LAST:event_jLabelAddStudentsMouseEntered

    private void jLabelAddStudentsMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabelAddStudentsMouseExited
        jPanelAddStudent.setBackground(new java.awt.Color(156, 39, 176));
    }//GEN-LAST:event_jLabelAddStudentsMouseExited

    private void jLabelAddStudentsMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabelAddStudentsMouseClicked
        DialogNewStudent jdialog = new DialogNewStudent(jFrameParent, true);
        jdialog.setVisible(true);
    }//GEN-LAST:event_jLabelAddStudentsMouseClicked

    private void jLabelGenelarMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabelGenelarMouseClicked
        String newSearch = "general";
        if (!search.equals(newSearch)) {
            previousSearch(search);
            nextSearch(newSearch);
            search = newSearch;
        }
    }//GEN-LAST:event_jLabelGenelarMouseClicked

    private void jLabelNameMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabelNameMouseClicked
        String newSearch = "name";
        if (!search.equals(newSearch)) {
            previousSearch(search);
            nextSearch(newSearch);
            search = newSearch;
        }
    }//GEN-LAST:event_jLabelNameMouseClicked

    private void jLabelLastNameMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabelLastNameMouseClicked
        String newSearch = "lastName";
        if (!search.equals(newSearch)) {
            previousSearch(search);
            nextSearch(newSearch);
            search = newSearch;
        }
    }//GEN-LAST:event_jLabelLastNameMouseClicked

    private void jLabelEmailMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabelEmailMouseClicked
        String newSearch = "email";
        if (!search.equals(newSearch)) {
            previousSearch(search);
            nextSearch(newSearch);
            search = newSearch;
        }
    }//GEN-LAST:event_jLabelEmailMouseClicked

    private void nextSearch(String search) {
        java.awt.Color color = new java.awt.Color(123, 31, 162);
        int ejeX = 0;
        int width = 0;
        String tipoBuqueda = "";
        switch (search) {
            case "general":
                jLabelGenelar.setForeground(color);
                ejeX = 0;
                width = 55;
                tipoBuqueda = "cédula";
                break;
            case "name":
                jLabelName.setForeground(color);
                ejeX = 71;
                width = 60;
                tipoBuqueda = "nombre";
                break;
            case "lastName":
                jLabelLastName.setForeground(color);
                ejeX = 147;
                width = 60;
                tipoBuqueda = "apellido";
                break;
            case "email":
                jLabelEmail.setForeground(color);
                ejeX = 223;
                width = 125;
                tipoBuqueda = "correo electrónico";
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
            case "general":
                jLabelGenelar.setForeground(color);
                tipoBuqueda = "cédula";
                break;
            case "name":
                jLabelName.setForeground(color);
                tipoBuqueda = "nombre";
                break;
            case "lastName":
                jLabelLastName.setForeground(color);
                tipoBuqueda = "apellido";
                break;
            case "email":
                jLabelEmail.setForeground(color);
                tipoBuqueda = "correo electrónico";
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
            case "general":
                tipoBuqueda = "cédula";
                break;
            case "name":
                tipoBuqueda = "nombre";
                break;
            case "lastName":
                tipoBuqueda = "apellido";
                break;
            case "email":
                tipoBuqueda = "correo electrónico";
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
            case "general":
                tipoBuqueda = "cédula";
                break;
            case "name":
                tipoBuqueda = "nombre";
                break;
            case "lastName":
                tipoBuqueda = "apellido";
                break;
            case "email":
                tipoBuqueda = "correo electrónico";
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
            case "general":
                tipoBuqueda = "cédula";
                break;
            case "name":
                tipoBuqueda = "nombre";
                break;
            case "lastName":
                tipoBuqueda = "apellido";
                break;
            case "email":
                tipoBuqueda = "correo electrónico";
                break;
            default:
        }
        if (!jTextFieldSearch.getText().equals("Buscar por " + tipoBuqueda)) {
            busqueda(jTextFieldSearch.getText());
        }
    }//GEN-LAST:event_jButtonSearchMouseClicked

    private void busqueda(String cadena) {
        ArrayList<Student> lista;
        try {
            lista = StudentController.searchStudents(search, cadena);
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
    private javax.swing.JLabel jLabelAddStudents;
    private javax.swing.JLabel jLabelEmail;
    private javax.swing.JLabel jLabelGenelar;
    private javax.swing.JLabel jLabelLastName;
    private javax.swing.JLabel jLabelName;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanelAddStudent;
    private javax.swing.JPanel jPanelButtonSearch;
    private javax.swing.JPanel jPanelFocus;
    private javax.swing.JPanel jPanelHorizontal;
    private javax.swing.JPanel jPanelSearch;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextField jTextFieldSearch;
    // End of variables declaration//GEN-END:variables

    @Override
    public void mouseClicked(MouseEvent e) {
        //capturo fila o columna dependiendo de mi necesidad
        int fila = jTable.rowAtPoint(e.getPoint());
        int columna = jTable.columnAtPoint(e.getPoint());
        
        if (columna==6) {
            Student student = new Student();
            student.setCedula(jTable.getValueAt(fila, 0).toString());
            student.setName(jTable.getValueAt(fila, 1).toString());
            student.setLastName(jTable.getValueAt(fila, 2).toString());
            student.setPhone(jTable.getValueAt(fila, 3).toString());
            student.setEmail(jTable.getValueAt(fila, 4).toString());
            DialogEditStudent jdialog = new DialogEditStudent(jFrameParent, true, student);
            jdialog.setVisible(true);
        }
        
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
