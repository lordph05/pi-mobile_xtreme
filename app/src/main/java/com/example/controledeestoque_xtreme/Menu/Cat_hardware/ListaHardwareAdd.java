package com.example.controledeestoque_xtreme.Menu.Cat_hardware;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.controledeestoque_xtreme.DAO.ProdutoDAO;
import com.example.controledeestoque_xtreme.Endidades.Produtos;
import com.example.controledeestoque_xtreme.R;
import com.example.controledeestoque_xtreme.Utils.BancoDeDados;
import com.google.android.material.snackbar.Snackbar;

public class ListaHardwareAdd extends AppCompatActivity  implements View.OnClickListener{

    private EditText edit_produto;
    private EditText edit_estoque;
    private EditText edit_valor;
    private EditText edit_valor_custo;
    private Button btn_salvar;
    private ImageView imagem_produto;
    private Button btn_foto;
    String [] mensagens = {"preencha todos os campos", "outra mensagem"};
    BancoDeDados bd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.lista_hardware_add);


         IniciarComponentes ();
        //eventos de cliques dos componentes
        btn_salvar.setOnClickListener(this);
    }

    private void IniciarComponentes (){
        // captura dos componentes
        edit_produto = findViewById(R.id.edit_produto);
        edit_estoque = findViewById(R.id.edit_estoque);
        edit_valor = findViewById(R.id.edit_valor);
        edit_valor_custo = findViewById(R.id.edit_valor_custo);
        btn_salvar = findViewById(R.id.btn_salvar);
        imagem_produto = findViewById(R.id.imagem_produto);
    }


    @Override
    public void onClick(View origem) {
        String nome = edit_produto.getText().toString();
        String estoque = edit_estoque.getText().toString();
        String valor = edit_valor.getText().toString();
        String valor_custo = edit_valor_custo.getText().toString();

        if (origem.getId()== R.id.btn_salvar){
            if (nome.isEmpty() || estoque.isEmpty()|| valor.isEmpty() || valor_custo.isEmpty()){
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
        String nome = edit_produto.getText().toString();
        String estoque = edit_estoque.getText().toString();
        String valor = edit_valor.getText().toString();
        String valor_custo = edit_valor_custo.getText().toString();

        bd = Room.databaseBuilder(getApplicationContext(), BancoDeDados.class, "BancoApp").allowMainThreadQueries().build();
        //obteando o DAO do produto
        ProdutoDAO produtoDAO = bd.getProdutoDAO();
//        List<Produtos> produtos = produtoDAO.getProdutos();

        // insere novo usario no banco e mostra na tela
        Produtos novoProduto = new Produtos();
        novoProduto.nome = nome;
        novoProduto.estoque= Integer.parseInt(estoque);
        novoProduto.valor= Double.parseDouble(valor);
        novoProduto.valor_custo=Double.parseDouble(valor_custo);

        produtoDAO.insert(novoProduto);
        Toast.makeText(this, "produto Cadastrado", Toast.LENGTH_SHORT).show();
        startActivity(new Intent(this, ListaHardware.class));
        finish();
    }





    }
