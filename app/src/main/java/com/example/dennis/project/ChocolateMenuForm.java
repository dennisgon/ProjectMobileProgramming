package com.example.dennis.project;

import android.Manifest;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.example.dennis.project.Utility.Adapter;
import com.example.dennis.project.Utility.Cookie;
import com.example.dennis.project.Utility.MenuChocolate;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Vector;

public class ChocolateMenuForm extends AppCompatActivity {
    Adapter adapter;
    ListView lv;
//    public void init(){
//        lv = (ListView)findViewById(R.id.listView);
//        lv.setAdapter(adapter = new Adapter(this));
//    }
    @Override
    protected void onCreate(Bundle savedInstanceState)  {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chocolate_menu_form);
      // init();
        ActivityCompat.requestPermissions(ChocolateMenuForm.this, new String[]{Manifest.permission.INTERNET},123);
        new JSONParse().execute("http://www.json-generator.com/api/json/get/cfXYCYeaSq?indent=2");

    }

    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.menu_item, menu);
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
                Intent j = new Intent(getApplicationContext(), LoginForm.class);
                startActivity(j);
                break;
        }
        return super.onOptionsItemSelected(item);
    }
    class JSONParse extends AsyncTask <String, String, String>{

        @Override
        protected String doInBackground(String... params) {
            HttpURLConnection httpURLConnection = null;
            StringBuilder stringBuilder = new StringBuilder();
            try {
                URL url = new URL(params[0]);
                httpURLConnection = (HttpURLConnection)url.openConnection();
                httpURLConnection.connect();
                InputStream inputStream = httpURLConnection.getInputStream();
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
                String line = "";
                while ((line = bufferedReader.readLine()) != null){
                    stringBuilder.append(line);
                }
                return stringBuilder.toString();
            } catch (Exception e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(String s) {

            super.onPostExecute(s);
            try {
                JSONObject jsonObject = new JSONObject(s);
                JSONArray jsonArray = jsonObject.getJSONArray("Chocolates");
                Vector<MenuChocolate> chocolateMenu = new Vector<MenuChocolate>();
                for (int i = 0; i<jsonArray.length(); i++){
                    JSONObject jsonObject1 = jsonArray.getJSONObject(i);
                    //adapter.addChocolateMenu(jsonObject1.getString("name"),jsonObject1.getString("price"),jsonObject1.getString("description"));
                    MenuChocolate menuChocolate = new MenuChocolate();
                    menuChocolate.setHarga(jsonObject1.getString("price"));
                    menuChocolate.setNama(jsonObject1.getString("name"));
                    menuChocolate.setDeskripsi(jsonObject1.getString("description"));
                    chocolateMenu.add(menuChocolate);
                }
                adapter = new Adapter(chocolateMenu,getApplicationContext());
                lv = (ListView)findViewById(R.id.listView);
                lv.setAdapter(adapter);
            } catch (Exception e) {
                e.printStackTrace();
            }

        }
    }
}
