package br.com.tech4me.pedidos.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import br.com.tech4me.pedidos.service.PedidoService;
import br.com.tech4me.pedidos.shared.PedidoCompletoDTO;
import br.com.tech4me.pedidos.shared.PedidoDTO;
import jakarta.validation.Valid;

@RestController
@RequestMapping ("/pedidos")
public class PedidoController {
    
    @Autowired
    private PedidoService servico;

    @GetMapping
    private ResponseEntity<List<PedidoDTO>> obterPedido(){
        return new ResponseEntity<>(servico.obterPedidos(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    private ResponseEntity<PedidoCompletoDTO> obterPorId(@PathVariable String id){
        if (servico.obterPorId(id).isPresent()) {
            return new ResponseEntity<>(servico.obterPorId(id).get(), HttpStatus.OK);
        }

        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PostMapping
    private ResponseEntity<PedidoCompletoDTO> cadastrarPedido(@Valid @RequestBody PedidoCompletoDTO pedido){
        return new ResponseEntity<>(servico.cadastrarPedido(pedido), HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    private ResponseEntity<Void> excluirPorId(@PathVariable String id){
        servico.excluirPorId(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }   

}