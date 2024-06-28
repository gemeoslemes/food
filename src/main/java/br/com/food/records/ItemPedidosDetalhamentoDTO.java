package br.com.food.records;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

public record ItemPedidosDetalhamentoDTO(
        Long id,
        ItensDetalhamentoDTO itens,
        Integer quantidade,
        BigDecimal precoUnitario,
        List<PersonalizacoesPedidosDetalhamentoDTO> personalizacoesPedidos
) {
    public ItemPedidosDetalhamentoDTO(br.com.food.domain.ItemPedidos itemPedido) {
        this(
                itemPedido.getId(),
                new ItensDetalhamentoDTO(itemPedido.getItens()),
                itemPedido.getQuantidade(),
                itemPedido.getPrecoUnitario(),
                itemPedido.getPersonalizacoesPedidos().stream()
                        .map(PersonalizacoesPedidosDetalhamentoDTO::new)
                        .collect(Collectors.toList())
        );
    }
}
