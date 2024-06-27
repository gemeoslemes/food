package br.com.food.services;

import br.com.food.domain.Categorias;
import br.com.food.exceptions.ResourceNotFoundException;
import br.com.food.records.CategoriaDetalhamentoDTO;
import br.com.food.repositories.CategoriasRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CategoriasService {

    @Autowired
    private CategoriasRepository categoriasRepository;

    public CategoriaDetalhamentoDTO criarCategoria(Categorias categoria) {
        Categorias categoriaCriada = categoriasRepository.save(categoria);
        return new CategoriaDetalhamentoDTO(categoriaCriada);
    }

    public CategoriaDetalhamentoDTO mostrarCategoriaPorId(Long id) {
        var buscandoCategoriaPorId = categoriasRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Categoria não encontrada!"));

        return new CategoriaDetalhamentoDTO(buscandoCategoriaPorId);
    }
}
