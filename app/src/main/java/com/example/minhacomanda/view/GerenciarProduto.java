package com.example.minhacomanda.view;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.minhacomanda.R;
import com.example.minhacomanda.control.GerenciarProdutoControl;

public class GerenciarProduto extends AppCompatActivity {
 private EditText editNomeProduto;
 private EditText editValorProduto;
 private ListView listaProdutos;
 private Button btnSalvar;
 private GerenciarProdutoControl control;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gerenciar_produto);
        initialize();
        control = new GerenciarProdutoControl(this);
    }
    private  void initialize(){
        editNomeProduto = findViewById(R.id.editNomeProduto);
        editValorProduto = findViewById(R.id.editValorProduto);
        listaProdutos = findViewById(R.id.listaProdutos);
        btnSalvar = findViewById(R.id.btnSalvar);
        btnSalvar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                control.salvarAcao();
            }
        });
    }

    public EditText getEditNomeProduto() {
        return editNomeProduto;
    }

    public void setEditNomeProduto(EditText editNomeProduto) {
        this.editNomeProduto = editNomeProduto;
    }

    public EditText getEditValorProduto() {
        return editValorProduto;
    }

    public void setEditValorProduto(EditText editValorProduto) {
        this.editValorProduto = editValorProduto;
    }

    public ListView getListaProdutos() {
        return listaProdutos;
    }

    public void setListaProdutos(ListView listaProdutos) {
        this.listaProdutos = listaProdutos;
    }

    public Button getBtnSalvar() {
        return btnSalvar;
    }

    public void setBtnSalvar(Button btnSalvar) {
        this.btnSalvar = btnSalvar;
    }



}
