package br.com.food.services;

import br.com.food.domain.Categorias;
import br.com.food.exceptions.ResourceNotFoundException;
import br.com.food.records.CategoriaDTO;
import br.com.food.records.CategoriaDetalhamentoDTO;
import br.com.food.repositories.CategoriasRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoriasService {

    @Autowired
    private CategoriasRepository categoriasRepository;

    public CategoriaDetalhamentoDTO criarCategoria(Categorias categoria) {
        Categorias categoriaCriada = categoriasRepository.save(categoria);
        return new CategoriaDetalhamentoDTO(categoriaCriada);
    }

    public Categorias mostrarCategoriaPorId(Long id) {
        return categoriasRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Categoria não encontrada!"));
    }

//    public List<CategoriaDetalhamentoDTO> listaTodasCategorias(CategoriaDTO dto) {
//
//    }
}
