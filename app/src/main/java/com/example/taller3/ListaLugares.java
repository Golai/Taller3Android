package com.example.taller3;

import android.os.AsyncTask;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class ListaLugares extends AppCompatActivity {

    String DATA_URL="";
    Consultar obj= null;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listar);

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
