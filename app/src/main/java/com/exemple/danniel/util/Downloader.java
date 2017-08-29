package com.exemple.danniel.util;

import android.content.Context;
import android.os.AsyncTask;
import android.os.Looper;
import android.util.Log;
import android.widget.Toast;

import com.example.danniel.exerciciosapp.Main2Activity;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * @author danniel on 29/08/17.
 */

public class Downloader extends AsyncTask<Void,Void,String> {

    private static final String TAG = "DWLDR";

    private URL url;
    private Context ctx;

    public Downloader(Context _ctx, String _url) {
        if (_ctx != null)
            this.ctx = _ctx;

        try {
            url = new URL(_url);
        } catch (MalformedURLException e) {
            e.printStackTrace();
            Toast.makeText(ctx, "Endereço (URL) incorreto ou inexistente.", Toast.LENGTH_LONG).show();
        }
    }

    @Override
    protected String doInBackground(Void... params) {
        HttpURLConnection urlConnection = null;
        BufferedReader reader = null;

        Log.i(TAG, "Iniciando...");
        Log.d(TAG, ctx.toString());
        Log.d(TAG, url.toString());
        Log.d(TAG, url.getFile());
        try {
            urlConnection = (HttpURLConnection) url.openConnection();
            urlConnection.setRequestMethod("GET");
            urlConnection.connect();

            InputStream inputStream = urlConnection.getInputStream();
            reader = new BufferedReader(new InputStreamReader(inputStream));

            String linha;
            StringBuffer buffer = new StringBuffer();
            while((linha = reader.readLine()) != null) {
                buffer.append(linha);

                Log.i(TAG, "linha = " + linha);

                buffer.append("\n");
            }
            return buffer.toString();
        } catch (Exception e) {
            e.printStackTrace();
            if (urlConnection != null) {
                urlConnection.disconnect();
            }
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
            }
        }
        return null;
    }

    @Override
    protected void onPostExecute(String dados) {
        Log.d(TAG, "ctx.getClass().toString() = " + ctx.getClass().toString());
        Log.d(TAG, "Main2Activity.class.toString() = " + Main2Activity.class.toString());
        if (ctx instanceof Main2Activity) {


            if (dados != null) {
                dados = dados.replace("\\/", "/").replace(",", "\n");
            ((Main2Activity) ctx).getEdttexto().setText(dados);
                Log.i(TAG, dados);
            } else {
                Log.e(TAG, "Dados não encontrados.");
            }

            Log.d(TAG, "setText executado");
        } else {
            Log.e(TAG, "setText não executado");
        }
        Log.d(TAG, "Processo finalizado");
    }
}
