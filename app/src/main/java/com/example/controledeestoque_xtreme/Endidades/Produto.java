package com.example.controledeestoque_xtreme.Endidades;

public class Produto {
    private String nome;
    private int estoque;
    private Double valor;

    public Double getValor_estoque() {
        return valor_estoque;
    }

    public void setValor_estoque(Double valor_estoque) {
        this.valor_estoque = valor_estoque;
    }

    private Double valor_estoque;


    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getEstoque() {
        return estoque;
    }

    public void setEstoque(int estoque) {
        this.estoque = estoque;
    }

    public Double getValor() {
        return valor;
    }

    public void setValor(Double valor) {
        this.valor = valor;
    }
}
