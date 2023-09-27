package br.com.tech4me.pedidos.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.tech4me.pedidos.repository.PedidoRepository;
import br.com.tech4me.pedidos.shared.PedidoCompletoDTO;
import br.com.tech4me.pedidos.shared.PedidoDTO;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import br.com.tech4me.pedidos.httpClient.CafeteriaClient;
import br.com.tech4me.pedidos.model.Cafe;
import br.com.tech4me.pedidos.model.Pedido;

@Service
public class PedidoServiceImpl implements PedidoService {
@Autowired
PedidoRepository repositorio;

@Autowired
CafeteriaClient cliente;

    @Override
    public List<PedidoCompletoDTO> obterTodos() {
        return repositorio.findAll().stream()
        .map(p -> PedidoCompletoDTO.fromPedido(p))
        .toList();
    }

    @CircuitBreaker(name = "obterCafePorId", fallbackMethod = "fallbackObterPorId")

    @Override
    public Optional<PedidoDTO> obterPorId(String id) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'obterPorId'");
    }

    @Override
    public PedidoCompletoDTO cadastrarPedido(PedidoCompletoDTO pedidos) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'cadastrarPedido'");
    }

    @Override
    public Optional<PedidoCompletoDTO> atualizarPorId(String id) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'atualizarPorId'");
    }

    @Override
    public void excluirPorId(String id) {
        repositorio.deleteById(id);
    }

    public Optional<PedidoDTO> fallbackObterPorId(String id, Exception e) {
    Optional<Pedido> pedido = repositorio.findById(id);

    if (pedido.isPresent()) {
        // Caso o micro serviço café cair, ele vai criar um café vazio para trazer informações
        Cafe cafe = new Cafe();
        return Optional.of(PedidoDTO.fromPedido(pedido.get(), cafe));
    }
    return Optional.empty();
}

}
