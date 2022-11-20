package com.example.controledeestoque_xtreme.Endidades;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;


@Entity (tableName = "user")
public class User {

    public User() {
    }

    public User(int id, String nome, String email, String perfil, String senha) {
        this.id = id;
        this.nome = nome;
        this.email = email;
        this.perfil = perfil;
        this.senha = senha;
    }

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
