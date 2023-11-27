package com.eduardoejunin.manipulationcontrol;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

public class DbHelper extends SQLiteOpenHelper {
    public static final int DATABASE_VERSION = 5;
    public static final String DATABASE_NOME = "manipulationControle";
    private static final String CREATE_Componente = "create table " +
            Contract.Componente.TABELA + " ( "+
            Contract.Componente._ID + " integer primary key AUTOINCREMENT, " +
            Contract.Componente.COLUNA_NOME + " text, "+
//            Contract.Componente.COLUNA_idPedido + " integer, "+

            Contract.Componente.COLUNA_Dosagem + " text) ";
    private static final String CREATE_Pedido = "create table " +
            Contract.Pedido.TABELA + " ( "+
            Contract.Pedido._ID + " INTEGER primary key AUTOINCREMENT, " +
            Contract.Pedido.COLUNA_QUANTIDADE + " text, "+
            Contract.Pedido.COLUNA_Status + " text,  " +
            Contract.Pedido.COLUNA_Tamanho + " float, " +
            Contract.Pedido.COLUNA_idUsuario + " integer, " +
            Contract.Pedido.COLUNA_idCliente + " integer)";

    private static final String CREATE_Usuario = "create table " +
            Contract.Usuario.TABELA + " ( "+
            Contract.Usuario._ID + " INTEGER primary key AUTOINCREMENT, " +
            Contract.Usuario.COLUNA_nome + " text, "+
            Contract.Usuario.COLUNA_Funcao + " text,  " +
            Contract.Usuario.COLUNA_Login + " text, " +
            Contract.Usuario.COLUNA_Senha + " text) ";

    private static final String CREATE_Cliente = "create table " +
            Contract.Cliente.TABELA + " ( "+
            Contract.Cliente._ID + " INTEGER primary key AUTOINCREMENT, " +
            Contract.Cliente.COLUNA_nome + " text, "+
            Contract.Cliente.COLUNA_Endereco + " text,  " +
            Contract.Cliente.COLUNA_Telefone + " text) ";

    private static final String DROP_Componente = "drop table if exists " +
            Contract.Componente.TABELA;
    private static final String DROP_Pedido = "drop table if exists " +
            Contract.Pedido.TABELA;
    private static final String DROP_Usuario = "drop table if exists " +
            Contract.Usuario.TABELA;
    private static final String DROP_Cliente = "drop table if exists " +
            Contract.Cliente.TABELA;
    public DbHelper(Context context){
        super(context, DATABASE_NOME, null, DATABASE_VERSION);
    }
    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase){
        try {
            sqLiteDatabase.execSQL(CREATE_Componente);
            sqLiteDatabase.execSQL(CREATE_Pedido);
            sqLiteDatabase.execSQL(CREATE_Cliente);
            sqLiteDatabase.execSQL(CREATE_Usuario);

        } catch (SQLException e) {
            e.printStackTrace();
            Log.e("onCreate", "Erro durante a criação do banco de dados", e);
        }
    }
    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int oldVersion, int newVersion){
        try {
            sqLiteDatabase.execSQL(DROP_Componente);
            sqLiteDatabase.execSQL(DROP_Pedido);
            sqLiteDatabase.execSQL(DROP_Usuario);
            sqLiteDatabase.execSQL(DROP_Cliente);

            onCreate(sqLiteDatabase);
        } catch (SQLException e) {
            e.printStackTrace();
            Log.e("onUpgrade", "Erro durante a atualização do banco de dados", e);
        }
    }
    @Override
    public void onDowngrade(SQLiteDatabase sqLiteDatabase, int oldVersion, int newVersion){
        onUpgrade(sqLiteDatabase, oldVersion, newVersion);
    }
    public void salvarComponente(Componente componente) {
        SQLiteDatabase sqLiteDatabase = getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(Contract.Componente.COLUNA_Dosagem, componente.getDosagem());
        contentValues.put(Contract.Componente.COLUNA_NOME, componente.getNome());
//        contentValues.put(Contract.Componente.COLUNA_idPedido, componente.getIdPedido());
        long id = sqLiteDatabase.insert(Contract.Componente.TABELA, null, contentValues);

        componente.setId(id);

    }
    public void salvarPedido(Pedido pedido){
        SQLiteDatabase sqLiteDatabase = getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(Contract.Pedido.COLUNA_QUANTIDADE, pedido.getTamanho());
        contentValues.put(Contract.Pedido.COLUNA_Status, pedido.getStatus());
        contentValues.put(Contract.Pedido.COLUNA_idUsuario, pedido.getIdUsuario());
        contentValues.put(Contract.Pedido.COLUNA_idCliente, pedido.getIdCliente());
        long id = sqLiteDatabase.insert(Contract.Pedido.TABELA, null, contentValues);
        pedido.setId(id);
    }
    @SuppressLint("Range")
    public List<Componente> consultaComponentes(){

//        ArrayList lista = new ArrayList();
        List<Componente> lista = new ArrayList<>();
        SQLiteDatabase sqLiteDatabase = getReadableDatabase();
        String query = "SELECT * FROM " + Contract.Componente.TABELA;
        Cursor cursor = sqLiteDatabase.rawQuery("select * from " +
            Contract.Componente.TABELA, null);
        Log.d("RowCount", "Número de linhas: " + cursor.getCount());
            while (cursor.moveToNext()) {
                lista.add(new Componente(cursor.getInt(
                        cursor.getColumnIndex(Contract.Componente._ID)),
                        cursor.getString(cursor.getColumnIndex(Contract.Componente.COLUNA_Dosagem)),
                        cursor.getString(cursor.getColumnIndex(Contract.Componente.COLUNA_NOME))));
//                        cursor.getInt(cursor.getColumnIndex(Contract.Componente.COLUNA_idPedido))));

            }
        cursor.close();
            sqLiteDatabase.close();


        return lista;
    }
    public ArrayList consultaPedidos(){
        ArrayList lista = new ArrayList();
        SQLiteDatabase sqLiteDatabase = getReadableDatabase();



        return lista;
    }
    public void salvaCliente(Cliente cliente){

    }
    public List<Cliente> consultaClientes(){
        ArrayList lista = new ArrayList();
        return lista;
    }
    public void salvaUsuario(Usuario usuario){

    }
    public List<Usuario> consultaUsuarios(){
        ArrayList lista = new ArrayList();
        return lista;
    }
}
