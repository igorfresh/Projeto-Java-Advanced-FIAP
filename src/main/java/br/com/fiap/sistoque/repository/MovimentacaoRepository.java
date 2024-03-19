package br.com.fiap.sistoque.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.fiap.sistoque.model.Movimentacao;

public interface MovimentacaoRepository extends JpaRepository<Movimentacao, Long>{
    
}
