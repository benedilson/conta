package br.com.conta.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import br.com.conta.model.Conta;
import br.com.conta.repository.ContaRepository;

@ExtendWith(MockitoExtension.class)
public class ContaServiceTest {

    @Mock
    private ContaRepository contaRepository;

    @InjectMocks
    private ContaService contaService;

    @Test
    public void testCriarConta() {
        // Dados de exemplo
        Conta conta = new Conta("Teste", "Corrente");

        // Mock do comportamento do repositório
        when(contaRepository.save(conta)).thenReturn(conta);

        // Chama o método do serviço que cria uma conta
        Conta contaCriada = contaService.criarConta(conta);

        // Verifica se a conta retornada é a mesma que foi passada para o método save
        assertEquals(conta, contaCriada);

        // Verifica se o método save do repositório foi chamado uma vez
        verify(contaRepository, times(1)).save(conta);
    }
}
