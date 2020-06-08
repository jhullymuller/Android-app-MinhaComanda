package com.example.minhacomanda.model;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

@DatabaseTable(tableName = "produto")
public class Produto {

    @DatabaseField(canBeNull = false)
    private String nome;

    @DatabaseField(canBeNull = false)
    private Double valor;

    @DatabaseField(canBeNull = false)
    private String situacao;

    @DatabaseField(allowGeneratedIdInsert = true , generatedId = true)
    private Integer id;

    public Produto(Integer id, String nome, Double valor, String situacao) {
        this.id = id;
        this.nome = nome;
        this.valor = valor;
        this.situacao = situacao;
    }

    public Produto() {
        super();
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Double getValor() {
        return valor;
    }

    public void setValor(Double valor) {
        this.valor = valor;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getSituacao() {
        return situacao;
    }

    public void setSituacao(String situacao) {
        this.situacao = situacao;
    }

    @Override
    public String toString() {
        return nome + " (" + valor + ")";
    }
}
