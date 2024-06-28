package br.com.food.repositories;

import br.com.food.domain.Personalizacoes;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PersonalizacoesRepository extends JpaRepository<Personalizacoes, Long> {
}
