package com.example.minhacomanda.view;

import android.os.Bundle;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.minhacomanda.R;
import com.example.minhacomanda.control.MainControl;

public class MainActivity extends AppCompatActivity {
    private MainControl control;
    private Button btnAddProduto;
    private Button btnLimparLista;
    private ListView listaComanda;
    private LinearLayout layoutResultado;
    private TextView telaResultado;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initialize();
        control = new MainControl(this);
    }

    @Override
    protected void onResume() {
        super.onResume();
        this.control.recarregar();
    }

    private void initialize(){
        btnAddProduto = findViewById(R.id.btnAddProduto);
        btnLimparLista = findViewById(R.id.btnLimparLista);
        listaComanda = findViewById(R.id.listaComanda);
        layoutResultado = findViewById(R.id.layoutResultado);
        telaResultado = findViewById(R.id.telaResultado);
    }

    public Button getBtnAddProduto() { return btnAddProduto; }

    public void setBtnAddProduto(Button btnAddProduto) { this.btnAddProduto = btnAddProduto; }

    public Button getBtnLimparLista() { return btnLimparLista; }

    public void setBtnLimparLista(Button btnLimparLista) { this.btnLimparLista = btnLimparLista; }

    public LinearLayout getLayoutResultado() { return layoutResultado; }

    public void setLayoutResultado(LinearLayout layoutResultado) { this.layoutResultado = layoutResultado; }

    public ListView getListaComanda() { return listaComanda; }

    public void setListaComanda(ListView listaComanda) {
        this.listaComanda = listaComanda;
    }

    public TextView getTelaResultado() { return telaResultado; }

    public void setTelaResultado(TextView telaResultado) { this.telaResultado = telaResultado; }
}
