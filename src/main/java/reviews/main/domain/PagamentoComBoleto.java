package reviews.main.domain;

import reviews.main.domain.enums.EstadoPagamento;

import java.util.Date;

public class PagamentoComBoleto extends Pagamento {
  private Date dataVencimento;
  private Date dataPagamento;

  public PagamentoComBoleto() {}

  public PagamentoComBoleto(Integer id, EstadoPagamento estado, Pedido pedido, Date dataVencimento, Date dataPagamento) {
    super(id, estado, pedido);
    this.dataPagamento = dataPagamento;
    this.dataVencimento = dataVencimento;
  }
}
