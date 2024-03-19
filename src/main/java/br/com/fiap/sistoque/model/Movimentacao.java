package br.com.fiap.sistoque.model;

import java.math.BigDecimal;
import java.time.LocalDate;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

@Data
@Entity

public class Movimentacao {
    
    @Id @GeneratedValue(strategy = GenerationType.AUTO) //dizer qual a estratégia pra gerar a chave primária
    private Long id;
    private String descricao;
    private BigDecimal valor;
    private LocalDate data;
    private String tipo; //ENTRADA OU SAÍDA

}
