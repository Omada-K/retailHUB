import com.controller.DataBaseConfig;
import com.model.User;
import com.ui.*;

import java.sql.SQLException;
import java.util.ArrayList;

public class Main {

  public static void main (String[] args) throws SQLException {
    System.out.println("Starting application...");

    //Database related stuff
    // Initialize
    DataBaseConfig.createAllTables();
    DataBaseConfig.insertUserIfNotExists("test", "t@test.gr", "123456");

    //this code create a DUMMY array list of User to show on the table
    //it is temporary, just to show how the Table Models work
    ArrayList<User> exampleList = new ArrayList<>();
    for (int i = 0; i < 10; i++) {
      exampleList.add(new User(i + 1, "Takis", "takaros@retailhub.com", "123456"));
    }
    String[] columns = {"id", "name", "email", "password"};
    Object[][] userRows = new Object[exampleList.size()][columns.length];
    for (int i = 0; i < exampleList.size(); i++) {
      User user = exampleList.get(i);
      userRows[i][0] = user.getId();
      userRows[i][1] = user.getName();
      userRows[i][2] = user.getEmail();
      userRows[i][3] = user.getUserPassword();
    }
    TableModel userTableModel = new TableModel(exampleList, columns, userRows);

    //GUI related stuff
    AppState uiState = new AppState();//All frames initialized in this obj
    uiState.tableFrame = new TableFrame(uiState, userTableModel);
    uiState.mainFrame = new MainFrame(uiState);
    uiState.loginFrame = new LoginFrame(uiState);
  }
}
