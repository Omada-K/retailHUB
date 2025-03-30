import java.io.*;

public class PythonRunner {
  public static void main(String[] args) {
    try {
      //is the python my local python or the venv python?
      ProcessBuilder pb = new ProcessBuilder("python", "src/main/python/test.py");
      Process process = pb.start();

      BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
      String line;
      while ((line = reader.readLine()) != null) {
        System.out.println(line);
      }
      process.waitFor();
    } catch (Exception e) {
      e.printStackTrace();
    }
    MainWindow mainWindow = new MainWindow();
  }
}
