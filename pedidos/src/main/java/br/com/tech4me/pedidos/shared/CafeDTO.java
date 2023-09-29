package br.com.tech4me.pedidos.shared;

import java.util.List;

import br.com.tech4me.pedidos.model.Cafe;
import br.com.tech4me.pedidos.model.Tamanho;

public record CafeDTO(String id, Double valor, List<String> ingredientes, String nomeCafe, Tamanho tamanho) {

    // Construtor que aceita o Cafe
    public CafeDTO(Cafe cafe) {
        this(
            cafe.getId(),
            cafe.getValor(),
            cafe.getIngredientes(),
            cafe.getNomeCafe(),
            cafe.getTamanho()
        );
    }
}
