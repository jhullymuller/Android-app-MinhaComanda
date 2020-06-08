package com.example.minhacomanda.view;

import android.os.Bundle;
import android.widget.Button;
import android.widget.NumberPicker;
import android.widget.Spinner;

import androidx.appcompat.app.AppCompatActivity;

import com.example.minhacomanda.R;
import com.example.minhacomanda.control.AdicionaComandaProdutoControl;

public class AdicionaComandaProdutoActivity extends AppCompatActivity {

    private AdicionaComandaProdutoControl control;
    private Spinner spinnerProdutos;
    private NumberPicker npQuantidade;
    private Button btnEnviar;
    private Button btnGerenciarProduto;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_adiciona_comanda_produto);
        initialize();
        npQuantidade = findViewById(R.id.npQuantidade);
        npQuantidade.setMinValue(1);
        npQuantidade.setMaxValue(99);
        btnEnviar = findViewById(R.id.btnEnviar);
        btnGerenciarProduto = findViewById(R.id.btnGericiaProduto);
        control = new AdicionaComandaProdutoControl(this);
    }

    @Override
    protected void onResume() {
        super.onResume();
        this.control.configSpinner();
    }

    private void initialize(){
        spinnerProdutos = findViewById(R.id.spinnerProduto);
    }

    public Spinner getSpinnerProdutos() {
        return spinnerProdutos;
    }

    public void setSpinnerProdutos(Spinner spinnerProdutos) {
        this.spinnerProdutos = spinnerProdutos;
    }

    public Button getBtnEnviar() {
        return btnEnviar;
    }

    public NumberPicker getNpQuantidade() {
        return npQuantidade;
    }

    public void setNpQuantidade(NumberPicker npQuantidade) {
        this.npQuantidade = npQuantidade;
    }

    public void setBtnEnviar(Button btnEnviar) {
        this.btnEnviar = btnEnviar;
    }

    public Button getBtnGerenciarProduto() {
        return btnGerenciarProduto;
    }

    public void setBtnGerenciarProduto(Button btnGerenciarProduto) {
        this.btnGerenciarProduto = btnGerenciarProduto;
    }
}
