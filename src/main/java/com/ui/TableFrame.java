package com.ui;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TableFrame extends JFrame {
  private JPanel tablePanel;
  private JTable mainTable;
  private JButton deleteButton;
  private JButton updateButton;
  private JTextArea searchQuery;
  private JButton searchButton;
  private JButton createButton;
  private JButton backButton;

  public TableFrame (AppState state, TableModel content) {
    setContentPane(tablePanel);// don't forget this, the window will be empty
    setVisible(false);
    setResizable(true);
    setExtendedState(JFrame.MAXIMIZED_BOTH);
    //    setSize(1000, 800);
    setDefaultCloseOperation(EXIT_ON_CLOSE);
    setLocationRelativeTo(null);

    mainTable.setModel(content);

    //Back button
    backButton.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed (ActionEvent e) {
        state.tableFrame.setVisible(false);
        state.mainFrame.setVisible(true);

      }
    });
  }
}
