package br.com.food.controllers;

import br.com.food.exceptions.InvalidDateFormatException;
import br.com.food.records.PedidosAtualizaStatusDTO;
import br.com.food.records.PedidosDTO;
import br.com.food.records.PedidosDetalhamentoDTO;
import br.com.food.services.PedidosService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

@RestController
@RequestMapping("/api/pedidos/v1")
public class PedidosController {

    @Autowired
    private PedidosService service;

    @PostMapping
    public ResponseEntity<PedidosDetalhamentoDTO> criarPedido(@RequestBody PedidosDTO dto) {
        var pedido = service.criarPedido(dto);
        return ResponseEntity.ok(new PedidosDetalhamentoDTO(pedido));
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<PedidosDetalhamentoDTO> buscarPorId(@PathVariable("id") Long id) {
        var pedido = service.buscarPorId(id);
        return ResponseEntity.ok(new PedidosDetalhamentoDTO(pedido));
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<PedidosDetalhamentoDTO> atualizarPedido(@PathVariable("id") Long id, @RequestBody PedidosAtualizaStatusDTO dto) {
        var pedido = service.atualizarPedido(id, dto);
        return ResponseEntity.ok(new PedidosDetalhamentoDTO(pedido));
    }

    @GetMapping
    public ResponseEntity<List<PedidosDetalhamentoDTO>> listarTodosOsPedidos() {
        var pedidos = service.listarTodosOsPedidos();
        return ResponseEntity.ok(pedidos);
    }

    @GetMapping("/ativos")
    public ResponseEntity<List<PedidosDetalhamentoDTO>> buscarPedidosAtivos() {
        var pedidos = service.buscarPedidosAtivos();
        return ResponseEntity.ok(pedidos);
    }

    @GetMapping("/ativos/finalizados-hoje")
    public ResponseEntity<List<PedidosDetalhamentoDTO>> buscarPedidosAtivosComStatusEntregueHoje() {
        var pedidos = service.buscarPedidosAtivosComStatusEntregueHoje();
        return ResponseEntity.ok(pedidos);
    }

    @GetMapping("/ativos/finalizados-por-periodo")
    public ResponseEntity<List<PedidosDetalhamentoDTO>> buscarPedidosAtivosComStatusEData(
            @RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss") LocalDateTime dataInicio,
            @RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss") LocalDateTime dataFim

    ) {
        try {
            var pedidos = service.buscarPedidosAtivosEPorData(dataInicio, dataFim);
            return ResponseEntity.ok(pedidos);
        } catch (Exception e) {
            throw new InvalidDateFormatException("Formato de data inválido. Por favor, use o formato yyyy-MM-dd'T'HH:mm:ss");
        }

    }
}