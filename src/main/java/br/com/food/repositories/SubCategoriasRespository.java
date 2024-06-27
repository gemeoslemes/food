package br.com.food.repositories;

import br.com.food.domain.SubCategorias;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SubCategoriasRespository extends JpaRepository<SubCategorias, Long> {
}
