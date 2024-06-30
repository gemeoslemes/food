package br.com.food.records;

import jakarta.validation.constraints.NotNull;

import java.util.List;

public record PedidosDTO(
        @NotNull(message = "Selecione pelo menos um item para realizar o pedido.")
        List<ItemPedidosDTO> itemPedidos
) {
}
