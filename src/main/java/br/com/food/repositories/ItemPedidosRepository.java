package br.com.food.repositories;

import br.com.food.domain.ItemPedidos;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ItemPedidosRepository extends JpaRepository<ItemPedidos, Long> {
}
