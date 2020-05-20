package com.example.taller3;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
public class LoginActivity extends AppCompatActivity implements View.OnClickListener{

    EditText name, pass;
    TextView data;
    Button btnIngresar, btnRegistrar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        name = findViewById(R.id.nombre);
        pass = findViewById(R.id.password);
        btnIngresar = findViewById(R.id.btnIngresar);
        btnRegistrar  = findViewById(R.id.btnRegistrar);
        data = findViewById(R.id.datos);

        //btnRegistrar.setOnClickListener(this);
    }

    public void ingresar(View v) {
        data.setText(name.getText().toString() +
                "\n" + pass.getText().toString());
    }


    @Override
    protected void onPause() {
        super.onPause();
        SharedPreferences datos = PreferenceManager.getDefaultSharedPreferences(this);
        SharedPreferences.Editor datosEdit = datos.edit();
        datosEdit.putString("data", data.toString());
        datosEdit.apply();
    }

    protected void onResume() {
        super.onResume();
        SharedPreferences datos = PreferenceManager.getDefaultSharedPreferences(this);
        String data1 = data.toString();
        data1 = datos.getString("data", " Aún no hay datos registrados");
        data.setText(data1);
    }

    @Override
    public void onClick(View v) {

    }

    public void goToRegistro(View k){
        Intent ir = new Intent(this,RegistroActivity.class);
        ir.addFlags(ir.FLAG_ACTIVITY_CLEAR_TOP | ir.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(ir);
    }


}