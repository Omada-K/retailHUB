import com.controler.DataBaseConfig;
import com.ui.LoginFrame;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.SQLException;

public class PythonRunner{
  public static void main(String[] args) throws SQLException{
    System.out.println("Starting application...");

    //python runner
    try{
      //is the python my local python or the venv python?
      ProcessBuilder pb = new ProcessBuilder("python", "src/main/python/test.py");
      Process process = pb.start();

      BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
      String line;
      while((line = reader.readLine()) != null){
        System.out.println(line);
      }
      process.waitFor();
    }catch(Exception e){
      e.printStackTrace();
    }

    //    MainWindow mainWindow = new MainWindow();
    LoginFrame loginFrame = new LoginFrame();

    //Database related stuff
    // Initialize
    DataBaseConfig.createUserTable();

    // Test connection
    try(Connection conn = DataBaseConfig.getConnection()){
      if(conn != null){
        System.out.println("Connected to HSQLDB successfully!");
      }else{
        System.out.println("Connection failed!");
      }
    }catch(SQLException e){
      e.printStackTrace();
    }
  }

}
