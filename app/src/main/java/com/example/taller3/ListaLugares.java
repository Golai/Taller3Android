package com.example.taller3;

import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.SpinnerAdapter;

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

public class ListaLugares extends AppCompatActivity {

    String DATA_URL="http://www.mocky.io/v2/5ea8e7e02d000097883a4159";
    Consultar obj= null;
    private Spinner spinDeparta;
    private ListView listaProvi;
    List<String> usu = new ArrayList<String>();

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listar);

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
        ClassConnection connectio =new ClassConnection();
        try {
            String response = connectio.execute("http://www.mocky.io/v2/5ec546622f000094c8dc3125").get();

            ////Leer el formato
            JSONArray jsonArray=new JSONArray(response);
            for (int i=0; i<response.length();i++){
                JSONObject jsonObject= jsonArray.getJSONObject(i);
                String usuario = jsonObject.getString("user");
                usu.add(usuario);
            }
            spinDeparta.setAdapter((SpinnerAdapter) usu);




        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }


    }

    public void consultar(View q){
        obj = new Consultar();
        obj.execute();
    }

    public void setContentView(int activity_listar) {
    }


    class Consultar extends AsyncTask<Void, Void, Void> {
        /**
         * Override this method to perform a computation on a background thread. The
         * specified parameters are the parameters passed to {@link #execute}
         * by the caller of this task.
         * <p>
         * This will normally run on a background thread. But to better
         * support testing frameworks, it is recommended that this also tolerates
         * direct execution on the foreground thread, as part of the {@link #execute} call.
         * <p>
         * This method can call {@link #publishProgress} to publish updates
         * on the UI thread.
         *
         * @param voids The parameters of the task.
         * @return A result, defined by the subclass of this task.
         * @see #onPreExecute()
         * @see #onPostExecute
         * @see #publishProgress
         */
        @Override
        protected Void doInBackground(Void... voids) {
            URL urlConexion =null;
            try {
                 urlConexion=new URL(DATA_URL);
                HttpURLConnection conexion=(HttpURLConnection) urlConexion.openConnection();
                int conexionStatus = conexion.getResponseCode();
                if (conexionStatus == HttpURLConnection.HTTP_OK){
                    System.out.println(conexion.getInputStream());

                }else{
                    System.out.println("error" + conexionStatus);
                }
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }


            return null;
        }

        /**
         * Runs on the UI thread after {@link #publishProgress} is invoked.
         * The specified values are the values passed to {@link #publishProgress}.
         * The default version does nothing.
         *
         * @param values The values indicating progress.
         * @see #publishProgress
         * @see #doInBackground
         */
        @Override
        protected void onProgressUpdate(Void... values) {
            super.onProgressUpdate(values);
        }
    }
}
