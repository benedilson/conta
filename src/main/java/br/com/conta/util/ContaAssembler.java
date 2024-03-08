package br.com.conta.util;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;

import br.com.conta.controller.ContaController;
import br.com.conta.model.Conta;

public class ContaAssembler {

    public static EntityModel<Conta> toEntityModel(Conta conta) {
        return EntityModel.of(conta,
                WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(ContaController.class).getContaById(conta.getId())).withSelfRel(),
                WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(ContaController.class).getAllContas()).withRel("contas"));
    }

    public static List<EntityModel<Conta>> toEntityModelList(List<Conta> contas) {
        return contas.stream().map(ContaAssembler::toEntityModel).collect(Collectors.toList());
    }
}
