package com.eduardoejunin.manipulationcontrol;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        EditText login = findViewById(R.id.edtNomeCliente);
        EditText senha = findViewById(R.id.edtTelefone);
        DbHelper base = new DbHelper(getApplicationContext());
        Button btCadastro = findViewById(R.id.btCadastro);
        Button btEntrar = findViewById(R.id.btEntrar);

        btEntrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Usuario usuario = new Usuario();
                usuario.setLogin(login.getText().toString());
                usuario.setSenha(senha.getText().toString());
                if (TextUtils.isEmpty(usuario.getLogin()) || TextUtils.isEmpty(usuario.getSenha())) {
                    Toast.makeText(getApplicationContext(), "Informe o nome de usuário e a senha", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (base.isValidUser(usuario.getLogin(), usuario.getSenha())) {
                    // Login bem-sucedido

                    Toast.makeText(getApplicationContext(), "Login bem-sucedido", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(getApplicationContext(), Dashboard.class);
                    intent.putExtra("idUsuario", usuario.getId());
                    startActivity(intent);
                } else {
                    // Login falhou
                    Toast.makeText(getApplicationContext(), "Usuário ou senha inválidos", Toast.LENGTH_SHORT).show();
                }


            }
        });
        btCadastro.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                Intent intent = new Intent(MainActivity.this, CadastroUsuario.class);
                startActivity(intent);
            }
        });

    }
}