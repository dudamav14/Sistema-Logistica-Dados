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
}