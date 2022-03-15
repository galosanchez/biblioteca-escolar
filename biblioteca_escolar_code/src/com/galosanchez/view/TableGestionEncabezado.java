package com.galosanchez.view;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;

import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;

/**
 *
 * @author Galo Sánchez
 */
public class TableGestionEncabezado extends DefaultTableCellRenderer{
	
	@Override
    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
        
        JComponent jcomponent = null;
        
        if( value instanceof String ) {
            jcomponent = new JLabel((String) value);
            ((JLabel)jcomponent).setHorizontalAlignment( SwingConstants.LEFT );
            ((JLabel)jcomponent).setSize( 32, jcomponent.getWidth());
            ((JLabel)jcomponent).setFont(new Font( "Hind Siliguri SemiBold",Font.PLAIN ,14 ));
                
            ((JLabel)jcomponent).setPreferredSize( new Dimension(6, jcomponent.getWidth())  );
        }         
   
//        jcomponent.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 0, 1, new java.awt.Color(245,245,245)));
        jcomponent.setOpaque(true);
        //jcomponent.setBackground( new Color(236,234,219) );
        jcomponent.setBackground( new Color(97,97,97) );
//        jcomponent.setToolTipText("Tabla Seguimiento");
        jcomponent.setForeground(Color.white);
        
        return jcomponent;
    }
}


