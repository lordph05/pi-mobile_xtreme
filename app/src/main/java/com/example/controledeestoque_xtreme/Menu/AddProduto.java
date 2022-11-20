package com.example.controledeestoque_xtreme.Menu;

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
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.controledeestoque_xtreme.DAO.ProdutoDAO;
import com.example.controledeestoque_xtreme.Endidades.Produtos;
import com.example.controledeestoque_xtreme.Endidades.User;
import com.example.controledeestoque_xtreme.Menu.MenuPrincipal.MenuAdmin;
import com.example.controledeestoque_xtreme.Menu.MenuPrincipal.MenuFuncionario;
import com.example.controledeestoque_xtreme.R;
import com.example.controledeestoque_xtreme.Utils.BancoDeDados;
import com.example.controledeestoque_xtreme.Utils.SessionData;
import com.google.android.material.snackbar.Snackbar;

public class AddProduto extends AppCompatActivity  implements View.OnClickListener {

    private EditText edit_produto;
    private EditText edit_estoque;
    private EditText edit_valor;
    private EditText edit_valor_custo;
    private Button btn_salvar;
    private Spinner spinner_produto;
    private Integer spinner_produto_codigo_selecionado;
    private TextView categorias;
    public ImageView imagem_produto;
    public Button btn_carregarImagen;
    String[] mensagens = {"preencha todos os campos", "outra mensagem"};
    BancoDeDados bd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.lista_hardware_add);


        IniciarComponentes();
        configClique();
        ProcurarImagen();
        //eventos de cliques dos componentes
        btn_salvar.setOnClickListener(this);
    }

    private void IniciarComponentes() {
        // captura dos componentes
        edit_produto = findViewById(R.id.edit_produto);
        edit_estoque = findViewById(R.id.edit_estoque);
        edit_valor = findViewById(R.id.edit_valor);
        edit_valor_custo = findViewById(R.id.edit_valor_custo);
        btn_salvar = findViewById(R.id.btn_salvar);
        btn_carregarImagen = findViewById(R.id.btn_carregarImagen);
        imagem_produto = findViewById(R.id.imagem_produto);
        spinner_produto = findViewById(R.id.spinner_produto);
        spinner_produto_codigo_selecionado=0;


        //sínner
//        ArrayList<String> categorias = new  ArrayList<String>();
//        categorias.add("Hardware");
//        categorias.add("Periferico");
//        categorias.add("Redes e conectividade");
//        categorias.add("Computador");

//        Spinner spinner = (Spinner) findViewById(R.id.spinner_produto);
//        ArrayList<String> categorias = new ArrayList<String>();
//        categorias.add("Hardware");
//        categorias.add("Periferico");
//        categorias.add("Redes e conectividade");
//        categorias.add("Computador");

        String[] categoria= { "Hardware", "Periferico", "Redes", "Computador"};

        ArrayAdapter adapter =  new ArrayAdapter(this,
                 android.R.layout.simple_spinner_item,categoria);
// Specify the layout to use when the list of choices appears
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
// Apply the adapter to the spinner
        spinner_produto.setAdapter(adapter);


        spinner_produto.setOnItemSelectedListener( new AdapterView.OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                String item = spinner_produto.getSelectedItem().toString();
                spinner_produto_codigo_selecionado = i+1;
                Toast.makeText(AddProduto.this, "categoria escolhida foi " + item, Toast.LENGTH_SHORT).show();

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }

        });


    }


//        ArrayAdapter<String> adapter =
//                new ArrayAdapter<String>(getApplicationContext(), categorias);
//        adapter.setDropDownViewResource( android.R.layout.);
//        spinner_produto.setAdapter(adapter);


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
                ProcurarImagen ();
            }

        }

    }

    private void salvarProduto (){
        String nome = edit_produto.getText().toString();
        String estoque = edit_estoque.getText().toString();
        String valor = edit_valor.getText().toString();
        String valor_custo = edit_valor_custo.getText().toString();
        String tipo = spinner_produto_codigo_selecionado.toString();

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
        novoProduto.tipo = Integer.parseInt(tipo);

        produtoDAO.insert(novoProduto);
        Toast.makeText(this, "produto Cadastrado", Toast.LENGTH_SHORT).show();
        startActivity(new Intent(this, MenuAdmin.class));
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
    private  void configClique () { // eventos de cliques fora da origem.
        findViewById(R.id.ib_voltar).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                User userLogado = SessionData.getInstance().getUserLogado();
                //criar um if para checar o perfil de usuario
                if (userLogado.perfil.equals("admin")){
                    startActivity(new Intent(AddProduto.this,MenuAdmin.class));
                }else {
                    startActivity(new Intent(AddProduto.this, MenuFuncionario.class));
                }
            }
        });
        TextView text_titulo = findViewById(R.id.text_titulo);
        text_titulo.setText("Adicionar Produto");

    }
}