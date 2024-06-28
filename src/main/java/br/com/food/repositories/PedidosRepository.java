package br.com.food.repositories;

import br.com.food.domain.Pedidos;
import br.com.food.enuns.Status;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PedidosRepository extends JpaRepository<Pedidos, Long> {
    @Query("SELECT p FROM Pedidos p WHERE p.status <> :status")
    List<Pedidos> findByStatusNot(Status status);
}
