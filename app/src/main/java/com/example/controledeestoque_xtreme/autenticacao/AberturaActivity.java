package com.example.controledeestoque_xtreme.autenticacao;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import com.example.controledeestoque_xtreme.R;

public class AberturaActivity extends AppCompatActivity implements Runnable {
Thread thread;
Handler handler;
int i;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_abertura2);

        handler = new Handler();
        thread = new Thread(this);
        thread.start();

    }

    @Override
    public void run() {
        i=1;

        try {
            while (i<100){
                Thread.sleep(10);
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        i++;
                    }
                });
            }

        }catch (Exception e){

        }
        startActivity(new Intent(this,LoginActivity.class));


    }
}