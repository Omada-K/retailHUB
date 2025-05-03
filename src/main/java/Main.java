import com.controller.DataBaseConfig;
import com.ui.AppState;
import com.ui.LoginFrame;
import com.ui.MainFrame;

import java.sql.SQLException;

public class Main {

  public static void main (String[] args) throws SQLException {
    System.out.println("Starting application...");

    //Database related stuff
    // Initialize
    DataBaseConfig.createAllTables();
    DataBaseConfig.insertUserIfNotExists("test", "t@test.gr", "123456");

    //GUI related stuff
    AppState uiState = new AppState();//All frames initialized in this obj
    //uiState.tableFrame = new TableFrame(uiState, userTableModel);
    uiState.mainFrame = new MainFrame(uiState);
    uiState.loginFrame = new LoginFrame(uiState);
  }
}
