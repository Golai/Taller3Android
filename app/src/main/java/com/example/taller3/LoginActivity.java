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

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.concurrent.ExecutionException;

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
    }

    public void ingresar(View k) {
        Intent ir = new Intent(this, ListaLugares.class);
        ir.addFlags(ir.FLAG_ACTIVITY_CLEAR_TOP | ir.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(ir);

        /*
        btnIngresar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ClassConnection conexion = new ClassConnection();
                String respuesta;
                try {
                    respuesta = conexion.execute("http://www.mocky.io/v2/5ec53e922f0000d4c7dc30cf").get();
                    JSONObject jsonObject = new JSONObject(respuesta);
                    JSONArray lista = jsonObject.optJSONArray("areas");
                } catch (ExecutionException e) {
                    e.printStackTrace();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });
         */
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