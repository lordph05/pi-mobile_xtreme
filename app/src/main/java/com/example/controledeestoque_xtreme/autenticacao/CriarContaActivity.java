package com.example.controledeestoque_xtreme.autenticacao;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.controledeestoque_xtreme.DAO.UserDAO;
import com.example.controledeestoque_xtreme.Endidades.User;
import com.example.controledeestoque_xtreme.R;
import com.example.controledeestoque_xtreme.Utils.BancoDeDados;

public class CriarContaActivity extends AppCompatActivity implements View.OnClickListener {

    // atributos
    private  EditText edit_nome, edit_email,edit_senha;
    private  Button btn_cria_conta;
    BancoDeDados bd;

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

    private boolean validaDados () {
        if (edit_nome.getText().length() ==0) {
            edit_nome.setError("Digite seu nome");
            edit_nome.requestFocus();
            return false;
        } else if (edit_email.getText().length() == 0) {
            edit_email.setError("* Digite seu e-mail");
            edit_email.requestFocus();
            return false;
        } else if (edit_senha.getText().length() == 0) {

            edit_senha.setError("* Digite sua senha");
            edit_senha.requestFocus();
            return false;
        }
        return true;
    }
    @Override
    public void onClick(View origem) {

        if (origem.getId()== R.id.btn_cria_conta){
            if (validaDados()){ // verifica se os campos não estão vazios

            }else {
                Toast.makeText(this, "Erro de validação", Toast.LENGTH_SHORT).show();
            }
capturarDados();
        }

    }
    private void capturarDados(){
        String nome = edit_nome.getText().toString();
        String email = edit_email.getText().toString();
        String senha = edit_senha.getText().toString();

        // salvar no banco de dados
        bd = Room.databaseBuilder(getApplicationContext(),BancoDeDados.class,"BancoApp").allowMainThreadQueries().build();
        // cria uma instancia do DAO
        UserDAO userDAO = bd.getuserDAO();

        // insere novo usario no banco e mostra na tela
        User user = new User();
        user.nome = nome;
        user.email=email;
        user.senha = senha;

        //insere o novo usuario no banco
        userDAO.insert(user);
        Toast.makeText(this, "Usuario Cadastrado", Toast.LENGTH_SHORT).show();
        finish();



    }

    private  void configClique () { // eventos de cliques fora da origem.
        findViewById(R.id.ib_voltar).setOnClickListener(view -> finish() );

        TextView text_titulo = findViewById(R.id.text_titulo);
        text_titulo.setText("Criar conta");

    }

}