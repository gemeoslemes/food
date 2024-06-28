package br.com.food.controllers;

import br.com.food.records.PersonalizacaoDTO;
import br.com.food.records.PersonalizacoesDetalhamentoDTO;
import br.com.food.services.PersonalizacoesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/personalizacoes/v1")
public class PersonalizacoesController {
    @Autowired
    private PersonalizacoesService service;

    @GetMapping("/{id}")
    public ResponseEntity<PersonalizacoesDetalhamentoDTO> buscandoPorId(@PathVariable("id") Long id) {
        var personalizacao = service.buscarPorId(id);
        return ResponseEntity.ok(new PersonalizacoesDetalhamentoDTO(personalizacao));
    }

    @PostMapping
    public ResponseEntity<PersonalizacoesDetalhamentoDTO> criarPersonalizacao(@RequestBody PersonalizacaoDTO dto) {
        var personalizacao = service.criarPersonalizacao(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(new PersonalizacoesDetalhamentoDTO(personalizacao));
    }

    @GetMapping
    public ResponseEntity<List<PersonalizacoesDetalhamentoDTO>> listarTodasPersonalizacoes() {
        return ResponseEntity.ok(service.listarTodasPersonalizacoes());
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<PersonalizacoesDetalhamentoDTO> atualizarPersonalizacao(@PathVariable("id") Long id, @RequestBody PersonalizacaoDTO dto) {
        var personalizacaoAtualizada = service.atualizarPersonalizacao(id, dto);
        return ResponseEntity.ok(new PersonalizacoesDetalhamentoDTO(personalizacaoAtualizada));
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<?> detelarPersonalizacao(@PathVariable("id") Long id) {
        service.deletarPersonalizacao(id);
        return ResponseEntity.noContent().build();
    }

}
