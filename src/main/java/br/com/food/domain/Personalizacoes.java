package br.com.food.domain;

import br.com.food.records.PersonalizacaoDTO;
import jakarta.persistence.*;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "personalizacoes")
public class Personalizacoes implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nome", nullable = false)
    private String nome;

    @Column(name = "descricao", nullable = false)
    private String descricao;

    @Column(name = "preco_adicional", nullable = false)
    private BigDecimal precoAdicional;

    @Column(name = "quantidade", nullable = false)
    private Integer quantidade;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_itens")
    private Itens itens;

    @OneToMany(mappedBy = "personalizacoes", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<PersonalizacoesPedidos> personalizacoesPedidos;

    public Personalizacoes() {}

    public Personalizacoes(PersonalizacaoDTO dto, Itens item) {
        this.nome = dto.nome();
        this.descricao = dto.descricao();
        this.precoAdicional = dto.preco();
        this.quantidade = dto.quantidade();
        this.itens = item;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Personalizacoes that)) return false;
        return Objects.equals(id, that.id) && Objects.equals(nome, that.nome) && Objects.equals(descricao, that.descricao) && Objects.equals(precoAdicional, that.precoAdicional) && Objects.equals(quantidade, that.quantidade) && Objects.equals(itens, that.itens);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, nome, descricao, precoAdicional, quantidade, itens);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public BigDecimal getPrecoAdicional() {
        return precoAdicional;
    }

    public void setPrecoAdicional(BigDecimal precoAdicional) {
        this.precoAdicional = precoAdicional;
    }

    public Integer getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(Integer quantidade) {
        this.quantidade = quantidade;
    }

    public Itens getItens() {
        return itens;
    }

    public void setItens(Itens itens) {
        this.itens = itens;
    }
}
