package com.example.minhacomanda.control;


import android.app.AlertDialog;
import android.content.DialogInterface;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.minhacomanda.bo.ProdutoBo;
import com.example.minhacomanda.dao.ProdutoDao;
import com.example.minhacomanda.model.Produto;
import com.example.minhacomanda.view.GerenciarProduto;

import java.sql.SQLException;
import java.util.List;

public class GerenciarProdutoControl extends AppCompatActivity {

    private GerenciarProduto activity;
    private Produto produto;
    private ArrayAdapter<Produto> adapterProdutos;
    private List<Produto> listProdutos;
    private ProdutoDao produtoDao;


    public GerenciarProdutoControl(GerenciarProduto activity) {
        this.activity = activity;
        produtoDao = new ProdutoDao(this.activity);
        configListaViewProdutos();

    }

    private void  configListaViewProdutos(){
        // ELEMENTOS DA LISTA
            try{
                listProdutos = produtoDao.listar();
                adapterProdutos = new ArrayAdapter<>(
                        activity,
                        android.R.layout.simple_list_item_1,
                        listProdutos
                );
                activity.getListaProdutos().setAdapter(adapterProdutos);
            }catch (SQLException e){
                e.printStackTrace();
            }
            addCliqueLongoListProdutos();
            addCliqueCurtoListProdutos();
    }

    private void addCliqueLongoListProdutos(){
        activity.getListaProdutos().setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                produto = adapterProdutos.getItem(position);
                confirmarExclusaoAction(produto);
                return true;
            }
        });
    }
    public void addCliqueCurtoListProdutos(){
        activity.getListaProdutos().setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                produto = adapterProdutos.getItem(position);
                AlertDialog.Builder alerta = new AlertDialog.Builder(activity);
                alerta.setTitle("Produto");
                alerta.setMessage(produto.toString());
                alerta.setNegativeButton("Fechar", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        produto = null;
                    }
                });
                alerta.setPositiveButton("Editar", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        popularFormAction(produto);
                    }
                });
                alerta.show();
            }
        });
    }
    private Produto getProdutoFormulario() {
        Produto e = new Produto();
        e.setNome(activity.getEditNomeProduto().getText().toString());
        try {
            e.setValor(Double.parseDouble(activity.getEditValorProduto().getText().toString()));
        } catch (Exception e1) {
        }
        e.setSituacao("Ativo");
        return e;
    }

    public void confirmarExclusaoAction(final Produto e) {
        AlertDialog.Builder alerta = new AlertDialog.Builder(activity);
        alerta.setTitle("Excluindo produto");
        alerta.setMessage("Deseja realmente excluir o produto " + e.getNome() + "?");
        alerta.setIcon(android.R.drawable.ic_menu_delete);
        alerta.setNegativeButton("Não", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                produto = null;
            }
        });
        alerta.setPositiveButton("Sim", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                try {
                    e.setSituacao("Inativo");
                    produtoDao.getDao().update(e);
                    adapterProdutos.remove(e);
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }

                produto = null;
            }
        });
        alerta.show();
    }

    public void popularFormAction(Produto e) {
        activity.getEditNomeProduto().setText(e.getNome());
        activity.getEditValorProduto().setText(e.getValor().toString());
    }


    public void limparFormAction() {
        activity.getEditNomeProduto().setText("");
        activity.getEditValorProduto().setText("");
    }

    private void cadastrar() {
        Produto produto = getProdutoFormulario();
        try {
            int res = produtoDao.getDao().create(produto);  //Envia par o banco de dados
            adapterProdutos.add(produto); //Atualiza no ListView

            if (res > 0) {
                Toast.makeText(activity, "Cadastrado com sucesso", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(activity, "Tente novamente em breve", Toast.LENGTH_SHORT).show();
            }
            Log.i("Testando", "Cadastrou");
            Toast.makeText(activity, "Id:" + produto.getId(), Toast.LENGTH_SHORT).show();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void editar(Produto newProduto) {
        produto.setNome(newProduto.getNome());
        produto.setValor(newProduto.getValor());
        try {
            adapterProdutos.notifyDataSetChanged(); //Atualiza no view
            int res = produtoDao.getDao().update(produto); //Editar no banco de dados
            if (res > 0) {
                Toast.makeText(activity, "Sucesso", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(activity, "Tente mais tarde", Toast.LENGTH_SHORT).show();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void salvarAcao() {
        Produto produtoFormulario = getProdutoFormulario();
        if (ProdutoBo.validarNomeProduto(produtoFormulario.getNome()) && ProdutoBo.validarValorProduto(produtoFormulario.getValor())) {
            if (produto == null) {
                cadastrar();
            } else {
                editar(produtoFormulario);
            }
            produto = null;
            limparFormAction();
        } else {
            Toast.makeText(activity, "Preencha os campos corretamente", Toast.LENGTH_SHORT).show();
        }
    }
}
