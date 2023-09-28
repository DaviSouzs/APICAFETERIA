package br.com.tech4me.pedidos.model;
import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document("pedido")
public class Pedido {
    @Id
    private String id;
    private String nomeCliente;
    private String idCafe;
    private double valor;
    private List<String> ingredientes;
    private String nomeCafe;
    private Tamanho tamanho;

public Pedido(){

}

    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }
    public String getNomeCliente() {
        return nomeCliente;
    }
    public void setNomeCliente(String nomeCliente) {
        this.nomeCliente = nomeCliente;
    }
    public String getIdCafe() {
        return idCafe;
    }
    public void setIdCafe(String idCafe) {
        this.idCafe = idCafe;
    }

    public double getValor() {
        return valor;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }

    public List<String> getIngredientes() {
        return ingredientes;
    }

    public void setIngredientes(List<String> ingredientes) {
        this.ingredientes = ingredientes;
    }

    public String getNomeCafe() {
        return nomeCafe;
    }

    public void setNomeCafe(String nomeCafe) {
        this.nomeCafe = nomeCafe;
    }

    public Tamanho getTamanho() {
        return tamanho;
    }

    public void setTamanho(Tamanho tamanho) {
        this.tamanho = tamanho;
    }
}
