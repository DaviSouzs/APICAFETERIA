package br.com.tech4me.cafeteria.Controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.tech4me.cafeteria.service.CafeService;
import br.com.tech4me.cafeteria.shared.CafeDTO;
import jakarta.validation.Valid;
import br.com.tech4me.cafeteria.shared.CafeCompletoDTO;

@RestController
@RequestMapping ("/cafeteria")
public class CafeController {

    @Autowired
    private CafeService servico;

    @GetMapping
    private ResponseEntity<List<CafeDTO>> obterCafe(){
        return new ResponseEntity<>(servico.obterTodos(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    private ResponseEntity<CafeCompletoDTO> obterCafePorId(@PathVariable String id){
        if (servico.obterPorId(id).isPresent()) {
            return new ResponseEntity<>(servico.obterPorId(id).get(), HttpStatus.OK);
        }

        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PostMapping
    private ResponseEntity<CafeCompletoDTO> cadastrarCafe(@Valid @RequestBody CafeCompletoDTO cafe){
        return new ResponseEntity<>(servico.cadastrar(cafe), HttpStatus.CREATED);
    }
    
    @DeleteMapping("/{id}")
    private ResponseEntity<Void> excluirCafePorId(@PathVariable String id){
        servico.excluirPorId(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PutMapping("/{id}")
    private ResponseEntity<CafeCompletoDTO> atualizarCafe(@Valid @PathVariable String id, 
                                                    @RequestBody CafeCompletoDTO cafe){
        Optional<CafeCompletoDTO> CafeAtualizar = servico.atualizarPorId(id, cafe);

        if (CafeAtualizar.isPresent()) {
            return new ResponseEntity<>(CafeAtualizar.get(), HttpStatus.OK);
        }

        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    


}