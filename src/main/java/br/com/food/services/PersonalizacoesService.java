package br.com.food.services;

import br.com.food.domain.Personalizacoes;
import br.com.food.exceptions.ResourceNotFoundException;
import br.com.food.records.PersonalizacaoDTO;
import br.com.food.records.PersonalizacoesDetalhamentoDTO;
import br.com.food.repositories.PersonalizacoesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class PersonalizacoesService {
    @Autowired
    private PersonalizacoesRepository repository;

    @Autowired
    private ItensService itensService;


    public Personalizacoes buscarPorId(Long id) {
        var personalizacao = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Personalização não encontrada!"));
        return personalizacao;
    }

    public Personalizacoes criarPersonalizacao(PersonalizacaoDTO dto) {
        var item = itensService.buscaItemPorId(dto.itens());
        var personalizacao = new Personalizacoes(dto, item);
        return repository.save(personalizacao);
    }

    public List<PersonalizacoesDetalhamentoDTO> listarTodasPersonalizacoes() {
        List<Personalizacoes> personalizacoes = repository.findAll();
        List<PersonalizacoesDetalhamentoDTO> dtos = personalizacoes.stream()
                .map(PersonalizacoesDetalhamentoDTO::new)
                .collect(Collectors.toList());
        return dtos;
    }

    public Personalizacoes atualizarPersonalizacao(Long id, PersonalizacaoDTO dto) {
        var personalizacao = buscarPorId(id);
        if(dto.nome() != null && !dto.nome().isBlank()) {
            personalizacao.setNome(dto.nome());
        }
        if(dto.descricao() != null && !dto.descricao().isBlank()) {
            personalizacao.setDescricao(dto.descricao());
        }
        if(dto.preco().compareTo(BigDecimal.ZERO) > 0) {
            personalizacao.setNome(dto.nome());
        }
        if(dto.quantidade() != null && dto.quantidade() > 0) {
            personalizacao.setQuantidade(dto.quantidade());
        }
        if(dto.itens() != null) {
            var item = itensService.buscaItemPorId(dto.itens());
            personalizacao.setItens(item);
        }

        return repository.save(personalizacao);
    }

    public void deletarPersonalizacao(Long id) {
        buscarPorId(id);
        repository.deleteById(id);
    }
}
