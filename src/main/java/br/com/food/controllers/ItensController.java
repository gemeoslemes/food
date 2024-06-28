package br.com.food.controllers;

import br.com.food.domain.Itens;
import br.com.food.records.ItensDTO;
import br.com.food.records.ItensDetalhamentoDTO;
import br.com.food.services.ItensService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/itens/v1")
public class ItensController {

    @Autowired
    private ItensService itensService;

    @PostMapping
    public ResponseEntity<ItensDetalhamentoDTO> criarItem(@RequestBody ItensDTO dto) {
        Itens itens = itensService.criarItem(dto);
        return ResponseEntity.ok(new ItensDetalhamentoDTO(itens));
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<ItensDetalhamentoDTO> buscaItemPorId(@PathVariable(value = "id") Long id) {
        var item = itensService.buscaItemPorId(id);
        return ResponseEntity.ok(new ItensDetalhamentoDTO(item));
    }

    @GetMapping
    public ResponseEntity<List<ItensDetalhamentoDTO>> buscandoTodosItens() {
        List<ItensDetalhamentoDTO> listaItensDTO = itensService.buscandoTodosItens();
        return ResponseEntity.ok(listaItensDTO);
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<ItensDetalhamentoDTO> atualizandoItem(@PathVariable(value = "id") Long id,
                                                                @RequestBody ItensDTO dto) {
        Itens itemAtualizado = itensService.atualizandoItem(id, dto);
        return ResponseEntity.ok(new ItensDetalhamentoDTO(itemAtualizado));
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<?> deletandoItem(@PathVariable(value = "id") Long id) {
        itensService.deletandoItem(id);
        return ResponseEntity.noContent().build();
    }
}
