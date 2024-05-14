package br.com.fiap.sistoque.controller;

import static org.springframework.http.HttpStatus.CREATED;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.web.PageableDefault;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.Link;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;
import br.com.fiap.sistoque.model.Movimentacao;
import br.com.fiap.sistoque.repository.MovimentacaoRepository;
import jakarta.validation.Valid;

@RestController
@RequestMapping("movimentacao")
public class MovimentacaoController {

        record TotalPorCategoria(String categoria, BigDecimal valor) { }
        record TotalPorMes (String mes, BigDecimal saida, BigDecimal entrada){}
    
    @Autowired
    MovimentacaoRepository repository;

    @GetMapping("{id}")
    public EntityModel<Movimentacao> show(@PathVariable Long id) {
        var movimentacao = repository.findById(id).orElseThrow(
            () -> new IllegalArgumentException("movimentação não encontrada")
        );

        return EntityModel.of(
            movimentacao,
            linkTo(methodOn(MovimentacaoController.class).show(id)).withSelfRel(),
            linkTo(methodOn(MovimentacaoController.class).show(id)).withRel("delete"),
            linkTo(methodOn(MovimentacaoController.class).index(null, null, null)).withRel("contents")
        );
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Object> destroy(@PathVariable Long id){
        repository.findById(id).orElseThrow{
            () -> new IllegalArgumentException("movimentação não encontrada")
        };

        return repository.deleteById(id);
    }

    @GetMapping
    public Page<Movimentacao> index(
        @RequestParam(required = false) String categoria,
            @RequestParam(required = false) Integer mes,
            @PageableDefault(size = 5, sort = "data", direction = Direction.DESC) Pageable pageable

    ){
        if(mes != null && categoria != null){
            return repository.findByCategoriaNomeAndMes(categoria, mes, pageable);
        }

        if (mes != null){
            return repository.findByMes(mes, pageable);
        }

        if (categoria != null){
            return repository.findByCategoriaNome(categoria, pageable);
        }

        return repository.findAll(pageable);

    
    }

        @GetMapping("maior")
    public Movimentacao getMaior(
            @PageableDefault(size = 1, sort = "valor", direction = Direction.DESC) Pageable pageable) {
        return repository.findAll(pageable).getContent().get(0);
    }

    @GetMapping("ultima")
    public Movimentacao getUltima() {
        var pageable = PageRequest.of(0, 1, Direction.DESC, "data");
        return repository.findAll(pageable).getContent().get(0);
    }

    @GetMapping("menor")
    public Movimentacao getMenor() {
        return repository.findFirstByOrderByValorUnitario();
    }

    @GetMapping("totais-por-categoria")
    public List<TotalPorCategoria> getTotaisPorCategoria() {
        var movimentacoes = repository.findAll();

        Map<String, BigDecimal> collect = movimentacoes.stream()
                .collect(
                        Collectors.groupingBy(
                                m -> m.getCategoria().getNome(),
                                Collectors.reducing(BigDecimal.ZERO, Movimentacao::getValorUnitario, BigDecimal::add)));

        return collect
                .entrySet()
                .stream()
                .map(e -> new TotalPorCategoria(e.getKey(), e.getValue()))
                .toList();

    }

    @GetMapping("totais-por-mes")
    public List<TotalPorMes> getTotaisPorMes(){
        var movimentacoes = repository.findAll();

        Map<String, BigDecimal> totaisEntradas = movimentacoes.stream()
            .filter(m -> m.getTipo().equals("ENTRADA"))
            .collect(
                Collectors.groupingBy(
                    m -> m.getData().getMonth().toString(),
                    Collectors.reducing(BigDecimal.ZERO, Movimentacao::getValorUnitario, BigDecimal::add)
                )
            );

        Map<String, BigDecimal> totaisSaida = movimentacoes.stream()
            .filter(m -> m.getTipo().equals("SAIDA"))
            .collect(
                Collectors.groupingBy(
                    m -> m.getData().getMonth().toString(),
                    Collectors.reducing(BigDecimal.ZERO, Movimentacao::getValorUnitario, BigDecimal::add)
                )
            );

            System.out.println(totaisEntradas);

        return totaisEntradas
            .keySet()
            .stream()
            .map( mes -> new TotalPorMes(
                mes, 
                totaisSaida.getOrDefault(mes, BigDecimal.ZERO), 
                totaisEntradas.getOrDefault(mes, BigDecimal.ZERO)
            ))
            .toList();

    }

    @PostMapping
    @ResponseStatus(CREATED)
    public Movimentacao create(@RequestBody @Valid Movimentacao movimentacao){
        return repository.save(movimentacao);

    }

}