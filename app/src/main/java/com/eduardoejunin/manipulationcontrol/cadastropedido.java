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
        Spinner spnUsuario = findViewById(R.id.spnUsuario);
        tamanho = findViewById(R.id.edtTamanho);
        quantidade = findViewById(R.id.edtQuantidade);
        responsavel = findViewById(R.id.edtResponsavel);
        List<Componente> dataList = criarListaComponentes();
        ComponentAdapterDropDownComponentes adapter = new ComponentAdapterDropDownComponentes(this, dataList);

        spnComponente.setAdapter(adapter);
        adapter.notifyDataSetChanged();
        Button btnAdicionarComponente = findViewById(R.id.btnAdicionarComponentes);
        Button btnCadastroCliente = findViewById(R.id.btnAdicionarClientes);
        btnCancelar = findViewById(R.id.btnCancelar);
        btnCadastraPedido = findViewById(R.id.btnCadastrarPedido);
        Usuario usuarioLogado  = new Usuario();
        List<Usuario> dataListUsuarios = criarListaUsuarios();
        List<Cliente> dataListClientes = criarListaClientes();
        ClienteAdapter adapterDropDownClientes = new ClienteAdapter(this, dataListClientes);
        spnUsuario.setAdapter(adapterDropDownClientes);
        UsuarioAdapter usuarioAdapter = new UsuarioAdapter(this, dataListUsuarios);

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
                usuarioLogado.setId(base.idUsuarioLogado);
                pedido.setQuantitade(Integer.parseInt(quantidade.getText().toString()));
                pedido.setIdUsuario(usuarioLogado.getId());
                pedido.setTamanho(tamanho.getText().toString());


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
        btnCadastroCliente.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), cadastroCliente.class);
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

        return lista;
    }
    private List<Usuario> criarListaUsuarios(){
        List<Usuario> lista = new ArrayList<>();
        lista = base.consultaUsuarios();
        return lista;
    }
    private List<Cliente> criarListaClientes(){
        List<Cliente> lista = new ArrayList<>();
        lista = base.consultaClientes();
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