package com.example.controledeestoque_xtreme.Admin;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.controledeestoque_xtreme.R;
import com.example.controledeestoque_xtreme.autenticacao.CriarContaActivity;
import com.example.controledeestoque_xtreme.autenticacao.LoginActivity;

public class Setor_AdminActivity extends AppCompatActivity implements View.OnClickListener {

  private Button btn_cadastrar_user,btn_excluir_user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setor_admin);

        IniciarComponentes();

        btn_cadastrar_user.setOnClickListener(this);
        btn_excluir_user.setOnClickListener(this);


    }

    private void IniciarComponentes() {
        btn_cadastrar_user = findViewById(R.id.btn_cadastrar_user);
        btn_excluir_user = findViewById(R.id.btn_excluir_user);


        findViewById(R.id.ib_voltar).setOnClickListener(view -> finish() );

        TextView text_titulo = findViewById(R.id.text_titulo);
        text_titulo.setText("Area do administrador");
    }

    @Override
    public void onClick(View origem) {
        if (origem.getId() == R.id.btn_cadastrar_user) {
            Intent intent = new Intent(Setor_AdminActivity.this, CriarContaActivity.class);
            startActivity(intent);

        }else if (origem.getId() == R.id.btn_excluir_user){
            Intent intent = new Intent(Setor_AdminActivity.this, ExcluirUserActivity.class);
            startActivity(intent);
            
        }
    }
}