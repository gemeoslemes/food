package br.com.food.repositories;

import br.com.food.domain.Pedidos;
import br.com.food.enuns.Status;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface PedidosRepository extends JpaRepository<Pedidos, Long> {
    @Query("SELECT p FROM Pedidos p WHERE p.status <> :status")
    List<Pedidos> findByStatusNot(@Param("status") Status status);

    @Query("SELECT p FROM Pedidos p WHERE p.status = :status AND DATE(p.dataHora) = CURRENT_DATE")
    List<Pedidos> buscarPedidosAtivosComStatusEntregueHoje(@Param("status") Status status);

    @Query("SELECT p FROM Pedidos p WHERE p.status = :status AND p.dataHora BETWEEN :inicio AND :fim")
    List<Pedidos> buscarPedidosAtivosComStatusEData(@Param("status") Status status, @Param("inicio") LocalDateTime inicio, @Param("fim") LocalDateTime fim);
}
