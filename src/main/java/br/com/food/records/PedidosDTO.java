package br.com.food.records;

import java.util.List;

public record PedidosDTO(
        List<ItemPedidosDTO> itemPedidos
) {
}
