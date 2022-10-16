package com.example.controledeestoque_xtreme.Endidades;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity (tableName = "user")
public class User {

@PrimaryKey (autoGenerate = true)
    public int id;

@ColumnInfo (name = "nome")
    public String nome;

    @ColumnInfo (name = "email")
    public String email;

    @ColumnInfo (name = "perfil")
    public String perfil;


    @ColumnInfo(name = "senha")
    public String senha;
}
