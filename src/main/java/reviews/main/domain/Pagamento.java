package reviews.main.domain;

import reviews.main.domain.enums.EstadoPagamento;

public class Pagamento {
  private Integer id;
  private EstadoPagamento estado;

  private Pedido pedido;

  public Pagamento() {}

  public Pagamento(Integer id, EstadoPagamento estado, Pedido pedido) {
    this.id = id;
    this.estado = estado;
    this.pedido = pedido;
  }
}
