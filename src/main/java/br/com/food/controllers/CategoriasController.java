package br.com.food.controllers;

import br.com.food.domain.Categorias;
import br.com.food.records.CategoriaDTO;
import br.com.food.records.CategoriaDetalhamentoDTO;
import br.com.food.services.CategoriasService;
import br.com.food.utils.DTOsValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/categorias/v1")
public class CategoriasController {

    @Autowired
    CategoriasService categoriasService;

    @Autowired
    DTOsValidator dtosValidator;

    @PostMapping
    public ResponseEntity<CategoriaDetalhamentoDTO> criaCategoria(@RequestBody CategoriaDTO dto) {
        dtosValidator.validateDTO(dto);
        Categorias categoria = new Categorias(dto);
        var categoriaCriada = categoriasService.criarCategoria(categoria);
        return ResponseEntity.ok(new CategoriaDetalhamentoDTO(categoriaCriada));
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<CategoriaDetalhamentoDTO> mostrarCategoriaPorId(@PathVariable("id") Long id) {
        var mostrarCategoriaPorId = categoriasService.mostrarCategoriaPorId(id);
        return ResponseEntity.ok(new CategoriaDetalhamentoDTO(mostrarCategoriaPorId));
    }

    @GetMapping
    public ResponseEntity<List<CategoriaDetalhamentoDTO>> listaTodasCategorias() {
        List<CategoriaDetalhamentoDTO> listaDeCategorias = categoriasService.listaTodasCategorias();
        return ResponseEntity.ok(listaDeCategorias);
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<CategoriaDetalhamentoDTO> atualizarCategoria(@RequestBody CategoriaDTO dto,
                                                                       @PathVariable("id") Long id) {
        Categorias categoriaAtualizada = categoriasService.atualizarCategoria(dto, id);
        return ResponseEntity.ok(new CategoriaDetalhamentoDTO(categoriaAtualizada));
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<?> deletarCategoria(@PathVariable("id") Long id) {
        categoriasService.deletarCategoria(id);
        return ResponseEntity.noContent().build();
    }
}
