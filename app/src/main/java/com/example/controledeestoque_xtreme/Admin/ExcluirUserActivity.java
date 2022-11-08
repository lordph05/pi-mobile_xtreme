package com.example.controledeestoque_xtreme.Admin;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.room.Room;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.controledeestoque_xtreme.DAO.UserDAO;
import com.example.controledeestoque_xtreme.Endidades.User;
import com.example.controledeestoque_xtreme.R;
import com.example.controledeestoque_xtreme.Utils.BancoDeDados;
import com.tsuryo.swipeablerv.SwipeLeftRightCallback;
import com.tsuryo.swipeablerv.SwipeableRecyclerView;

import java.util.ArrayList;

public class ExcluirUserActivity extends AppCompatActivity {

    private SwipeableRecyclerView rvUser;
    private ImageView img_User;
    private TextView text_nome,text_perfil;
    ListaUserAdapter listaUserAdapter;
    BancoDeDados bd;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_excluir_user);

        // captura dos componentes
        img_User = findViewById(R.id.img_User);
        text_nome = findViewById(R.id.text_nome);
        text_perfil = findViewById(R.id.text_perfil);
        rvUser = findViewById(R.id.rvUser);


        configClique ();
        configRecyclerView ();
    }

    private  void configClique () { // eventos de cliques fora da origem.
        findViewById(R.id.ib_voltar).setOnClickListener(view -> finish() );

        TextView text_titulo = findViewById(R.id.text_titulo);
        text_titulo.setText("Excluir Usuários");
    }
    private void configRecyclerView (){
        rvUser.setLayoutManager(new LinearLayoutManager(this));
        listaUserAdapter = new ListaUserAdapter(this);
        rvUser.setAdapter(listaUserAdapter);
        rvUser.setHasFixedSize(true); // carregar a lista mas rapido.
        rvUser.setListener(new SwipeLeftRightCallback.Listener() {
            @Override
            public void onSwipedLeft(int position) {

            }

            @Override
            public void onSwipedRight(int position) { // removendo os itens da lista
                bd = Room.databaseBuilder(getApplicationContext(), BancoDeDados.class, "BancoApp").allowMainThreadQueries().build();
                User user = listaUserAdapter.produtosList.get(position);
                UserDAO userDAO = bd.getuserDAO();
                userDAO.remove(user);
                //atualizar tela apos remoção
                ArrayList<User> listaProdutos = listaUserAdapter.produtosList;
                listaProdutos.remove(user);
                listaUserAdapter.mudarDados(listaProdutos);
            }
        });

    }


}