package com.eduardoejunin.manipulationcontrol;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class cadastrocomponente extends AppCompatActivity {

    DbHelper base;
    private TextView dosagem, formula;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastrocomponente);
        base = new DbHelper(getApplicationContext());
        Button btnCadastrar = findViewById(R.id.btnCadastrarComponente);
        Button btnVoltar = findViewById(R.id.btnVoltarTelaInicial);
       formula = findViewById(R.id.edtFormula);
       dosagem = findViewById(R.id.edtDose);
        Button btnCadastrarComponente = findViewById(R.id.btnCadastrarComponente);
        btnCadastrarComponente.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            //    Componente componente = new Componente(0, dosagem.getText().toString(), formula.getText().toString(), 1 );
               Componente componente = new Componente();
                componente.setDosagem(dosagem.getText().toString());
                componente.setNome(formula.getText().toString());
              //  componente.setIdPedido(3);
                base.salvarComponente(componente);
                Toast.makeText(getApplicationContext(), "Cadastrado com sucesso!", Toast.LENGTH_SHORT).show();

            }
        });
        btnVoltar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), cadastropedido.class );
                startActivity(intent);
            }
        });

    }
    public void click(View v){

    }
}