package com.example.controledeestoque_xtreme.Menu.Cat_periferico;

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

import com.example.controledeestoque_xtreme.DAO.PerifericoDAO;
import com.example.controledeestoque_xtreme.DAO.ProdutoDAO;
import com.example.controledeestoque_xtreme.Endidades.Periferico;
import com.example.controledeestoque_xtreme.Endidades.Produtos;
import com.example.controledeestoque_xtreme.Menu.Cat_hardware.ListaHardwareAdapter;
import com.example.controledeestoque_xtreme.Menu.Cat_hardware.ListaHardwareAdd;
import com.example.controledeestoque_xtreme.Menu.Cat_hardware.MenuFuncionario;
import com.example.controledeestoque_xtreme.R;
import com.example.controledeestoque_xtreme.Utils.BancoDeDados;
import com.example.controledeestoque_xtreme.autenticacao.LoginActivity;
import com.tsuryo.swipeablerv.SwipeLeftRightCallback;
import com.tsuryo.swipeablerv.SwipeableRecyclerView;

import java.util.ArrayList;

public class ListaPeriferico extends AppCompatActivity implements TextWatcher {

    private SwipeableRecyclerView rvPeriferico;
    private ImageButton ibAdd, ibVerMais,ib_voltar_inicio;
    private EditText edit_pesquisa;
    ListaPerifericoAdapter listaPerifericoAdapter;
    BancoDeDados bd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_periferico);
        // captura dos componentes

        ibAdd = findViewById(R.id.ib_add);
        ibVerMais = findViewById(R.id.ib_ver_mais);
        ib_voltar_inicio = findViewById(R.id.ib_voltar_inicio);
        edit_pesquisa = findViewById(R.id.edit_pesquisa);
     edit_pesquisa.addTextChangedListener(this);// faz um filtro no buscar
        rvPeriferico = findViewById(R.id.rvPeriferico);

        ouvinteCliques ();
        configRecyclerView ();

    }
    public void beforeTextChanged(CharSequence var1, int var2, int var3, int var4) {
    }

    public void onTextChanged(CharSequence var1, int var2, int var3, int var4){

    }

    public void afterTextChanged(Editable text){
        String novoTexto = text.toString ();// captura o que esta no filtro buscar
        ArrayList<Periferico> listaProdutos = listaPerifericoAdapter.produtosList;
        ArrayList<Periferico>dadosFiltrados = new ArrayList<>();
        /*percorrer a lista original e filtrar os dados pelo buscar*/
        for (int i=0; i<listaProdutos.size();i++){
            Periferico periferico = listaProdutos.get(i);
            if (periferico.nome.contains(novoTexto)){
                dadosFiltrados.add(periferico);
            }
        }
        listaPerifericoAdapter.mudarDados(dadosFiltrados);
    }
    private void ouvinteCliques (){ // metodos de cliques da toolbar
        ibAdd.setOnClickListener(view -> {
            startActivity(new Intent(this, ListaPerifericoAdd.class));
            finish();
        });
        ib_voltar_inicio.setOnClickListener(View -> {
            startActivity(new Intent(this, MenuFuncionario.class));
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
        rvPeriferico.setLayoutManager(new LinearLayoutManager(this));
        listaPerifericoAdapter = new ListaPerifericoAdapter(this);
        rvPeriferico.setAdapter(listaPerifericoAdapter);
        rvPeriferico.setHasFixedSize(true); // carregar a lista mas rapido.
        rvPeriferico.setListener(new SwipeLeftRightCallback.Listener() {
            @Override
            public void onSwipedLeft(int position) {

            }

            @Override
            public void onSwipedRight(int position) { // removendo os itens da lista
                bd = Room.databaseBuilder(getApplicationContext(), BancoDeDados.class, "BancoApp").allowMainThreadQueries().build();
                Periferico periferico = listaPerifericoAdapter.produtosList.get(position);
                PerifericoDAO perifericoDAO = bd.getPerifericoDAO();
                perifericoDAO.remove(periferico);
                //atualizar tela apos remoção
                ArrayList<Periferico> listaProdutos = listaPerifericoAdapter.produtosList;
                listaProdutos.remove(periferico);
                listaPerifericoAdapter.mudarDados(listaProdutos);
            }
        });

    }

}