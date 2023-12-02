package com.eduardoejunin.manipulationcontrol;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class cadastroCliente extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro_cliente);
        DbHelper base = new DbHelper(getApplicationContext());
          EditText nome = findViewById(R.id.edtNomeCliente);
         EditText endereco = findViewById(R.id.edtEnderecoCliente);
         EditText telefone = findViewById(R.id.edtTelefoneCliente);
        Button btnVoltarTelaPedido = findViewById(R.id.btnVoltarTelaPedidos);

          Button btnCadastrarCliente = findViewById(R.id.btnCadastrarCliente);
            btnCadastrarCliente.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    try{
                        Cliente cliente = new Cliente();
                        cliente.setEndereco(endereco.getText().toString());
                        cliente.setNome(nome.getText().toString());
                        cliente.setTelefone(telefone.getText().toString());
                        base.salvaCliente(cliente);
                        endereco.setText("");
                        nome.setText("");
                        telefone.setText("");
                        Toast.makeText(getApplicationContext(), "Cadastrado com sucesso!", Toast.LENGTH_SHORT).show();

                    }
                    catch (Exception e){

                    }
                }
            });
        btnVoltarTelaPedido.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), cadastropedido.class);
                startActivity(intent);

            }
        });

    }
}