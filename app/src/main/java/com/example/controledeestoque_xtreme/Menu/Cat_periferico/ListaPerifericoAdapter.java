package com.example.controledeestoque_xtreme.Menu.Cat_periferico;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;

import com.example.controledeestoque_xtreme.DAO.PerifericoDAO;
import com.example.controledeestoque_xtreme.DAO.ProdutoDAO;
import com.example.controledeestoque_xtreme.Endidades.Periferico;
import com.example.controledeestoque_xtreme.Endidades.Produtos;
import com.example.controledeestoque_xtreme.R;
import com.example.controledeestoque_xtreme.Utils.BancoDeDados;

import java.util.ArrayList;

public class ListaPerifericoAdapter extends RecyclerView.Adapter <ListaPerifericoAdapter.itemLista> {
    public ArrayList<Periferico> produtosList, fonteDados_Recentes;
  LayoutInflater inflater;
  BancoDeDados bd;

    public ListaPerifericoAdapter(Context context){
        bd = Room.databaseBuilder(context.getApplicationContext(), BancoDeDados.class, "BancoApp").allowMainThreadQueries().build();
        //obteando o DAO de produto
        PerifericoDAO perifericoDAO = bd.getPerifericoDAO();
        produtosList = (ArrayList <Periferico>) perifericoDAO.getAll();
        fonteDados_Recentes = produtosList;
        inflater = LayoutInflater.from(context);
    }

    public void mudarDados(ArrayList <Periferico> novosDados){
        if (novosDados.isEmpty()) fonteDados_Recentes = produtosList;
        fonteDados_Recentes = novosDados;
        notifyDataSetChanged(); // notifica que a as alterações na tela no filtro
    }

    @NonNull
    @Override
    public itemLista onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View hardwareXML = inflater.inflate(R.layout.lista_periferico_item,parent,false);
        itemLista novaitemLista = new itemLista(hardwareXML);

        return novaitemLista;
    }

    @Override
    public void onBindViewHolder(@NonNull itemLista holder, int position) {
// vincular os dados de cada indice do array do viewHolder
        holder.text_produto.setText(produtosList.get(position).nome);
        holder.text_estoque.setText("EStoque: "+String.valueOf(fonteDados_Recentes.get(position).estoque));
        holder.text_valor.setText("Valor de venda R$: "+String.valueOf(fonteDados_Recentes.get(position).valor));
        holder.text_valor_custo.setText("Valor custo R$: "+String.valueOf(fonteDados_Recentes.get(position).valor_custo));

    }

    @Override
    public int getItemCount() {
        return fonteDados_Recentes.size();
    }


    //classe interna
    public static class itemLista extends RecyclerView.ViewHolder{
        // atributos
        ImageView img_descricao;
        TextView text_produto;
        TextView text_estoque;
        TextView text_valor;
        TextView text_valor_custo;

        public itemLista(@NonNull View hardwareXML) {
            super(hardwareXML);
            img_descricao = hardwareXML.findViewById(R.id.img_descricao);
            text_produto = hardwareXML.findViewById(R.id.text_produto);
            text_estoque = hardwareXML.findViewById(R.id.text_estoque);
            text_valor = hardwareXML.findViewById(R.id.text_valor);
            text_valor_custo = hardwareXML.findViewById(R.id.text_valor_custo);

        }
    }
}
