package com.example.taller3;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.Network;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.SpinnerAdapter;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.lang.reflect.Array;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;


import com.example.taller3.servicios.*;

public class ListaLugares extends AppCompatActivity{

    private Spinner spinDepartamentos;
    private ListView listaMunicipios;


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listar);


        spinDepartamentos=(Spinner)findViewById(R.id.spinDepartamentos);
        listaMunicipios=(ListView)findViewById(R.id.listMunicipio);

         ArrayAdapter spinAdapter= ArrayAdapter.createFromResource(this,R.array.Depas, android.R.layout.simple_spinner_item);
         spinDepartamentos.setAdapter(spinAdapter);

         spinDepartamentos.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
             @Override
             public void onItemSelected(AdapterView<?> lista, View view, int pos, long id) {
                 if(lista.getItemAtPosition(pos).equals("Cauca")){
                     ArrayAdapter listaAdapter= ArrayAdapter.createFromResource(ListaLugares.this, R.array.Cauca, android.R.layout.simple_list_item_1);
                     listaMunicipios.setAdapter(listaAdapter);
                 }
                 if (lista.getItemAtPosition(pos).equals("Antioquia")){
                     ArrayAdapter listaAdapter= ArrayAdapter.createFromResource(ListaLugares.this, R.array.Antioquia, android.R.layout.simple_list_item_1);
                     listaMunicipios.setAdapter(listaAdapter);
                 }
                 if (lista.getItemAtPosition(pos).equals("Valle")){
                     ArrayAdapter listaAdapter= ArrayAdapter.createFromResource(ListaLugares.this, R.array.Valle, android.R.layout.simple_list_item_1);
                     listaMunicipios.setAdapter(listaAdapter);
                 }
             }

             @Override
             public void onNothingSelected(AdapterView<?> parent) {

             }
         });

    }

    public void llamarServicio(){
        Intent servicio= new Intent(this, Servicio.class);
        startService(servicio);
    }

    public boolean conexion(){
        boolean con=false;
        ConnectivityManager connectivityManager= (ConnectivityManager)getSystemService(Context.CONNECTIVITY_SERVICE);
        boolean isWifiConn=false;
        boolean isMobileConn=false;
        for(Network network: connectivityManager.getAllNetworks()){
            NetworkInfo networkInfo=connectivityManager.getActiveNetworkInfo();
            if(networkInfo.getType()== ConnectivityManager.TYPE_WIFI){
                isWifiConn |= networkInfo.isConnected();
            }
            if(networkInfo.getType()== ConnectivityManager.TYPE_MOBILE){
                isMobileConn |= networkInfo.isConnected();
            }
        }
        System.out.println("ya verifico");
        if(isWifiConn){
            System.out.println("wifi conectado   ");
            con=true;
            try {
                Toast.makeText(this, "conexion Wifi aprovada", Toast.LENGTH_LONG).show();
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }else if(isMobileConn){
            System.out.println("datos conectado   ");
            con=true;
            try {
                Toast.makeText(this, "conexion movil aprovada", Toast.LENGTH_LONG).show();
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }else{
            con=false;
            Toast.makeText(this, "Sin conexion: ", Toast.LENGTH_LONG).show();
        }
        return con;
    }

    public void backToLogin(){
        Intent ir = new Intent(this,LoginActivity.class);
        ir.addFlags(ir.FLAG_ACTIVITY_CLEAR_TOP | ir.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(ir);
    }



}
