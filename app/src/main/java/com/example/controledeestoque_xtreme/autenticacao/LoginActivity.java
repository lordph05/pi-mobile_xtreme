package com.example.controledeestoque_xtreme.autenticacao;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.controledeestoque_xtreme.Activity.InforProdutoActivity;
import com.example.controledeestoque_xtreme.DAO.UserDAO;
import com.example.controledeestoque_xtreme.Endidades.User;
import com.example.controledeestoque_xtreme.R;
import com.example.controledeestoque_xtreme.Utils.BancoDeDados;
import com.google.android.material.snackbar.Snackbar;

import java.util.List;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {

    //atributos
    EditText edit_email, edit_senha;
    Button btn_login;
    TextView text_criar_conta, text_recuperar_conta;
    ProgressBar progressBar;
    String [] mensagens = {"Entre com e-mail e senha.", "outra mensagem"};
    BancoDeDados bd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        // captura os componente
        edit_email = findViewById(R.id.edit_email);
        edit_senha = findViewById(R.id.edit_senha);
        btn_login = findViewById(R.id.btn_login);
        text_criar_conta = findViewById(R.id.text_criar_conta);
        text_recuperar_conta = findViewById(R.id.text_recuperar_conta);
        progressBar = findViewById(R.id.progressBar);

        // eventos de Cliques
        text_criar_conta.setOnClickListener(this);
        text_recuperar_conta.setOnClickListener(this);
        btn_login.setOnClickListener(this);

    }


    // Métodos interface OnclickListener
    @Override
    public void onClick(View origem) {

        if (origem.getId() == R.id.text_criar_conta) {
            Intent intent = new Intent(LoginActivity.this, CriarContaActivity.class);
            startActivity(intent);
        } else if (origem.getId() == R.id.text_recuperar_conta) {
            Intent intent = new Intent(LoginActivity.this, RecuperarContaActivity.class);
            startActivity(intent);
        } else if (origem.getId() == R.id.btn_login) {
            // capturar email e senha
            String email = edit_email.getText().toString();
            String senha = edit_senha.getText().toString();

            if (email.isEmpty() || senha.isEmpty()){
                Snackbar snackbar = Snackbar.make(origem, mensagens[0],Snackbar.LENGTH_LONG);
                snackbar.setBackgroundTint(Color.WHITE);
                snackbar.setTextColor(Color.RED);
                snackbar.show();
            }
            // acesso ao banco
            bd = Room.databaseBuilder(getApplicationContext(), BancoDeDados.class, "BancoApp").allowMainThreadQueries().build();
            //obteando o DAO de user
            UserDAO userDAO = bd.getuserDAO();
            List<User> user = userDAO.getUser(email, senha);


            if (user.size() ==0) { // dados incorretos
                Toast toast = Toast.makeText(LoginActivity.this, "Dados do usuário incorretos!", Toast.LENGTH_SHORT);
                toast.show();
                progressBar.setVisibility(View.VISIBLE);
            } else { // dados corretos (permissão concedida)
                // mudar de tela
                Intent intent = new Intent(LoginActivity.this, InforProdutoActivity.class);
                startActivity(intent);
                progressBar.setVisibility(View.GONE);


            }
        }
    }
}