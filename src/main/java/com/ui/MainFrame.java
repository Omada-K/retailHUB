package com.ui;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainFrame extends JFrame {
  //ui
  private JButton logoutButton;
  private JButton viewInvoicesButton;
  private JButton viewUsersButton;
  private JButton viewCustomersButton;
  private JButton viewKatiAlloButton;
  private JTabbedPane tabbedPane1;
  private JCheckBox prediction1CheckBox;
  private JCheckBox prediction2CheckBox;
  private JCheckBox prediction3CheckBox;
  private JButton generateButton;
  private JPanel mainFramePanel;

  public MainFrame (TableFrame tableFrame) {
    setContentPane(mainFramePanel);// don't forget this, the window will be empty
    setVisible(false);
    setResizable(true);
    setSize(800, 600);
    setDefaultCloseOperation(EXIT_ON_CLOSE);

    //View customers OnClick event listener
    viewCustomersButton.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed (ActionEvent e) {
        dispose();
        tableFrame.setVisible(true);
      }
    });
  }
}
