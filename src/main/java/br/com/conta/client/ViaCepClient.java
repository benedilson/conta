package br.com.conta.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import br.com.conta.model.Endereco;

@FeignClient(name = "viacep", url = "https://viacep.com.br")
public interface ViaCepClient {
	@GetMapping("/ws/{cep}/json/")
    Endereco getCep(@PathVariable("cep") String cep);
}
