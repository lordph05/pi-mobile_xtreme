package com.example.controledeestoque_xtreme.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.controledeestoque_xtreme.R;

public class FormProdutoctivity extends AppCompatActivity {

    private EditText edit_produto;
    private EditText edit_quantidade;
    private EditText edit_valor;
    private EditText edit_valor_custo;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form_produtoctivity);
    }


    public void salvarProduto(View view) {

        String nome = edit_produto.getText().toString();
        String quantidade = edit_quantidade.getText().toString();
        String valor = edit_valor.getText().toString();
        String Valor_custo = edit_valor_custo.getText().toString();

        if (!nome.isEmpty()) {
            if (!quantidade.isEmpty()) {

                int qtd = Integer.parseInt(quantidade);

                if (qtd >= 1) {
                    if (!valor.isEmpty()) {
                        double valorProduto = Double.parseDouble(valor);

                        if (valorProduto > 0) {
                            Toast.makeText(this, "Tudo certo", Toast.LENGTH_SHORT).show();
                        } else {
                            edit_valor.requestFocus();
                            edit_valor.setError("Informe o valor do produto");
                        }

                    } else {
                        edit_valor.requestFocus();
                        edit_valor.setError("Informe o valor do produto");

                    }

                } else {
                    edit_quantidade.requestFocus();
                    edit_quantidade.setError("Informe a quantidade do produto");
                }

            } else {
                edit_quantidade.requestFocus();
                edit_quantidade.setError("Informe a quantidade do produto");
            }

        } else {
            edit_produto.requestFocus();
            edit_produto.setError("Informe o nome do produto.");
        }

    }
    }
