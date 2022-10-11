package com.example.controledeestoque_xtreme.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import com.example.controledeestoque_xtreme.R;
import com.example.controledeestoque_xtreme.autenticacao.Cadastro_UserActivity;
import com.example.controledeestoque_xtreme.autenticacao.LoginActivity;

public class InforProdutoActivity extends AppCompatActivity {

    private Button btn_hardware, btn_perifericos, btn_redes_conectividade,btn_cad_user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_infor_produto);

        iniciaComponentes ();
        configClique();
    }

    private  void iniciaComponentes (){

        btn_hardware = findViewById(R.id.btn_hardware);
        btn_perifericos = findViewById(R.id.btn_perifericos);
        btn_redes_conectividade = findViewById(R.id.btn_redes_conectividade);
        btn_cad_user = findViewById(R.id.btn_cad_user);

        TextView text_titulo = findViewById(R.id.text_titulo);
        text_titulo.setText("Categoria");


    }
    private void configClique() { // eventos de cliques fora da origem.
        btn_hardware.setOnClickListener(view -> startActivity(new Intent(this, MainActivity.class)));
        btn_cad_user.setOnClickListener(view -> startActivity(new Intent(this, Cadastro_UserActivity.class)));
        findViewById(R.id.ib_voltar).setOnClickListener(view -> startActivity(new Intent(this, LoginActivity.class)));
//        btn_perifericos.setOnClickListener(view -> startActivity(new Intent(this, MainPerifericosActivity.class)));
//        btn_redes_conectividade.setOnClickListener(view -> startActivity(new Intent(this,Redes_ConectividadeActivity.class)));

    }
}