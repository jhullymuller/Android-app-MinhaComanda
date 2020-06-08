package com.example.minhacomanda.control;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Toast;

import com.example.minhacomanda.R;
import com.example.minhacomanda.bo.ComandaProdutoBo;
import com.example.minhacomanda.dao.ComandaProdutoDao;
import com.example.minhacomanda.model.ComandaProduto;
import com.example.minhacomanda.view.AdicionaComandaProdutoActivity;
import com.example.minhacomanda.view.MainActivity;

import java.sql.SQLException;
import java.util.List;

public class MainControl {
    private MainActivity activity;

    // lista da comanda
    private ArrayAdapter<ComandaProduto> adapterComandaProdutos;
    private List<ComandaProduto> listComandaProdutos;

    // Criação dos Daos
    private ComandaProdutoDao comandaProdutoDao;

    public MainControl(final MainActivity activity) {
        this.activity = activity;
        comandaProdutoDao = new ComandaProdutoDao(this.activity);
        activity.getBtnAddProduto().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addComandaProduto();
            }
        });

        activity.getBtnLimparLista().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                limparTela();
            }
        });

        activity.getListaComanda().setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                ComandaProduto comandaProduto = adapterComandaProdutos.getItem(position);
                confirmarExclusaoAction(comandaProduto);
                return true;
            }
        });
        addCliqueCurtoListProdutos();

        recarregar();
    }
    public void addCliqueCurtoListProdutos(){
        activity.getListaComanda().setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                final ComandaProduto comandaProduto = adapterComandaProdutos.getItem(position);
                AlertDialog.Builder alerta = new AlertDialog.Builder(activity);
                alerta.setTitle("Editar comanda");
                alerta.setMessage(comandaProduto.toString());
                alerta.setNegativeButton("-1", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        if ((comandaProduto.getQuantidade() - 1) <= 0) {
                            try {
                                comandaProdutoDao.getDao().delete(comandaProduto);
                                recarregar();
                            } catch (SQLException e) {
                                Toast.makeText(activity, e.getMessage(), Toast.LENGTH_LONG).show();
                            }
                        } else {
                            try {
                                comandaProduto.setQuantidade(comandaProduto.getQuantidade() - 1);
                                comandaProdutoDao.getDao().update(comandaProduto);
                                recarregar();
                            } catch (SQLException e) {
                                Toast.makeText(activity, e.getMessage(), Toast.LENGTH_LONG).show();
                            }
                        }
                    }
                });
                alerta.setPositiveButton("+1", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        try {
                            comandaProduto.setQuantidade(comandaProduto.getQuantidade() + 1);
                            comandaProdutoDao.getDao().update(comandaProduto);
                            recarregar();
                        } catch (SQLException e) {
                            Toast.makeText(activity, e.getMessage(), Toast.LENGTH_LONG).show();
                        }
                    }
                });
                alerta.setNeutralButton("Fechar", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                    }
                });
                alerta.show();
            }
        });
    }

    public void confirmarExclusaoAction(final ComandaProduto e){
        AlertDialog.Builder alerta = new AlertDialog.Builder(activity);
        alerta.setTitle(R.string.tituloExcluir);
        alerta.setMessage(R.string.perguntaExcluir);
        alerta.setIcon(android.R.drawable.ic_menu_delete);
        alerta.setNegativeButton(R.string.nao, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
            }
        });
        alerta.setPositiveButton(R.string.sim, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                try {
                    comandaProdutoDao.getDao().delete(e);
                    recarregar();
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            }
        });
        alerta.show();
    }

    public void addComandaProduto(){
        Intent it = new Intent(activity, AdicionaComandaProdutoActivity.class);
        activity.startActivity(it);
    }

    private void limparTela(){
        try {
            comandaProdutoDao.getDao().delete(listComandaProdutos);
            recarregar();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void configListViewComandaProdutos(){
        try{
            listComandaProdutos = comandaProdutoDao.getDao().queryForAll();

            adapterComandaProdutos= new ArrayAdapter<ComandaProduto>(
                    activity,
                    android.R.layout.simple_list_item_1,
                    listComandaProdutos
            );
            activity.getListaComanda().setAdapter(adapterComandaProdutos);
        } catch (SQLException e){
            e.printStackTrace();
        }
    }

    public void recarregar() {
        activity.getLayoutResultado().removeAllViews();
        activity.getTelaResultado().setText("");
        configListViewComandaProdutos();
        activity.getTelaResultado().setText(activity.getString(R.string.total) + ComandaProdutoBo.calcularTotalaPagar(listComandaProdutos));
    }

}
