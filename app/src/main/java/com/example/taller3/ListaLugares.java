package com.example.taller3;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import java.util.List;


import com.example.taller3.servicios.*;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;

public class ListaLugares extends AppCompatActivity{

    String DATA_URL="http://www.mocky.io/v2/5ea8e7e02d000097883a4159";
    private Spinner spinDepartamentos;
    private ListView listaMunicipios;
    List<String> usu;
    Button btnSingOut;

    ArrayAdapter<String> adapter_option = null;
    GoogleSignInClient mGoogleSignInClient;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listar);

        spinDepartamentos=(Spinner)findViewById(R.id.spinDepartamentos);
        listaMunicipios=(ListView)findViewById(R.id.listMunicipio);
        btnSingOut = findViewById(R.id.singOut);

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

         //google
        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail()
                .build();
        mGoogleSignInClient = GoogleSignIn.getClient(this, gso);
        //findViewById(R.id.singOut).setOnClickListener((View.OnClickListener) this);

         btnSingOut.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View v) {
                 switch (v.getId()) {
                     // ...
                     case R.id.singOut:
                         signOut();
                         break;
                     // ...
                 }
             }
         });





    }


    public void llamarServicio(){
        Intent servicio= new Intent(this, Servicio.class);
        startService(servicio);
    }

    public void goToLogin(){
        Intent ir = new Intent(this, LoginActivity.class);
        ir.addFlags(ir.FLAG_ACTIVITY_CLEAR_TOP | ir.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(ir);
    }

    private void signOut() {
        mGoogleSignInClient.signOut()
                .addOnCompleteListener(this, new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        Toast.makeText(getApplicationContext(), "Sesión cerrada", Toast.LENGTH_LONG).show();
                        //goToLogin();
                        finish();
                    }
                });
    }



}
