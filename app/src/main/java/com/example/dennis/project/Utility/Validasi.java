package com.example.dennis.project.Utility;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

/**
 * Created by dennis on 3/26/2016.
 */
public class Validasi {
    //validasi register
    public boolean cek(Context context,EditText username,EditText password,EditText confirmPassword, EditText  address, EditText fullname, EditText phonenumber,RadioButton male, RadioButton female, Context application) {
        boolean temp=false;
        //        Validate the Username must be filled and must be at least 7 character
        if (validasiUsername(username))
            Toast.makeText(application, "Username must be filled and must be at least 7 character", Toast.LENGTH_SHORT).show();
        //        Validate the Password must be filled and must contain at least 1 small letter, 1 capital letter and 1 number.
        else if (!validasiPassword(password))
            Toast.makeText(application, "Password must be filled and must contain at least 1 small letter, 1 capital letter and 1 number", Toast.LENGTH_SHORT).show();
        //        Validate the Confirm Password must be filled.
        else if (validasiConfirmPasswor(confirmPassword))
            Toast.makeText(application, "Confirm Password must be filled.", Toast.LENGTH_SHORT).show();
        //        Validate Password and Confirm password must match.
        else if(validasiMatchConfirmPassword(confirmPassword,password))
            Toast.makeText(application, "Password and Confirm password must match.", Toast.LENGTH_SHORT).show();
        //        Validate the Address must be filled and must between 5 and 20 character.
        else if(validasiAddress(address))
            Toast.makeText(application, "Address must be filled and must between 5 and 20 character.", Toast.LENGTH_SHORT).show();
        //        Validate the Address must be ended with “street”.
        else if (validasiAddress2(address))
            Toast.makeText(application, "Address must be ended with \"street\"", Toast.LENGTH_SHORT).show();
        //        Validate the Gender must be chosen from Male or Female choice.
        else if (!validasiGender(male)&&!validasiGender(female))
            Toast.makeText(application, "Gender must be chosen from Male or Female choice", Toast.LENGTH_SHORT).show();
        //        Validate the Phone Number must be filled, starts with “+62” and consists of numerical character only.
        else if (validasiPhoneNumber(phonenumber)||!validasiDigitPhoneNumber(phonenumber))
            Toast.makeText(application, "Phone Number must be filled, starts with “+62” and consists of numerical character only", Toast.LENGTH_SHORT).show();
        //        Validate the Username must be unique or has not been registered in the application databaseMember before.
        else  if (validasiKondisiUsername(username, context))
            Toast.makeText(application, "Username must be unique or has not been registered in the application database before.", Toast.LENGTH_SHORT).show();
        //        After user has been successfully registered, the application will automatically logged in the user and redirect user to Chocolate Menu Form.
        else{
            temp = true;
        }
        return temp;

    }
    //validasi profile
    public boolean cek(Context context,EditText username,EditText password,EditText confirmPassword, EditText  address, EditText fullname, EditText phonenumber,RadioButton male, RadioButton female, Context application,EditText oldPassword) {
        boolean temp=false;
        //        Validate the Username must be filled and must be at least 7 character
        if (validasiUsername(username))
            Toast.makeText(application, "Username must be filled and must be at least 7 character", Toast.LENGTH_SHORT).show();
            //        Validate the Password must be filled and must contain at least 1 small letter, 1 capital letter and 1 number.
        else if (validasiOldPassword(oldPassword))
            Toast.makeText(application, "Old Password must be filled and must match with current password.", Toast.LENGTH_SHORT).show();
        else if (!validasiPassword(password))
            Toast.makeText(application, "New Password must be filled and must contain at least 1 small letter, 1 capital letter and 1 number", Toast.LENGTH_SHORT).show();
            //        Validate the Confirm Password must be filled.
        else if (validasiConfirmPasswor(confirmPassword))
            Toast.makeText(application, "Confirm Password must be filled.", Toast.LENGTH_SHORT).show();
            //        Validate Password and Confirm password must match.
        else if(validasiMatchConfirmPassword(confirmPassword, password))
            Toast.makeText(application, "Password and Confirm password must match.", Toast.LENGTH_SHORT).show();
            //        Validate the Address must be filled and must between 5 and 20 character.
        else if(validasiAddress(address))
            Toast.makeText(application, "Address must be filled and must between 5 and 20 character.", Toast.LENGTH_SHORT).show();
            //        Validate the Address must be ended with “street”.
        else if (validasiAddress2(address))
            Toast.makeText(application, "Address must be ended with \"street\"", Toast.LENGTH_SHORT).show();
            //        Validate the Gender must be chosen from Male or Female choice.
        else if (!validasiGender(male)&&!validasiGender(female))
            Toast.makeText(application, "Gender must be chosen from Male or Female choice", Toast.LENGTH_SHORT).show();
            //        Validate the Phone Number must be filled, starts with “+62” and consists of numerical character only.
        else if (validasiPhoneNumber(phonenumber)||!validasiDigitPhoneNumber(phonenumber))
            Toast.makeText(application, "Phone Number must be filled, starts with “+62” and consists of numerical character only", Toast.LENGTH_SHORT).show();
            //        Validate the Username must be unique or has not been registered in the application databaseMember before.
        else  if (validasiKondisiUsername(username,context))
            Toast.makeText(application, "Username must be unique or has not been registered in the application database before.", Toast.LENGTH_SHORT).show();
            //        After user has been successfully registered, the application will automatically logged in the user and redirect user to Chocolate Menu Form.

        else{
            temp = true;
        }
        return temp;

    }
    //validasi checkout
    public boolean cek(EditText qty){
        return (ValidasiQty(qty))?true:false;
    }

    private boolean validasiOldPassword(EditText oldPassword){
        Cookie ck = new Cookie();
        return (!oldPassword.getText().toString().equals(ck.pass))?true: false;
    }

    private boolean validasiKondisiUsername(EditText username, Context context) {
        DatabaseHelper databaseHelper = new DatabaseHelper(context);
        SQLiteDatabase sqLiteDatabase = databaseHelper.getReadableDatabase();
        Cursor cursor = sqLiteDatabase.query(User.USER_TABLE,new String[]{ User.USER_COL_USERNAME},User.USER_COL_USERNAME+"= ?",new String[]{username.getText().toString()},null,null,null);
        if (cursor.moveToNext()){
            return true;
        }else {
            return false;
        }

       // String tampung = db.searchUsername(username.getText().toString());
        //return (tampung.equals(username.getText().toString()))? true:false;
    }

    private boolean validasiGender(RadioButton rdr) {
        return (rdr.isChecked())?true:false;
    }

    private boolean validasiDigitPhoneNumber(EditText phoneNumber) {
        boolean temp = false;
        for (int i = 1; i<phoneNumber.getText().toString().length()-1; i++) {
            temp=(Character.isDigit(phoneNumber.getText().toString().charAt(i)))?true:false;
        }
        return (temp == true)?true:false;
    }

    private boolean validasiPhoneNumber(EditText phoneNumber) {
        return (!phoneNumber.getText().toString().startsWith("+62"))?true:false;
    }

    private boolean validasiAddress2(EditText address) {
        return (!address.getText().toString().endsWith("street"))?true:false;
    }

    private boolean validasiAddress(EditText address) {
        return (address.getText().toString().length()<5||address.getText().toString().length()>20)?true:false;
    }

    private boolean validasiMatchConfirmPassword(EditText confirmPassword, EditText password) {
        return (!confirmPassword.getText().toString().equals(password.getText().toString()))? true:false;
    }

    private boolean validasiUsername(EditText username) {
        return (username.getText().toString().length()<7)? true:false;
    }

    private boolean validasiConfirmPasswor(EditText confirmPassword) {
        return (confirmPassword.getText().toString().isEmpty())?true:false;
    }

    private boolean validasiPassword(EditText password){
        boolean temp = false, temp1 = false, temp2 = false;
        for (int i = 0; i<password.getText().toString().length();i++) {
            if (Character.isLowerCase(password.getText().toString().charAt(i)))
                temp = true;
            else if (Character.isDigit(password.getText().toString().charAt(i)))
                temp1 = true;
            else if (Character.isUpperCase(password.getText().toString().charAt(i)))
                temp2 = true;
        }
        return (temp&&temp1&&temp2)?true:false;
    }

    private boolean ValidasiQty(EditText qty){

        return (qty.getText().toString().isEmpty())?true:false;
    }
  
}
