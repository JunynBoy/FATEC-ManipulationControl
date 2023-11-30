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
    public static int idUsuarioLogado;
    public static final int DATABASE_VERSION = 6;
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

        SQLiteDatabase sqLiteDatabase = getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(Contract.Cliente.COLUNA_nome, cliente.getNome());
        contentValues.put(Contract.Cliente.COLUNA_Endereco, cliente.getEndereco());
        contentValues.put(Contract.Cliente.COLUNA_Telefone, cliente.getTelefone());
        long id = sqLiteDatabase.insert(Contract.Cliente.TABELA, null, contentValues);
        cliente.setId(id);
    }
    public List<Cliente> consultaClientes(){

        List<Cliente> lista = new ArrayList<>();
        SQLiteDatabase sqLiteDatabase = getReadableDatabase();
        String query = "SELECT * FROM " + Contract.Cliente.TABELA;
        Cursor cursor = sqLiteDatabase.rawQuery("select * from " +
                Contract.Cliente.TABELA, null);
        Log.d("RowCount", "Número de linhas: " + cursor.getCount());
        while (cursor.moveToNext()) {
            lista.add(new Cliente(cursor.getInt(
                    cursor.getColumnIndex(Contract.Cliente._ID)),
                    cursor.getString(cursor.getColumnIndex(Contract.Cliente.COLUNA_nome)),
                    cursor.getString(cursor.getColumnIndex(Contract.Cliente.COLUNA_Endereco)),
            cursor.getString(cursor.getColumnIndex(Contract.Cliente.COLUNA_Telefone))));
//                        cursor.getInt(cursor.getColumnIndex(Contract.Componente.COLUNA_idPedido))));

        }
        cursor.close();
        sqLiteDatabase.close();

        return lista;
    }
    public void salvaUsuario(Usuario usuario){
        SQLiteDatabase sqLiteDatabase = getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(Contract.Usuario.COLUNA_nome, usuario.getNome());
        contentValues.put(Contract.Usuario.COLUNA_Funcao, usuario.getFuncao());
        contentValues.put(Contract.Usuario.COLUNA_Login, usuario.getLogin());
        contentValues.put(Contract.Usuario.COLUNA_Senha, usuario.getSenha());
        long id = sqLiteDatabase.insert(Contract.Usuario.TABELA, null, contentValues);
        usuario.setId(id);
    }
    public List<Usuario> consultaUsuarios(){
        ArrayList lista = new ArrayList();
        return lista;
    }
    public boolean entrar(Usuario usuario) {

        SQLiteDatabase sqLiteDatabase = getReadableDatabase();
         String query = "Select * from usuario where login = " + "? and senha = ?" ;
         String login;
         String senha;
         List<Usuario> lista = new ArrayList<>();
        Cursor cursor =  sqLiteDatabase.rawQuery(query,new String[]{usuario.getLogin(), usuario.getSenha()});
        if(cursor.getCount() > 0){
            return true;
        }
        else{
            return false;
        }

    }
    public boolean isValidUser(String username, String password) {
        SQLiteDatabase db = this.getReadableDatabase();
        String selection = Contract.Usuario.COLUNA_Login + " = ? AND " + Contract.Usuario.COLUNA_Senha + " = ?";
        String[] selectionArgs = {username, password};

        Cursor cursor = db.query(Contract.Usuario.TABELA, null, selection, selectionArgs, null, null, null);

        boolean isValid = cursor.getCount() > 0;
        idUsuarioLogado = cursor.getColumnIndex(Contract.Componente._ID);
        cursor.close();
        db.close();

        return isValid;
    }
}
