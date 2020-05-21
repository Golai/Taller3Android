package com.example.taller3;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Spinner;
<<<<<<< HEAD
import android.widget.Toast;
=======
import android.widget.SpinnerAdapter;
>>>>>>> 353e1390e3ff6e544f8dee8610b23d64f4bdc38f

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
<<<<<<< HEAD
   // Consultar obj= null;
    private Spinner spinDeparta;
    private ListView listaProvi;
=======
    private Spinner spinDepartamentos;
    private ListView listaMunicipios;
    List<String> usu;

    ArrayAdapter<String> adapter_option = null;
>>>>>>> 353e1390e3ff6e544f8dee8610b23d64f4bdc38f

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listar);

<<<<<<< HEAD
        Toast.makeText(getApplicationContext(), "Bienvenido", Toast.LENGTH_LONG).show();

        spinDeparta=(Spinner)findViewById(R.id.spinDeparta);
        listaProvi=(ListView)findViewById(R.id.listProvi);

        ArrayAdapter spinAdapter = ArrayAdapter.createFromResource(this,R.array.Depas, android.R.layout.simple_spinner_item);
        spinDeparta.setAdapter(spinAdapter);

        spinDeparta.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> lista, View view, int pos, long id) {
                if(lista.getItemAtPosition(pos).equals("Lima")){
                    ArrayAdapter listaAdapter = ArrayAdapter.createFromResource(ListaLugares.this,R.array.Lima,android.R.layout.simple_list_item_1);
                    listaProvi.setAdapter(listaAdapter);
                }
                if(lista.getItemAtPosition(pos).equals("Antioquia")){
                    ArrayAdapter listaAdapter = ArrayAdapter.createFromResource(ListaLugares.this,R.array.Antioquia,android.R.layout.simple_list_item_1);
                    listaProvi.setAdapter(listaAdapter);
                }
                if(lista.getItemAtPosition(pos).equals("ValleCauca")){
                    ArrayAdapter listaAdapter = ArrayAdapter.createFromResource(ListaLugares.this,R.array.ValleCauca,android.R.layout.simple_list_item_1);
                    listaProvi.setAdapter(listaAdapter);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
=======
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
>>>>>>> 353e1390e3ff6e544f8dee8610b23d64f4bdc38f


<<<<<<< HEAD
=======
    }

    public void llamarServicio(){
        Intent servicio= new Intent(this, Servicio.class);
        startService(servicio);
    }


>>>>>>> 353e1390e3ff6e544f8dee8610b23d64f4bdc38f

}
