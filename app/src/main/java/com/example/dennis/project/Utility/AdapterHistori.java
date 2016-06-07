package com.example.dennis.project.Utility;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.dennis.project.R;

import java.util.Vector;

/**
 * Created by dennis on 3/30/2016.
 */
public class AdapterHistori extends BaseAdapter{
    Vector<Transaksi> transaksi; Database db; Context context;

    public AdapterHistori(Vector <Transaksi> transaksis,Context c) {
//        Cookie cookie = new Cookie();
        context = c;
        transaksi = transaksis;
//        db = new Database();
//        transaksi = new Vector<>();
        //transaksi = db.getDatabaseTransaksi(cookie.getUsername());
    }

    @Override
    public int getCount() {
        return transaksi.size();
    }

    @Override
    public Object getItem(int position) {
        return transaksi.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null)
            convertView = LayoutInflater.from(context).inflate(R.layout.list_view_item_histori,null);
        TextView nama = (TextView)convertView.findViewById(R.id.name_transaksi);
        TextView deskripsi=(TextView)convertView.findViewById(R.id.detail_transaksi);
        TextView harga = (TextView)convertView.findViewById(R.id.price_transaksi);
        TextView Quantity = (TextView)convertView.findViewById(R.id.quantity_transaksi);
        nama.setText(transaksi.get(position).getMenu_name().toString());
        deskripsi.setText(transaksi.get(position).getMenu_description().toString());
        harga.setText(transaksi.get(position).getMenu_price().toString());
        Quantity.setText(String.valueOf(transaksi.get(position).getQuantity()));
        return convertView;
    }
}
