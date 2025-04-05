import com.controler.DataBaseConfig;
import com.ui.LoginFrame;

import java.sql.SQLException;

public class Main {
  public static void main (String[] args) throws SQLException {
    System.out.println("Starting application...");

    //GUI related stuff
    LoginFrame loginFrame = new LoginFrame();

    //Database related stuff
    // Initialize
    DataBaseConfig.createUserTable();

  }

}
