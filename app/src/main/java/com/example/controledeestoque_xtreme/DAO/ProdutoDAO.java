package com.example.controledeestoque_xtreme.DAO;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.example.controledeestoque_xtreme.Endidades.Produtos;

import java.util.List;

@Dao
public interface ProdutoDAO {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    public void insert (Produtos produtos);

    @Query("select * from produto")
    public List<Produtos>getAll();



    @Delete
    public void remove(Produtos produtos);

}