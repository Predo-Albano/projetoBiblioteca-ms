package com.ifce.frontms.dto;

import lombok.Data;

@Data
public class AutorDTO {
    private Long id;
    private String nome;
    private String nacionalidade;
    private Integer anoNascimento;
}