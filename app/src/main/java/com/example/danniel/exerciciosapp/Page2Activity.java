package com.example.danniel.exerciciosapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class Page2Activity extends AppCompatActivity {

    private Button btnSair;
    private TextView texto;
    private Button btnVoltar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_page2);

        btnVoltar = (Button) findViewById(R.id.btnVoltar);
        btnSair = (Button) findViewById(R.id.btnSair);
        texto = (TextView) findViewById(R.id.texto);

        Intent it = getIntent();

        texto.setText("Email: " +
                it.getStringExtra("email") +
                " Senha: " +
                it.getStringExtra("senha"));

        btnVoltar.setOnClickListener(btnVoltarClick);
        btnSair.setOnClickListener(btnSairClick);
    }

    protected View.OnClickListener btnVoltarClick = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            Intent it = new Intent();

            String pkgName = this.getClass().getPackage().toString().substring(8);

            it.setClassName(pkgName, pkgName+".MainActivity");
            startActivity(it);
        }
    };

    protected View.OnClickListener btnSairClick = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            finish();
        }
    };
}
