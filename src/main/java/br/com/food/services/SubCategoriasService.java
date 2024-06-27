package br.com.food.services;

import br.com.food.domain.SubCategorias;
import br.com.food.exceptions.ResourceNotFoundException;
import br.com.food.records.SubCategoriasDTO;
import br.com.food.records.SubCategoriasDetalhamentoDTO;
import br.com.food.repositories.SubCategoriasRespository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class SubCategoriasService {
    @Autowired
    private SubCategoriasRespository respository;

    @Autowired
    private CategoriasService categoriasService;

    public SubCategorias mostrarSubCategoriaPorId(Long id) {
        var subCategorias = respository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Sub-Categoria não encontrada!"));
        return subCategorias;
    }

    public SubCategorias criarSubCategoria(SubCategoriasDTO dto) {
        var categoria = categoriasService.mostrarCategoriaPorId(dto.categoria());
        var subCategoria = new SubCategorias(dto, categoria);
        return respository.save(subCategoria);
    }

    public List<SubCategoriasDetalhamentoDTO> listandoTodasSubCategorias() {
        List<SubCategorias> subCategorias = respository.findAll();
        List<SubCategoriasDetalhamentoDTO> dtos = subCategorias.stream()
                .map(SubCategoriasDetalhamentoDTO::new)
                .collect(Collectors.toList());
        return dtos;
    }

    public SubCategorias atualizarSubCategoria(SubCategoriasDTO dto, Long id) {
        var subCategoria = mostrarSubCategoriaPorId(id);

        if(dto.nome() != null && !dto.nome().isBlank()) {
            subCategoria.setNome(dto.nome());
        }
        if(dto.descricao() != null && !dto.descricao().isBlank()) {
            subCategoria.setDescricao(dto.descricao());
        }
        if(dto.categoria() != null) {
            var categoria = categoriasService.mostrarCategoriaPorId(dto.categoria());
            subCategoria.setCategorias(categoria);
        }
        return respository.save(subCategoria);
    }

    public void deletarSubCategoria(Long id) {
        mostrarSubCategoriaPorId(id);
        respository.deleteById(id);
    }
}
