package br.com.food.records;

import br.com.food.domain.Itens;

import java.math.BigDecimal;

public record ItensDetalhamentoDTO(
        Long id,
        String nome,
        String descricao,
        BigDecimal preco,
        String fotoItem,
        Long subCategorias
        ) {
    public ItensDetalhamentoDTO(Itens itens) {
        this(
                itens.getId(),
                itens.getNome(),
                itens.getDescricao(),
                itens.getPreco(),
                itens.getFotoItem(),
                itens.getSubCategorias().getId());
    }
}
