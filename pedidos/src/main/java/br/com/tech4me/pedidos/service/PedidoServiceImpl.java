package br.com.tech4me.pedidos.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import br.com.tech4me.pedidos.shared.PedidoCompletoDTO;
import br.com.tech4me.pedidos.shared.PedidoDTO;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import br.com.tech4me.pedidos.httpClient.CafeteriaClient;
import br.com.tech4me.pedidos.model.Cafe;
import br.com.tech4me.pedidos.model.Pedido;
import br.com.tech4me.pedidos.repository.PedidoRepository;

@Service
public class PedidoServiceImpl implements PedidoService {
@Autowired
PedidoRepository repositorio;

@Autowired
CafeteriaClient cliente;

    @Override
    public List<PedidoDTO> obterPedidos() {
        return repositorio.findAll().stream()
        .map(p -> PedidoDTO.fromPedido(p))
        .toList();
    }

    @CircuitBreaker(name = "obterCafePorId", fallbackMethod = "fallbackObterPorId")

    @Override
    public Optional<PedidoCompletoDTO> obterPorId(String id) {
        Optional<Pedido> pedido = repositorio.findById(id);
        
        if (pedido.isPresent()){
            PedidoCompletoDTO pedidoDTO = PedidoCompletoDTO.fromPedido(pedido.get());
           return Optional.of(pedidoDTO);
        }
        return Optional.empty();
    }

    @Override
    public PedidoCompletoDTO cadastrarPedido(PedidoCompletoDTO pedidoDTO) {
        Cafe cafe = cliente.obterCafePorId(pedidoDTO.idCafe());

        Pedido pedido = new Pedido();
        pedido.setNomeCliente(pedidoDTO.nomeCliente());
        pedido.setIdCafe(pedidoDTO.idCafe());
        pedido.setValor(cafe.getValor());
        pedido.setIngredientes(cafe.getIngredientes());
        pedido.setNomeCafe(cafe.getNomeCafe());
        pedido.setTamanho(cafe.getTamanho());
        pedido = repositorio.save(pedido);
        PedidoCompletoDTO pedidoCompletoDTO = PedidoCompletoDTO.fromPedido(pedido);

            return pedidoCompletoDTO;
}

    @Override
    public void excluirPorId(String id) {
        repositorio.deleteById(id);
    }

    public Optional<PedidoDTO> fallbackObterPorId(String id, Exception e) {
    Optional<Pedido> pedido = repositorio.findById(id);

    if (pedido.isPresent()) {
        // Caso o micro serviço café cair, ele vai criar um café vazio para trazer informações
        return Optional.of(PedidoDTO.fromPedido(pedido.get()));
    }
    return Optional.empty();
}

    @Override
    public Optional<PedidoCompletoDTO> atualizarPorId(String id) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'atualizarPorId'");
    }

}
