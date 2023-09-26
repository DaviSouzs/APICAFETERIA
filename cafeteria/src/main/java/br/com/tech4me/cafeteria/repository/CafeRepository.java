package br.com.tech4me.cafeteria.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import br.com.tech4me.cafeteria.model.Cafe;

public interface CafeRepository extends MongoRepository <Cafe, String> {
    
}
