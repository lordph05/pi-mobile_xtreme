package com.example.controledeestoque_xtreme.DAO;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;

import com.example.controledeestoque_xtreme.Endidades.Produtos;

@Dao
public interface ProdutoDAO {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    public void insert (Produtos produtos);

    @Delete
    public void remove(Produtos produtos);

}