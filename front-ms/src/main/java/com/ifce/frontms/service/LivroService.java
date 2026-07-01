package com.ifce.frontms.service;

import com.ifce.frontms.dto.AutorDTO;
import com.ifce.frontms.dto.LivroDTO;
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
public class LivroService {

    private final RestTemplate restTemplate;
    private final String livroApiUrl;
    private final AutorService autorService;

    public LivroService(RestTemplate restTemplate, 
                        @Value("${livro.ms.url}") String livroApiUrl,
                        AutorService autorService) {
        this.restTemplate = restTemplate;
        this.livroApiUrl = livroApiUrl;
        this.autorService = autorService;
    }

    public List<LivroDTO> listarTodos() {
        List<LivroDTO> livros = restTemplate.exchange(
                livroApiUrl,
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<List<LivroDTO>>() {}
        ).getBody();

        if (livros != null) {
            for (LivroDTO livro : livros) {
                try {
                    AutorDTO autor = autorService.buscarPorId(livro.getAutorId());
                    if (autor != null) {
                        livro.setNomeAutor(autor.getNome());
                    } else {
                        livro.setNomeAutor("Autor não encontrado");
                    }
                } catch (Exception e) {
                    livro.setNomeAutor("Autor removido");
                }
            }
        }
        return livros;
    }

    public LivroDTO buscarPorId(Long id) {
        LivroDTO livro = restTemplate.getForObject(livroApiUrl + "/" + id, LivroDTO.class);
        if (livro != null) {
            try {
                AutorDTO autor = autorService.buscarPorId(livro.getAutorId());
                if (autor != null) {
                    livro.setNomeAutor(autor.getNome());
                } else {
                    livro.setNomeAutor("Autor não encontrado");
                }
            } catch (Exception e) {
                livro.setNomeAutor("Autor removido");
            }
        }
        return livro;
    }

    public LivroDTO criar(LivroDTO livro) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<LivroDTO> request = new HttpEntity<>(livro, headers);
        return restTemplate.postForObject(livroApiUrl, request, LivroDTO.class);
    }

    public LivroDTO atualizar(Long id, LivroDTO livro) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<LivroDTO> request = new HttpEntity<>(livro, headers);
        restTemplate.put(livroApiUrl + "/" + id, request);
        return buscarPorId(id);
    }

    public void deletar(Long id) {
        restTemplate.delete(livroApiUrl + "/" + id);
    }
}