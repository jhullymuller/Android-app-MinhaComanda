package com.example.minhacomanda.dao;

import android.content.Context;

import com.example.minhacomanda.helpers.DaoHelper;
import com.example.minhacomanda.model.Produto;

import java.sql.SQLException;
import java.util.List;

public class ProdutoDao extends DaoHelper<Produto> {

    public ProdutoDao(Context c){ super(c,Produto.class); }

    public List<Produto>listar() throws SQLException {

            return getDao().queryBuilder().where().not().eq("situacao", "Inativo").query();

    }
    public List<Produto>listaPorNome(String nome){
        try{
            return getDao().queryBuilder().where().eq("nome",nome).query();
        }catch (SQLException e){
            e.printStackTrace();
        }
        return  null;
    }

}
