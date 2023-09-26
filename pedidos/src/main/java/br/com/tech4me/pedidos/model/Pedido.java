package br.com.tech4me.pedidos.model;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document("pedidos")
public class Pedido {
    @Id
    private String id;
    private String nomeCliente;
    private String idCafe;

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
}
