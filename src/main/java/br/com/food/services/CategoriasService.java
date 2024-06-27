package br.com.food.services;

import br.com.food.domain.Categorias;
import br.com.food.exceptions.ResourceNotFoundException;
import br.com.food.records.CategoriaDTO;
import br.com.food.records.CategoriaDetalhamentoDTO;
import br.com.food.repositories.CategoriasRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CategoriasService {

    @Autowired
    private CategoriasRepository categoriasRepository;

    public Categorias criarCategoria(Categorias categoria) {
        return categoriasRepository.save(categoria);
    }

    public Categorias mostrarCategoriaPorId(Long id) {
        return categoriasRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Categoria não encontrada!"));
    }

    public List<CategoriaDetalhamentoDTO> listaTodasCategorias() {
        List<Categorias> categorias = categoriasRepository.findAll();

        List<CategoriaDetalhamentoDTO> listaDTO = categorias.stream()
                .map(c -> new CategoriaDetalhamentoDTO(c))
                .collect(Collectors.toList());

        return listaDTO;
    }

    public Categorias atualizarCategoria(CategoriaDTO dto, Long id) {
        var categoriaASerAtualizada = mostrarCategoriaPorId(id);

        if (dto.nome() != null && !dto.nome().isBlank()) {
            categoriaASerAtualizada.setNome(dto.nome());
        }
        if (dto.descricao() != null && !dto.descricao().isBlank()) {
            categoriaASerAtualizada.setDescricao(dto.descricao());
        }
        return categoriasRepository.save(categoriaASerAtualizada);
    }

    public void deletarCategoria(Long id) {
        categoriasRepository.deleteById(id);
    }
}
