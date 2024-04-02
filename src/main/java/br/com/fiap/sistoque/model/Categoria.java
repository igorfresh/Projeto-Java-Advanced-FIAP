package br.com.fiap.sistoque.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.validation.constraints.NotBlank;
import jakarta.persistence.Id;
import lombok.Data;

@Data
@Entity

public class Categoria {

    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @NotBlank(message = "{categoria.nome.notblank}")
    private String nome;

    private String icone = "request_quote";
    }
