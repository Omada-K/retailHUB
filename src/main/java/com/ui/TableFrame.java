package com.ui;

import javax.swing.*;

public class TableFrame extends JFrame {
  private JPanel tablePanel;
  private JTable mainTable;
  private JScrollBar scrollBar1;
  private JButton createButton;
  private JButton backButton;
  private JButton deleteButton;
  private JButton updateButton;
  private JTextArea searchQuery;
  private JButton searchButton;

  public TableFrame () {
    setContentPane(tablePanel);// don't forget this, the window will be empty
    setVisible(false);
    setResizable(true);
    setSize(1000, 800);
    setDefaultCloseOperation(EXIT_ON_CLOSE);
  }
}
