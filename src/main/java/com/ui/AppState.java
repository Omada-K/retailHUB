package com.ui;

public class AppState {
  public String loggedInUserName = "";
  public boolean isUserManager = false ;
  public String selectedTableType = TableTypes.NoSelection.name();

  public void resetState () {
    loggedInUserName = "";
    selectedTableType = TableTypes.NoSelection.name();
  }

  public void resetSelectedTableType () {
    selectedTableType = TableTypes.NoSelection.name();
  }

  public enum TableTypes {
    Users,
    Customers,
    Orders,
    Products,
    NoSelection
  }

}


