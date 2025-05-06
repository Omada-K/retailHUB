import com.controller.DataBaseConfig;
import com.dao.OrdersDAO;
import com.dao.UserDAO;
import com.model.Order;
import com.model.User;
import com.ui.AppState;
import com.ui.LoginFrame;
import com.ui.MainFrame;

import java.sql.SQLException;
import java.time.LocalDate;

public class Main {

  public static void main (String[] args) throws SQLException {
    System.out.println("Starting application...");

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
    OrdersDAO.insertOrder(new Order(LocalDate.now(), 34.6, 0, 0));

    //GUI related stuff
    AppState uiState = new AppState();//All frames initialized in this obj
    uiState.mainFrame = new MainFrame(uiState);
    uiState.loginFrame = new LoginFrame(uiState);
  }
}
