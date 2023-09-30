package br.com.tech4me.pedidos.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.tech4me.pedidos.shared.CafeDTO;
import br.com.tech4me.pedidos.shared.PedidoCompletoDTO;
import br.com.tech4me.pedidos.shared.PedidoDTO;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import br.com.tech4me.pedidos.httpClient.CafeteriaClient;
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
        return repositorio.findAll()
                .stream()
                .map(PedidoDTO::fromPedido) //Aqui chama o método criado no PedidoDTO
                .toList();
    }
    

    @CircuitBreaker(name = "obterCafePorId", fallbackMethod = "fallbackObterPorId")

@Override
public Optional<PedidoCompletoDTO> obterPorId(String id) {
    Optional<Pedido> novoPedido = repositorio.findById(id);
    

    if (novoPedido.isPresent()) {
        // Pego as informações do Pedido e crio uma variável idCafe para usar no feign
        Pedido pedido = novoPedido.get();
        String idCafe = pedido.getIdCafe();

        // Aqui o feign busca as informações com base no idCafe
        CafeDTO cafeDTO = cliente.obterCafePorId(idCafe);

        // Feita a associação do Pedido com o CafeDTO
        PedidoCompletoDTO pedidoCompletoDTO = new PedidoCompletoDTO(
            pedido.getId(),
            pedido.getNomeCliente(),
            cafeDTO,
            idCafe
        );

        return Optional.of(pedidoCompletoDTO);
    }

    return Optional.empty();
}


@Override

public PedidoCompletoDTO cadastrarPedido(PedidoCompletoDTO pedidoDTO) {
    Pedido pedido = new Pedido();
    pedido.setNomeCliente(pedidoDTO.nomeCliente());
    String idCafe = pedidoDTO.idCafe();
    
    // Pego as informações do café pelo feing com o ID 
    CafeDTO cafeDTO = cliente.obterCafePorId(idCafe);
    
    // Aqui é associado as informações do Café que pegou no feing, 
    // passa pelo CafeDTO e vai para o pedido
    pedido.setIdCafe(idCafe);
    pedido = repositorio.save(pedido);
    
    // Aqui cria o pedidoCompletoDTO com base no CafeDTO e PedidoDTO
    PedidoCompletoDTO pedidoCompletoDTO = new PedidoCompletoDTO(
        pedido.getId(),
        pedido.getNomeCliente(),
        cafeDTO,
        idCafe
    );
    
    return pedidoCompletoDTO;
}

    @Override
    public void excluirPorId(String id) {
        repositorio.deleteById(id);
    }

    public Optional<PedidoCompletoDTO> fallbackObterPorId(String id, Exception e) {
        Optional<Pedido> pedido = repositorio.findById(id);
    
        if (pedido.isPresent()) {
            // Retorne as informações disponíveis em Pedido como PedidoCompletoDTO
            Pedido pedidoExistente = pedido.get();
            return Optional.of(new PedidoCompletoDTO(pedidoExistente));
        }
    
        // Se não houver informações em Pedido, retorne Optional.empty()
        return Optional.empty();
    }
    
    

    @Override
  public Optional<PedidoCompletoDTO> atualizarPorId(String id, PedidoCompletoDTO dto) {
    Optional<Pedido> pedido = repositorio.findById(id);

    if (pedido.isPresent()) {
        Pedido pedidoAtualizar = pedido.get();
        CafeDTO cafeDTO = cliente.obterCafePorId(dto.idCafe());

        pedidoAtualizar.setNomeCliente(dto.nomeCliente());
        pedidoAtualizar.setIdCafe(dto.idCafe());

       //Aqui é atualizado o pedido do Café com base no DTO
        pedidoAtualizar.getCafe().setValor(cafeDTO.valor());
        pedidoAtualizar.getCafe().setIngredientes(cafeDTO.ingredientes());
        pedidoAtualizar.getCafe().setNomeCafe(cafeDTO.nomeCafe());
        pedidoAtualizar.getCafe().setTamanho(cafeDTO.tamanho());
    

        Pedido pedidoAtualizado = repositorio.save(pedidoAtualizar);

        // Retorno de tudo que foi feito. 
        return Optional.of(new PedidoCompletoDTO(
            pedidoAtualizado.getId(),
            pedidoAtualizado.getNomeCliente(),
            cafeDTO,cafeDTO.id()
        ));
    }

    return Optional.empty();
}
}