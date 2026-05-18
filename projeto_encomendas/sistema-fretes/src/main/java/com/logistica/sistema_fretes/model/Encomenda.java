package com.logistica.sistema_fretes.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "tb_encomendas")
public class Encomenda {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String codigoRastreio;
    private String regiaoDestino;
    private Double percentualProgresso; // 0.0 (postado) / 100.0 (entregue)
    private Double custoFrete;
    private Double distanciaKm;

    // Construtor obrigatório para o Spring funcionar
    public Encomenda() {
    }

    // Getters e Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCodigoRastreio() {
        return codigoRastreio;
    }

    public void setCodigoRastreio(String codigoRastreio) {
        this.codigoRastreio = codigoRastreio;
    }

    public String getRegiaoDestino() {
        return regiaoDestino;
    }

    public void setRegiaoDestino(String regiaoDestino) {
        this.regiaoDestino = regiaoDestino;
    }

    public Double getPercentualProgresso() {
        return percentualProgresso;
    }

    public void setPercentualProgresso(Double percentualProgresso) {
        this.percentualProgresso = percentualProgresso;
    }

    public Double getCustoFrete() {
        return custoFrete;
    }

    public void setCustoFrete(Double custoFrete) {
        this.custoFrete = custoFrete;
    }

    public Double getDistanciaKm() {
        return distanciaKm;
    }

    public void setDistanciaKm(Double distanciaKm) {
        this.distanciaKm = distanciaKm;
    }
}
