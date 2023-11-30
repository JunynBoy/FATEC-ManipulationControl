package com.eduardoejunin.manipulationcontrol;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

public class ComponentAdapterDropDownClientes extends ArrayAdapter<Cliente> {

    private Context context;
    private List<Cliente> clientes;

    public ComponentAdapterDropDownClientes(Context context, int resource, List<Cliente> clientes) {
        super(context, resource, clientes);
        this.context = context;
        this.clientes = clientes;
    }

    @Override
    public View getDropDownView(int position, View convertView, ViewGroup parent) {
        return createItemView(position, convertView, parent);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        return createItemView(position, convertView, parent);
    }

    private View createItemView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(android.R.layout.simple_spinner_dropdown_item, parent, false);
        }

        TextView textViewClienteNome = convertView.findViewById(android.R.id.text1);
        textViewClienteNome.setText(clientes.get(position).getNome());

        return convertView;
    }
}
