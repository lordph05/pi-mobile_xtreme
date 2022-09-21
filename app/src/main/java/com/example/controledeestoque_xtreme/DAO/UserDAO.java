package com.example.controledeestoque_xtreme.DAO;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;

import com.example.controledeestoque_xtreme.Endidades.User;

@Dao
public interface UserDAO {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    public void insert (User usuarios);


}
