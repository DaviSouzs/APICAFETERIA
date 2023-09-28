package br.com.tech4me.cafeteria.shared;

import java.util.List;

public record CafeDTO (String id, 
                        List<String> Ingredientes,
                        String NomeCafe ) {
}
