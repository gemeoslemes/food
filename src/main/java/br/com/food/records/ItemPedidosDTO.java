package br.com.food.records;

import java.util.List;

public record ItemPedidosDTO(
        List<ItensDetalhamentoDTO> itens,
        Integer quantidade,
        List<PersonalizacoesDetalhamentoDTO> personalizacoesPedidos
) {
    public ItemPedidosDTO {
        if (quantidade == null) {
            quantidade = 1;
        }
    }
}
