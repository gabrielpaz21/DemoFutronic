package com.futronic.project.utility;

import java.awt.Component;

import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;

public class TableImageRender extends DefaultTableCellRenderer {

	private static final long serialVersionUID = 1L;

	@Override
	public Component getTableCellRendererComponent(JTable table, Object object, boolean b1, boolean b2, int i1,
			int i2) {
		if(object instanceof JLabel) {
			JLabel label  = (JLabel) object;
			return label;
		}
	
		return super.getTableCellRendererComponent(table, object, b1, b2, i1, i2);
	}
	
	

}
