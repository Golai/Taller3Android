package com.example.taller3;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

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

public class LoginActivity extends AppCompatActivity implements View.OnClickListener{

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
        btnRegistrar  = findViewById(R.id.btnRegistrar);

<<<<<<< HEAD
=======
    public void ingresar(View k) {
        Intent ir = new Intent(this, ListaLugares.class);
        ir.addFlags(ir.FLAG_ACTIVITY_CLEAR_TOP | ir.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(ir);
>>>>>>> 353e1390e3ff6e544f8dee8610b23d64f4bdc38f

        btnIngresar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               ingresar(this);
            }
        });


        /*
        signInButton = findViewById(R.id.sign_in_button);
        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail()
                .build();
        mGoogleSignInClient = GoogleSignIn.getClient(this, gso);
        findViewById(R.id.sign_in_button).setOnClickListener(this);
        */
    }

    @Override
    protected void onStart() {
        super.onStart();
        //GoogleSignInAccount account = GoogleSignIn.getLastSignedInAccount(this);
        //updateUI(account);
    }

    @Override
    protected void onPause() {
        super.onPause();
        //SharedPreferences datos = PreferenceManager.getDefaultSharedPreferences(this);
        //SharedPreferences.Editor datosEdit = datos.edit();
        //datosEdit.putString("data", data.toString());
        //datosEdit.apply();
    }

    protected void onResume() {
        super.onResume();
        //SharedPreferences datos = PreferenceManager.getDefaultSharedPreferences(this);
        //String data1 = data.toString();
        //data1 = datos.getString("data", " Aún no hay datos registrados");
        //data.setText(data1);
    }

    @Override
    public void onClick(View w) {
        //switch (v.getId()){
            //case R.id.sign_in_button:
                //signIn();
                //break;
            //default:
                //Toast.makeText(this, "Hola default", Toast.LENGTH_LONG).show();
        //}
    }

    /*
    private void updateUI(GoogleSignInAccount account) {
            Intent ir = new Intent(this,ListaLugares.class);
            ir.addFlags(ir.FLAG_ACTIVITY_CLEAR_TOP | ir.FLAG_ACTIVITY_CLEAR_TASK);
            //Bundle data = new Bundle();
            System.out.println(account.getEmail());
            //data.putString("name",account.getDisplayName().toString());
            //data.putString("pass",account.getEmail().toString());
            //<ir.putExtras(data);
            startActivity(ir);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        // Result returned from launching the Intent from GoogleSignInClient.getSignInIntent(...);
        if (requestCode == 1) {
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
            updateUI(account);
        } catch (ApiException e) {
            // The ApiException status code indicates the detailed failure reason.
            // Please refer to the GoogleSignInStatusCodes class reference for more information.
            //Log.w(TAG, "signInResult:failed code=" + e.getStatusCode());
            System.out.println("signInResult:failed code=" + e.getStatusCode());
            updateUI(null);
        }
    }

    private void signIn() {
        Intent signInIntent = mGoogleSignInClient.getSignInIntent();
        startActivityForResult(signInIntent,1);
    }

     */

    public void goToRegistro(View k){
        Intent ir = new Intent(this,RegistroActivity.class);
        ir.addFlags(ir.FLAG_ACTIVITY_CLEAR_TOP | ir.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(ir);
    }

    public void goToListar(){
        System.out.println("entre a la actividad listar");
        Intent ir = new Intent(this, ListaLugares.class);
        ir.addFlags(ir.FLAG_ACTIVITY_CLEAR_TOP | ir.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(ir);
    }

    public void ingresar(View.OnClickListener v) {
    //btnIngresar.setOnClickListener(new View.OnClickListener() {
    // @Override
    //public void onClick(View v) {

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
                    Toast.makeText(getApplicationContext(), "Error, campos vacios", Toast.LENGTH_LONG).show();
                }else{
                    if ((name.getText().toString().equals(json_data.getString("user"))) &&
                            (pass.getText().toString().equals(json_data.getString("passw")))) {
                        //System.out.println("soy un usuario, entre");
                        goToListar();
                    } else {
                        //System.out.println("usuario no registrado");
                        Toast.makeText(getApplicationContext(), "Usuario no registrado", Toast.LENGTH_LONG).show();
                    }
                }
            }
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }
        //}
        //});
    }
}