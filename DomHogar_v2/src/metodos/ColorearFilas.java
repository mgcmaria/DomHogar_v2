package metodos;

import java.awt.*;
import javax.swing.*;
import javax.swing.table.*;


public class ColorearFilas extends DefaultTableCellRenderer {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

@Override
public Component getTableCellRendererComponent(JTable table, Object value, boolean Selected, boolean hasFocus, int row, int col){

super.getTableCellRendererComponent(table, value, Selected, hasFocus, row, col);

    if (table.getValueAt(row, 4).toString().equals("1") ) {

         table.setValueAt("X", row, 4);
         setHorizontalAlignment(CENTER);

    } else if(table.getValueAt(row, 5).toString().equals("1")){

    	table.setValueAt("X", row, 5);
    	setHorizontalAlignment(CENTER);
        
    }  else if(table.getValueAt(row, 6).toString().equals("1")){

    	table.setValueAt("X", row, 6);
    	setHorizontalAlignment(CENTER);
    
    } else if(table.getValueAt(row, 7).toString().equals("1")){

    	table.setValueAt("X", row, 7);
    	setHorizontalAlignment(CENTER);
    
    } else if(table.getValueAt(row, 8).toString().equals("1")){

    	table.setValueAt("X", row, 8);
    	setHorizontalAlignment(CENTER);
    
    } else if(table.getValueAt(row, 4).toString().equals("0")){

    	table.setValueAt("", row, 4);
    	setHorizontalAlignment(CENTER);
    
    } else if(table.getValueAt(row, 5).toString().equals("0")){

    	table.setValueAt("", row, 5);
    	setHorizontalAlignment(CENTER);
    
    } else if(table.getValueAt(row, 6).toString().equals("0")){

    	table.setValueAt("", row, 6);
    	setHorizontalAlignment(CENTER);
    
    } else if(table.getValueAt(row, 7).toString().equals("0")){

    	table.setValueAt("", row, 7);
    	setHorizontalAlignment(CENTER);
    
    } else if(table.getValueAt(row, 8).toString().equals("0")){

    	table.setValueAt("", row, 8);
    	setHorizontalAlignment(CENTER);
    
    } 
 return this;
}
}

            


