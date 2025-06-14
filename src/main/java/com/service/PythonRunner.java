package com.service;

import javax.swing.*;
import java.awt.*;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Objects;

public class PythonRunner {
    //give the name of the .py
    public static void runScript(String scriptName) {
        //    "venv/Scripts/python.exe","-u", "src/main/python/"
        //python script runner
        try {
            //is the python my local python or the venv python?
            String pythonExec = System.getProperty("os.name").toLowerCase().contains("win")
                    ? "venv\\Scripts\\python.exe"
                    : "venv/bin/python";
            ProcessBuilder pb = new ProcessBuilder(pythonExec, "python/" + scriptName);
            Process process = pb.start();
            BufferedReader errorReader = new BufferedReader(new InputStreamReader(process.getErrorStream()));
            String firstError = errorReader.readLine();
            System.out.println(firstError);
            // python error printer
            String errLine;
            if (!Objects.equals(firstError, "Error: Nothing went wrong")) {
                while ((errLine = errorReader.readLine()) != null) {
                    System.err.println(errLine);
                }
            }

            //python terminal printer
            BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
            String line;
            String firstLine = reader.readLine();

            if (!Objects.equals(firstLine, " ")) {
                while ((line = reader.readLine()) != null) {
                    System.out.println(line);
                }
            }

            //      process.waitFor();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Displays the saved .png plot in a JFrame
    public static void showImageFrame(String imagePath) {
        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame("Plot Output");
            frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

            // Load image
            ImageIcon icon = new ImageIcon(imagePath);

            if (icon.getIconWidth() == -1) {
                System.err.println("Failed to load image: " + imagePath);
                return;
            }

            JLabel label = new JLabel(icon);
            frame.getContentPane().add(label, BorderLayout.CENTER);

            frame.pack();
            frame.setLocationRelativeTo(null); // Center the window
            frame.setVisible(true);
        });
    }
}
