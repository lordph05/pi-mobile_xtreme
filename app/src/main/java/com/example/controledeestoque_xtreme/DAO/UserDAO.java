package com.example.controledeestoque_xtreme.DAO;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.example.controledeestoque_xtreme.Endidades.User;

import java.util.List;

@Dao
public interface UserDAO {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    public long insert (User user);

@Query("select * from user WHERE email=:email AND senha=:senha")
 List <User> getUser(String email, String senha);

}
