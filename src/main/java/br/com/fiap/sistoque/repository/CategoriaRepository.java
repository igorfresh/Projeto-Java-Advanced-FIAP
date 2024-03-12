package br.com.fiap.sistoque.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.fiap.sistoque.model.Categoria;

//classe que permite acessar a tabela categoria

public interface CategoriaRepository extends JpaRepository<Categoria, Long>{
    
}
