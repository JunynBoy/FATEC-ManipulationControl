package com.eduardoejunin.manipulationcontrol;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.google.android.material.snackbar.Snackbar;

import java.util.ArrayList;
import java.util.List;

public class Dashboard extends AppCompatActivity {
    DbHelper base ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);
        ListView listView = findViewById(R.id.listViewPedidos);
        List<Pedido> dataList = consultaPedido();
        //dataList = consultaPedido();


        base = new DbHelper(getApplicationContext());

        CustomAdapter adapter = new CustomAdapter(this, dataList);
        listView.setAdapter(adapter);
//        dataList.add("Pedido 1");
//        dataList.add("Pedido 2");
//        dataList.add("Pedido 3");
        Button btnAdicionarPedido = findViewById(R.id.btnAdicionarPedido);
        btnAdicionarPedido.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int count = adapter.getCount() + 1;
                Intent intent = new Intent(getApplicationContext(),  cadastropedido.class);
                startActivity(intent);
                //dataList.add("Pedido " + count);
             //   Toast.makeText(getApplicationContext(), "Adicionado", Toast.LENGTH_SHORT).show();
                adapter.notifyDataSetChanged();

            }
        });


    }
    private List<Pedido> consultaPedido(){
        base = new DbHelper(getApplicationContext());
        List<Pedido>lista = new ArrayList<>();
        lista = base.consultaPedidos();
        return lista;
    }
}