package com.eduardoejunin.manipulationcontrol;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class cadastropedido extends AppCompatActivity {
    private DbHelper base;
    private Button btnCadastraPedido;
    private Button btnCancelar;
    private TextView quantidade;
    private TextView tamanho;
    private TextView responsavel;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastropedido);
        base = new DbHelper(getApplicationContext());
        Spinner spnComponente = findViewById(R.id.spnComponentes);
        tamanho = findViewById(R.id.edtTamanho);
        quantidade = findViewById(R.id.edtQuantidade);
        responsavel = findViewById(R.id.edtResponsavel);
        List<Componente> dataList = criarListaComponentes();
        ComponentAdapterDropDownComponentes adapter = new ComponentAdapterDropDownComponentes(this, dataList);
        spnComponente.setAdapter(adapter);
        adapter.notifyDataSetChanged();
        Button btnAdicionarComponente = findViewById(R.id.btnAdicionarComponentes);
        btnCancelar = findViewById(R.id.btnCancelar);
        btnCadastraPedido = findViewById(R.id.btnCadastrarPedido);

        btnCancelar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), Dashboard.class );
                startActivity(intent);
            }
        });
        btnCadastraPedido.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Pedido pedido = new Pedido();
                pedido.setQuantitade(Integer.parseInt(quantidade.getText().toString()));


                cadastrarPedido(pedido);
            }
        });
        btnAdicionarComponente.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),  cadastrocomponente.class);
                startActivity(intent);
            }
        });
    }
    private void cadastrarPedido(Pedido pedido){

    }
    private List<Componente> criarListaComponentes() {
        List<Componente> lista = new ArrayList<>();
        try {


            lista = base.consultaComponentes();

        }
        catch (Exception e){
            e.printStackTrace();
        }
//        lista.add(new Componente(1, "Dosagem1", "Nome1", 101));
//        lista.add(new Componente(2, "Dosagem2", "Nome2", 102));
//        lista.add(new Componente(3, "Dosagem3", "Nome3", 103));
        return lista;
    }

    private void cadastrarComponentes(){


        try {


//            base.salvarComponente(componente);


        }
        catch (Exception e){
            throw e;
        }
    }
}