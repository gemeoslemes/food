package br.com.food.records;

import br.com.food.domain.Itens;
import br.com.food.domain.Personalizacoes;

import java.math.BigDecimal;

public record PersonalizacoesDetalhamentoDTO(
        Long id,
        String nome,
        String descricao,
        BigDecimal preco,
        Integer quantidade,
        Long itens
        ) {
    public PersonalizacoesDetalhamentoDTO(Personalizacoes personalizacoes) {
        this(personalizacoes.getId(), personalizacoes.getNome(), personalizacoes.getDescricao(),
                personalizacoes.getPrecoAdicional(), personalizacoes.getQuantidade(), personalizacoes.getItens().getId());
    }
}
