package com.example.minhacomanda.dao;

import android.content.Context;

import com.example.minhacomanda.helpers.DaoHelper;
import com.example.minhacomanda.model.ComandaProduto;

import java.sql.SQLException;
import java.util.List;

public class ComandaProdutoDao  extends DaoHelper<ComandaProduto> {

    public ComandaProdutoDao(Context c){ super(c,ComandaProduto.class); }

    public List<ComandaProduto> listar(){
        try{
            return getDao().queryForAll();
        }catch (SQLException e){
            e.printStackTrace();

        }        return  null;
    }
}
