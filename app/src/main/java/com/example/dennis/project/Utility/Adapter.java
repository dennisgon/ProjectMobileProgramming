package com.example.dennis.project.Utility;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;

import com.example.dennis.project.CheckOutForm;
import com.example.dennis.project.R;

import java.util.Vector;

/**
 * Created by dennis on 3/29/2016.
 */
public class Adapter extends BaseAdapter  {
    Vector<MenuChocolate> chocolateMenu;
    Context context;
    MenuChocolate mc;

    public Adapter(Vector<MenuChocolate> chocolates,Context c) {
        context = c;
        chocolateMenu = chocolates;
    }

    @Override

    public int getCount() {
        return chocolateMenu.size();
    }

    @Override
    public Object getItem(int position) {
        return chocolateMenu.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        if (convertView == null)
            convertView = LayoutInflater.from(context).inflate(R.layout.list_view_item,null);
        TextView nama = (TextView)convertView.findViewById(R.id.menu_name);
        TextView deskripsi=(TextView)convertView.findViewById(R.id.menu_description);
        TextView harga = (TextView)convertView.findViewById(R.id.price);
        nama.setText(chocolateMenu.get(position).getNama());
        deskripsi.setText(chocolateMenu.get(position).getDeskripsi());
        harga.setText(chocolateMenu.get(position).getHarga());
        Button btnBuy = (Button)convertView.findViewById(R.id.buy);
        btnBuy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(context, CheckOutForm.class);
                i.putExtra("nama", chocolateMenu.get(position).getNama().toString());
                i.putExtra("deskripsi",chocolateMenu.get(position).getDeskripsi().toString());
                i.putExtra("harga",chocolateMenu.get(position).getHarga().toString());
                i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(i);
            }
        });
        return convertView;
    }



}
