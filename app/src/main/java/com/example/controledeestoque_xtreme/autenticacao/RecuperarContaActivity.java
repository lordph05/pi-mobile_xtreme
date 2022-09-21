package com.example.controledeestoque_xtreme.autenticacao;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.controledeestoque_xtreme.R;

public class RecuperarContaActivity extends AppCompatActivity implements View.OnClickListener {

    // atributos
    private EditText edit_email;
    private Button btn_recuperar_senha;

    // captura dos componentes



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recuperar_conta);

        configClique ();
    }

    @Override
    public void onClick(View origem) {
        if (origem.getId()== R.id.btn_recuperar_senha){

        }

    }

    private  void configClique () { // eventos de cliques fora da origem.
        findViewById(R.id.ib_voltar).setOnClickListener(view -> finish() );

        TextView text_titulo = findViewById(R.id.text_titulo);
        text_titulo.setText("Recuperar senha");

    }
    }
