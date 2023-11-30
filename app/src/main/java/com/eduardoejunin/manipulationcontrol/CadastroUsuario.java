package com.eduardoejunin.manipulationcontrol;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class CadastroUsuario extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro_usuario);
        DbHelper base = new DbHelper(getApplicationContext());
        EditText nome = findViewById(R.id.edtNome);
        EditText funcao = findViewById(R.id.edtFuncao);
        EditText login = findViewById(R.id.edtNomeCliente);
        EditText senha  = findViewById(R.id.edtTelefone);

        Button btVoltar = findViewById(R.id.btEntrar);
        Button btCadastrar = findViewById(R.id.btCadastro);

        btCadastrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Usuario usuario = new Usuario();
                usuario.setNome(nome.getText().toString());
                usuario.setFuncao(funcao.getText().toString());
                usuario.setLogin(login.getText().toString());
                usuario.setSenha(senha.getText().toString());
                base.salvaUsuario(usuario);
                nome.setText("");
                funcao.setText("");
                login.setText("");
                senha.setText("");

                Toast.makeText(getApplicationContext(), "Usuário Cadastrado com sucesso!", Toast.LENGTH_SHORT).show();

            }
        });
        btVoltar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }
}