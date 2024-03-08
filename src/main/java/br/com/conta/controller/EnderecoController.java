package br.com.conta.controller;


import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.conta.model.Endereco;
import br.com.conta.service.ViaCepService;

@RestController
@RequestMapping("/endereco")
public class EnderecoController {

	@Autowired
    ViaCepService viaCepService;

    @GetMapping("/{cep}")
    public EntityModel<Endereco> obterEndereco(@PathVariable String cep) {
        Endereco endereco = viaCepService.consultarCep(cep);
        EntityModel<Endereco> resource = EntityModel.of(endereco);
        
        // Adicionar link para a própria solicitação
        WebMvcLinkBuilder selfLink = WebMvcLinkBuilder.linkTo(methodOn(this.getClass()).obterEndereco(cep));
        resource.add(selfLink.withSelfRel());

        return resource;
    }
}