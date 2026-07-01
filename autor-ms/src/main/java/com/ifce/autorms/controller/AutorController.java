package com.ifce.autorms.controller;

import com.ifce.autorms.model.Autor;
import com.ifce.autorms.repository.AutorRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/autores")
public class AutorController {
    
    @Autowired
    private AutorRepository repository;
    
    @GetMapping
    public List<Autor> listarTodos() {
        return repository.findAll();
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<Autor> buscarPorId(@PathVariable Long id) {
        return repository.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
    
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Autor criar(@Valid @RequestBody Autor autor) {
        return repository.save(autor);
    }
    
    @PutMapping("/{id}")
    public ResponseEntity<Autor> atualizar(@PathVariable Long id, @Valid @RequestBody Autor autorAtualizado) {
        return repository.findById(id)
                .map(autor -> {
                    autor.setNome(autorAtualizado.getNome());
                    autor.setNacionalidade(autorAtualizado.getNacionalidade());
                    autor.setAnoNascimento(autorAtualizado.getAnoNascimento());
                    return ResponseEntity.ok(repository.save(autor));
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