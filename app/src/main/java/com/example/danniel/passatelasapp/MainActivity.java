package com.example.danniel.passatelasapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;


public class MainActivity extends AppCompatActivity {

    private Button btnLogin;
    private EditText edtEmail;
    private EditText edtSenha;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnLogin = (Button) findViewById(R.id.btnLogin);
        edtEmail = (EditText) findViewById(R.id.edtEmail);
        edtSenha = (EditText) findViewById(R.id.edtSenha);

        btnLogin.setOnClickListener(btnLoginClick);

    }

    //Testando método específico de ação do click
    protected View.OnClickListener btnLoginClick = new View.OnClickListener() {
        public void onClick(View v) {
            Intent it = new Intent();

            if (!edtEmail.getText().toString().isEmpty()) {
                it.putExtra("email", edtEmail.getText().toString());
            }

            it.putExtra("senha", edtSenha.getText().toString());

            String pkgName  = this.getClass().getPackage().toString().substring(8);

            Log.i("PassaTelasApp", pkgName);

            it.setClassName(pkgName, pkgName+".Page2Activity");
            startActivity(it);
        }
    };
}
