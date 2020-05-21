package com.example.taller3;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.SpinnerAdapter;

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

    String DATA_URL="http://www.mocky.io/v2/5ea8e7e02d000097883a4159";
    private Spinner spinDepartamentos;
    private ListView listaMunicipios;
    List<String> usu;

    ArrayAdapter<String> adapter_option = null;

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



}
