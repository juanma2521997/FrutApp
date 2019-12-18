package com.example.frutapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

public class PantallaFrutas extends AppCompatActivity {

    ListView listView;
    ArrayList<String> lista;
    ArrayAdapter adaptador;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pantalla_frutas);

        listView = (ListView)findViewById(R.id.listview);
        final BBDD_Helper dbHelper = new BBDD_Helper(this);

        lista = dbHelper.selectAll();
        adaptador = new ArrayAdapter(this,android.R.layout.simple_list_item_1,lista);
        listView.setAdapter(adaptador);
    }
}
