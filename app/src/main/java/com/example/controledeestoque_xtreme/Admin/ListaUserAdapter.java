package com.example.controledeestoque_xtreme.Admin;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;

import com.example.controledeestoque_xtreme.DAO.UserDAO;
import com.example.controledeestoque_xtreme.Endidades.User;
import com.example.controledeestoque_xtreme.R;
import com.example.controledeestoque_xtreme.Utils.BancoDeDados;

import java.util.ArrayList;

public class ListaUserAdapter extends RecyclerView.Adapter <ListaUserAdapter.itemLista> {
    public ArrayList<User> produtosList, fonteDados_Recentes;
    LayoutInflater inflater;
    BancoDeDados bd;

    public ListaUserAdapter(Context context){
        bd = Room.databaseBuilder(context.getApplicationContext(), BancoDeDados.class, "BancoApp").allowMainThreadQueries().build();
        //obteando o DAO de produto
        UserDAO userDAO = bd.getuserDAO();
        produtosList = (ArrayList <User>) userDAO.getAll();
        fonteDados_Recentes = produtosList;
        inflater = LayoutInflater.from(context);
    }

    public void mudarDados(ArrayList <User> novosDados){
        if (novosDados.isEmpty()) fonteDados_Recentes = produtosList;
        fonteDados_Recentes = novosDados;
        notifyDataSetChanged(); // notifica que a as alterações na tela no filtro
    }

    @NonNull
    @Override
    public itemLista onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View UserXML = inflater.inflate(R.layout.lista_user_item,parent,false);
        itemLista novaitemLista = new itemLista(UserXML);

        return novaitemLista;
    }

    @Override
    public void onBindViewHolder(@NonNull itemLista holder, int position) {
// vincular os dados de cada indice do array do viewHolder
        holder.text_nome.setText(produtosList.get(position).nome);
    }

    @Override
    public int getItemCount() {
        return fonteDados_Recentes.size();
    }


    //classe interna
    public static class itemLista extends RecyclerView.ViewHolder{
        // atributos
        ImageView img_user;
        TextView text_nome;
        TextView text_perfil;


        public itemLista(@NonNull View UserXML) {
            super(UserXML);
            img_user = UserXML.findViewById(R.id.img_User);
            text_nome = UserXML.findViewById(R.id.text_nome);
            text_perfil = UserXML.findViewById(R.id.text_perfil);


        }
    }
}