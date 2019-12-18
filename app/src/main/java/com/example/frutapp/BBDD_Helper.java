package com.example.frutapp;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

import static com.example.frutapp.DataBaseCreate.SQL_CREATE_ENTRIES;
import static com.example.frutapp.DataBaseCreate.SQL_DELETE_ENTRIES;

public class BBDD_Helper extends SQLiteOpenHelper {
    // If you change the database schema, you must increment the database version.
    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "TheFruitis.db";


    public BBDD_Helper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(SQL_CREATE_ENTRIES);
    }
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // This database is only a cache for online data, so its upgrade policy is
        // to simply to discard the data and start over
        db.execSQL(SQL_DELETE_ENTRIES);
        onCreate(db);
    }
    public void onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        onUpgrade(db, oldVersion, newVersion);
    }

    /**
     * Método que devuelve una lista de todos los campos de la tabla fruitis
     * @return lista de fruitis
     */
    public ArrayList<String> selectAll(){
        String podrida = "";
        ArrayList<String> listaFrutas = new ArrayList<String>();
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "SELECT * FROM fruitis";
        Cursor registros = db.rawQuery(query,null);
        if(registros.moveToFirst()){
            do{
                //Si el checkbox esta marcado (nos devuelve la bbdd "1") esta podrida, si no no lo esta
                podrida = registros.getString(4).equals("1") ? "Rotten": "NotRotten";

                listaFrutas.add("ID: " + registros.getString(0)+"  "+ registros.getString(1)+
                        " Weight: "+ registros.getString(2)+ " " + podrida);
            }while(registros.moveToNext());
        }
        return listaFrutas;

    }

    /**
     * metodo que devuelve el ultimo dato de nuestra tabla
     * @return
     */
    public ArrayList<String> selectLast(){

        String podrida = "";
        ArrayList<String> listaFrutas = new ArrayList<String>();
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "SELECT * FROM fruitis";
        Cursor registros = db.rawQuery(query,null);
        registros.moveToLast();

            podrida = registros.getString(4).equals("1") ? "Rotten": "NotRotten";
                listaFrutas.add("ID: " + registros.getString(0)+"  "+ registros.getString(1)+
                        " Weight: "+ registros.getString(2)+ " " + podrida);
        return listaFrutas;

    }

    /**
     * Metodo que devuelve una fruta dado un nombre
     * @param name
     * @return
     */
    public ArrayList<String> selectbyName(String name){
        String podrida = "";
        ArrayList<String> listaFrutas = new ArrayList<String>();
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "SELECT * FROM fruitis WHERE NAME LIKE '"+name+"'";
        Cursor registros = db.rawQuery(query,null);
        registros.moveToFirst();

        podrida = registros.getString(4).equals("1") ? "Rotten": "NotRotten";
        listaFrutas.add("ID: " + registros.getString(0)+"  "+ registros.getString(1)+
                " Weight: "+ registros.getString(2)+ " " + podrida);
        return listaFrutas;

    }
}
