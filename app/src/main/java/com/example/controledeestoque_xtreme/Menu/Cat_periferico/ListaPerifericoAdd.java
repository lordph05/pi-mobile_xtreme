package com.example.controledeestoque_xtreme.Menu.Cat_periferico;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.controledeestoque_xtreme.DAO.PerifericoDAO;
import com.example.controledeestoque_xtreme.DAO.ProdutoDAO;
import com.example.controledeestoque_xtreme.Endidades.Periferico;
import com.example.controledeestoque_xtreme.Endidades.Produtos;
import com.example.controledeestoque_xtreme.Menu.Cat_hardware.ListaHardware;
import com.example.controledeestoque_xtreme.R;
import com.example.controledeestoque_xtreme.Utils.BancoDeDados;
import com.google.android.material.snackbar.Snackbar;

public class ListaPerifericoAdd extends AppCompatActivity implements View.OnClickListener {

    EditText edit_produto;
    private EditText edit_estoque;
    private EditText edit_valor;
    private EditText edit_valor_custo;
    private Button btn_salvar;
    public ImageView imagem_produto;
    public  Button btn_carregarImagen;
    String [] mensagens = {"preencha todos os campos", "outra mensagem"};
    BancoDeDados bd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_periferico_add);

        IniciarComponentes ();
        ProcurarImagen ();
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
        btn_carregarImagen = findViewById(R.id.btn_carregarImagen);
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
                salvarPeriferico ();
                ProcurarImagen ();
            }

        }

    }
    private void salvarPeriferico (){
        String nome = edit_produto.getText().toString();
        String estoque = edit_estoque.getText().toString();
        String valor = edit_valor.getText().toString();
        String valor_custo = edit_valor_custo.getText().toString();

        bd = Room.databaseBuilder(getApplicationContext(), BancoDeDados.class, "BancoApp").allowMainThreadQueries().build();
        //obteando o DAO do produto
        PerifericoDAO perifericoDAO = bd.getPerifericoDAO();
//        List<Produtos> produtos = produtoDAO.getProdutos();

        // insere novo usario no banco e mostra na tela
        Periferico novoProduto = new Periferico();
        novoProduto.nome = nome;
        novoProduto.estoque= Integer.parseInt(estoque);
        novoProduto.valor= Double.parseDouble(valor);
        novoProduto.valor_custo=Double.parseDouble(valor_custo);

        perifericoDAO.insert(novoProduto);
        Toast.makeText(this, "produto Cadastrado", Toast.LENGTH_SHORT).show();
        startActivity(new Intent(this, ListaPeriferico.class));
        finish();
    }
    public void ProcurarImagen (){
        btn_carregarImagen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (checkCallingOrSelfPermission(Manifest.permission.READ_EXTERNAL_STORAGE)== PackageManager.PERMISSION_DENIED){
                    String [] permissao = {Manifest.permission.READ_EXTERNAL_STORAGE};
                    requestPermissions(permissao,1001);
                }
                else{
                }escolherImagen ();

            }
        });
    }
    private void escolherImagen (){
        Intent intent= new Intent(Intent.ACTION_PICK);
        intent.setType("image/*");
        startActivityForResult(intent,1000);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK && requestCode ==1000){
            imagem_produto.setImageURI(data.getData());
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode) {
            case 1001: {
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    escolherImagen();
                } else {
                    Toast.makeText(this, "Tem que aceitar a permissão", Toast.LENGTH_SHORT).show();
                }
            }
        }
    }
}