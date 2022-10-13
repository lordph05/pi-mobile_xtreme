package com.example.controledeestoque_xtreme.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.room.Room;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.PopupMenu;
import android.widget.Toast;

import com.example.controledeestoque_xtreme.DAO.ProdutoDAO;
import com.example.controledeestoque_xtreme.DAO.UserDAO;
import com.example.controledeestoque_xtreme.Endidades.Produtos;
import com.example.controledeestoque_xtreme.R;
import com.example.controledeestoque_xtreme.Utils.AdapterProduto;
import com.example.controledeestoque_xtreme.Utils.BancoDeDados;
import com.example.controledeestoque_xtreme.autenticacao.LoginActivity;
import com.tsuryo.swipeablerv.SwipeLeftRightCallback;
import com.tsuryo.swipeablerv.SwipeableRecyclerView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    //atributos
    private SwipeableRecyclerView rvProdutos;

    private ImageButton ibAdd, ibVerMais,ib_voltar_inicio;

    BancoDeDados bd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        ibAdd = findViewById(R.id.ib_add);
        ibVerMais = findViewById(R.id.ib_ver_mais);
        ib_voltar_inicio = findViewById(R.id.ib_voltar_inicio);
        rvProdutos = findViewById(R.id.rvProdutos);

        configRecyclerView ();
        ouvinteCliques ();
    }
    AdapterProduto adapterProduto = new AdapterProduto();

    private void ouvinteCliques (){ // metodos de cliques da toolbar
        ibAdd.setOnClickListener(view -> {
            startActivity(new Intent(this, FormProdutoctivity.class));
        });
        ib_voltar_inicio.setOnClickListener(View -> {
        startActivity(new Intent(this, InforProdutoActivity.class));
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
        rvProdutos.setHasFixedSize(true); // carregar a lista mas rapido.
        rvProdutos.setAdapter(adapterProduto);
        rvProdutos.setListener(new SwipeLeftRightCallback.Listener() {
            @Override
            public void onSwipedLeft(int position) {

            }

            @Override
            public void onSwipedRight(int position) { // removendo os itens da lista



            }
        });

    }



}