package com.example.dennis.project;

import android.Manifest;
import android.content.BroadcastReceiver;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.dennis.project.Utility.Cookie;
import com.example.dennis.project.Utility.Database;
import com.example.dennis.project.Utility.DatabaseHelper;
import com.example.dennis.project.Utility.MenuChocolate;
import com.example.dennis.project.Utility.Transaksi;
import com.example.dennis.project.Utility.User;
import com.example.dennis.project.Utility.Validasi;

import java.util.Vector;

public class CheckOutForm extends AppCompatActivity implements View.OnClickListener{
//    a.	Use EditText component for Quantity to order.
    EditText Quantity;
//    b.	Use TextView to view the selected Item Name, Item Description and Item Price.
    TextView itemName,itemDescription, itemPrice;
    Button checkOut;
    BroadcastReceiver broadcastReceiver = null;
    public void init(){
        Bundle b = new Bundle();
        b = getIntent().getExtras();
        itemName = (TextView)findViewById(R.id.judul);
        itemDescription = (TextView)findViewById(R.id.deskripsi);
        itemPrice = (TextView)findViewById(R.id.harga);
        checkOut = (Button)findViewById(R.id.checkOut);
        Quantity = (EditText)findViewById(R.id.qty);
        itemName.setText(b.getString("nama"));
        itemDescription.setText(b.getString("deskripsi"));
        itemPrice.setText(b.getString("harga"));
        checkOut.setOnClickListener(this);
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_check_out_form);
        ActivityCompat.requestPermissions(this,new String[]{Manifest.permission.SEND_SMS},123);
        broadcastReceiver = new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                Toast.makeText(CheckOutForm.this, "Chocolate sudah terpesan", Toast.LENGTH_SHORT).show();
            }
        };
        init();
    }

    @Override
    public void onClick(View v) {
//        Validate the Quantity must be filled and must be numeric.
        Validasi validasi = new Validasi();
        if (validasi.cek(Quantity))
            Toast.makeText(CheckOutForm.this, "the Quantity must be filled and must be numeric.", Toast.LENGTH_SHORT).show();
        else {
            //checkout
            doCheckout();

        }
    }

    private void doCheckout() {
        Cookie cookie = new Cookie();
//        Transaksi tr = new Transaksi();
//        Database db = new Database();
//        tr.setUsername(cookie.getUsername());
//        tr.setMenu_name(itemName.getText().toString());
//        tr.setMenu_description(itemDescription.getText().toString());
//        tr.setMenu_price(itemPrice.getText().toString());
//        try {
//            tr.setQuantity(Integer.parseInt(Quantity.getText().toString()));
//        }catch (Exception e){
//
//        }
//        db.addTransaksi(tr);
        DatabaseHelper databaseHelper = new DatabaseHelper(this);
        SQLiteDatabase sqLiteDatabase = databaseHelper.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(Transaksi.TRASACTION_COL_UserID, cookie.getUsername());
        contentValues.put(Transaksi.TRASACTION_COL_MenuDescription,itemDescription.getText().toString());
        contentValues.put(Transaksi.TRASACTION_COL_MenuName,itemName.getText().toString());
        contentValues.put(Transaksi.TRASACTION_COL_Price,itemPrice.getText().toString());
        contentValues.put(Transaksi.TRASACTION_COL_Qty,Integer.parseInt(Quantity.getText().toString()));
        sqLiteDatabase.insert(Transaksi.TRASACTION_TABLE,null, contentValues);
        SmsManager sms = SmsManager.getDefault();
        sms.sendTextMessage("5554", null, itemName.getText().toString()+" sudah terpesan", null, null);

        Intent i = new Intent(getApplicationContext(),ChocolateMenuForm.class);
        startActivity(i);



    }
}
