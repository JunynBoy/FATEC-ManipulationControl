package com.eduardoejunin.manipulationcontrol;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class ClienteAdapter extends ArrayAdapter<Cliente> {

    public ClienteAdapter(Context context, List<Cliente> clientes) {
        super(context, 0, clientes);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        return criarItemView(position, convertView, parent);
    }

    @Override
    public View getDropDownView(int position, View convertView, ViewGroup parent) {
        return criarItemView(position, convertView, parent);
    }

    private View criarItemView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(android.R.layout.simple_spinner_dropdown_item, parent, false);
        }

        Cliente cliente = getItem(position);

        TextView textViewNome = convertView.findViewById(android.R.id.text1);
        textViewNome.setText(cliente.getNome());

        return convertView;
    }
}
