package com.ui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Objects;

public class AboutForm extends JFrame {
  private JPanel aboutPanel;
  private JLabel logo;
  private JButton closeButton;
  private JLabel headLineLabel;
  private JLabel retailHubLabel;

  public AboutForm () {
    setContentPane(aboutPanel);
    ImageIcon logoIcon = new ImageIcon(Objects.requireNonNull(getClass().getResource("/images/logo.jpeg")));
    logo.setIcon(logoIcon);
    setVisible(true);
    setResizable(true);
    pack();
    Font currentFont = headLineLabel.getFont();
    headLineLabel.setFont(new Font(currentFont.getName(), currentFont.getStyle(), 24));
    retailHubLabel.setFont(new Font(currentFont.getName(), currentFont.getStyle(), 32));
    closeButton.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed (ActionEvent e) {
        dispose();
      }
    });
  }
}
