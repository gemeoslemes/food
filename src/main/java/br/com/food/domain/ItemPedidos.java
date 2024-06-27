package br.com.food.domain;

import jakarta.persistence.*;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "item_pedidos")
public class ItemPedidos implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_itens", nullable = false)
    private Itens itens;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_pedidos", nullable = false)
    private Pedidos pedidos;

    @Column(name = "quantidade", nullable = false)
    private Integer quantidade;

    @Column(name = "preco_unitario")
    private BigDecimal precoUnitario;

    @OneToMany(mappedBy = "itemPedidos", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<PersonalizacoesPedidos> personalizacoesPedidos;

    public ItemPedidos(){}

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ItemPedidos that = (ItemPedidos) o;
        return Objects.equals(id, that.id) && Objects.equals(itens, that.itens) && Objects.equals(pedidos, that.pedidos) && Objects.equals(quantidade, that.quantidade) && Objects.equals(precoUnitario, that.precoUnitario) && Objects.equals(personalizacoesPedidos, that.personalizacoesPedidos);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, itens, pedidos, quantidade, precoUnitario, personalizacoesPedidos);
    }

    public List<PersonalizacoesPedidos> getPersonalizacoesPedidos() {
        return personalizacoesPedidos;
    }

    public void setPersonalizacoesPedidos(List<PersonalizacoesPedidos> personalizacoesPedidos) {
        this.personalizacoesPedidos = personalizacoesPedidos;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Itens getItens() {
        return itens;
    }

    public void setItens(Itens itens) {
        this.itens = itens;
    }

    public Pedidos getPedidos() {
        return pedidos;
    }

    public void setPedidos(Pedidos pedidos) {
        this.pedidos = pedidos;
    }

    public Integer getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(Integer quantidade) {
        this.quantidade = quantidade;
    }

    public BigDecimal getPrecoUnitario() {
        return precoUnitario;
    }

    public void setPrecoUnitario(BigDecimal precoUnitario) {
        this.precoUnitario = precoUnitario;
    }
}
