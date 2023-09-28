package br.com.tech4me.pedidos.shared;
import br.com.tech4me.pedidos.model.Pedido;
import br.com.tech4me.pedidos.model.Tamanho;
import br.com.tech4me.pedidos.model.Cafe;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public record PedidoCompletoDTO (
@NotEmpty (message = "Esse campo deve ser preenchido") String id, 
@NotBlank (message = "Favor informar o nome do Cliente") String nomeCliente, 
@NotEmpty (message = "Esse campo deve ser preenchido") String idCafe, 
@NotEmpty (message = "Esse campo deve ser preenchido") String nomeCafe, 
@NotNull (message = "Opção inválida, tamanhos permitidos: PEQ, MED, GRD ") Tamanho tamanho,
@Positive (message = "O valor precisa ser um valor positivo, verifique. ")  Double valor) {
    
    public static PedidoCompletoDTO fromPedido(Pedido pedido) {
        return new PedidoCompletoDTO(pedido.getId(), pedido.getNomeCliente(),
         pedido.getIdCafe(),pedido.getNomeCafe(),pedido.getTamanho(), pedido.getValor()); 
        }
}