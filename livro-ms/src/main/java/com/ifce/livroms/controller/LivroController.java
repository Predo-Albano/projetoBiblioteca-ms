package com.ifce.livroms.controller;

import com.ifce.livroms.model.Livro;
import com.ifce.livroms.repository.LivroRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/livros")
public class LivroController {
    
    @Autowired
    private LivroRepository repository;
    
    @GetMapping
    public List<Livro> listarTodos(@RequestParam(required = false) Boolean disponivel) {
        if (disponivel != null) {
            return repository.findByDisponivel(disponivel);
        }
        return repository.findAll();
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<Livro> buscarPorId(@PathVariable Long id) {
        return repository.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
    
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Livro criar(@Valid @RequestBody Livro livro) {
        return repository.save(livro);
    }
    
    @PutMapping("/{id}")
    public ResponseEntity<Livro> atualizar(@PathVariable Long id, @Valid @RequestBody Livro livroAtualizado) {
        return repository.findById(id)
                .map(livro -> {
                    livro.setTitulo(livroAtualizado.getTitulo());
                    livro.setGenero(livroAtualizado.getGenero());
                    livro.setAnoPublicacao(livroAtualizado.getAnoPublicacao());
                    livro.setDisponivel(livroAtualizado.getDisponivel());
                    livro.setAutorId(livroAtualizado.getAutorId());
                    return ResponseEntity.ok(repository.save(livro));
                })
                .orElse(ResponseEntity.notFound().build());
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        if (repository.existsById(id)) {
            repository.deleteById(id);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}