package br.com.tech4me.pedidos.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import br.com.tech4me.pedidos.model.Pedido;

@Repository
public interface PedidoRepository extends MongoRepository <Pedido, String> {
    
}
