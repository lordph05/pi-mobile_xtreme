package com.example.controledeestoque_xtreme.Menu.Cat_Computador;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;

import com.example.controledeestoque_xtreme.DAO.ProdutoDAO;
import com.example.controledeestoque_xtreme.Endidades.Produtos;
import com.example.controledeestoque_xtreme.Menu.EditarProduto;
import com.example.controledeestoque_xtreme.R;
import com.example.controledeestoque_xtreme.Utils.BancoDeDados;

import java.util.ArrayList;

public class ListaComputadorAdapter extends RecyclerView.Adapter <ListaComputadorAdapter.itemLista> {
    public ArrayList<Produtos> computadorList, fonteDados_Recentes;
  LayoutInflater inflater;
  BancoDeDados bd;

    public ListaComputadorAdapter(Context context){
        bd = Room.databaseBuilder(context.getApplicationContext(), BancoDeDados.class, "BancoApp").allowMainThreadQueries().build();
        //obteando o DAO de produto
        ProdutoDAO produtoDAO = bd.getProdutoDAO();
        computadorList = (ArrayList <Produtos>) produtoDAO.getAllComputadores();
        fonteDados_Recentes = computadorList;
        inflater = LayoutInflater.from(context);
    }

    public void mudarDados(ArrayList <Produtos> novosDados){
        if (novosDados.isEmpty()) fonteDados_Recentes = computadorList;
        fonteDados_Recentes = novosDados;
        notifyDataSetChanged(); // notifica que a as alterações na tela no filtro

    }

    @NonNull
    @Override
    public itemLista onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View hardwareXML = inflater.inflate(R.layout.lista_computador_item,parent,false);
        itemLista novaitemLista = new itemLista(hardwareXML);

        return novaitemLista;
    }

    @Override
    public void onBindViewHolder(@NonNull itemLista holder, int position) {
// vincular os dados de cada indice do array do viewHolder
        holder.text_produto.setText(computadorList.get(position).nome);
        holder.text_estoque.setText("EStoque: "+String.valueOf(fonteDados_Recentes.get(position).estoque));
        holder.text_valor.setText("VL de venda R$:"+String.valueOf(fonteDados_Recentes.get(position).valor));
        holder.text_valor_custo.setText("VL custo R$ :"+String.valueOf(fonteDados_Recentes.get(position).valor_custo));
        holder.id = fonteDados_Recentes.get(position).id;

    }

    @Override
    public int getItemCount() {
        return fonteDados_Recentes.size();
    }


    //classe interna
    public static class itemLista extends RecyclerView.ViewHolder implements View.OnClickListener {
        // atributos
        ImageView img_descricao;
        TextView text_produto;
        TextView text_estoque;
        TextView text_valor;
        TextView text_valor_custo;
        Integer id;


        public itemLista(@NonNull View hardwareXML) {
            super(hardwareXML);
            img_descricao = hardwareXML.findViewById(R.id.img_descricao);
            text_produto = hardwareXML.findViewById(R.id.text_produto);
            text_estoque = hardwareXML.findViewById(R.id.text_estoque);
            text_valor = hardwareXML.findViewById(R.id.text_valor);
            text_valor_custo = hardwareXML.findViewById(R.id.text_valor_custo);
            img_descricao.setOnClickListener(this);

        }

        @Override
        public void onClick(View v) {
            Intent intent = new Intent(v.getContext(), EditarProduto.class);
            intent.putExtra("produto", ListaComputadorAdapter.itemLista.this.text_produto.getText().toString());
            intent.putExtra("estoque", ListaComputadorAdapter.itemLista.this.text_estoque.getText().toString());
            intent.putExtra("valor", ListaComputadorAdapter.itemLista.this.text_valor.getText().toString());
            intent.putExtra("valor_custo", ListaComputadorAdapter.itemLista.this.text_valor_custo.getText().toString());
            intent.putExtra("id",id);
            v.getContext().startActivity(intent);

        }
    }
}
