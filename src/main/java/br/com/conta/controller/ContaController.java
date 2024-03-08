package br.com.conta.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.EntityModel;
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

import br.com.conta.model.Conta;
import br.com.conta.service.ContaService;
import br.com.conta.util.ContaAssembler;

@RestController
@RequestMapping("/contas")
public class ContaController {

    @Autowired
    private ContaService contaService;

    @GetMapping
    public ResponseEntity<List<EntityModel<Conta>>> getAllContas() {
        List<Conta> contas = contaService.getAllContas();
        List<EntityModel<Conta>> entityModels = ContaAssembler.toEntityModelList(contas);
        return new ResponseEntity<>(entityModels, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<EntityModel<Conta>> getContaById(@PathVariable Long id) {
        Optional<Conta> conta = contaService.getContaById(id);
        if (conta.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        EntityModel<Conta> entityModel = ContaAssembler.toEntityModel(conta.get());
        return new ResponseEntity<>(entityModel, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<EntityModel<Conta>> criarConta(@RequestBody Conta conta) {
        Conta novaConta = contaService.criarConta(conta);
        EntityModel<Conta> entityModel = ContaAssembler.toEntityModel(novaConta);
        return new ResponseEntity<>(entityModel, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<EntityModel<Conta>> atualizarConta(@PathVariable Long id, @RequestBody Conta contaAtualizada) {
        Optional<Conta> contaAtualizadaOptional = contaService.atualizarConta(id, contaAtualizada);
        if (contaAtualizadaOptional.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        EntityModel<Conta> entityModel = ContaAssembler.toEntityModel(contaAtualizadaOptional.get());
        return new ResponseEntity<>(entityModel, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> excluirConta(@PathVariable Long id) {
        if (!contaService.excluirConta(id)) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
