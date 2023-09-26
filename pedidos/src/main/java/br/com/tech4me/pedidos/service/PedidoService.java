package br.com.tech4me.pedidos.service;

import java.util.List;
import java.util.Optional;

import br.com.tech4me.pedidos.shared.PedidoCompletoDTO;
import br.com.tech4me.pedidos.shared.PedidoDTO;

public interface PedidoService {
    List<PedidoCompletoDTO> obterTodos();
    Optional<PedidoDTO> obterPorId(String id);
    PedidoCompletoDTO cadastrarPedido(PedidoCompletoDTO pedidos);
    Optional<PedidoCompletoDTO> atualizarPorId(String id);
    void excluirPorId(String id);
}
