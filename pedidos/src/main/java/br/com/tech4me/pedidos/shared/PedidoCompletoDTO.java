package br.com.tech4me.pedidos.shared;
import br.com.tech4me.pedidos.model.Pedido;
import br.com.tech4me.pedidos.shared.*;

public record PedidoCompletoDTO(String id, String nomeCliente, CafeDTO cafe, String idCafe) {

    // Construtor que aceita o Pedido e o CafeDTO
    public PedidoCompletoDTO(Pedido pedido, CafeDTO cafe) {
        this(
            pedido.getId(),
            pedido.getNomeCliente(),
            cafe,
            pedido.getIdCafe()
        );
    }

    // Construtor que aceita o Pedido
    public PedidoCompletoDTO(Pedido pedido) {
        this(
            pedido.getId(),
            pedido.getNomeCliente(),
            new CafeDTO(pedido.getCafe()),
            pedido.getIdCafe()
        );
    }
    
    }
    



