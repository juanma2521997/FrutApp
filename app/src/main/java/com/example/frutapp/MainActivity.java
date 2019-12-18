package com.example.frutapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    EditText textoName, textoWeight;
    static EditText textoNameBottom;
    Button botonAdd, botonGetAll,botonGetLast,botonGetByName;
    Spinner spinner;
    CheckBox rotten;
    ListView listView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //botones
        botonAdd = (Button)findViewById(R.id.button);
        botonGetAll = (Button)findViewById(R.id.button2);
        botonGetLast = (Button)findViewById(R.id.button3);
        botonGetByName = (Button)findViewById(R.id.button4);
        //campos de texto
        textoName = (EditText)findViewById(R.id.editText2);
        textoWeight = (EditText)findViewById(R.id.editText3);
        textoNameBottom = (EditText)findViewById(R.id.editText4);
        //desplegable
        spinner = (Spinner)findViewById(R.id.spinner);
        //checkbox
        rotten = (CheckBox)findViewById(R.id.checkBox);
        //listView de la pantalla donde mostramos las frutas
        listView = (ListView)findViewById(R.id.listview);

        //intancia helper de la bbdd
        final BBDD_Helper dbHelper = new BBDD_Helper(this);



        //Funcionalidad de los botones

        //Añade un nuevo fruiti a la base de datos pulsando el boton add
        botonAdd.setOnClickListener((new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int bool = 0;
                SQLiteDatabase db = dbHelper.getWritableDatabase();

                // Create a new map of values, where column names are the keys
                ContentValues values = new ContentValues();
                values.put(DataBaseCreate.NOMBRE_CAMPO2, textoName.getText().toString());
                values.put(DataBaseCreate.NOMBRE_CAMPO3, textoWeight.getText().toString());
                values.put(DataBaseCreate.NOMBRE_CAMPO4, spinner.getSelectedItem().toString());
                //Si el checkbox está marcado insertamos 1 (true), si no insertamos 0 (false)
                if(rotten.isChecked()){
                    bool++;
                }
                values.put(DataBaseCreate.NOMBRE_CAMPO5, bool);
                // Insert the new row, returning the primary key value of the new row
                long newRowId = db.insert(DataBaseCreate.TABLE_NAME, null, values);

                Toast.makeText(getApplicationContext(),"Fruiti añadido con exito, id: " + newRowId, Toast.LENGTH_LONG).show();
            }

        }));


    }


    //Carga la actividad que muestra todos los fruitis
    public void loadAllView(View view){

        Intent i = new Intent(this, PantallaFrutas.class);
        startActivity(i);
    }

    public void loadLastView(View view){

        Intent i = new Intent(this, PantallaUltimaFruta.class);
        startActivity(i);
    }

    public void loadByNameView(View view){

        if(textoNameBottom.getVisibility() == View.VISIBLE){
            Intent i = new Intent(this, PantallaFrutaByName.class);
            startActivity(i);
        }else{
            textoNameBottom.setVisibility(View.VISIBLE);
        }
    }
}
