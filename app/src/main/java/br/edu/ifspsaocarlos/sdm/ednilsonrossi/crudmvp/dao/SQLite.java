package br.edu.ifspsaocarlos.sdm.ednilsonrossi.crudmvp.dao;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class SQLite extends SQLiteOpenHelper{

    public static final String DATABASE_NAME = "crudmvp.db";
    public static final String DATABASE_TABLE_CONTATOS = "contatos";
    public static final String CONTATOS_FIELD_ID = "id";
    public static final String CONTATOS_FIELD_APELIDO = "apelido";
    public static final String CONTATOS_FIELD_NOME = "nomeCompleto";
    public static final int DATABASE_VERSION = 1;

    public SQLite(Context context){
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String databaseCreate;

        databaseCreate = "CREATE TABLE " + DATABASE_TABLE_CONTATOS + " ( " +
                CONTATOS_FIELD_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                CONTATOS_FIELD_APELIDO + " TEXT NOT NULL, " +
                CONTATOS_FIELD_NOME + " TEXT NOT NULL);";

        sqLiteDatabase.execSQL(databaseCreate);

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}
