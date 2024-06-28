package br.com.food.records;

import java.math.BigDecimal;

public record PersonalizacaoDTO(
        String nome,
        String descricao,
        BigDecimal preco,
        Integer quantidade,
        Long itens
) {
}
