package com.example.dennis.project;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.dennis.project.Utility.Cookie;
import com.example.dennis.project.Utility.Database;
import com.example.dennis.project.Utility.DatabaseHelper;
import com.example.dennis.project.Utility.User;

public class LoginForm extends AppCompatActivity implements View.OnClickListener {
    EditText username, password;
    Button Login, Register;
    DatabaseHelper databaseHelper;
    SQLiteDatabase sqLiteDatabase;
    //inisialisasi variable
    public void init(){
        username = (EditText)findViewById(R.id.username);
        password = (EditText)findViewById(R.id.password);
        Login = (Button)findViewById(R.id.btnlogin);
        Register = (Button)findViewById(R.id.btnRegister);
        Login.setOnClickListener(this);
        Register.setOnClickListener(this);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_form);
        init();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btnlogin:
                //melakukan login
                doLogin();
                break;
            case R.id.btnRegister :
                //pindah ke halaman register
                doRegister();
                break;
        }
    }

    private void doRegister() {
        Intent i = new Intent(getApplicationContext(), Register.class);
        startActivity(i);
    }

    private void doLogin() {
        //cekusername & password
        Database db = new Database();
//        Validate Username and Password must be filled.
        if (username.getText().toString().equals("")||username.getText().equals(""))
            Toast.makeText(LoginForm.this, "Username and Password must be filled.", Toast.LENGTH_SHORT).show();
//        Validate the Username and Password must be registered before in the application’s databaseMember.
        else if (loginSalah())
            Toast.makeText(LoginForm.this, "Username and Password must be registered before in the application’s database.", Toast.LENGTH_SHORT).show();
        else {

            Intent i = new Intent(getApplicationContext(),ChocolateMenuForm.class);
            startActivity(i);
        }

    }
    private boolean loginSalah(){
        databaseHelper = new DatabaseHelper(this);
        sqLiteDatabase = databaseHelper.getReadableDatabase();
        Cursor cursor = sqLiteDatabase.query(User.USER_TABLE,new String[]{User.USER_COL_ID, User.USER_COL_USERNAME, User.USER_COL_PASSWORD},User.USER_COL_USERNAME+"= ? AND "+User.USER_COL_PASSWORD+"= ?",new String[]{username.getText().toString(), password.getText().toString()},null,null,null);
        if (cursor.moveToNext()){
            Cookie cookie = new Cookie();
            cookie.setUsername(cursor.getString(0));
            cookie.pass = cursor.getString(2);
            cursor.close();
            databaseHelper.close();
            sqLiteDatabase.close();
            return false;
        }else {
            cursor.close();
            databaseHelper.close();
            sqLiteDatabase.close();
            return true;
        }
    }


}
