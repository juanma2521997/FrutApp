package com.example.frutapp;

public class DataBaseCreate {
    private DataBaseCreate(){}

    public static final String TABLE_NAME = "fruitis";
    public static final String NOMBRE_CAMPO1 = "id";
    public static final String NOMBRE_CAMPO2 = "name";
    public static final String NOMBRE_CAMPO3 = "weight";
    public static final String NOMBRE_CAMPO4 = "type";
    public static final String NOMBRE_CAMPO5 = "rotten";

    public static final String SQL_CREATE_ENTRIES =
            "CREATE TABLE " + DataBaseCreate.TABLE_NAME + " (" +
                    DataBaseCreate.NOMBRE_CAMPO1 + " INTEGER PRIMARY KEY AUTOINCREMENT,"+
                    DataBaseCreate.NOMBRE_CAMPO2 + " TEXT,"+
                    DataBaseCreate.NOMBRE_CAMPO3 + " INTEGER,"+
                    DataBaseCreate.NOMBRE_CAMPO4 + " TEXT,"+
                    DataBaseCreate.NOMBRE_CAMPO5 + " BOOLEAN)";

    public static final String SQL_DELETE_ENTRIES =
            "DROP TABLE IF EXISTS " + DataBaseCreate.TABLE_NAME;
}
