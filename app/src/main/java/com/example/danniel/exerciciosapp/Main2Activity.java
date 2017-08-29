package com.example.danniel.exerciciosapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.exemple.danniel.util.Downloader;

public class Main2Activity extends AppCompatActivity {

    Button btnSelecionar;
    EditText edtTexto;

    Downloader downloader;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        downloader = new Downloader(this, "http://pokeapi.co/api/v2/pokemon/1/");
        btnSelecionar = (Button) findViewById(R.id.btnSelecionar);
        btnSelecionar.setOnClickListener(btnSelecionarClick);

        edtTexto = (EditText) findViewById(R.id.edtTexto);
    }

    public EditText getEdttexto(){
        return edtTexto;
    }

    private View.OnClickListener btnSelecionarClick = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            if (downloader != null) {
                downloader.execute();
                Log.i("TAG", "downloader != null");
            } else {
                Log.i("TAG", "downloader == null");
            }
        }
    };
}
