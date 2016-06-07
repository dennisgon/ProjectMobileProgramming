package com.example.dennis.project.Utility;

/**
 * Created by dennis on 6/2/2016.
 */
public class User {
    public static final String USER_TABLE = "User";
    public static final String USER_COL_ID = "UserID";
    public static final String USER_COL_USERNAME = "Username";
    public static final String USER_COL_PASSWORD = "Password";
    public static final String USER_COL_NAME = "Name";
    public static final String USER_COL_Phone = "Phone";
    public static final String USER_COL_Address = "Address";

    public static final String CREATE_USER = "CREATE TABLE "+USER_TABLE
            + "("
            + USER_COL_ID +" INTEGER PRIMARY KEY AUTOINCREMENT,"
            + USER_COL_USERNAME +" VARCHAR(100),"
            + USER_COL_PASSWORD +" VARCHAR(100),"
            + USER_COL_NAME +" VARCHAR(100),"
            + USER_COL_Phone +" VARCHAR(100),"
            + USER_COL_Address +" VARCHAR(100)"
            + ")";

    public static final String DROP_USER = "DROP USER IF EXISTS" +USER_TABLE;
}
