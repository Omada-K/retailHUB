package com.ui;

public class AppState {
    public String loggedInUserName = "";
    public String selectedTableType = TableTypes.NoSelection.name();
    public boolean loggedInUserIsAdmin = false;

    public void resetState() {
        loggedInUserName = "";
        loggedInUserIsAdmin = false;
        selectedTableType = TableTypes.NoSelection.name();
    }

    public void resetSelectedTableType() {
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
