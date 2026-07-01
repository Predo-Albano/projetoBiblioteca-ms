package com.ifce.frontms.dto;

import lombok.Data;

@Data
public class LivroDTO {
    private Long id;
    private String titulo;
    private String genero;
    private Integer anoPublicacao;
    private Boolean disponivel;
    private Long autorId;
    private String nomeAutor;
}