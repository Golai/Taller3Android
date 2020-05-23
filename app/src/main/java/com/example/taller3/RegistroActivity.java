package com.example.taller3;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class RegistroActivity extends AppCompatActivity {

    EditText name, pass, email;
    TextView data;
    Button btnR;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro);

        name = findViewById(R.id.nombre);
        pass = findViewById(R.id.password);
        email = findViewById(R.id.email);
        data = findViewById(R.id.datos);
        btnR = findViewById(R.id.btnRegistrar);

        btnR.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                registrar(v);
            }
        });

    }

    public void registrar(View v) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);

        builder.setIcon(R.mipmap.ic_launcher).
                setTitle("Tus datos son: " + "\n").
                setMessage(name.getText().toString() + "\n" + pass.getText().toString() + "\n" + email.getText().toString()).
                setPositiveButton("Aceptar", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(getApplicationContext(), "Registro exitoso", Toast.LENGTH_LONG).show();
                    }
                }).
                setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });
        AlertDialog alertDialog = builder.create();
        alertDialog.show();

        /*
        data.setText("Tus datos son: " +
                "\n" + name.getText().toString() +
                "\n" + pass.getText().toString() +
                "\n" + email.getText().toString());
        Toast.makeText(getApplicationContext(), "Registro exitoso", Toast.LENGTH_LONG).show();

         */
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    protected void onResume() {
        super.onResume();
    }

    public void goToLogin(View k){
        Intent ir = new Intent(this, LoginActivity.class);
        ir.addFlags(ir.FLAG_ACTIVITY_CLEAR_TOP | ir.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(ir);
    }

}