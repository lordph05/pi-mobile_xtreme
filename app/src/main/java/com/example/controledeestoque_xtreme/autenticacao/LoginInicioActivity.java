package com.example.controledeestoque_xtreme.autenticacao;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.controledeestoque_xtreme.R;

public class LoginInicioActivity extends AppCompatActivity implements View.OnClickListener {
    //atributos
    Button btn_login;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_inicio);

        //capturar componentes
        btn_login = findViewById(R.id.btn_login);

        // eventos de cliques
        btn_login.setOnClickListener(this);
    }

    @Override
    public void onClick(View origem) {
        if (origem.getId() == R.id.btn_login) {
            Intent intent = new Intent(LoginInicioActivity.this, LoginActivity.class);
            startActivity(intent);

        }
    }
}

