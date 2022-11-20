package com.example.controledeestoque_xtreme.Endidades;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.util.ArrayList;

@Entity (tableName = "produtos")
public class Produtos {
    public Produtos() {
    }

    public Produtos(int id, String nome, int estoque, double valor, double valor_custo, int tipo) {
        this.id = id;
        this.nome = nome;
        this.estoque = estoque;
        this.valor = valor;
        this.valor_custo = valor_custo;
        this.tipo = tipo;
    }

    @PrimaryKey (autoGenerate = true)
    public int id;

    @ColumnInfo (name = "nome")
    public String nome;

    @ColumnInfo (name = "estoque")
    public int estoque;

    @ColumnInfo(name = "valor")
    public double valor;

    @ColumnInfo(name = "valor_custo")
    public double valor_custo;

    @ColumnInfo (name = "tipo")
    public int tipo;



}