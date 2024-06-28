package br.com.food.repositories;

import br.com.food.domain.PersonalizacoesPedidos;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PersonalizacoesPedidosRepository extends JpaRepository<PersonalizacoesPedidos, Long> {
}
