package br.com.fiap.sistoque.model;

import java.math.BigDecimal;
import java.time.LocalDate;

import br.com.fiap.sistoque.validation.TipoMovimentacao;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
@Entity

public class Movimentacao {
    

    @Id @GeneratedValue(strategy = GenerationType.AUTO) //dizer qual a estratégia pra gerar a chave primária
    private Long id;

    @NotBlank(message = "{movimentacao.nome.notblank}")
    @Size(min = 3, max = 255, message = "{movimentacao.nome.size}")
    private String descricao;

    @Positive(message = "{movimentacao.valor.positive}")
    private BigDecimal valor;

    private LocalDate data;

    @TipoMovimentacao
    private String tipo; //ENTRADA OU SAÍDA

    

}
