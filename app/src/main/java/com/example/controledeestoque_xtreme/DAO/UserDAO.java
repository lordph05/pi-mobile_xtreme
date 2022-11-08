package com.example.controledeestoque_xtreme.DAO;

import android.widget.TextView;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.example.controledeestoque_xtreme.Endidades.Produtos;
import com.example.controledeestoque_xtreme.Endidades.User;

import java.util.List;

@Dao
public interface UserDAO {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    public long insert (User user);

@Query("select * from user WHERE email=:email AND senha=:senha")
 List <User> getUser(String email, String senha);

    @Query("select * from user")
    public List<User>getAll();

    @Delete
    public  void remove(User user);


}
