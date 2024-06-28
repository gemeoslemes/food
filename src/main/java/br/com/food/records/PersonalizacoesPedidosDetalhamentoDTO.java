package br.com.food.records;

import br.com.food.domain.PersonalizacoesPedidos;

public record PersonalizacoesPedidosDetalhamentoDTO(
        Long id,
        PersonalizacoesDetalhamentoDTO personalizacoes
) {
    public PersonalizacoesPedidosDetalhamentoDTO(PersonalizacoesPedidos personalizacoesPedidos) {
        this(
                personalizacoesPedidos.getId(),
                new PersonalizacoesDetalhamentoDTO(personalizacoesPedidos.getPersonalizacoes())
        );
    }
}
