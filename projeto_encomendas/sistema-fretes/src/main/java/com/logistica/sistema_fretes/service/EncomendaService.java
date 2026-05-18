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
        
        Double taxaBase = 50.00;
        Double valorPorKm = 0.0;
        
        // Se a distância vier vazia, assumimos 1km para não quebrar a matemática
        Double distancia = (encomenda.getDistanciaKm() != null) ? encomenda.getDistanciaKm() : 1.0;

        if (encomenda.getRegiaoDestino() != null) {
            switch (encomenda.getRegiaoDestino()) {
                case "Sudeste":
                    valorPorKm = 0.15; // Estradas melhores, km mais barato
                    break;
                case "Sul":
                    valorPorKm = 0.20;
                    break;
                case "Centro-Oeste":
                    valorPorKm = 0.30;
                    break;
                case "Nordeste":
                    valorPorKm = 0.40;
                    break;
                case "Norte":
                    valorPorKm = 0.55; // Logística fluvial/complexa, km mais caro
                    break;
                default:
                    valorPorKm = 0.25;
                    break;
            }
        }

        // Fórmula real: Taxa Base + (Distância * Preço do Km)
        Double custoFinal = taxaBase + (distancia * valorPorKm);
        encomenda.setCustoFrete(custoFinal);

        return repository.save(encomenda);
    }
}