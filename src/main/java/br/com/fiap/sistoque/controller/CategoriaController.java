package br.com.fiap.sistoque.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import br.com.fiap.sistoque.model.Categoria;
import br.com.fiap.sistoque.repository.CategoriaRepository;

@RestController
@RequestMapping("categoria")
public class CategoriaController {

    Logger log = LoggerFactory.getLogger(getClass());

    @Autowired
    CategoriaRepository repository;

    @GetMapping
    public List<Categoria> index() {
        return repository.findAll();
    }


    @PostMapping
    @ResponseStatus(code = HttpStatus.CREATED)
    public Categoria create(@RequestBody Categoria categoria) { // binding
        log.info("cadastrando categoria {} ", categoria);
        repository.save(categoria);
        return categoria;
    }


    // @GetMapping("{id}")
    // public ResponseEntity<Categoria> show(@PathVariable Long id) {
    //     log.info("buscando categoria por id {}", id);

    //     // for(Categoria categoria : repository){
    //     // if (categoria.id().equals(id))
    //     // return ResponseEntity.ok(categoria);
    //     // }

    //     var categoriaEncontrada = getCategoriaBydId(id);

    //     // Optional é a solução do NupointerException -- Ao invés dele retornar nulo,
    //     // ele retorna um Optional vazio. Analogia = ele é uma caixa, uma caixa que pode
    //     // estar vazia.

    //     if (categoriaEncontrada.isEmpty()) {
    //         return ResponseEntity.notFound().build();
    //     }

    //     return ResponseEntity.ok(categoriaEncontrada.get());
    // }


    // @DeleteMapping("{id}")
    // public ResponseEntity<Object> destroy(@PathVariable Long id) {
    //     log.info("apagando categoria");

    //     var categoriaEncontrada = getCategoriaBydId(id);

    //     if (categoriaEncontrada.isEmpty()) {
    //         return ResponseEntity.notFound().build();
    //     }

    //     repository.remove(categoriaEncontrada.get());

    //     return ResponseEntity.noContent().build();
    // }


    // private Optional<Categoria> getCategoriaBydId(Long id) {
    //     var categoriaEncontrada = repository
    //             .stream()
    //             .filter(c -> c.id().equals(id))
    //             .findFirst();
    //     return categoriaEncontrada;
    // }


    // @PutMapping("{id}")
    // public ResponseEntity<Categoria> update (@PathVariable Long id, @RequestBody Categoria categoria) {
    //     log.info("atualizando categoria com id {} para {}", id, categoria);
    //     //buscar a categoria
    //     var categoriaEncontrada = getCategoriaBydId(id);

    //     if (categoriaEncontrada.isEmpty())
    //         return ResponseEntity.notFound().build();

    //     //criar uma nova categoria com os novos dados
    //     var categoriaAntiga = categoriaEncontrada.get();
    //     var categoriaNova = new Categoria(id, categoria.nome(), categoria.icone());

    //     // apagar a categoria
    //     repository.remove(categoriaAntiga);

    //     // add a categoria nova
    //     repository.add(categoriaNova);


    //     return ResponseEntity.ok(categoriaNova);
    // }


}

/*
 * static -> arquivos entregaveis pro cliente (imagem, javascript)
 * template -> vamos processar e mandar pro cliente (telas)
 * 
 * @RequestMapping -> requisição da página vai ser mapeada para esse método
 * 
 * @ResponseBody -> retorna resposta no corpo
 * 
 * @Controller -> em todas as controllers |
 * 
 * CTRL + SHIFT + P -> ADD STARTERS ->
 * 
 * devTools -> dependencia para salvar e ja recarregar a página
 * 
 * produces = "application/json" -> tipo de resposta é json, tipo de resposta
 */

/*
 * log info ->
 * Controller -> recebe as requisições
 */