package com.example.controledeestoque_xtreme;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class AdapterProduto extends RecyclerView.Adapter <AdapterProduto.MyViewHolder> {

    private List <Produto>produtoList;
    private  Onclick onclick;

    public AdapterProduto(List<Produto> produtoList, Onclick onclick) {
        this.produtoList = produtoList;
        this.onclick = onclick;
    }

    @NonNull
    @Override // passando o layout criado
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_produto,parent,false);
        return new MyViewHolder(itemView);
    }

    @Override // configura a linha de cada produto
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
Produto produto = produtoList.get(position);

holder.textProduto.setText(produto.getNome());
holder.textEstoque.setText("Estoque " + String.valueOf(produto.getEstoque()));
holder.textValor.setText("R$ " + String.valueOf(produto.getValor()));
       //  Picasso.get().load(produto.getUrlImagem()).into(holder.img_descricao);

        holder.itemView.setOnClickListener( view -> onclick.onClickListener(produto));
    }

    @Override // retona a quantidade de linha de cada produto
    public int getItemCount() {
        return produtoList.size();
    }

    public interface Onclick{
        void onClickListener (Produto produto);
    }

    static class MyViewHolder extends RecyclerView.ViewHolder{
TextView textProduto, textEstoque, textValor;
ImageView img_descricao;


        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            textProduto = itemView.findViewById(R.id.text_produto);
            textEstoque = itemView.findViewById(R.id.text_estoque);
            textValor = itemView.findViewById(R.id.text_valor);
            img_descricao = itemView.findViewById(R.id.img_descricao);
        }
    }
}
