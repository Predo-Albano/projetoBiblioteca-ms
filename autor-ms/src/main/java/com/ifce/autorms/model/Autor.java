package com.ifce.autorms.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

@Entity
@Table(name = "autores")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Autor {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @NotBlank(message = "Nome é obrigatório")
    @Size(max = 100, message = "Nome deve ter no máximo 100 caracteres")
    @Column(nullable = false, length = 100)
    private String nome;
    
    @NotBlank(message = "Nacionalidade é obrigatória")
    @Size(max = 80, message = "Nacionalidade deve ter no máximo 80 caracteres")
    @Column(nullable = false, length = 80)
    private String nacionalidade;
    
    @NotNull(message = "Ano de nascimento é obrigatório")
    @Positive(message = "Ano de nascimento deve ser positivo")
    @Column(name = "ano_nascimento", nullable = false)
    private Integer anoNascimento;
}