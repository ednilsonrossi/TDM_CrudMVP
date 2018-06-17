package br.edu.ifspsaocarlos.sdm.ednilsonrossi.crudmvp.model.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.LinkedList;
import java.util.List;

import br.edu.ifspsaocarlos.sdm.ednilsonrossi.crudmvp.model.excecoes.CampoNaoEncontradoException;
import br.edu.ifspsaocarlos.sdm.ednilsonrossi.crudmvp.model.excecoes.DadoNaoEncontradoException;
import br.edu.ifspsaocarlos.sdm.ednilsonrossi.crudmvp.model.excecoes.ObjetoNuloException;
import br.edu.ifspsaocarlos.sdm.ednilsonrossi.crudmvp.model.dominio.Contato;

public class ContatoDAO {
    private SQLiteDatabase database;
    private SQLite sqLite;

    public ContatoDAO(Context context) {
        sqLite = new SQLite(context);
    }

    public void create(Contato contato) throws ObjetoNuloException{
        if(contato == null){
            throw new ObjetoNuloException("Contato não instanciado");
        }
        ContentValues valores = new ContentValues();
        valores.put(SQLite.CONTATOS_FIELD_APELIDO, contato.getApelido());
        valores.put(SQLite.CONTATOS_FIELD_NOME, contato.getNomeCompleto());

        database = sqLite.getWritableDatabase();
        database.insert(SQLite.DATABASE_TABLE_CONTATOS, null, valores);

        database.close();
    }

    public Contato recuperate(int id) throws CampoNaoEncontradoException, DadoNaoEncontradoException{
        Contato retorno = null;
        Cursor cursor;

        if(id <= 0){
            throw new CampoNaoEncontradoException("Id informado é inválido.");
        }

        String colunas[] = new String[]{SQLite.CONTATOS_FIELD_ID, SQLite.CONTATOS_FIELD_APELIDO, SQLite.CONTATOS_FIELD_NOME};
        String where = SQLite.CONTATOS_FIELD_ID + " = " + id;

        database = sqLite.getReadableDatabase();
        cursor = database.query(SQLite.DATABASE_TABLE_CONTATOS, colunas, where, null, null, null, SQLite.CONTATOS_FIELD_ID);

        if(!cursor.moveToNext()){
            cursor.close();
            database.close();
            throw new DadoNaoEncontradoException("Contato com id: " + id + " não está cadastrado cadastrado.");
        }
        retorno = new Contato(cursor.getInt(0), cursor.getString(1), cursor.getString(2));
        cursor.close();
        database.close();

        return retorno;
    }

    public List<Contato> recuperate() throws DadoNaoEncontradoException {
        Contato contato;
        List<Contato> retorno = new LinkedList<>();
        Cursor cursor;

        String colunas[] = new String[]{SQLite.CONTATOS_FIELD_ID, SQLite.CONTATOS_FIELD_APELIDO, SQLite.CONTATOS_FIELD_NOME};

        database = sqLite.getReadableDatabase();
        cursor = database.query(SQLite.DATABASE_TABLE_CONTATOS, colunas, null, null, null, null, SQLite.CONTATOS_FIELD_APELIDO);

        if(!cursor.moveToNext()){
            cursor.close();
            database.close();
            throw new DadoNaoEncontradoException("Não há contatos cadastrados.");
        }
        do{
            contato = new Contato(cursor.getInt(0), cursor.getString(1), cursor.getString(2));
            retorno.add(contato);
        }while (cursor.moveToNext());

        cursor.close();
        database.close();

        return retorno;
    }

    public void update(Contato contato) throws ObjetoNuloException, DadoNaoEncontradoException{
        if(contato == null){
            throw new ObjetoNuloException("Contato inválido.");
        }
        String where = SQLite.CONTATOS_FIELD_ID + " = " + contato.getId();
        ContentValues valores = new ContentValues();
        valores.put(SQLite.CONTATOS_FIELD_ID, contato.getId());
        valores.put(SQLite.CONTATOS_FIELD_APELIDO, contato.getApelido());
        valores.put(SQLite.CONTATOS_FIELD_NOME, contato.getNomeCompleto());

        database = sqLite.getWritableDatabase();
        if(database.update(SQLite.DATABASE_TABLE_CONTATOS, valores, where, null) <= 0 ){
            database.close();
            throw new DadoNaoEncontradoException("Contato não cadastrado: " + contato.toString());
        }
        database.close();
    }

    public void delete(Contato contato) throws ObjetoNuloException, DadoNaoEncontradoException{
        if(contato == null){
            throw new ObjetoNuloException("Contato inválido.");
        }
        String where;
        database = sqLite.getWritableDatabase();

        where = SQLite.CONTATOS_FIELD_ID + " = " + contato.getId();
        if(database.delete(SQLite.DATABASE_TABLE_CONTATOS, where, null) <= 0){
            database.close();
            throw new DadoNaoEncontradoException("Contato não cadastrado: " + contato.toString() );
        }
        database.close();
    }

}


