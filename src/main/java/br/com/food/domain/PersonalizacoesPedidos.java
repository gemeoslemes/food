package br.com.food.domain;

import jakarta.persistence.*;

import java.io.Serializable;
import java.util.Objects;

@Entity
@Table(name = "personalizacoes_pedidos")
public class PersonalizacoesPedidos implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_itens_pedidos", nullable = false)
    private ItemPedidos itemPedidos;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_personalizacoes", nullable = false)
    private Personalizacoes personalizacoes;

    public PersonalizacoesPedidos(){}

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PersonalizacoesPedidos that = (PersonalizacoesPedidos) o;
        return Objects.equals(id, that.id) && Objects.equals(itemPedidos, that.itemPedidos) && Objects.equals(personalizacoes, that.personalizacoes);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, itemPedidos, personalizacoes);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public ItemPedidos getItensPedidos() {
        return itemPedidos;
    }

    public void setItensPedidos(ItemPedidos itemPedidos) {
        this.itemPedidos = itemPedidos;
    }

    public Personalizacoes getPersonalizacoes() {
        return personalizacoes;
    }

    public void setPersonalizacoes(Personalizacoes personalizacoes) {
        this.personalizacoes = personalizacoes;
    }
}
