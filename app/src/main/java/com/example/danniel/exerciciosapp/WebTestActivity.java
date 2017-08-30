package com.example.danniel.exerciciosapp;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.exemple.danniel.util.Downloader;

public class WebTestActivity extends AppCompatActivity {

    Button btnSelecionar;
    TextView txtView;
    EditText edtCep;
    Context thisView = this;
    Downloader downloader;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_webtest);

        edtCep = (EditText) findViewById(R.id.edtCep);

        btnSelecionar = (Button) findViewById(R.id.btnSelecionar);
        btnSelecionar.setOnClickListener(btnSelecionarClick);

        txtView = (TextView) findViewById(R.id.txtView);
    }

    public TextView getTxtView(){
        return txtView;
    }

    private View.OnClickListener btnSelecionarClick = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            downloader = new Downloader(thisView);
            ((WebTestActivity) thisView).getTxtView().setText(R.string.buscando);
            if (!edtCep.getText().toString().isEmpty()) {
                downloader.setUrl("https://viacep.com.br/ws/"+ edtCep.getText().toString() +"/json/");
                downloader.execute();
                Log.d("TAG", "CEP : " + edtCep.getText().toString());
                Log.i("TAG", "edtCep.getText() != null");
            } else {
                Log.i("TAG", "edtCep.getText() == null");
            }
        }
    };
}
