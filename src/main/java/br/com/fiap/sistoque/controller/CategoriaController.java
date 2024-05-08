package br.com.fiap.sistoque.controller;

import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.NO_CONTENT;
import java.util.List;

import org.hibernate.annotations.Cache;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;
import br.com.fiap.sistoque.model.Categoria;
import br.com.fiap.sistoque.repository.CategoriaRepository;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
@RestController
@RequestMapping("categoria")
@CacheConfig(cacheNames = "categorias")
@Slf4j
@Tag(name = "categorias", description = "Categorias de produtos de material escolar")
public class CategoriaController {

    @Autowired
    CategoriaRepository repository;

    
    @GetMapping
    @Cacheable("categorias")
    @Operation(
        summary = "Listar Categorias",
        description = "Retorna um array com todas as categorias de produto cadastradas."
    )
    public List<Categoria> index() {
        return repository.findAll();
    }


    @PostMapping
    @ResponseStatus(CREATED)
    @CacheEvict(allEntries = true)
    @Operation(
        summary = "Cadastrar Categoria",
        description = "Cadastra uma nova categoria de produto com os dados enviados na requisição."
    )
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "Categoria cadastrada com sucesso"),
        @ApiResponse(responseCode = "401", description = "Validação falhou. Verifique as regras para o corpo da requisição", useReturnTypeSchema = false)
    })
    public Categoria create(@RequestBody @Valid Categoria categoria) { // binding
        log.info("cadastrando categoria {} ", categoria);
        return repository.save(categoria);
    }


    @GetMapping("{id}")
    @Operation(
        summary = "Detalhes da Categoria",
        description = "Retorna os dados detalhados da categoria com o ID informado no parametro do path"
    )
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "Categorias retornadas com sucesso"),
        @ApiResponse(responseCode = "401", description = "Usuário não autenticado. Realize a autenticação em /login."),
        @ApiResponse(responseCode = "404", description = "Não existe categoria com o ID informado. Consulte lista em /categoria")
        })
    public ResponseEntity<Categoria> show(@PathVariable Long id) {
        log.info("buscando categoria por id {}", id);

        return repository
            .findById(id)
            .map(ResponseEntity::ok) //referente method
            .orElse(ResponseEntity.notFound().build());

            // map -> recebe a função para transformar

        // Optional é a solução do NupointerException -- Ao invés dele retornar nulo,
        // ele retorna um Optional vazio. Analogia = ele é uma caixa, uma caixa que pode
        // estar vazia.
    }


    @DeleteMapping("{id}")
    @ResponseStatus(NO_CONTENT)
    @CacheEvict(allEntries = true)
    @Operation(
        summary = "Apagar Categoria",
        description = "Apaga a categoria indicada pelo ID enviado no parametro de path."
    )
    @ApiResponses({
        @ApiResponse(responseCode = "204", description = "Categoria apagada com sucesso."),
        @ApiResponse(responseCode = "401", description = "Usuário não autenticado. Realize autenticação em /login"),
        @ApiResponse(responseCode = "404", description = "Não existe categoria com o ID informado. Consulte lista em /categoria")
    })
    public void destroy(@PathVariable Long id) {
        log.info("apagando categoria");

        verificarSeExisteCategoria(id);

        repository.deleteById(id);
    }



    // private Optional<Categoria> getCategoriaBydId(Long id) {
    //     var categoriaEncontrada = repository
    //             .stream()
    //             .filter(c -> c.id().equals(id))
    //             .findFirst();
    //     return categoriaEncontrada;
    // }


    @PutMapping("{id}")
    @CacheEvict(allEntries = true)
    @Operation(
        summary = "Atualizar Categoria",
        description = "Atualizar os dados da categoria com o ID informado no path, utilizando os novos dados enviados no corpo da requisição."
    )
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "Categoria atualizada com sucesso."),
        @ApiResponse(responseCode = "400", description = "Validação falhou. Verifique as regras para o corpo da requisição"),
        @ApiResponse(responseCode = "401", description = "Usuário não autenticado. Realize autenticação em /login"),
        @ApiResponse(responseCode = "404", description = "Não existe categoria com o ID informado. Consulte lista em /categoria")
    })
    public Categoria update (@PathVariable Long id, @RequestBody Categoria categoria) {
        log.info("atualizando categoria com id {} para {}", id, categoria);

        verificarSeExisteCategoria(id);

            categoria.setId(id);
            return repository.save(categoria);
    }


     private void verificarSeExisteCategoria(Long id) {
        repository
        .findById(id)
        .orElseThrow(() -> new ResponseStatusException(
            HttpStatus.NOT_FOUND, 
            "Não existe categoria com o id informado. Consulte lista em /categoria"));
    }

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