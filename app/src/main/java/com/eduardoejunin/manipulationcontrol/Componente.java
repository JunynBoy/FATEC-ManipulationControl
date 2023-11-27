package com.eduardoejunin.manipulationcontrol;

public class Componente {

    private long id;

    private  String dosagem;
    private String nome ;
//    private long idPedido;
    public Componente(int id, String dosagem, String nome){
        this.id = id;
        this.dosagem = dosagem;
        this.nome = nome;
//        this.idPedido = idPedido;
    }
    public Componente(){}
    public String getNome(){
        return nome;
    }
    public void setNome(String nome){
        this.nome = nome;
    }
    public String getDosagem(){
        return dosagem;
    }
    public void setDosagem(String dosagem){
        this.dosagem = dosagem;
    }
//    public long getIdPedido(){
//        return idPedido;
//    }
//    public void setIdPedido(int idPedido){
//        this.idPedido = idPedido;
//    }
    public void setId(long id){
         this.id = id;
    }
    public long getId(){
        return id;
    }

}
