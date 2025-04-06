package com.ui;

import javax.swing.*;

public class TableFrame extends JFrame {
  private JPanel tablePanel;
  private JTable mainTable;
  private JButton createButton;
  private JButton backButton;
  private JButton deleteButton;
  private JButton updateButton;
  private JTextArea searchQuery;
  private JButton searchButton;
  private JScrollBar scrollBar1;

  public TableFrame (TableModel content) {
    setContentPane(tablePanel);// don't forget this, the window will be empty
    setVisible(false);
    setResizable(true);
    setSize(1000, 800);
    setDefaultCloseOperation(EXIT_ON_CLOSE);

    mainTable.setModel(content);
  }
}
