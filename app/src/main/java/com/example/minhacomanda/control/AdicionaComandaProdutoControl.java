package com.example.minhacomanda.control;

import android.content.Intent;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Toast;

import com.example.minhacomanda.R;
import com.example.minhacomanda.bo.ComandaProdutoBo;
import com.example.minhacomanda.dao.ComandaProdutoDao;
import com.example.minhacomanda.dao.ProdutoDao;
import com.example.minhacomanda.model.ComandaProduto;
import com.example.minhacomanda.model.Produto;
import com.example.minhacomanda.view.AdicionaComandaProdutoActivity;
import com.example.minhacomanda.view.GerenciarProduto;

import java.sql.SQLException;

public class AdicionaComandaProdutoControl {

    private AdicionaComandaProdutoActivity activity;

    //spiner produtos
    private ArrayAdapter<Produto> adapterProdutos;
    private Produto produto;
    private ProdutoDao produtoDao;
    private ComandaProdutoDao comandaProdutoDao;

    public AdicionaComandaProdutoControl(AdicionaComandaProdutoActivity activity) {
        this.activity = activity;
        produtoDao = new ProdutoDao(this.activity);
        comandaProdutoDao = new ComandaProdutoDao(this.activity);
        this.configSpinner();
        this.configBtnEnviar();
        this.configBtnGerenciarProduto();
    }

    private void configBtnEnviar() {
        this.activity.getBtnEnviar().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    int quantidade = activity.getNpQuantidade().getValue();
                    Produto produto = (Produto) activity.getSpinnerProdutos().getSelectedItem();
                    ComandaProduto comandaProduto = new ComandaProduto();
                    //comandaProduto.setId();
                    comandaProduto.setProduto(produto);
                    comandaProduto.setQuantidade(quantidade);
                    if(ComandaProdutoBo.validaProduto(comandaProduto)){
                        comandaProdutoDao.getDao().createOrUpdate(comandaProduto);
                        Toast.makeText(activity, R.string.sucesso, Toast.LENGTH_SHORT).show();
                        activity.finish();
                    } else {
                        Toast.makeText(activity,R.string.erro, Toast.LENGTH_SHORT).show();
                    }

                } catch (SQLException e) {
                    e.printStackTrace();
                    Toast.makeText(activity, e.getMessage(), Toast.LENGTH_LONG).show();
                }
            }
        });
    }

    public void configSpinner(){
        try{
            produtoDao.getDao().createIfNotExists(new Produto(1,"Refrigerante", 3.0, "Ativo"));
            produtoDao.getDao().createIfNotExists(new Produto(2,"Cerveja", 5.0, "Ativo"));
            produtoDao.getDao().createIfNotExists(new Produto(3, "Batata frita", 10.0, "Ativo"));
            produtoDao.getDao().createIfNotExists(new Produto(4, "Pastel", 3.5, "Ativo"));
            produtoDao.getDao().createIfNotExists(new Produto(5, "Petiscos", 6.0, "Ativo"));

            adapterProdutos = new ArrayAdapter<>(activity, android.R.layout.simple_spinner_item, produtoDao.listar());
            activity.getSpinnerProdutos().setAdapter(adapterProdutos);

        }catch (Exception e){
            e.printStackTrace();
        }
    }

    private void configBtnGerenciarProduto() {
        this.activity.getBtnGerenciarProduto().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               gerenciarProduto();
            }
        });
    }

    public void gerenciarProduto(){
        Intent it = new Intent(activity, GerenciarProduto.class);
        activity.startActivity(it);
    }

}
