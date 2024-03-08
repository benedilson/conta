package br.com.conta.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.conta.model.Conta;
import br.com.conta.repository.ContaRepository;

@Service
public class ContaService {

    @Autowired
    private ContaRepository contaRepository;

    public List<Conta> getAllContas() {
        return (List<Conta>) contaRepository.findAll();
    }

    public Optional<Conta> getContaById(Long id) {
        return contaRepository.findById(id);
    }

    public Conta criarConta(Conta conta) {
        return contaRepository.save(conta);
    }

    public Optional<Conta> atualizarConta(Long id, Conta contaAtualizada) {
        Optional<Conta> contaExistente = contaRepository.findById(id);
        if (contaExistente.isPresent()) {
            Conta conta = contaExistente.get();
            conta.setNome(contaAtualizada.getNome());
            conta.setDescricao(contaAtualizada.getDescricao());
            return Optional.of(contaRepository.save(conta));
        }
        return Optional.empty();
    }

    public boolean excluirConta(Long id) {
        if (contaRepository.existsById(id)) {
            contaRepository.deleteById(id);
            return true;
        }
        return false;
    }
}
