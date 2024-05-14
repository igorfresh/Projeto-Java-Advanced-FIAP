package br.com.fiap.sistoque.model;

import java.math.BigDecimal;
import java.time.LocalDate;

import org.springframework.hateoas.EntityModel;

import com.fasterxml.jackson.annotation.JsonFormat;

import br.com.fiap.sistoque.validation.TipoMovimentacao;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Movimentacao extends EntityModel<Movimentacao> {
    

    @Id @GeneratedValue(strategy = GenerationType.AUTO) //dizer qual a estratégia pra gerar a chave primária
    private Long id;

    @NotBlank(message = "{movimentacao.nome.notblank}")
    @Size(min = 3, max = 255, message = "{movimentacao.nome.size}")
    private String descricao;

    @Positive(message = "{movimentacao.valor.positive}")
    private BigDecimal valorUnitario;

    private int quantidade;

    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate data;

    @TipoMovimentacao
    private String tipo; //ENTRADA OU SAÍDA

    //cardinalidade 1 - 1 1 - n
    @ManyToOne
    private Categoria categoria;

    

}
