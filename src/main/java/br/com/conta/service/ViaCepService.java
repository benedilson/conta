package br.com.conta.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.conta.client.ViaCepClient;
import br.com.conta.model.Endereco;

@Service
public class ViaCepService {

	@Autowired
    ViaCepClient viaCepClient;

    public Endereco consultarCep(String cep) {
        return viaCepClient.getCep(cep);
    }
}