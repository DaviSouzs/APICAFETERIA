package br.com.tech4me.pedidos.shared;
import br.com.tech4me.pedidos.model.Pedido;
import br.com.tech4me.pedidos.model.Tamanho;



public record PedidoDTO (String id, String nomeCliente, String nomeCafe, Tamanho tamanho){
    public static PedidoDTO fromPedido(Pedido pedido) {
        return new PedidoDTO(pedido.getId(), pedido.getNomeCliente(), 
        pedido.getNomeCafe(),pedido.getTamanho());
}
}
