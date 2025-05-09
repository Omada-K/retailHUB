package com.service;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class PythonRunner {
  //give the name of the .py
  public static void runScript (String scriptName) {

    //python script runner
    try {
      //is the python my local python or the venv python?
      ProcessBuilder pb = new ProcessBuilder("venv/Scripts/python.exe", "src/main/python/" + scriptName);
      Process process = pb.start();
      BufferedReader errorReader = new BufferedReader(new InputStreamReader(process.getErrorStream()));
      // python error printer
      String errLine;
      while ((errLine = errorReader.readLine()) != null) {
        System.err.println(errLine);
      }

      //python terminal printer
      BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
      String line;
      while (( line = reader.readLine() ) != null) {
        System.out.println(line);
      }
      process.waitFor();
    } catch (Exception e) {
      e.printStackTrace();
    }
  }
}
