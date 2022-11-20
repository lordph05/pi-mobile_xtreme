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

import com.example.controledeestoque_xtreme.Endidades.Produtos;
import com.example.controledeestoque_xtreme.Menu.MenuPrincipal.MenuFuncionario;
import com.example.controledeestoque_xtreme.Menu.MenuPrincipal.MenuAdmin;
import com.example.controledeestoque_xtreme.DAO.UserDAO;
import com.example.controledeestoque_xtreme.Endidades.User;
import com.example.controledeestoque_xtreme.R;
import com.example.controledeestoque_xtreme.Utils.BancoDeDados;
import com.example.controledeestoque_xtreme.Utils.SessionData;
import com.google.android.material.snackbar.Snackbar;

import java.util.List;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {

    //atributos
    EditText edit_email, edit_senha;
    Button btn_login;
    TextView text_criar_conta, text_recuperar_conta,text_perfil;
    ProgressBar progressBar;
    String [] mensagens = {"Entre com e-mail e senha.", "outra mensagem"};
    BancoDeDados bd;
    

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        RestabelecerDados();// Temporario

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
            progressBar.setVisibility(View.VISIBLE);

            // capturar email e senha
            String email = edit_email.getText().toString();
            String senha = edit_senha.getText().toString();


            if (email.isEmpty() || senha.isEmpty()){
                Snackbar snackbar = Snackbar.make(origem, mensagens[0],Snackbar.LENGTH_LONG);
                snackbar.setBackgroundTint(Color.WHITE);
                snackbar.setTextColor(Color.RED);
                snackbar.show();
                progressBar.setVisibility(View.GONE);
                return;
            }
            // acesso ao banco
            bd = Room.databaseBuilder(getApplicationContext(), BancoDeDados.class, "BancoApp").allowMainThreadQueries().build();
            //obteando o DAO de user
            UserDAO userDAO = bd.getuserDAO();
            List<User> user = userDAO.getUser(email, senha);

            if (user.size() ==0) { // dados incorretos
                Toast toast = Toast.makeText(LoginActivity.this, "Dados do usuário incorretos!", Toast.LENGTH_SHORT);
                progressBar.setVisibility(View.GONE);
                toast.show();

            } else { // dados corretos (permissão concedida)
                //salva usuario logado no Singleton
                SessionData.getInstance().setUserLogado(user.get(0));
                //verificar se o usuario que acabou de logar é admin.
                if (user.get(0).perfil.equals("admin")){
                    Intent intent = new Intent(LoginActivity.this, MenuAdmin.class);
                    progressBar.setVisibility(View.GONE);
                    startActivity(intent);
                }else {
                    Intent intent = new Intent(LoginActivity.this, MenuFuncionario.class);
                    progressBar.setVisibility(View.GONE);
                    startActivity(intent);
                }
            }
        }
    }
    //Funcao Temporaria Para Reseta os dados no BancodeDados
    private void RestabelecerDados (){
        //TODO limpa todos os dados do banco
        bd = Room.databaseBuilder(getApplicationContext(), BancoDeDados.class, "BancoApp").allowMainThreadQueries().build();
//        bd.clearAllTables();
        //TODO Inserir Dados de Usuarios e Produtos
        for (int i=1; i<=5; i++) {
            bd.getuserDAO().insert(new User(i, "nome"+i, "ph.jesus@gmail.com", "admin", "123"));
        }
//        for (int i=1; i<=5; i++) {
//            bd.getuserDAO().insert(new User(i, "nome"+i, "fabriciosousa@gmail.com", "user", "1234"));
//        }
        //Inseção de Hardware
        for (int i=1; i<=10; i++) {
            bd.getProdutoDAO().insert(new Produtos(i,"Hardware"+i,10+i,100.0+i,90.0+i,1));
        }
    }
}