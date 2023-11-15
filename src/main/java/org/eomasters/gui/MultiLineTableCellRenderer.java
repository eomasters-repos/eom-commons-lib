package org.eomasters.gui;

import java.awt.Component;
import javax.swing.JList;
import javax.swing.JTable;
import javax.swing.UIManager;
import javax.swing.table.TableCellRenderer;

public class MultiLineTableCellRenderer extends JList<String> implements TableCellRenderer {

  @Override
  public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus,
      int row, int column) {
    if (value instanceof String[]) {
      setListData((String[]) value);
    } else {
      setListData(new String[0]);
    }

    // cell background color when selected
    if (isSelected) {
      setBackground(UIManager.getColor("Table.selectionBackground"));
      setForeground(UIManager.getColor("Table.selectionForeground"));
    } else {
      setBackground(UIManager.getColor("Table.background"));
      setForeground(UIManager.getColor("Table.foreground"));
    }

    return this;
  }
}
