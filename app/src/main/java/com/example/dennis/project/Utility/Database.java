package com.example.dennis.project.Utility;

import android.os.Bundle;

import java.util.Vector;

/**
 * Created by dennis on 3/25/2016.
 */
public class  Database  {

    public Database() {
    }

    /*
    * /////////////////////////////////////////////////////
    *               Database member
    * ////////////////////////////////////////////////////
    * */
    public static Vector <Member> databaseMember = new Vector<>();
    //getter
    public static Vector<Member> getUsername() {
        return databaseMember;
    }
    //setter
    public static void setUsername(Vector<Member> username) {
        Database.databaseMember = username;
    }
    //tambahkan username
    public   void addUsername(Member data){
        databaseMember.add(data);
    }
    //mencari username
    public String searchUsername(String username){
        String tampung="";
        for (int i = 0; i< databaseMember.size();i++){
            tampung = (databaseMember.get(i).getUsername().toString().equals(username))? databaseMember.get(i).getUsername().toString():"";
        }
        return (tampung.isEmpty())? "":tampung;
    }
    //cek username
    public boolean cekUsername(String username, String password){
        boolean temp = false;
        for (int i = 0; i< databaseMember.size();i++){
            if (databaseMember.get(i).getUsername().toString().equals(username)&& databaseMember.get(i).getPassword().toString().equals(password))
                temp = true;
        }
        return (temp)? true: false;
    }
    //search password
    public String searchPassword(String username){
        int tampung = cekUrutan(username);
        return databaseMember.get(tampung).getPassword().toString();
    }

    public int cekUrutan(String username){
        int tampung=0;
        for (int i = 0; i< databaseMember.size();i++){
            if (databaseMember.get(i).getUsername().toString().equals(username))
                tampung = i;
        }
        return tampung;
    }
    /*
    * /////////////////////////////////////////////////////
    *               Database Transaksi
    * ////////////////////////////////////////////////////
    * */

    public static Vector <Transaksi> databaseTransaksi = new Vector<>();

    public static Vector<Transaksi> getDatabaseTransaksi() {
        return databaseTransaksi;
    }
    public static Vector<Transaksi> getDatabaseTransaksi(String username){
        Vector  <Transaksi> tampung = new Vector<>();
        for (int i = databaseTransaksi.size()-1;i>=0;i--){
            if (databaseTransaksi.get(i).getUsername().equals(username))
                tampung.add(databaseTransaksi.get(i));
        }
        return tampung;
    }

    public static void setDatabaseTransaksi(Vector<Transaksi> databaseTransaksi) {
        Database.databaseTransaksi = databaseTransaksi;
    }

    public   void addTransaksi(Transaksi data){
        databaseTransaksi.add(data);
    }

    public void updateData(String usernamelama, String usernameBaru){

        for (int i = 0; i<databaseTransaksi.size();i++){
            if (databaseTransaksi.get(i).getUsername().equals(usernamelama)){
                Transaksi tr = new Transaksi();
                tr.setUsername(usernameBaru);
                tr.setMenu_description(databaseTransaksi.get(i).getMenu_description().toString());
                tr.setMenu_name(databaseTransaksi.get(i).getMenu_name().toString());
                tr.setMenu_price(databaseTransaksi.get(i).getMenu_price().toString());
                tr.setQuantity(databaseTransaksi.get(i).getQuantity());
                databaseTransaksi.set(i,tr);
            }
        }

    }
}

