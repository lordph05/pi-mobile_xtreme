package com.example.controledeestoque_xtreme.autenticacao;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.controledeestoque_xtreme.R;

public class CriarContaActivity extends AppCompatActivity implements View.OnClickListener {

    // atributos
    private  EditText edit_nome, edit_email,edit_senha;
    private  Button btn_cria_conta;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_criar_conta);

        configClique ();

        // captura dos componentes
        edit_nome = findViewById(R.id.edit_nome);
        edit_email = findViewById(R.id.edit_email);
        edit_senha = findViewById(R.id.edit_senha);
        btn_cria_conta = findViewById(R.id.btn_cria_conta);

        //eventos de cliques dos componentes
        btn_cria_conta.setOnClickListener(this);

    }

    @Override
    public void onClick(View origem) {

        if (origem.getId()== R.id.btn_cria_conta){

        }

    }

    private  void configClique () { // eventos de cliques fora da origem.
        findViewById(R.id.ib_voltar).setOnClickListener(view -> finish() );

        TextView text_titulo = findViewById(R.id.text_titulo);
        text_titulo.setText("Criar conta");

    }

}