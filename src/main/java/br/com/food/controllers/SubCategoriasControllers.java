package br.com.food.controllers;

import br.com.food.records.SubCategoriasDTO;
import br.com.food.records.SubCategoriasDetalhamentoDTO;
import br.com.food.services.SubCategoriasService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/subcategorias/v1")
public class SubCategoriasControllers {
    @Autowired
    private SubCategoriasService service;

    @GetMapping(value = "/{id}")
    public ResponseEntity<SubCategoriasDetalhamentoDTO> buscandoPorId(@PathVariable("id") Long id) {
        var subCategoria = service.mostrarSubCategoriaPorId(id);
        return ResponseEntity.ok(new SubCategoriasDetalhamentoDTO(subCategoria));
    }

    @PostMapping
    public ResponseEntity<SubCategoriasDetalhamentoDTO> criandoSubCategoria(@RequestBody SubCategoriasDTO dto) {
        var subCategoria = service.criarSubCategoria(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(new SubCategoriasDetalhamentoDTO(subCategoria));
    }

    @GetMapping
    public ResponseEntity<List<SubCategoriasDetalhamentoDTO>> listandoTodasSubCategorias() {
        return ResponseEntity.ok(service.listandoTodasSubCategorias());
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<SubCategoriasDetalhamentoDTO> atualizarSubCategoria(@PathVariable("id") Long id,
                                                                              @RequestBody SubCategoriasDTO dto) {
        var subCategoria = service.atualizarSubCategoria(dto, id);
        return ResponseEntity.ok(new SubCategoriasDetalhamentoDTO(subCategoria));
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<?> deletarSubCategoria(@PathVariable("id") Long id) {
        service.deletarSubCategoria(id);
        return ResponseEntity.noContent().build();
    }
}
