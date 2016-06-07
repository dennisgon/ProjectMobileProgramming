package com.example.dennis.project;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;

import com.example.dennis.project.Utility.Cookie;
import com.example.dennis.project.Utility.DatabaseHelper;
import com.example.dennis.project.Utility.User;
import com.example.dennis.project.Utility.Validasi;

public class Register extends AppCompatActivity implements View.OnClickListener {
    EditText username, password, confirmPassword, phonenumber, address,fullname;
    RadioButton male, female;
    Button register;

    private void init() {

        username = (EditText) findViewById(R.id.txtUsername);
        password = (EditText) findViewById(R.id.txtPassword);
        confirmPassword = (EditText) findViewById(R.id.txtConfirmPassword);
        phonenumber = (EditText)findViewById(R.id.txtPhoneNumber);
        address = (EditText)findViewById(R.id.txtaddress);
        fullname = (EditText)findViewById(R.id.fullname);
        male = (RadioButton)findViewById(R.id.rdrMale);
        female = (RadioButton)findViewById(R.id.rdrFemale);
        register = (Button)findViewById(R.id.btnRegister);
        register.setOnClickListener(this);

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_form);
        init();
    }


    @Override
    public void onClick(View v) {
        Validasi validasi = new Validasi();
        if (validasi.cek(getApplicationContext(),username,password,confirmPassword,address,fullname,phonenumber,male,female,getApplicationContext())){
            DatabaseHelper databaseHelper = new DatabaseHelper(this);
            SQLiteDatabase sqLiteDatabase = databaseHelper.getWritableDatabase();
            ContentValues contentValues = new ContentValues();
            contentValues.put(User.USER_COL_USERNAME,username.getText().toString());
            contentValues.put(User.USER_COL_PASSWORD, password.getText().toString());
            contentValues.put(User.USER_COL_NAME, fullname.getText().toString());
            contentValues.put(User.USER_COL_Phone, phonenumber.getText().toString());
            contentValues.put(User.USER_COL_Address, address.getText().toString());
            long id = sqLiteDatabase.insert(User.USER_TABLE,null, contentValues);
            Cookie cookie = new Cookie();
            cookie.setUsername(Long.toString(id));
            Intent i = new Intent(getApplicationContext(), ChocolateMenuForm.class);
            startActivity(i);
        }
    }

}
