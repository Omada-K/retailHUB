package com.ui;

import javax.swing.*;
import java.util.Objects;

public class AboutForm extends JFrame {
  private JPanel aboutPanel;
  private JLabel logo;
  private JButton closeButton;

  public AboutForm () {
    setContentPane(aboutPanel);
    ImageIcon logoIcon = new ImageIcon(Objects.requireNonNull(getClass().getResource("/images/logo.jpeg")));
    logo.setIcon(logoIcon);
    setVisible(true);
    setResizable(true);
    pack();
  }
}
