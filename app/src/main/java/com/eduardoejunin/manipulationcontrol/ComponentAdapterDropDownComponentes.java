package com.eduardoejunin.manipulationcontrol;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

public class ComponentAdapterDropDownComponentes extends ArrayAdapter<Componente> {

    public ComponentAdapterDropDownComponentes(Context context, List<Componente> componentes) {
        super(context, 0, componentes);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        return createView(position, convertView, parent);
    }

    @Override
    public View getDropDownView(int position, View convertView, ViewGroup parent) {
        return createView(position, convertView, parent);
    }

    private View createView(int position, View convertView, ViewGroup parent) {
        Componente componente = getItem(position);

        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(android.R.layout.simple_spinner_dropdown_item, parent, false);
        }

        TextView textViewNome = convertView.findViewById(android.R.id.text1);

        textViewNome.setText(componente.getNome());

        return convertView;
    }
}