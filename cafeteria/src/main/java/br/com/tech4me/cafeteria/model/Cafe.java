package br.com.tech4me.cafeteria.model;

import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import br.com.tech4me.cafeteria.shared.CafeCompletoDTO;

@Document("cafeteria")
public class Cafe {
    @Id
    private String id;
    private double valor;
    private List<String> ingredientes;
    private String nomeCafe;
    private Tamanho tamanho;


public Cafe (){

}

public Cafe(CafeCompletoDTO dto){
    setId(dto.id());
    setIngredientes(dto.ingredientes());
    setTamanho(dto.tamanho());
    setNomeCafe(dto.nomeCafe());
    setValor(dto.valor());

}

public String getId() {
    return id;
}

public void setId(String id) {
    this.id = id;
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
