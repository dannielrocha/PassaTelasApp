package com.example.danniel.exerciciosapp;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.EditText;
import android.widget.TextView;

import com.example.danniel.util.Downloader;

import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.ViewById;

@EActivity
public class WebTestActivity extends AppCompatActivity {

    @ViewById(R.id.txtView)
    TextView txtView;

    @ViewById(R.id.edtCep)
    EditText edtCep;

    Context thisView = this;
    Downloader downloader;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_webtest);

        //edtCep = (EditText) findViewById(R.id.edtCep);

        //btnSelecionar = (Button) findViewById(R.id.btnSelecionar);
        //btnSelecionar.setOnClickListener(btnSelecionarClick);

        //txtView = (TextView) findViewById(R.id.txtView);
    }

    public TextView getTxtView(){
        return txtView;
    }

    @Click
    void btnSelecionar() {
            downloader = new Downloader(thisView);
            txtView.setText(R.string.buscando);

            if (!edtCep.getText().toString().isEmpty()) {
                downloader.setUrl("https://viacep.com.br/ws/"+ edtCep.getText().toString() +"/json/");
                downloader.execute();
                Log.d("TAG", "CEP : " + edtCep.getText().toString());
                Log.i("TAG", "edtCep.getText() != null");
            } else {
                Log.i("TAG", "edtCep.getText() == null");
            }
    }
}
