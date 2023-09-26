package br.com.tech4me.pedidos.httpClient;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import br.com.tech4me.pedidos.model.Cafe;

@FeignClient("cafeteria")
public interface CafeteriaClient {
    
    @RequestMapping(method = RequestMethod.GET, value = "/cafeteria/{id}")
    Cafe obterPizzaPorId(@PathVariable String id);
}
