import com.controler.DataBaseConfig;
import com.ui.LoginFrame;
import com.ui.MainFrame;
import com.ui.TableFrame;

import java.sql.SQLException;

public class Main {
  public static void main (String[] args) throws SQLException {
    System.out.println("Starting application...");

    //Database related stuff
    // Initialize
    DataBaseConfig.createUserTable();

    //GUI related stuff
    TableFrame tableFrame = new TableFrame();
    MainFrame mainFrame = new MainFrame(tableFrame);
    LoginFrame loginFrame = new LoginFrame(mainFrame);
  }

}
