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
        // Repassamos a encomenda para o "Gerente" (Service) calcular o frete e salvar
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

        // Se o banco estiver vazio, não gastamos a nossa cota de IA
        if (todasEncomendas.isEmpty()) {
            return "Nenhuma encomenda encontrada no banco de dados para analisar.";
        }

        // 2. Construindo o Prompt (A Instrução)
        StringBuilder prompt = new StringBuilder();
        prompt.append("Você é um analista de logística sênior. ");
        prompt.append("Analise os seguintes dados de fretes extraídos do nosso banco de dados ");
        prompt.append("e escreva um resumo executivo de 1 parágrafo destacando qual região está mais cara:\n\n");

        // 3. Injetando os dados reais do banco de dados no texto para a IA ler
        for (Encomenda e : todasEncomendas) {
            prompt.append("- Rastreio: ").append(e.getCodigoRastreio())
                  .append(" | Região: ").append(e.getRegiaoDestino())
                  .append(" | Custo de Frete: R$ ").append(e.getCustoFrete())
                  .append("\n");
        }

        // 4. Enviamos o texto gigante para o nosso Serviço de IA e retornamos o resultado
        return iaService.gerarResumo(prompt.toString());
    }
}