package com.example.minhacomanda.bo;

public class ProdutoBo {
    public static boolean validarNomeProduto(String nome){return nome != null && !nome.equals("");}
    public static boolean validarValorProduto(Double valor){
        return valor !=null && valor>=0 && valor<10.000;
    }
}
