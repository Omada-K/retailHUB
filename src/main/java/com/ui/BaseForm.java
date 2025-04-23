package com.ui;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public abstract class BaseForm extends JFrame {
  public BaseForm () {
    setVisible(true);
    setResizable(true);
    setSize(480, 320);
    setLocationRelativeTo(null);
  }

  protected void setupCancelButton (JButton cancelButton) {
    if (cancelButton != null) {
      cancelButton.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed (ActionEvent e) {
          dispose();
        }
      });
    } else {
      System.out.println("Cancel button is null. Cannot add listener.");
    }
  }
}
