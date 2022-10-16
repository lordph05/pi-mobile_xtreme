package com.example.controledeestoque_xtreme.Endidades;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.util.ArrayList;

@Entity (tableName = "produtos")
public class Produtos {

    @PrimaryKey (autoGenerate = true)
    public int id;

    @ColumnInfo (name = "nome")
    public String nome;

    @ColumnInfo (name = "estoque")
    public String estoque;

    @ColumnInfo(name = "valor")
    public String valor;

    @ColumnInfo(name = "valor_custo")
    public String valor_custo;

//    /*gera produtos ficticios*/
//    public static ArrayList<Produtos> produtosficticios(int n){
//        ArrayList <Produtos>lista = new ArrayList<>();
//        for (int i=0; i<n; i++){
//            Produtos novo = new Produtos();
//            novo.nome=" nome i" +i;
//            novo.estoque= +i;
//            novo.valor= +i;
//            novo.valor_custo= +i;
//            lista.add(novo);
//
//        }
//        return lista;
//    }

}