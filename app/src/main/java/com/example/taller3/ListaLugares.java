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

public class ListaLugares extends AppCompatActivity implements AdapterView.OnItemClickListener {

    String DATA_URL="http://www.mocky.io/v2/5ea8e7e02d000097883a4159";
    private Spinner spinUsuarios;
    private ListView listaProvi;
    List<String> usu;

    ArrayAdapter<String> adapter_option = null;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listar);
        try {
            traerDatos();
        } catch (JSONException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }






        //ArrayAdapter<String> adapter_option=new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item,usu)
        //spinUsuarios.setAdapter((SpinnerAdapter) usu);



    }


    public void traerDatos() throws JSONException, ExecutionException, InterruptedException {
        ClassConnection connectio =new ClassConnection();
        usu= new ArrayList<>();
        String response = connectio.execute("http://www.mocky.io/v2/5ec546622f000094c8dc3125").get();
        ////Leer el formato
        JSONObject jsonObject= new JSONObject(response);
        JSONArray lista = jsonObject.optJSONArray("usuarios");
        String[] u2=new String[lista.length()];
        for (int i=0; i<lista.length();i++){
            JSONObject json_data = lista.getJSONObject(i);
            String usuario = json_data.getString("user");
            usu.add(usuario);
        }


        spinUsuarios=(Spinner)findViewById(R.id.spinUsuarios);
        adapter_option = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item,usu);
        adapter_option.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        System.out.println("asdfasdfad"+ adapter_option);
        //listaProvi=(ListView)findViewById(R.id.listProvi);
        spinUsuarios.setAdapter(adapter_option);
        //spinUsuarios.setAdapter(new ArrayAdapter<String>(ListaLugares.this, android.R.layout.simple_spinner_dropdown_item,u2));
        //adapter_option.setDropDownViewResource(android.R.layout.simple_expandable_list_item_1);
        //spinUsuarios.setAdapter(adapter_option);
        //System.out.println("spin  "+spinUsuarios);

    }
    /*public void consultar(){
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
*/
    public void setContentView(int activity_listar) {
    }


    /**
     * Callback method to be invoked when an item in this AdapterView has
     * been clicked.
     * <p>
     * Implementers can call getItemAtPosition(position) if they need
     * to access the data associated with the selected item.
     *
     * @param parent   The AdapterView where the click happened.
     * @param view     The view within the AdapterView that was clicked (this
     *                 will be a view provided by the adapter)
     * @param position The position of the view in the adapter.
     * @param id       The row id of the item that was clicked.
     */
    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

    }
}
