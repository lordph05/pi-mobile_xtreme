package com.example.controledeestoque_xtreme.DAO;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.example.controledeestoque_xtreme.Endidades.Conectividade;
import com.example.controledeestoque_xtreme.Endidades.Produtos;

import java.util.List;

@Dao
public interface ConectividadeDAO {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    public void insert(Conectividade conectividade);

    @Query("select * from conectividade")
    List <Conectividade> getConectividade();

    @Query("select * from conectividade")
    public List<Conectividade>getAll();

    @Delete
    public  void remove(Conectividade conectividade);

}