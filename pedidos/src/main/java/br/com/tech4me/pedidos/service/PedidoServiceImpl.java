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
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'excluirPorId'");
    }
    
}
