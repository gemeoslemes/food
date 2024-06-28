package br.com.food.services;

import br.com.food.domain.Itens;
import br.com.food.exceptions.ResourceNotFoundException;
import br.com.food.records.ItensDTO;
import br.com.food.repositories.ItensRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
}
