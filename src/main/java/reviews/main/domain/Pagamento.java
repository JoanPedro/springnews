package reviews.main.domain;

import reviews.main.domain.enums.EstadoPagamento;

public class Pagamento {
  private Integer id;
  private EstadoPagamento estado;

  private Pedido pedido;
}
