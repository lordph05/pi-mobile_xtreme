package com.example.controledeestoque_xtreme.Menu.Cat_hardware;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import com.example.controledeestoque_xtreme.Menu.Cat_Computador.ListaComputador;
import com.example.controledeestoque_xtreme.Menu.Cat_Conectividade.ListaConectividade;
import com.example.controledeestoque_xtreme.Menu.Cat_periferico.ListaPeriferico;
import com.example.controledeestoque_xtreme.R;
import com.example.controledeestoque_xtreme.autenticacao.LoginActivity;

public class MenuFuncionario extends AppCompatActivity {

    private Button btn_hardware, btn_perifericos, btn_redes_conectividade,btn_sair,btn_computadores;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_funcionario);
        iniciaComponentes ();
        configClique();
    }

    private  void iniciaComponentes (){

        btn_hardware = findViewById(R.id.btn_hardware);
        btn_perifericos = findViewById(R.id.btn_perifericos);
        btn_redes_conectividade = findViewById(R.id.btn_redes_conectividade);
        btn_computadores = findViewById(R.id.btn_computadores);
        btn_sair = findViewById(R.id.btn_sair);

        TextView text_titulo = findViewById(R.id.text_titulo);
        text_titulo.setText("Categoria");


    }
    private void configClique() { // eventos de cliques fora da origem.
        btn_hardware.setOnClickListener(view -> startActivity(new Intent(this, ListaHardware.class)));
        btn_perifericos.setOnClickListener(view -> startActivity(new Intent(this, ListaPeriferico.class)));
        btn_redes_conectividade.setOnClickListener(view -> startActivity(new Intent(this, ListaConectividade.class)));
        btn_computadores.setOnClickListener(view -> startActivity(new Intent(this, ListaComputador.class)));
        btn_sair.setOnClickListener(view -> startActivity(new Intent(this, LoginActivity.class)));
        findViewById(R.id.ib_voltar).setOnClickListener(view -> startActivity(new Intent(this, MenuFuncionario.class)));
//        btn_perifericos.setOnClickListener(view -> startActivity(new Intent(this, MainPerifericosActivity.class)));
//        btn_redes_conectividade.setOnClickListener(view -> startActivity(new Intent(this,Redes_ConectividadeActivity.class)));

    }

    }
