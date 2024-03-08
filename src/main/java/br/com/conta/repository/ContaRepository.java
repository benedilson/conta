package br.com.conta.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import br.com.conta.model.Conta;

@Repository
public interface ContaRepository extends CrudRepository<Conta, Long> {

}
