package br.com.food.domain;

import br.com.food.enuns.Status;
import jakarta.persistence.*;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "pedidos")
public class Pedidos implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "data_hora", nullable = false)
    private LocalDateTime dataHora;

    @Column(name = "valor_total", nullable = false)
    private BigDecimal valorTotal;

    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = false)
    private Status status;

    @OneToMany(mappedBy = "pedidos", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ItemPedidos> itemPedidos = new ArrayList<>();

    public Pedidos() {}

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Pedidos pedidos)) return false;
        return Objects.equals(id, pedidos.id) && Objects.equals(dataHora, pedidos.dataHora) && Objects.equals(valorTotal, pedidos.valorTotal) && status == pedidos.status && Objects.equals(itemPedidos, pedidos.itemPedidos);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, dataHora, valorTotal, status, itemPedidos);
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

    public LocalDateTime getDataHora() {
        return dataHora;
    }

    public void setDataHora(LocalDateTime dataHora) {
        this.dataHora = dataHora;
    }

    public BigDecimal getValorTotal() {
        return valorTotal;
    }

    public void setValorTotal(BigDecimal valorTotal) {
        this.valorTotal = valorTotal;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }
}
