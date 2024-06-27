package br.com.food.records;

import br.com.food.domain.Categorias;

public record CategoriaDetalhamentoDTO(
        Long id,
        String nome,
        String descricao) {
    public CategoriaDetalhamentoDTO(Categorias categoriaCriada) {
        this(categoriaCriada.getId(), categoriaCriada.getNome(), categoriaCriada.getDescricao());
    }
}
