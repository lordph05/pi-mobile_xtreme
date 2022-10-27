package com.example.controledeestoque_xtreme.Endidades;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity (tableName = "conectividade")
public class Conectividade {

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
}