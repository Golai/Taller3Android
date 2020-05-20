package com.example.taller3;
import android.os.AsyncTask;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class ClassConnection extends AsyncTask<String, String, String> {

    @Override
    protected String doInBackground(String... strings) {
        HttpURLConnection conexion = null;
        URL urlConexion = null;

        try {
            urlConexion = new URL(strings[0]);
            conexion = (HttpURLConnection) urlConexion.openConnection();
            conexion.connect();

            int connectionStatus = conexion.getResponseCode();
            if (connectionStatus == HttpURLConnection.HTTP_OK) {// LA CONEXION FUE EXITOSA
                System.out.println("HOLA RESPUESTA " + conexion.getInputStream());
                InputStream in = new BufferedInputStream(conexion.getInputStream());
                BufferedReader reader = new BufferedReader(new InputStreamReader(in));

                String linea = "";
                StringBuffer buffer = new StringBuffer();
                while ((linea = reader.readLine()) != null) { //guardo cada linea para luego se añaden al buffer
                    buffer.append(linea);
                }

                return buffer.toString();

            } else {
                System.out.println(connectionStatus);
            }

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;

    }
}
