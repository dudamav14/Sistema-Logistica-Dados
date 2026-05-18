package com.logistica.sistema_fretes.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service
public class AnaliseIaService {

    // A anotação @Value puxa a senha do application.properties
    @Value("${gemini.api.key}")
    private String apiKey;

    @Value("${gemini.api.url}")
    private String apiUrl;

    // Método que recebe o prompt, envia para o Google e devolve a resposta. 
    public String gerarResumo(String prompt) {
        // RestTemplate é a ferramenta do Spring para fazer requisições pela internet
        RestTemplate restTemplate = new RestTemplate();
        String urlCompleta = apiUrl + apiKey;

        // 1. Montando o "Pacote" (JSON) que o Google exige, usando Maps
        Map<String, Object> textMap = new HashMap<>();
        textMap.put("text", prompt);

        Map<String, Object> partsMap = new HashMap<>();
        partsMap.put("parts", List.of(textMap));

        Map<String, Object> requestBody = new HashMap<>();
        requestBody.put("contents", List.of(partsMap));

        // 2. Avisando que está enviando um arquivo JSON
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<Map<String, Object>> request = new HttpEntity<>(requestBody, headers);

        try {
            // 3. Enviando a requisição POST para o Google
            ResponseEntity<String> response = restTemplate.postForEntity(urlCompleta, request, String.class);

            // 4. Desembalando a resposta: O Google devolve um JSON gigante. 
            // O ObjectMapper ajuda a navegar nesse JSON e pegar só o texto da IA.
            ObjectMapper mapper = new ObjectMapper();
            JsonNode rootNode = mapper.readTree(response.getBody());
            
            return rootNode.path("candidates").get(0)
                           .path("content")
                           .path("parts").get(0)
                           .path("text").asText();

        } catch (Exception e) {
            return "Erro ao comunicar com a IA: " + e.getMessage();
        }
    }
}