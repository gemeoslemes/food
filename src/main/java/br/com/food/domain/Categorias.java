package br.com.food.domain;

import br.com.food.exceptions.NotFoundArgumentDTOs;
import br.com.food.records.CategoriaDTO;
import jakarta.persistence.*;

import java.io.Serializable;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "categorias")
public class Categorias implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 50)
    private String nome;

    @Column(nullable = false)
    private String descricao;

    @OneToMany(mappedBy = "categorias", fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    private List<SubCategorias> subCategorias;

    public Categorias() {}

    public Categorias(CategoriaDTO dto) {
        this.nome = dto.nome();
        this.descricao = dto.descricao();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Categorias that = (Categorias) o;
        return Objects.equals(id, that.id) && Objects.equals(nome, that.nome) && Objects.equals(descricao, that.descricao) && Objects.equals(subCategorias, that.subCategorias);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, nome, descricao, subCategorias);
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

    public List<SubCategorias> getSubCategorias() {
        return subCategorias;
    }

    public void setSubCategorias(List<SubCategorias> subCategorias) {
        this.subCategorias = subCategorias;
    }
}
