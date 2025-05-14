package com.service;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Objects;

public class PythonRunner {
  //give the name of the .py
  public static void runScript (String scriptName) {
    //    "venv/Scripts/python.exe","-u", "src/main/python/"
    //python script runner
    try {
      //is the python my local python or the venv python?
      ProcessBuilder pb = new ProcessBuilder(".venv/bin/python", "src/main/python/" + scriptName);
      Process process = pb.start();
      BufferedReader errorReader = new BufferedReader(new InputStreamReader(process.getErrorStream()));
      String firsterror = errorReader.readLine();
      System.out.println(firsterror);
      // python error printer
      String errLine;
      if (!Objects.equals(firsterror, "Error: Nothing went wrong")) {
        while (( errLine = errorReader.readLine() ) != null) {
          System.err.println(errLine);
        }
      }

      //python terminal printer
      BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
      String line;
      String firstLine = reader.readLine();

      if (!Objects.equals(firstLine, " ")) {
        while (( line = reader.readLine() ) != null) {
          System.out.println(line);
        }
      }

      //      process.waitFor();
    } catch (Exception e) {
      e.printStackTrace();
    }
  }
}
