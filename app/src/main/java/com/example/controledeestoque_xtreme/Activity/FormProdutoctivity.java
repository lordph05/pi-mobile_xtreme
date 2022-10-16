package com.example.controledeestoque_xtreme.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.controledeestoque_xtreme.DAO.ProdutoDAO;
import com.example.controledeestoque_xtreme.DAO.UserDAO;
import com.example.controledeestoque_xtreme.Endidades.Produtos;
import com.example.controledeestoque_xtreme.Endidades.User;
import com.example.controledeestoque_xtreme.R;
import com.example.controledeestoque_xtreme.Utils.BancoDeDados;
import com.google.android.material.snackbar.Snackbar;

import java.util.List;

public class FormProdutoctivity extends AppCompatActivity  implements View.OnClickListener{

    private EditText edit_produto;
    private EditText edit_quantidade;
    private EditText edit_valor;
    private EditText edit_valor_custo;
    private Button btn_salvar;
    String [] mensagens = {"preencha todos os campos", "outra mensagem"};
    BancoDeDados bd;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form_produtoctivity);
         IniciarComponentes ();
        //eventos de cliques dos componentes
        btn_salvar.setOnClickListener(this);
    }

    private void IniciarComponentes (){
        // captura dos componentes
        edit_produto = findViewById(R.id.edit_produto);
        edit_quantidade = findViewById(R.id.edit_quantidade);
        edit_valor = findViewById(R.id.edit_valor);
        edit_valor_custo = findViewById(R.id.edit_valor_custo);
        btn_salvar = findViewById(R.id.btn_salvar);
    }

    @Override
    public void onClick(View origem) {
        String nome = edit_produto.getText().toString();
        String quantidade = edit_quantidade.getText().toString();
        String valor = edit_valor.getText().toString();
        String valor_custo = edit_valor_custo.getText().toString();

        if (origem.getId()== R.id.btn_salvar){
            if (nome.isEmpty() || quantidade.isEmpty()|| valor.isEmpty() || valor_custo.isEmpty()){
                Snackbar snackbar = Snackbar.make(origem, mensagens[0],Snackbar.LENGTH_SHORT);
                snackbar.setBackgroundTint(Color.WHITE);
                snackbar.setTextColor(Color.RED);
                snackbar.show();
            }else {
                salvarProduto ();
            }

        }

    }

    private void salvarProduto (){

        Toast.makeText(this, "salvando produto", Toast.LENGTH_SHORT).show();

    }





    }
