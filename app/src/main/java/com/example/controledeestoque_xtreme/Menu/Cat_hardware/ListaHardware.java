package com.example.controledeestoque_xtreme.Menu.Cat_hardware;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.room.Room;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.PopupMenu;
import android.widget.Toast;

import com.example.controledeestoque_xtreme.DAO.ProdutoDAO;
import com.example.controledeestoque_xtreme.Endidades.Produtos;
import com.example.controledeestoque_xtreme.Menu.MenuAdmin;
import com.example.controledeestoque_xtreme.R;
import com.example.controledeestoque_xtreme.Utils.BancoDeDados;
import com.example.controledeestoque_xtreme.autenticacao.LoginActivity;
import com.tsuryo.swipeablerv.SwipeLeftRightCallback;
import com.tsuryo.swipeablerv.SwipeableRecyclerView;

import java.util.ArrayList;

public class ListaHardware extends AppCompatActivity implements TextWatcher {

    //atributos
    private SwipeableRecyclerView rvProdutos;
    private ImageButton ibAdd, ibVerMais,ib_voltar_inicio;
    private EditText edit_pesquisa;
    ListaHardwareAdapter listaHardwareAdapter_;


    BancoDeDados bd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.lista_hardware);

// captura dos componentes
        ibAdd = findViewById(R.id.ib_add);
        ibVerMais = findViewById(R.id.ib_ver_mais);
        ib_voltar_inicio = findViewById(R.id.ib_voltar_inicio);
        edit_pesquisa = findViewById(R.id.edit_pesquisa);
        edit_pesquisa.addTextChangedListener(this);// faz um filtro no buscar
        rvProdutos = findViewById(R.id.rvProdutos);

       configRecyclerView ();
        ouvinteCliques ();
    }

   public void beforeTextChanged(CharSequence var1, int var2, int var3, int var4) {
   }

    public void onTextChanged(CharSequence var1, int var2, int var3, int var4){

       }

    public void afterTextChanged(Editable text){
        String novoTexto = text.toString ();// captura o que esta no filtro buscar
        ArrayList <Produtos> listaProdutos = listaHardwareAdapter_.produtosList;
        ArrayList<Produtos>dadosFiltrados = new ArrayList<>();
        /*percorrer a lista original e filtrar os dados pelo buscar*/
        for (int i=0; i<listaProdutos.size();i++){
            Produtos produtos = listaProdutos.get(i);
            if (produtos.nome.contains(novoTexto)){
                dadosFiltrados.add(produtos);
            }
        }
        listaHardwareAdapter_.mudarDados(dadosFiltrados);
       }


    private void ouvinteCliques (){ // metodos de cliques da toolbar
        ibAdd.setOnClickListener(view -> {
            startActivity(new Intent(this, ListaHardwareAdd.class));
            finish();
        });
        ib_voltar_inicio.setOnClickListener(View -> {
        startActivity(new Intent(this, MenuAdmin.class));
        });
        ibVerMais.setOnClickListener(view -> {
            PopupMenu popupMenu = new PopupMenu(this, ibVerMais);
            popupMenu.getMenuInflater().inflate(R.menu.toolbar, popupMenu.getMenu());

            popupMenu.setOnMenuItemClickListener(menuItem -> {
                if (menuItem.getItemId()== R.id.menu_sobre){
                    Toast.makeText(this, "Cairu", Toast.LENGTH_SHORT).show();
                }else if ( menuItem.getItemId()==R.id.menu_sair){
                    startActivity(new Intent(this, LoginActivity.class));
                }
                return true;
            });
            popupMenu.show();

        });
    }

    private void configRecyclerView (){
        rvProdutos.setLayoutManager(new LinearLayoutManager(this));
        listaHardwareAdapter_ = new ListaHardwareAdapter(this);
        rvProdutos.setAdapter(listaHardwareAdapter_);
        rvProdutos.setHasFixedSize(true); // carregar a lista mas rapido.
        rvProdutos.setListener(new SwipeLeftRightCallback.Listener() {
            @Override
            public void onSwipedLeft(int position) {

            }

            @Override
            public void onSwipedRight(int position) { // removendo os itens da lista
                bd = Room.databaseBuilder(getApplicationContext(), BancoDeDados.class, "BancoApp").allowMainThreadQueries().build();
                Produtos produto = listaHardwareAdapter_.produtosList.get(position);
                ProdutoDAO produtoDAO = bd.getProdutoDAO();
                produtoDAO.remove(produto);
                //atualizar tela apos remoção
                ArrayList <Produtos> listaProdutos = listaHardwareAdapter_.produtosList;
                listaProdutos.remove(produto);
                listaHardwareAdapter_.mudarDados(listaProdutos);
            }
        });

    }



}