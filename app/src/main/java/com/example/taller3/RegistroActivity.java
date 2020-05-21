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
import android.widget.Toast;

public class RegistroActivity extends AppCompatActivity {

    EditText name, pass, email;
    TextView data;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro);

        name = findViewById(R.id.nombre);
        pass = findViewById(R.id.password);
        email = findViewById(R.id.email);
        data = findViewById(R.id.datos);
    }

    public void ingresar(View v) {
        data.setText("Tus datos son: " +
                "\n" + name.getText().toString() +
                "\n" + pass.getText().toString() +
                "\n" + email.getText().toString());
        Toast.makeText(getApplicationContext(), "Registro exitoso", Toast.LENGTH_LONG).show();
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