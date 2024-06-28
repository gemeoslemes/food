package br.com.food.records;

import java.util.List;

public record ItemPedidosDTO(
        List<ItensDetalhamentoDTO> itens,
        Integer quantidade,
        List<PersonalizacoesDetalhamentoDTO> personalizacoesPedidos
) {
}
