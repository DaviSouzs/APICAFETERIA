package br.com.tech4me.cafeteria.service;

import java.util.List;
import java.util.Optional;
import br.com.tech4me.cafeteria.shared.CafeCompletoDTO;
import br.com.tech4me.cafeteria.shared.CafeDTO;

public interface CafeService {
    List<CafeDTO> obterTodos();
    Optional<CafeCompletoDTO> obterPorId(String id);
    CafeCompletoDTO cadastrar (CafeCompletoDTO cafe);
    Optional<CafeCompletoDTO> atualizarPorId (String id, CafeCompletoDTO cafe);
    void excluirPorId(String id);
}
