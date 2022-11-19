package com.example.controledeestoque_xtreme.DAO;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import com.example.controledeestoque_xtreme.Endidades.Produtos;
import com.example.controledeestoque_xtreme.Endidades.User;

import java.util.List;

@Dao
public interface ProdutoDAO {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    public void insert(Produtos produtos);

    @Query("select * from produtos")
    List <Produtos> getProdutos();

    @Query("select * from produtos")
    public List<Produtos>getAll();

    @Query("select * from produtos Where tipo = 1")
    public List<Produtos>getAllHardwares();

    @Query("select * from produtos Where tipo = 2")
    public List<Produtos>getAllPerifericos();

    @Query("select * from produtos Where tipo = 3")
    public List<Produtos>getAllConectividade();

    @Query("select * from produtos Where tipo = 4")
    public List<Produtos>getAllComputadores();

    @Delete
    public  void remove(Produtos produto);

    @Update
     public void  update (Produtos produtos);

}