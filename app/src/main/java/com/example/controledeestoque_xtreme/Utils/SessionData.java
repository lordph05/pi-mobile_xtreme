package com.example.controledeestoque_xtreme.Utils;

import com.example.controledeestoque_xtreme.Endidades.User;

public class SessionData {

    public static SessionData singleton = null;

    // atributos
    private User userLogado;

    private SessionData(){}

    public static SessionData getInstance(){
        if(singleton==null)
            singleton = new SessionData();
        return singleton;
    }

    public User getUserLogado() {
        return userLogado;
    }

    public void setUserLogado(User userLogado) {
        this.userLogado = userLogado;
    }
}
