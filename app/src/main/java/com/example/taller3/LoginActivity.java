package com.example.taller3;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.Network;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.util.Log;
import android.util.SparseBooleanArray;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.taller3.servicios.Servicio;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.SignInButton;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.Task;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {

    EditText name, pass;
    Button btnIngresar, btnRegistrar;
    GoogleSignInClient mGoogleSignInClient;
    SignInButton signInButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        name = findViewById(R.id.nombre);
        pass = findViewById(R.id.password);
        btnIngresar = findViewById(R.id.btnIngresar);
        btnRegistrar = findViewById(R.id.btnRegistrar);
        signInButton = findViewById(R.id.sign_in_button);

        boolean goli = conexion();
        if (goli == true) {
            btnIngresar.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    ingresar(this);
                }
            });


            GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                    .requestEmail()
                    .build();
            mGoogleSignInClient = GoogleSignIn.getClient(this, gso);
            findViewById(R.id.sign_in_button).setOnClickListener(this);
        }
    }

    @Override
    protected void onStart() {
        super.onStart();
        GoogleSignInAccount account = GoogleSignIn.getLastSignedInAccount(this);
        //updateUI(account);

    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    protected void onResume() {
        super.onResume();
    }

    private void signIn() {
        Intent signInIntent = mGoogleSignInClient.getSignInIntent();
        startActivityForResult(signInIntent, 0);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        // Result returned from launching the Intent from GoogleSignInClient.getSignInIntent(...);
        if (requestCode == 0) {
            // The Task returned from this call is always completed, no need to attach
            // a listener.
            Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);
            handleSignInResult(task);
        }
    }

    private void handleSignInResult(Task<GoogleSignInAccount> completedTask) {
        try {
            GoogleSignInAccount account = completedTask.getResult(ApiException.class);

            // Signed in successfully, show authenticated UI.
            Intent ir = new Intent(this, ListaLugares.class);
            ir.addFlags(ir.FLAG_ACTIVITY_CLEAR_TOP | ir.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(ir);
        } catch (ApiException e) {
            // The ApiException status code indicates the detailed failure reason.
            // Please refer to the GoogleSignInStatusCodes class reference for more information.
            //Log.w("Error", "signInResult:failed code=" + e.getStatusCode());
            System.out.println("signInResult:failed code=" + e.getStatusCode());
            //updateUI(null);
        }
    }

    @Override
    public void onClick(View w) {
        switch (w.getId()) {
            case R.id.sign_in_button:
                signIn();
                break;
        }
    }

    public void goToRegistro(View k) {
        Intent ir = new Intent(this, RegistroActivity.class);
        ir.addFlags(ir.FLAG_ACTIVITY_CLEAR_TOP | ir.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(ir);
    }

    public void goToListar() {
        System.out.println("entre a la actividad listar");
        Intent ir = new Intent(this, ListaLugares.class);
        ir.addFlags(ir.FLAG_ACTIVITY_CLEAR_TOP | ir.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(ir);
    }

    public boolean conexion() {
        boolean con = false;
        ConnectivityManager connectivityManager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        boolean isWifiConn = false;
        boolean isMobileConn = false;

        for (Network network : connectivityManager.getAllNetworks()) {
            NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();
            if (networkInfo.getType() == ConnectivityManager.TYPE_WIFI) {
                isWifiConn |= networkInfo.isConnected();
            }
            if (networkInfo.getType() == ConnectivityManager.TYPE_MOBILE) {
                isMobileConn |= networkInfo.isConnected();
            }
        }
        System.out.println("ya verifico");
        if (isWifiConn) {
            System.out.println("wifi conectado  ");
            con = true;
            try {
                Toast.makeText(this, "Hay conexión por wi-fi", Toast.LENGTH_LONG).show();
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        } else if (isMobileConn) {
            System.out.println("datos conectado   ");
            con = true;
            try {
                Toast.makeText(this, "Hay conexión por datos", Toast.LENGTH_LONG).show();
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        } else {
            con = false;
            Toast.makeText(this, "No hay conexión", Toast.LENGTH_LONG).show();
        }
        return con;
    }

    public void ingresar(View.OnClickListener v) {
        //btnIngresar.setOnClickListener(new View.OnClickListener() {
        // @Override
        //public void onClick(View v) {
        //boolean goli = conexion();
        //if (goli == true) {
        ClassConnection conexion = new ClassConnection();
        String respuesta;
        try {
            List<String> con = new ArrayList<String>();
            respuesta = conexion.execute("http://www.mocky.io/v2/5ec53e922f0000d4c7dc30cf").get();
            JSONObject jsonObject = new JSONObject(respuesta);
            JSONArray lista = jsonObject.optJSONArray("lugare");

            for (int i = 0; i < lista.length(); i++) {
                JSONObject json_data = lista.getJSONObject(i);
                if (name.getText().toString().equals("") && pass.getText().toString().equals("")) {
                    //System.out.println("error, campos vacios");
                    Toast.makeText(getApplicationContext(), "Error, campos vacios o usuario no registrado", Toast.LENGTH_LONG).show();
                } else if ((name.getText().toString().equals(json_data.getString("user"))) &&
                        (pass.getText().toString().equals(json_data.getString("passw")))) {
                    System.out.println("soy un usuario, entre");
                    goToListar();
                } else {
                    System.out.println("usuario no registrado");
                    //Toast.makeText(this, "Usuario no registrado", Toast.LENGTH_LONG).show();
                }
            }
        } catch (InterruptedException ex) {
            ex.printStackTrace();
        } catch (ExecutionException ex) {
            ex.printStackTrace();
        } catch (JSONException ex) {
            ex.printStackTrace();
        }

        //} else {
        //Toast.makeText(this, "Sin conexion: ", Toast.LENGTH_LONG).show();
        //}
    }


}