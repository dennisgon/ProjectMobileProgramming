package com.example.dennis.project.Utility;

/**
 * Created by dennis on 3/29/2016.
 */
public class Transaksi {
//    menu_item name, menu_item description, menu_item price, quantity
    String menu_name, menu_description,menu_price,username;
    int quantity;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getMenu_name() {
        return menu_name;
    }

    public void setMenu_name(String menu_name) {
        this.menu_name = menu_name;
    }

    public String getMenu_description() {
        return menu_description;
    }

    public void setMenu_description(String menu_description) {
        this.menu_description = menu_description;
    }

    public String getMenu_price() {
        return menu_price;
    }

    public void setMenu_price(String menu_price) {
        this.menu_price = menu_price;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }



    public static final String TRASACTION_TABLE = "Transaksi";
    public static final String TRASACTION_COL_ID = "TransactionID";
    public static final String TRASACTION_COL_UserID = "UserID";
    public static final String TRASACTION_COL_MenuName = "MenuName";
    public static final String TRASACTION_COL_MenuDescription= "MenuDescription";
    public static final String TRASACTION_COL_Price = "Price";
    public static final String TRASACTION_COL_Qty = "Qty";

    public static final String CREATE_TRASACTION = "CREATE TABLE "+TRASACTION_TABLE
            + "("
            + TRASACTION_COL_ID +" INTEGER PRIMARY KEY AUTOINCREMENT,"
            + TRASACTION_COL_UserID +" INTEGER,"
            + TRASACTION_COL_MenuName+" VARCHAR(100),"
            + TRASACTION_COL_MenuDescription +" VARCHAR(100),"
            + TRASACTION_COL_Price +" VARCHAR(100),"
            + TRASACTION_COL_Qty +" INTEGER "
            + ")";

    public static final String DROP_TRASACTION  = "DROP USER IF EXISTS" +TRASACTION_TABLE;
}
