import com.controler.DataBaseConfig;
import com.model.User;
import com.ui.*;

import java.sql.SQLException;
import java.util.ArrayList;

public class Main {

  public static void main (String[] args) throws SQLException {
    System.out.println("Starting application...");

    //Database related stuff
    // Initialize
    DataBaseConfig.createUserTable();

    //this code create a DUMMY array list of User to show on the table
    //it is temporary, just to show how the Table Models work
    ArrayList<User> exampleList = new ArrayList<>();
    for (int i = 0; i < 100; i++) {
      exampleList.add(new User(i + 1, "Takis", "takaros@retailhub.com", "123456"));
    }
    String[] columns = {"id", "name", "email", "password"};
    TableModel userTableModel = new TableModel(exampleList, columns);

    //GUI related stuff
    AppState uiState = new AppState();//All frames initialized in this obj
    uiState.tableFrame = new TableFrame(uiState, userTableModel);
    uiState.mainFrame = new MainFrame(uiState);
    uiState.loginFrame = new LoginFrame(uiState);
  }

}
