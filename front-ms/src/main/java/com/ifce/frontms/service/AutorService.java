package com.ifce.frontms.service;

import com.ifce.frontms.dto.AutorDTO;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
public class AutorService {

    private final RestTemplate restTemplate;
    private final String autorApiUrl;

    public AutorService(RestTemplate restTemplate, @Value("${autor.ms.url}") String autorApiUrl) {
        this.restTemplate = restTemplate;
        this.autorApiUrl = autorApiUrl;
    }

    public List<AutorDTO> listarTodos() {
        return restTemplate.exchange(
                autorApiUrl,
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<List<AutorDTO>>() {}
        ).getBody();
    }

    public AutorDTO buscarPorId(Long id) {
        return restTemplate.getForObject(autorApiUrl + "/" + id, AutorDTO.class);
    }

    public AutorDTO criar(AutorDTO autor) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<AutorDTO> request = new HttpEntity<>(autor, headers);
        return restTemplate.postForObject(autorApiUrl, request, AutorDTO.class);
    }

    public AutorDTO atualizar(Long id, AutorDTO autor) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<AutorDTO> request = new HttpEntity<>(autor, headers);
        restTemplate.put(autorApiUrl + "/" + id, request);
        return buscarPorId(id);
    }

    public void deletar(Long id) {
        restTemplate.delete(autorApiUrl + "/" + id);
    }
}