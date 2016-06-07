package com.example.dennis.project;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.provider.ContactsContract;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

import com.example.dennis.project.Utility.Cookie;
import com.example.dennis.project.Utility.Database;
import com.example.dennis.project.Utility.DatabaseHelper;
import com.example.dennis.project.Utility.Member;
import com.example.dennis.project.Utility.User;
import com.example.dennis.project.Utility.Validasi;

public class Profile extends AppCompatActivity implements View.OnClickListener {
    EditText username, newPassword, oldPassword, confirmPassword, address, phonenumber,fullname;
    RadioButton male, female;
    Button update;

    private void init(){
        username = (EditText)findViewById(R.id.txtUsername);
        newPassword = (EditText)findViewById(R.id.txtPassword);
        oldPassword = (EditText)findViewById(R.id.oldtxtPassword);
        confirmPassword = (EditText)findViewById(R.id.txtConfirmPassword);
        address = (EditText)findViewById(R.id.txtaddress);
        phonenumber = (EditText)findViewById(R.id.txtPhoneNumber);
        male = (RadioButton)findViewById(R.id.rdrMale);
        female = (RadioButton)findViewById(R.id.rdrFemale);
        update = (Button)findViewById(R.id.btnUpdate);
        fullname = (EditText)findViewById(R.id.fullname);
        update.setOnClickListener(this);
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        init();
    }
    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.menu_item, menu);
        MenuItem profile = menu.findItem(R.id.profile);
        MenuItem history = menu.findItem(R.id.history);
        profile.setVisible(false);
        history.setVisible(false);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.profile:
                Intent k = new Intent(getApplicationContext(), Profile.class);
                startActivity(k);
                break;
            case R.id.history:
                Intent i = new Intent(getApplicationContext(),History.class);
                startActivity(i);
                break;
            case R.id.about:
                Intent intent = new Intent(getApplicationContext(), About.class);
                startActivity(intent);
                break;
            case R.id.logout:
                Cookie cookie = new Cookie();
                cookie.setUsername("");
                cookie.pass = "";
                Intent j = new Intent(getApplicationContext(), LoginForm.class);
                startActivity(j);
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onClick(View v) {
        Validasi validasi = new Validasi();

        if (validasi.cek(getApplicationContext(),username,newPassword,confirmPassword,address,fullname,phonenumber,male,female, getApplicationContext(),oldPassword)){
            Cookie ck = new Cookie();
//            Database db = new Database();
//            Member mb = new Member();
//            mb.setUsername(username.getText().toString());
//            mb.setPassword(newPassword.getText().toString());
//            mb.setAddress(address.getText().toString());
//            mb.setFullName(fullname.getText().toString());
//            String gender = (male.isChecked())?"male":"female";
//            mb.setGender(gender);
//            mb.setPhoneNumber(phonenumber.getText().toString());
//            db.updateData(ck.getUsername(), username.getText().toString());
//            db.databaseMember.set(db.cekUrutan(ck.getUsername()), mb);
//            ck.setUsername(username.getText().toString());
            DatabaseHelper databaseHelper = new DatabaseHelper(this);
            SQLiteDatabase sqLiteDatabase = databaseHelper.getWritableDatabase();
            ContentValues contentValues = new ContentValues();
            contentValues.put(User.USER_COL_USERNAME,username.getText().toString());
            contentValues.put(User.USER_COL_PASSWORD, newPassword.getText().toString());
            contentValues.put(User.USER_COL_NAME, fullname.getText().toString());
            contentValues.put(User.USER_COL_Phone, phonenumber.getText().toString());
            contentValues.put(User.USER_COL_Address, address.getText().toString());
            sqLiteDatabase.update(User.USER_TABLE,contentValues,User.USER_COL_ID+" =? ",new String[]{ck.getUsername()});

            Intent i = new Intent(getApplicationContext(),ChocolateMenuForm.class);
            startActivity(i);
        }


    }
}
