package br.com.tech4me.pedidos.shared;
import br.com.tech4me.pedidos.model.Pedido;

public record PedidoCompletoDTO (String id, String nomeCliente, String idCafe) {
    
    public static PedidoCompletoDTO fromPedido(Pedido pedido) {
        return new PedidoCompletoDTO(pedido.getId(), pedido.getNomeCliente(),
         pedido.getIdCafe()); 
        }
}