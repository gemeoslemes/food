package br.com.food.domain;

import br.com.food.records.ItensDTO;
import jakarta.persistence.*;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "itens")
public class Itens implements Serializable {
    private static long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nome", nullable = false)
    private String nome;

    @Column(name = "descricao", nullable = false)
    private String descricao;

    @Column(name = "preco", nullable = false)
    private BigDecimal preco;

    @Lob
    @Column(name = "foto_item", nullable = false)
    private String fotoItem;

    @ManyToOne
    @JoinColumn(name = "id_sub_categoria")
    private SubCategorias subCategorias;

    @OneToMany(mappedBy = "itens", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ItemPedidos> itemPedidos;

    @OneToMany(mappedBy = "itens", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Personalizacoes> personalizacoes;

    public Itens() {}

    public Itens(ItensDTO dto, SubCategorias subCategorias) {
        this.nome = dto.nome();
        this.descricao = dto.descricao();
        this.preco = dto.preco();
        this.fotoItem = dto.foto();
        this.subCategorias = subCategorias;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Itens itens = (Itens) o;
        return Objects.equals(id, itens.id) && Objects.equals(nome, itens.nome) && Objects.equals(descricao, itens.descricao) && Objects.equals(preco, itens.preco) && Objects.equals(fotoItem, itens.fotoItem) && Objects.equals(subCategorias, itens.subCategorias) && Objects.equals(itemPedidos, itens.itemPedidos) && Objects.equals(personalizacoes, itens.personalizacoes);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, nome, descricao, preco, fotoItem, subCategorias, itemPedidos, personalizacoes);
    }

    public List<Personalizacoes> getPersonalizacoes() {
        return personalizacoes;
    }

    public void setPersonalizacoes(List<Personalizacoes> personalizacoes) {
        this.personalizacoes = personalizacoes;
    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public static void setSerialVersionUID(long serialVersionUID) {
        Itens.serialVersionUID = serialVersionUID;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public List<ItemPedidos> getItemPedidos() {
        return itemPedidos;
    }

    public void setItemPedidos(List<ItemPedidos> itemPedidos) {
        this.itemPedidos = itemPedidos;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public BigDecimal getPreco() {
        return preco;
    }

    public void setPreco(BigDecimal preco) {
        this.preco = preco;
    }

    public String getFotoItem() {
        return fotoItem;
    }

    public void setFotoItem(String fotoItem) {
        this.fotoItem = fotoItem;
    }

    public SubCategorias getSubCategorias() {
        return subCategorias;
    }

    public void setSubCategorias(SubCategorias subCategorias) {
        this.subCategorias = subCategorias;
    }
}
