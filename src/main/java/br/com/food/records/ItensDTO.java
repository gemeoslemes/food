package br.com.food.records;

import java.math.BigDecimal;

public record ItensDTO(
        String nome,
        String descricao,
        BigDecimal preco,
        String foto,
        Long subcategorias
) {
}
