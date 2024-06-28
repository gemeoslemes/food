package br.com.food.services;

import br.com.food.domain.*;
import br.com.food.enuns.Status;
import br.com.food.exceptions.ResourceNotFoundException;
import br.com.food.records.*;
import br.com.food.repositories.ItemPedidosRepository;
import br.com.food.repositories.PedidosRepository;
import br.com.food.repositories.PersonalizacoesPedidosRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class PedidosService {
    @Autowired
    private PedidosRepository pedidosRepository;

    @Autowired
    private ItemPedidosRepository itemPedidosRepository;

    @Autowired
    private ItensService itensService;

    @Autowired
    private PersonalizacoesService personalizacoesService;

    @Autowired
    private PersonalizacoesPedidosRepository personalizacoesPedidosRepository;

    @Transactional
    public Pedidos criarPedido(PedidosDTO dto) {
        Pedidos pedido = new Pedidos();
        pedido.setDataHora(LocalDateTime.now());
        pedido.setStatus(Status.AGUARDANDO_CONFIRMACAO);

        ItemPedidos itemPedido = new ItemPedidos();
        PersonalizacoesPedidos personalizacoesPedidos = new PersonalizacoesPedidos();

        BigDecimal valorTotal = BigDecimal.ZERO;

        for (ItemPedidosDTO itemPedidosDTO : dto.itemPedidos()) {
            for(ItensDetalhamentoDTO itensDetalhamentoDTO : itemPedidosDTO.itens()) {
                Itens itens = itensService.buscaItemPorId(itensDetalhamentoDTO.id());

                itemPedido.setItens(itens);
                itemPedido.setQuantidade(itemPedidosDTO.quantidade());
                itemPedido.setPrecoUnitario(itens.getPreco());

                valorTotal = valorTotal.add(itens.getPreco()
                        .multiply(BigDecimal.valueOf(itemPedidosDTO.quantidade())));

                for(PersonalizacoesDetalhamentoDTO personalizacoesDetalhamentoDTO : itemPedidosDTO.personalizacoesPedidos()) {
                    Personalizacoes personalizacoes = personalizacoesService.buscarPorId(personalizacoesDetalhamentoDTO.id());

                    if(personalizacoesDetalhamentoDTO.itens().equals(itens.getId())) {
                        personalizacoesPedidos.setItensPedidos(itemPedido);
                        personalizacoesPedidos.setPersonalizacoes(personalizacoes);

                        BigDecimal valorPersonalizacao = personalizacoes.getPrecoAdicional().multiply(BigDecimal.valueOf(personalizacoesDetalhamentoDTO.quantidade()));
                        valorTotal = valorTotal.add(valorPersonalizacao);
                    }
                }
            }
        }

        pedido.setValorTotal(valorTotal);
        var pedidoSalvo = pedidosRepository.save(pedido);

        itemPedido.setPedidos(pedidoSalvo);
        var itemPedidoSalvo = itemPedidosRepository.save(itemPedido);

        personalizacoesPedidos.setItensPedidos(itemPedidoSalvo);
        personalizacoesPedidosRepository.save(personalizacoesPedidos);

        return pedidoSalvo;
    }

    public Pedidos buscarPorId(Long id) {
        return pedidosRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Pedido não encontrado!"));
    }

    public Pedidos atualizarPedido(Long id, PedidosAtualizaStatusDTO dto) {
        var pedido = buscarPorId(id);
        if(dto.status() != null) {
            pedido.setStatus(dto.status());
            pedido.setDataHora(LocalDateTime.now());
        }
        return pedidosRepository.save(pedido);
    }

    public List<PedidosDetalhamentoDTO> listarTodosOsPedidos() {
        List<Pedidos> pedidos = pedidosRepository.findAll();
        List<PedidosDetalhamentoDTO> dtos = convertToPedidosDetalhamentoDTOs(pedidos);
        return dtos;
    }

    public List<PedidosDetalhamentoDTO> buscarPedidosAtivos() {
        List<Pedidos> pedidos = pedidosRepository.findByStatusNot(Status.CANCELADO);
        List<PedidosDetalhamentoDTO> dtos = convertToPedidosDetalhamentoDTOs(pedidos);
        return dtos;
    }

    public List<PedidosDetalhamentoDTO> buscarPedidosAtivosComStatusEntregueHoje() {
        List<Pedidos> pedidos = pedidosRepository.buscarPedidosAtivosComStatusEntregueHoje(Status.ENTREGUE);
        List<PedidosDetalhamentoDTO> dtos = convertToPedidosDetalhamentoDTOs(pedidos);
        return dtos;
    }

    public List<PedidosDetalhamentoDTO> buscarPedidosAtivosEPorData(LocalDateTime inicio, LocalDateTime fim) {
        var pedidos = pedidosRepository.buscarPedidosAtivosComStatusEData(Status.ENTREGUE, inicio, fim);
        List<PedidosDetalhamentoDTO> dtos = convertToPedidosDetalhamentoDTOs(pedidos);
        return dtos;
    }

    public List<PedidosDetalhamentoDTO> convertToPedidosDetalhamentoDTOs(List<Pedidos> pedidos) {
        return pedidos.stream()
                .map(PedidosDetalhamentoDTO::new)
                .collect(Collectors.toList());
    }
}
