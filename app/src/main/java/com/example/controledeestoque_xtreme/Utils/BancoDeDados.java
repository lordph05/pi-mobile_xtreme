package com.example.controledeestoque_xtreme.Utils;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.example.controledeestoque_xtreme.DAO.ConectividadeDAO;
import com.example.controledeestoque_xtreme.DAO.PerifericoDAO;
import com.example.controledeestoque_xtreme.DAO.ProdutoDAO;
import com.example.controledeestoque_xtreme.DAO.UserDAO;
import com.example.controledeestoque_xtreme.Endidades.Conectividade;
import com.example.controledeestoque_xtreme.Endidades.Periferico;
import com.example.controledeestoque_xtreme.Endidades.Produtos;
import com.example.controledeestoque_xtreme.Endidades.User;

@Database(entities = {User.class, Produtos.class, Periferico.class, Conectividade.class},version = 1)
public abstract class BancoDeDados extends RoomDatabase {
    public abstract UserDAO getuserDAO();
    public abstract ProdutoDAO getProdutoDAO();
    public abstract PerifericoDAO getPerifericoDAO();
    public abstract ConectividadeDAO getConectividadeDAO();



}
