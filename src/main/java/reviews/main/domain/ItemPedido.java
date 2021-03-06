package reviews.main.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.io.Serializable;
import java.text.NumberFormat;
import java.util.Locale;
import java.util.Objects;

@Entity
@Table
public class ItemPedido implements Serializable {
  private static final long serialVersionUID = 1L;

  @JsonIgnore
  @EmbeddedId
  private ItemPedidoPK id = new ItemPedidoPK();

  @Column
  private Double desconto;

  @Column
  private Integer quantidade;

  @Column
  private Double preco;

  public ItemPedido() {
  }

  public ItemPedido(Pedido pedido, Produto produto, Double desconto, Integer quantidade, Double preco) {
    super();
    id.setPedido(pedido);
    id.setProduto(produto);
    this.desconto = desconto;
    this.quantidade = quantidade;
    this.preco = preco;
  }

  @JsonIgnore
  public Pedido getPedido() {
    return id.getPedido();
  }

  public void setPedido(Pedido pedido) {
    this.id.setPedido(pedido);
  }

  public double getSubtotal() {
    return (this.preco - desconto) * this.quantidade;
  }

  public Produto getProduto() {
    return id.getProduto();
  }

  public void setProduto(Produto produto) {
    this.id.setProduto(produto);
  }

  public ItemPedidoPK getId() {
    return id;
  }

  public void setId(ItemPedidoPK id) {
    this.id = id;
  }

  public Double getDesconto() {
    return desconto;
  }

  public void setDesconto(Double desconto) {
    this.desconto = desconto;
  }

  public Integer getQuantidade() {
    return quantidade;
  }

  public void setQuantidade(Integer quantidade) {
    this.quantidade = quantidade;
  }

  public Double getPreco() {
    return preco;
  }

  public void setPreco(Double preco) {
    this.preco = preco;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    ItemPedido that = (ItemPedido) o;
    return id.equals(that.id);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id);
  }

  @Override
  public String toString() {
    NumberFormat nf = NumberFormat.getCurrencyInstance(new Locale("pt", "BR"));
    final StringBuilder sb = new StringBuilder();
    sb.append(getProduto().getNome());
    sb.append(", Qtde: ");
    sb.append(getQuantidade());
    sb.append(", Pre??o unit??rio: ");
    sb.append(nf.format(getPreco()));
    sb.append(", Subtotal: ");
    sb.append(nf.format(getSubtotal()));
    sb.append("\n");
    return sb.toString();
  }
}
