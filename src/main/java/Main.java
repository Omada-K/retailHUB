import com.controler.DataBaseConfig;
import com.dao.UserDAO;
import com.model.User;
import com.ui.LoginFrame;
import com.ui.MainFrame;
import com.ui.TableFrame;
import com.ui.TableModel;

import java.sql.SQLException;
import java.util.ArrayList;

public class Main {

  public static void main (String[] args) throws SQLException {
    System.out.println("Starting application...");

    //Database related stuff
    // Initialize
    DataBaseConfig.createUserTable();
    DataBaseConfig.insertUserIfNotExists("admin1","admin1@example.com","123");//adds users to user table in db
    DataBaseConfig.insertUserIfNotExists("admin2","admin2@example.com","2312");



    //this code create a DUMMY array list of User to show on the table
    //it is temporary, just to show how the Table Models work
    ArrayList<User> exampleList = new ArrayList<>();
    for (int i = 0; i < 100; i++) {
      exampleList.add(new User(i + 1, "Takis", "takaros@retailhub.com", "123456"));
    }
    String[] columns = {"id", "name", "email", "password"};
    TableModel userTableModel = new TableModel(exampleList, columns);

    //GUI related stuff
    TableFrame tableFrame = new TableFrame(userTableModel);
    MainFrame mainFrame = new MainFrame(tableFrame);
    LoginFrame loginFrame = new LoginFrame(mainFrame);
  }

}
