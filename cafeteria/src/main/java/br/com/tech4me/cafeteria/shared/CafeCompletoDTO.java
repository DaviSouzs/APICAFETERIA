package br.com.tech4me.cafeteria.shared;


import java.util.List;

import br.com.tech4me.cafeteria.model.Tamanho;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public record CafeCompletoDTO (String id, 
                                @NotBlank (message = "Esse campo está em branco, favor verificar")
                                String nomeCafe, 
                                List<String> ingredientes,
                                @NotNull (message = "Opção inválida, tamanhos permitidos: PEQ, MED, GRD ")
                                Tamanho tamanho,
                                @Positive (message = "O valor precisa ser um valor positivo, verifique. ") 
                                Double valor){

}    

