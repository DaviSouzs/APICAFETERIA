package br.com.tech4me.pedidos.shared;
import br.com.tech4me.pedidos.model.Cafe;
import br.com.tech4me.pedidos.model.Pedido;


public record PedidoDTO (String id, String nomeCliente, Cafe cafe){
    public static PedidoDTO fromPedido(Pedido pedido, Cafe cafe) {
        return new PedidoDTO(pedido.getId(), pedido.getNomeCliente(), cafe);
}
}
