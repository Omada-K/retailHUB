import javax.swing.*;
import java.awt.*;

public class MainWindow extends JFrame{

  MainWindow(){
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //closes on X
    setTitle("Retail HUB ");
    //setSize(600, 600);
    setVisible(true);
    setResizable(true);
    getContentPane().setBackground(new Color(58, 97, 127));
    // setLayout(null);// border layout by default.

    //Labels contain text or/and images
    ImageIcon icon = new ImageIcon("C:\\ESD\\retailHUB\\src\\main\\resources\\images\\mastoras_san.png");

    JLabel label = new JLabel("Welcome to ESD25");
    label.setIcon(icon);

    label.setHorizontalTextPosition(JLabel.CENTER);
    label.setVerticalTextPosition(JLabel.TOP);
    label.setFont(label.getFont().deriveFont(20f));
    //label.setBounds(0, 0, 100, 100);

    //button
    JButton button = new JButton("exit");

    add(button, BorderLayout.SOUTH);
    add(label);
    pack();
  }
}
