package com.example.taller3;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;

import android.net.ConnectivityManager;
import android.net.Network;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.AsyncTask;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Spinner;

import android.widget.SpinnerAdapter;

import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import java.util.List;


import com.example.taller3.servicios.*;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;

public class ListaLugares extends AppCompatActivity {

    private Spinner spinDepartamentos;
    private ListView listaMunicipios;
    List<String> usu;
    ArrayAdapter<String> adapter_option = null;

    Button btnSingOut;
    GoogleSignInClient mGoogleSignInClient;
    String personName, personEmail;


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listar);
        //Toast.makeText(getApplicationContext(), "Bienvenido", Toast.LENGTH_LONG).show();

        spinDepartamentos = (Spinner) findViewById(R.id.spinDepartamentos);
        listaMunicipios = (ListView) findViewById(R.id.listMunicipio);
        btnSingOut = findViewById(R.id.singOut);

        //google
        GoogleSignInAccount acct = GoogleSignIn.getLastSignedInAccount(this);
        if (acct != null) {
            personName = acct.getDisplayName();
            personEmail = acct.getEmail();
        }

        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail()
                .build();
        mGoogleSignInClient = GoogleSignIn.getClient(this, gso);

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

        if(personName != null) {
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setIcon(R.mipmap.ic_launcher).
                    setMessage("Hola, " + personName + " bienvenid@").
                    setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            llenarLista();
                        }
                    }).
                    setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            Toast.makeText(getApplicationContext(), "Sesión cerrada", Toast.LENGTH_LONG).show();
                            finish();
                        }
                    });
            AlertDialog alertDialog = builder.create();
            alertDialog.show();
        }else{
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setIcon(R.mipmap.ic_launcher).
                    setMessage("Hola, bienvenido").
                    setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            llenarLista();
                        }
                    }).
                    setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            Toast.makeText(getApplicationContext(), "Sesión cerrada", Toast.LENGTH_LONG).show();
                            finish();
                        }
                    });
            AlertDialog alertDialog = builder.create();
            alertDialog.show();

        }
    }


    public void llamarServicio() {
        Intent servicio = new Intent(this, Servicio.class);
        startService(servicio);
    }

    public void llenarLista(){
        //Lista
        ArrayAdapter spinAdapter = ArrayAdapter.createFromResource(this, R.array.Depas, android.R.layout.simple_spinner_item);
        spinDepartamentos.setAdapter(spinAdapter);

        spinDepartamentos.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> lista, View view, int pos, long id) {
                if (lista.getItemAtPosition(pos).equals("Cauca")) {
                    ArrayAdapter listaAdapter = ArrayAdapter.createFromResource(ListaLugares.this, R.array.Cauca, android.R.layout.simple_list_item_1);
                    listaMunicipios.setAdapter(listaAdapter);
                }
                if (lista.getItemAtPosition(pos).equals("Antioquia")) {
                    ArrayAdapter listaAdapter = ArrayAdapter.createFromResource(ListaLugares.this, R.array.Antioquia, android.R.layout.simple_list_item_1);
                    listaMunicipios.setAdapter(listaAdapter);
                }
                if (lista.getItemAtPosition(pos).equals("Valle")) {
                    ArrayAdapter listaAdapter = ArrayAdapter.createFromResource(ListaLugares.this, R.array.Valle, android.R.layout.simple_list_item_1);
                    listaMunicipios.setAdapter(listaAdapter);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    private void signOut() {
        mGoogleSignInClient.signOut()
                .addOnCompleteListener(this, new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        Toast.makeText(getApplicationContext(), "Sesión cerrada", Toast.LENGTH_LONG).show();
                        finish();
                    }
                });
    }

    public void backToLogin() {
        Intent ir = new Intent(this, LoginActivity.class);
        ir.addFlags(ir.FLAG_ACTIVITY_CLEAR_TOP | ir.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(ir);

    }
}
