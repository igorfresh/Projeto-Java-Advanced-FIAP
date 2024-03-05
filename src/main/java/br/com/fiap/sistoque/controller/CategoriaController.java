package br.com.fiap.sistoque.controller;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import br.com.fiap.sistoque.model.Categoria;

@RestController
@RequestMapping("categoria")
public class CategoriaController {

    Logger log = LoggerFactory.getLogger(getClass());

    List<Categoria> repository = new ArrayList<>();

    @GetMapping
    public List<Categoria> index() {
        return repository;
    }


    @PostMapping
    @ResponseStatus(code = HttpStatus.CREATED)
    public Categoria create(@RequestBody Categoria categoria){ //binding
        log.info("cadastrando categoria {} ", categoria);
        repository.add(categoria);
        return categoria;

    }

    @GetMapping("{id}")
    public ResponseEntity<Categoria> show(@PathVariable Long id){
        log.info("buscando categoria por id {}", id);

        for(Categoria categoria : repository){
            if (categoria.id().equals(id))
             return ResponseEntity.ok(categoria);
        }

        //TODO Refatorar com stream
        return ResponseEntity.notFound().build();
    }
}


/*
 * static -> arquivos entregaveis pro cliente (imagem, javascript)
template -> vamos processar e mandar pro cliente (telas)

@RequestMapping -> requisição da página vai ser mapeada para esse método

@ResponseBody -> retorna resposta no corpo

@Controller -> em todas as controllers

CTRL + SHIFT + P -> ADD STARTERS -> 

devTools -> dependencia para salvar e ja recarregar a página

produces = "application/json" -> tipo de resposta é json, tipo de resposta
 */