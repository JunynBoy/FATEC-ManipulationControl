package com.eduardoejunin.manipulationcontrol;

import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

public class CustomAdapter extends BaseAdapter {

    private Context context;
    private List<Pedido> dataList;

    public CustomAdapter(Context context, List<Pedido> dataList) {
        this.context = context;
        this.dataList = dataList;
    }

    @Override
    public int getCount() {
        return dataList.size();
    }

    @Override
    public Object getItem(int position) {
        return dataList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }


    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        View view = LayoutInflater.from(context).inflate(R.layout.list_item, null);

        TextView textView = view.findViewById(R.id.textView);
        Button btnAlterar = view.findViewById(R.id.btnAlterar);
        Button btnExcluir = view.findViewById(R.id.btnExcluir);
        Button btnAprovar = view.findViewById(R.id.btnAprovar);
        Pedido pedido = dataList.get(position);

        textView.setText("Pedido: " + pedido.getId()  + " Status: "+pedido.getStatus());
        btnAlterar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Ação do botão
                Toast.makeText(context, "Alterar", Toast.LENGTH_SHORT).show();
            }
        });
        btnExcluir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Ação do botão exluir
                showConfirmationDialog(position, "Tem certeza de que deseja excluir este item?", 1);
                DbHelper base = new DbHelper(context);
                base.removePedido(pedido.getId());
          //      dataList.remove(position );
             //   notifyDataSetChanged();
                Toast.makeText(context, "Excluir", Toast.LENGTH_SHORT).show();
            }
        });
        btnAprovar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Ação do botão aprovar
                showConfirmationDialog(position, "Finalizar pedido?", 2);
            }
        });
        return view;
    }
    private void showConfirmationDialog(final int position, String mensagem, int tipoConfirmacao) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle("ATENÇÃO");
        builder.setMessage(mensagem);

        // Adiciona botões "Sim" e "Não"
        builder.setPositiveButton("Sim", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                // Lógica para excluir o item
                if (tipoConfirmacao == 1) {
                    dataList.remove(position);
                    notifyDataSetChanged();
                    Toast.makeText(context, "Item excluído com sucesso", Toast.LENGTH_SHORT).show();
                }
                else{
                    if(tipoConfirmacao == 2){
                        Pedido pedido = dataList.get(position);

//                        dataList.remove(position);
                        pedido.setStatus("Aprovado");
                        notifyDataSetChanged();
                        Toast.makeText(context, "Item Aprovado com sucesso", Toast.LENGTH_SHORT).show();
                    }
                }

            }
        });

        builder.setNegativeButton("Não", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                // Nada a fazer se o usuário clicar em "Não"
            }
        });

        // Mostra o diálogo
        builder.create().show();
    }

}
