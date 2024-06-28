package br.com.food.records;

import br.com.food.domain.Pedidos;
import br.com.food.enuns.Status;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

public record PedidosDetalhamentoDTO(
        Long id,
        LocalDateTime dataHora,
        BigDecimal valorTotal,
        Status status,
        List<ItemPedidosDetalhamentoDTO> itemPedidos
) {
    public PedidosDetalhamentoDTO(Pedidos pedido) {
        this(
                pedido.getId(),
                pedido.getDataHora(),
                pedido.getValorTotal(),
                pedido.getStatus(),
                pedido.getItemPedidos().stream()
                        .map(ItemPedidosDetalhamentoDTO::new)
                        .collect(Collectors.toList())
        );
    }
}
