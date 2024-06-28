package br.com.food.services;

import br.com.food.domain.Categorias;
import br.com.food.domain.Itens;
import br.com.food.domain.SubCategorias;
import br.com.food.exceptions.ResourceNotFoundException;
import br.com.food.records.CategoriaDetalhamentoDTO;
import br.com.food.records.ItensDTO;
import br.com.food.records.ItensDetalhamentoDTO;
import br.com.food.repositories.ItensRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ItensService {

    @Autowired
    private ItensRepository itensRepository;

    @Autowired
    private SubCategoriasService subCategoriasService;

    public Itens criarItem(ItensDTO dto) {
        var subCategorias = subCategoriasService.mostrarSubCategoriaPorId(dto.subcategorias());
        Itens itemCriado = new Itens(dto, subCategorias);
        return itensRepository.save(itemCriado);
    }

    public Itens buscaItemPorId(Long id) {
        return itensRepository.findById(id)
                .orElseThrow(() ->new ResourceNotFoundException("Item não encontrado!"));
    }

    public List<ItensDetalhamentoDTO> buscandoTodosItens() {
        List<Itens> itens = itensRepository.findAll();

        List<ItensDetalhamentoDTO> listaDeItensDTO = itens.stream()
                .map(i -> new ItensDetalhamentoDTO(i))
                .collect(Collectors.toList());

        return listaDeItensDTO;
    }

    public Itens atualizandoItem(Long id, ItensDTO dto) {
        Itens itens = buscaItemPorId(id);

        if (dto.nome() != null && !dto.nome().isBlank()) {
            itens.setNome(dto.nome());
        }
        if (dto.descricao() != null && !dto.descricao().isBlank()) {
            itens.setDescricao(dto.descricao());
        }
        if (dto.preco() != null && dto.preco().compareTo(BigDecimal.ZERO) > 0) {
            itens.setPreco(dto.preco());
        }
        if (dto.foto() != null && !dto.foto().isBlank()) {
            itens.setFotoItem(dto.foto());
        }
        if (dto.subcategorias() != null) {
            var subCategorias = subCategoriasService.mostrarSubCategoriaPorId(dto.subcategorias());
            itens.setSubCategorias(subCategorias);
        }
        return itensRepository.save(itens);
    }

    public void deletandoItem(Long id) {
        buscaItemPorId(id);
        itensRepository.deleteById(id);
    }
}
