import com.controller.DataBaseConfig;
import com.dao.UserDAO;
import com.model.User;
import com.ui.AppState;
import com.ui.LoginFrame;

import javax.swing.*;
import java.sql.SQLException;
import java.util.ArrayList;

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

    ArrayList<User> users = UserDAO.getData();
    if (users.isEmpty()) {
      UserDAO.createItem(new User("Krush Team", "test", "1234", true));
    }

    //OrdersDAO.createItem(new Order(LocalDate.now(), LocalDate.now(), 0, 0));

    //GUI related stuff
    AppState uiState = new AppState();//this is input for all Jframes, it has info about the app name of login user
    // etc...
    new LoginFrame(uiState);
  }

}
