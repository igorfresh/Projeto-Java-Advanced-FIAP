package br.com.fiap.sistoque.config;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import br.com.fiap.sistoque.model.Categoria;
import br.com.fiap.sistoque.model.Movimentacao;
import br.com.fiap.sistoque.repository.CategoriaRepository;
import br.com.fiap.sistoque.repository.MovimentacaoRepository;

@Configuration
@Profile("dev")
public class DatabaseSeeder implements CommandLineRunner {


    @Autowired
    CategoriaRepository categoriaRepository;

     @Autowired
    MovimentacaoRepository movimentacaoRepository;

    @Override
    public void run(String... args) throws Exception {
        categoriaRepository.saveAll(
            List.of(
                Categoria.builder().id(1L).nome("cadernos").icone("notebook").build(),
                Categoria.builder().id(2L).nome("livros").icone("book").build(),
                Categoria.builder().id(3L).nome("canetas").icone("pen").build()
            )
        );

        movimentacaoRepository.saveAll(
            List.of (
                Movimentacao.builder()
                    .id(1L)
                    .descricao("Caderno Tilibra 100 folhas")
                    .valorUnitario(new BigDecimal(12.89))
                    .data(LocalDate.now())
                    .quantidade(300)
                    .categoria(categoriaRepository.findById(1L).get())
                    .tipo("ENTRADA")
                    .build(),
                Movimentacao.builder()
                    .id(2L)
                    .descricao("Livro de Gramática")
                    .valorUnitario(new BigDecimal(100.00))
                    .data(LocalDate.now().minusMonths(4))
                    .quantidade(30)
                    .categoria(categoriaRepository.findById(2L).get())
                    .tipo("SAIDA")
                    .build(),
                    Movimentacao.builder()
                    .id(3L)
                    .descricao("Livro de Matemática")
                    .valorUnitario(new BigDecimal(130.00))
                    .data(LocalDate.now().minusWeeks(2))
                    .quantidade(50)
                    .categoria(categoriaRepository.findById(2L).get())
                    .tipo("SAIDA")
                    .build(),
                    Movimentacao.builder()
                    .id(4L)
                    .descricao("Caneta Presidente")
                    .valorUnitario(new BigDecimal(21.00))
                    .data(LocalDate.now().minusWeeks(1))
                    .quantidade(30)
                    .categoria(categoriaRepository.findById(3L).get())
                    .tipo("SAIDA")
                    .build(),
                    Movimentacao.builder()
                    .id(5L)
                    .descricao("Caneta Bic Azul")
                    .valorUnitario(new BigDecimal(2.50))
                    .data(LocalDate.now().minusMonths(5))
                    .quantidade(1000)
                    .categoria(categoriaRepository.findById(3L).get())
                    .tipo("ENTRADA")
                    .build(),
                    Movimentacao.builder()
                    .id(6L)
                    .descricao("Caneta Bic Preta")
                    .valorUnitario(new BigDecimal(2.89))
                    .data(LocalDate.now().minusWeeks(8))
                    .quantidade(3000)
                    .categoria(categoriaRepository.findById(3L).get())
                    .tipo("ENTRADA")
                    .build(),
                    Movimentacao.builder()
                    .id(7L)
                    .descricao("Caneta Bic Vermelha")
                    .valorUnitario(new BigDecimal(2.50))
                    .data(LocalDate.now().minusWeeks(5))
                    .quantidade(3000)
                    .categoria(categoriaRepository.findById(3L).get())
                    .tipo("ENTRADA")
                    .build(),
                    Movimentacao.builder()
                    .id(8L)
                    .descricao("Caderno Inteligente")
                    .valorUnitario(new BigDecimal(200.00))
                    .data(LocalDate.now().minusMonths(1))
                    .quantidade(300)
                    .categoria(categoriaRepository.findById(1L).get())
                    .tipo("ENTRADA")
                    .build(),
                    Movimentacao.builder()
                    .id(9L)
                    .descricao("Caderno Foroni")
                    .valorUnitario(new BigDecimal(15))
                    .data(LocalDate.now().minusWeeks(4))
                    .quantidade(800)
                    .categoria(categoriaRepository.findById(1L).get())
                    .tipo("ENTRADA")
                    .build(),
                    Movimentacao.builder()
                    .id(10L)
                    .descricao("Caderno sem espiral Bia Brasil do Brasil")
                    .valorUnitario(new BigDecimal(25.00))
                    .data(LocalDate.now().minusMonths(2))
                    .quantidade(600)
                    .categoria(categoriaRepository.findById(2L).get())
                    .tipo("ENTRADA")
                    .build(),
                    Movimentacao.builder()
                    .id(11L)
                    .descricao("Livro Brás Cubas")
                    .valorUnitario(new BigDecimal(30))
                    .data(LocalDate.now().minusWeeks(3))
                    .quantidade(30)
                    .categoria(categoriaRepository.findById(1L).get())
                    .tipo("SAIDA")
                    .build(),
                    Movimentacao.builder()
                    .id(12L)
                    .descricao("Livro de Biologia")
                    .valorUnitario(new BigDecimal(90))
                    .data(LocalDate.now().minusMonths(3))
                    .quantidade(60)
                    .categoria(categoriaRepository.findById(1L).get())
                    .tipo("SAIDA")
                    .build()
                    
            )

        );
        
    }

    
}
