package com.example.dennis.project;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.example.dennis.project.Utility.AdapterHistori;
import com.example.dennis.project.Utility.Cookie;
import com.example.dennis.project.Utility.DatabaseHelper;
import com.example.dennis.project.Utility.Transaksi;

import java.util.Vector;

public class History extends AppCompatActivity {
    AdapterHistori ah;
    ListView lv;
//    public void init(){
//        lv = (ListView)findViewById(R.id.listView2);
//        lv.setAdapter(ah = new AdapterHistori(this));
//    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history);
        Vector <Transaksi> transaksiVector = new Vector<>();
        Cookie cookie = new Cookie();
        DatabaseHelper databaseHelper = new DatabaseHelper(getApplicationContext());
        SQLiteDatabase sqLiteDatabase = databaseHelper.getReadableDatabase();
       // String selectAllQuery = " SELECT '"+Transaksi.TRASACTION_COL_MenuName+"','"+Transaksi.TRASACTION_COL_MenuDescription+"','"+Transaksi.TRASACTION_COL_Price+"','"+Transaksi.TRASACTION_COL_Qty+"' FROM " + Transaksi.TRASACTION_TABLE+ "WHERE"+Transaksi.;
        Cursor cursor = sqLiteDatabase.query(Transaksi.TRASACTION_TABLE,new String[]{Transaksi.TRASACTION_COL_MenuName,Transaksi.TRASACTION_COL_MenuDescription, Transaksi.TRASACTION_COL_Price, Transaksi.TRASACTION_COL_Qty}, Transaksi.TRASACTION_COL_UserID+" =?",new String[]{cookie.getUsername()},null,null,Transaksi.TRASACTION_COL_ID+" DESC");
        //Cursor cursor = sqLiteDatabase.rawQuery(selectAllQuery,null);
        while (cursor.moveToNext()){
            Transaksi transaksi = new Transaksi();
            transaksi.setMenu_name(cursor.getString(0));
            transaksi.setMenu_description(cursor.getString(1));
            transaksi.setMenu_price(cursor.getString(2));
            transaksi.setQuantity(Integer.parseInt(cursor.getString(3)));
            transaksiVector.add(transaksi);
        }
        cursor.close();
        lv = (ListView)findViewById(R.id.listView2);
        ah = new AdapterHistori(transaksiVector,getApplicationContext());
        lv.setAdapter(ah);
        //init();
       // getData.execute(null);
    }

}
