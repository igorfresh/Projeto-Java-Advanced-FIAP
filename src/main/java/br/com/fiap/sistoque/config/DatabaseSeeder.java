package br.com.fiap.sistoque.config;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;

import br.com.fiap.sistoque.model.Categoria;
import br.com.fiap.sistoque.model.Movimentacao;
import br.com.fiap.sistoque.repository.CategoriaRepository;
import br.com.fiap.sistoque.repository.MovimentacaoRepository;

@Configuration
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
                    .valorUnitario(new BigDecimal(100))
                    .data(LocalDate.now().minusWeeks(1))
                    .quantidade(30)
                    .categoria(categoriaRepository.findById(2L).get())
                    .tipo("SAIDA")
                    .build(),
                    Movimentacao.builder()
                    .id(3L)
                    .descricao("Livro de Matemática")
                    .valorUnitario(new BigDecimal(130))
                    .data(LocalDate.now().minusWeeks(1))
                    .quantidade(50)
                    .categoria(categoriaRepository.findById(2L).get())
                    .tipo("SAIDA")
                    .build(),
                    Movimentacao.builder()
                    .id(4L)
                    .descricao("Caneta Presidente")
                    .valorUnitario(new BigDecimal(150))
                    .data(LocalDate.now().minusWeeks(1))
                    .quantidade(30)
                    .categoria(categoriaRepository.findById(3L).get())
                    .tipo("SAIDA")
                    .build(),
                    Movimentacao.builder()
                    .id(5L)
                    .descricao("Caneta Bic Azul")
                    .valorUnitario(new BigDecimal(150))
                    .data(LocalDate.now().minusWeeks(1))
                    .quantidade(1000)
                    .categoria(categoriaRepository.findById(3L).get())
                    .tipo("ENTRADA")
                    .build()
            )

        );
        
    }

    
}
