import com.controller.DataBaseConfig;
import com.dao.UserDAO;
import com.model.User;
import com.ui.AppState;
import com.ui.LoginFrame;
import com.ui.MainFrame;

import javax.swing.*;
import java.sql.SQLException;

public class Main {

  public static void main (String[] args) throws SQLException {
    System.out.println("Starting application...");
    //set the theme of windows
    try {
      // Set Nimbus Look and Feel
      for (UIManager.LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
        if ("Nimbus".equals(info.getName())) {
          UIManager.setLookAndFeel(info.getClassName());
          break;
        }
      }
    } catch (Exception e) {
      e.printStackTrace(); // If Nimbus is not available, fallback to default
    }

    //this shuts the db down before exiting
    Runtime.getRuntime().addShutdownHook(new Thread(new Runnable() {
      @Override
      public void run () {
        DataBaseConfig.shutdownDatabase();
        System.out.println("Database shutdown successfully.");
      }
    }));

    //Database related stuff
    // Initialize
    DataBaseConfig.createAllTables();

    UserDAO.insertUser(new User("test", "test", "1234", true));

    //GUI related stuff
    AppState uiState = new AppState();//All frames initialized in this obj
    uiState.loginFrame = new LoginFrame(uiState);
    uiState.mainFrame = new MainFrame(uiState);
  }
}
