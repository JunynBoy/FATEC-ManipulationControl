package com.eduardoejunin.manipulationcontrol;

public class Pedido {
    private long id;
    private int quantitade;
    private String status;
    private String tamanho;
    private long idUsuario;
    private long idCliente;

    public Pedido(int id, int quantitade, String status, String tamanho, long idUsuario, long idCliente){
        this.id = id;
        this.quantitade = quantitade;
        this.status = status;
        this.tamanho = tamanho;
        this.idUsuario = idUsuario;
        this.idCliente = idCliente;
    }
    public Pedido(){}
    public float getQuantitade(){
        return quantitade;
    }
    public  void setQuantitade(int quantitade){
        this.quantitade = quantitade;
    }
    public String getStatus(){
        return status;
    }
    public String getTamanho(){
        return tamanho;
    }
    public void setTamanho(String tamanho){
        this.tamanho = tamanho;
    }
    public long getIdUsuario(){
        return idUsuario;
    }
    public void setIdUsuario(long idUsuario){
        this.idUsuario = idUsuario;
    }
    public long getIdCliente(){
        return idCliente;
    }
    public void setIdCliente(long idCliente){
        this.idCliente = idCliente;
    }
    public void setId(long id){
        this.id = id;
    }
}
