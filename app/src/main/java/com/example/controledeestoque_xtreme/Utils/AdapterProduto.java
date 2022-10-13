package com.example.controledeestoque_xtreme.Utils;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.controledeestoque_xtreme.R;

public class AdapterProduto extends RecyclerView.Adapter<AdapterProduto.MyviewHolder> {



    @NonNull
    @Override
    public MyviewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View intemLista = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_produto, parent, false);
        return new MyviewHolder(intemLista);
    }

    @Override
    public void onBindViewHolder(@NonNull MyviewHolder holder, int position) {
        holder.text_produto.setText("placa mae");
        holder.text_estoque.setText("30");
        holder.text_valor.setText("850");
        holder.text_valor_custo.setText("700");

    }

    @Override
    public int getItemCount() {
        return 2;
    }

    public class MyviewHolder extends RecyclerView.ViewHolder{

        ImageView img_descricao;
        TextView text_produto;
        TextView text_estoque;
        TextView text_valor;
        TextView text_valor_custo;

        public MyviewHolder(@NonNull View itemView) {
            super(itemView);
            img_descricao = itemView.findViewById(R.id.img_descricao);
            text_produto = itemView.findViewById(R.id.text_produto);
            text_estoque = itemView.findViewById(R.id.text_estoque);
            text_valor = itemView.findViewById(R.id.text_valor);
            text_valor_custo = itemView.findViewById(R.id.text_valor_custo);
        }
    }
}
