package com.exemple.danniel.util;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.Toast;

import com.example.danniel.exerciciosapp.WebTestActivity;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import org.json.*;

/**
 * @author danniel on 29/08/17.
 */

public class Downloader extends AsyncTask<Void,Void,String> {

    private static final String TAG = "DWLDR";
    private static final int MAX_LENGHT = 45;

    private URL url;
    private Context ctx;

    public Downloader(Context _ctx) {
        if (_ctx != null)
            this.ctx = _ctx;
    }

    public Downloader(Context _ctx, String _url) {
        if (_ctx != null)
            this.ctx = _ctx;

        setUrl(_url);
    }

    @Override
    protected String doInBackground(Void... params) {
        HttpURLConnection urlConnection = null;
        BufferedReader reader = null;

        Log.i(TAG, "Iniciando...");
        Log.d(TAG, "linha 50... Contexto : " + ctx.toString());
        Log.d(TAG, "linha 51... URL : " + url.toString());

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

                Log.i(TAG, "linha 66... linha do buffer = " + linha);

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
        Log.d(TAG, "WebTestActivity.class.toString() = " + WebTestActivity.class.toString());
        if (ctx instanceof WebTestActivity) {
            StringBuilder aux = new StringBuilder();

            if (dados != null) {
                JSONObject cep;
                try {
                    cep = new JSONObject(dados);
                    for (int i = 0; i < cep.length(); i++) {
                        String chave = cep.names().get(i) + " : ";
                        String valor = cep.getString(cep.names().get(i).toString());
                        valor = valor.length() > MAX_LENGHT ? valor.substring(0,MAX_LENGHT) : valor;

                        if (!valor.isEmpty())
                            aux.append(chave + valor + "\n");

                        Log.i(TAG, "linha 111..." + cep.names().get(i) + " : \t" + cep.getString(cep.names().get(i).toString()));
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }


                ((WebTestActivity) ctx).getTxtView().setText(aux.toString());

                Log.i(TAG, dados);
            } else {
                Log.e(TAG, "Dados não encontrados.");
            }

            Log.d(TAG, "setText executado");
        } else {
            Log.e(TAG, "setText não executado");
        }
        Log.d(TAG, "Processo finalizado");
        cancel(true);
    }

    public void setUrl(String _url) {
        try {
            url = new URL(_url);
        } catch (MalformedURLException e) {
            e.printStackTrace();
            Toast.makeText(ctx, "Endereço (URL) incorreto ou inexistente.", Toast.LENGTH_LONG).show();
        }
    }

    public URL getUrl(){
        return url;
    }
}
