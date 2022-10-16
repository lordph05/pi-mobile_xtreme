package com.example.controledeestoque_xtreme.Admin;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.example.controledeestoque_xtreme.R;
import com.example.controledeestoque_xtreme.autenticacao.CriarContaActivity;
import com.example.controledeestoque_xtreme.autenticacao.LoginActivity;

public class Setor_AdminActivity extends AppCompatActivity implements View.OnClickListener {

    private TextView text_cadastrar_user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setor_admin);

        IniciarComponentes();

        text_cadastrar_user.setOnClickListener(this);


    }

    private void IniciarComponentes() {
        text_cadastrar_user = findViewById(R.id.text_cadastrar_user);
    }

    @Override
    public void onClick(View origem) {
        if (origem.getId() == R.id.text_cadastrar_user) {
            Intent intent = new Intent(Setor_AdminActivity.this, CriarContaActivity.class);
            startActivity(intent);
        }
    }
}