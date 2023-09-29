package br.com.tech4me.pedidos.shared;


import br.com.tech4me.pedidos.model.Pedido;

public record PedidoDTO(String id, String nomeCliente) {
    
    public static PedidoDTO fromPedido(Pedido pedido) {
        return new PedidoDTO(
            pedido.getId(),
            pedido.getNomeCliente()
        );
    }
}
