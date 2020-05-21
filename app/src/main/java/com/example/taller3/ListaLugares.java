package com.example.taller3;

import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class ListaLugares extends AppCompatActivity {

    String DATA_URL="http://www.mocky.io/v2/5ea8e7e02d000097883a4159";
   // Consultar obj= null;
    private Spinner spinDeparta;
    private ListView listaProvi;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listar);

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

    }


}
