package com.logistica.sistema_fretes.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.logistica.sistema_fretes.model.Encomenda;
import com.logistica.sistema_fretes.repository.EncomendaRepository;
import com.logistica.sistema_fretes.service.AnaliseIaService;
import com.logistica.sistema_fretes.service.EncomendaService;


@RestController
@RequestMapping("/api/encomendas")
public class EncomendaController {

    // Injetamos o Serviço (para criar encomendas com cálculo de frete)
    @Autowired
    private EncomendaService service;
    // Injetamos o Repositório (para listar as encomendas cadastradas)
    @Autowired
    private EncomendaRepository repository;

    @Autowired
    private AnaliseIaService iaService;
    
    @PostMapping
    public Encomenda criarNova(@RequestBody Encomenda encomenda) {
        // Repassa a encomenda para o "Gerente" (Service) calcular o frete e salvar
        return service.registrarNovaEncomenda(encomenda);
    }

    @GetMapping
    public List<Encomenda> listarTodas() {
        // Vai direto no banco de dados e traz todas as linhas da tabela
        return repository.findAll();
    }

    @GetMapping("/resumo-ia")
    public String gerarResumoLogistico() {
        // 1. O Java vai lá no PostgreSQL e pega todas as encomendas
        List<Encomenda> todasEncomendas = repository.findAll();

        // Se o banco estiver vazio, não gasta a cota de IA
        if (todasEncomendas.isEmpty()) {
            return "Nenhuma encomenda encontrada no banco de dados para analisar.";
        }

        // 2. Construindo o Prompt (A Instrução)
        StringBuilder prompt = new StringBuilder();
        prompt.append("Atue como um Diretor de Logística. Analise a lista de fretes abaixo e escreva um resumo de 2 parágrafos. ");
        prompt.append("Sua análise DEVE incluir: 1) Qual região tem o maior volume de pedidos. ");
        prompt.append("2) Qual o impacto da distância no custo final. ");
        prompt.append("3) Uma recomendação estratégica para reduzir custos onde o frete está mais caro.\n\n");

        // 3. Injetando os dados reais do banco de dados no texto para a IA ler
        for (Encomenda e : todasEncomendas) {
            prompt.append("- Região: ").append(e.getRegiaoDestino())
                  .append(" | Distância: ").append(e.getDistanciaKm()).append(" km")
                  .append(" | Custo: R$ ").append(e.getCustoFrete())
                  .append("\n");
        }

        // 4. Enviamos o texto gigante para o nosso Serviço de IA e retornamos o resultado
        return iaService.gerarResumo(prompt.toString());
    }
}