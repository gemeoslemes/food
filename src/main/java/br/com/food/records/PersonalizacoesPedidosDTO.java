package br.com.food.records;

import java.math.BigDecimal;

public record PersonalizacoesPedidosDTO(
        Long id,
        String nome,
        String descricao,
        BigDecimal precoAdicional,
        Integer quantidade
) {
}
