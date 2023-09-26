package br.com.tech4me.cafeteria.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.tech4me.cafeteria.model.Cafe;
import br.com.tech4me.cafeteria.repository.CafeRepository;
import br.com.tech4me.cafeteria.shared.CafeCompletoDTO;
import br.com.tech4me.cafeteria.shared.CafeDTO;


@Service
public class CafeServiceImpl implements CafeService {

    @Autowired
    private CafeRepository repositorio;

    @Override
    public List<CafeDTO> obterTodos() {
        return repositorio.findAll().stream()
        .map(c -> new CafeDTO(c.getId(),c.getIngredientes(),c.getNomeCafe())).toList();
    }

    @Override
    public Optional<CafeCompletoDTO> obterPorId(String id) {
        Optional<Cafe> cafe = repositorio.findById(id);

        if (cafe.isPresent()){
        return Optional.of(new CafeCompletoDTO(cafe.get().getId(),cafe.get().getNomeCafe(),
                                                cafe.get().getIngredientes(),cafe.get().getTamanho()
                                                ,cafe.get().getValor()));
    }
        return Optional.empty();
}


    @Override
    public CafeCompletoDTO cadastrar(CafeCompletoDTO cafeDto) {
        Cafe c = new Cafe(cafeDto);
        repositorio.save(c);
        return new CafeCompletoDTO(c.getId(),c.getNomeCafe(),c.getIngredientes(),
                                    c.getTamanho(),c.getValor());
    }

    @Override
    public Optional<CafeCompletoDTO> atualizarPorId(String id, CafeCompletoDTO dto) {
        Optional<Cafe> cafe = repositorio.findById(id);
    
        if (cafe.isPresent()){
            Cafe cafeAtualizar = cafe.get();
            
            cafeAtualizar.setNomeCafe(dto.nomeCafe());
            cafeAtualizar.setIngredientes(dto.ingredientes());
            cafeAtualizar.setTamanho(dto.tamanho());
            cafeAtualizar.setValor(dto.valor());
            
            repositorio.save(cafeAtualizar);
            
            return Optional.of(new CafeCompletoDTO(cafeAtualizar.getId(),
                cafeAtualizar.getNomeCafe(), cafeAtualizar.getIngredientes(),
                cafeAtualizar.getTamanho(), cafeAtualizar.getValor()));
        }
        
        return Optional.empty();
    }
    

    @Override
    public void excluirPorId(String id) {
        repositorio.deleteById(id);
    }

    
    
}
