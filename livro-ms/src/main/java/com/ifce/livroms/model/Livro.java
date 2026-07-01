package com.ifce.livroms.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

@Entity
@Table(name = "livros")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Livro {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @NotBlank(message = "Título é obrigatório")
    @Size(max = 150)
    @Column(nullable = false, length = 150)
    private String titulo;
    
    @NotBlank(message = "Gênero é obrigatório")
    @Size(max = 60)
    @Column(nullable = false, length = 60)
    private String genero;
    
    @NotNull(message = "Ano de publicação é obrigatório")
    @Positive
    @Column(name = "ano_publicacao", nullable = false)
    private Integer anoPublicacao;
    
    @NotNull
    @Column(nullable = false)
    private Boolean disponivel = true;
    
    @NotNull(message = "ID do autor é obrigatório")
    @Column(name = "autor_id", nullable = false)
    private Long autorId;
}