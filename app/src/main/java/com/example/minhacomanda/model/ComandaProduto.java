package com.example.minhacomanda.model;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

@DatabaseTable(tableName = "comanda_produto")
public class ComandaProduto {

    @DatabaseField(canBeNull = false)
    private Integer quantidade;

    @DatabaseField(canBeNull = false, foreign = true, foreignAutoRefresh = true)
    private Produto produto;

    @DatabaseField(allowGeneratedIdInsert = true , generatedId = true)
    private Integer id;

    public Integer getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(Integer quantidade) {
        this.quantidade = quantidade;
    }

    public Produto getProduto() {
        return produto;
    }

    public void setProduto(Produto produto) {
        this.produto = produto;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Double getSubtotal() {
        return getProduto().getValor() * getQuantidade();
    }

    @Override
    public String toString() {
        return getProduto().getNome() + " - " + getProduto().getValor() + " - " + getQuantidade() + " - " + getSubtotal();
    }
}
