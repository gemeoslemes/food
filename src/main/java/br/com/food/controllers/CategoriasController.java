package br.com.food.controllers;

import br.com.food.domain.Categorias;
import br.com.food.records.CategoriaDTO;
import br.com.food.records.CategoriaDetalhamentoDTO;
import br.com.food.services.CategoriasService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/categorias/v1")
public class CategoriasController {

    @Autowired
    CategoriasService categoriasService;

    @PostMapping
    public ResponseEntity<CategoriaDetalhamentoDTO> criaCategoria(@RequestBody CategoriaDTO dto) {
        Categorias categoria = new Categorias(dto);
        CategoriaDetalhamentoDTO categoriaCriada = categoriasService.criarCategoria(categoria);
        return ResponseEntity.ok(categoriaCriada);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<CategoriaDetalhamentoDTO> mostrarCategoriaPorId(@PathVariable Long id) {
        var mostrarCategoriaPorId = categoriasService.mostrarCategoriaPorId(id);
        return ResponseEntity.ok(new CategoriaDetalhamentoDTO(mostrarCategoriaPorId));
    }

//    @GetMapping
//    public ResponseEntity<List<CategoriaDetalhamentoDTO>> listaTodasCategorias(CategoriaDTO dto) {
//        categoriasService.listaTodasCategorias(dto);
//    }
}
