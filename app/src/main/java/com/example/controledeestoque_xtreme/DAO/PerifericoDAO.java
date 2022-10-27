package com.example.controledeestoque_xtreme.DAO;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.example.controledeestoque_xtreme.Endidades.Periferico;
import com.example.controledeestoque_xtreme.Endidades.Produtos;

import java.util.List;

@Dao
public interface PerifericoDAO {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    public void insert(Periferico periferico);

    @Query("select * from periferico")
    List <Periferico> getPeriferico();

    @Query("select * from periferico")
    public List<Periferico>getAll();



    @Delete
    public  void remove(Periferico periferico);

}