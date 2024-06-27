package br.com.food.records;

import br.com.food.domain.SubCategorias;

public record SubCategoriasDetalhamentoDTO(
        Long id,
        String nome,
        String descricao,
        Long categoria) {

    public SubCategoriasDetalhamentoDTO(SubCategorias subCategorias) {
        this(subCategorias.getId(), subCategorias.getNome(), subCategorias.getDescricao(), subCategorias.getCategorias().getId());
    }
}
