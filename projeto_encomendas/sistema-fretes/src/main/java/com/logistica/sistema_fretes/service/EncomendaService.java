package com.logistica.sistema_fretes.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.logistica.sistema_fretes.model.Encomenda;
import com.logistica.sistema_fretes.repository.EncomendaRepository;

/**
 * Diz ao Spring que esta classe contém as regras de negócio.
 */
@Service
public class EncomendaService {

    // A anotação @Autowired injeta o repositório automaticamente,
    // permitindo usar os métodos de salvar no banco sem precisar instanciar a classe.
    @Autowired
    private EncomendaRepository repository;

    /**
     * Método para calcular o frete e salvar a encomenda no banco.
     */
    public Encomenda registrarNovaEncomenda(Encomenda encomenda) {
        
        // Regra de Negócio: Calcular o custo de envio com base no progresso e região.
        // Se o progresso for 0.0, aplica uma taxa base de R$ 50.00
        // Se o progresso for maior, simula um custo adicional de logística regional.
        Double custoCalculado = 50.00;
        
        if (encomenda.getPercentualProgresso() != null && encomenda.getPercentualProgresso() > 0.0) {
             custoCalculado += (encomenda.getPercentualProgresso() * 0.5); 
        }

        encomenda.setCustoFrete(custoCalculado);

        // O método .save() vem do JpaRepository e gera um comando "INSERT INTO tb_encomendas..."
        return repository.save(encomenda);
    }
}