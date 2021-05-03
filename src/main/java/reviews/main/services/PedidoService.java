package reviews.main.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reviews.main.domain.ItemPedido;
import reviews.main.domain.PagamentoComBoleto;
import reviews.main.domain.Pedido;
import reviews.main.domain.enums.EstadoPagamento;
import reviews.main.repositories.ItemPedidoRepository;
import reviews.main.repositories.PagamentoRepository;
import reviews.main.repositories.PedidoRepository;
import reviews.main.services.exceptions.ObjectNotFoundException;

import java.util.Date;
import java.util.Optional;

@Service
public class PedidoService {

  @Autowired
  private PedidoRepository repository;

  @Autowired
  private BoletoService boletoService;

  @Autowired
  private ProdutoService produtoService;

  @Autowired
  private PagamentoRepository pagamentoRepository;

  @Autowired
  private ItemPedidoRepository itemPedidoRepository;

  public Pedido find(Integer id) {
    Optional<Pedido> result = this.repository.findById(id);
    return result.orElseThrow(
        () -> new ObjectNotFoundException("Objeto não encontrado! Id: " + id + ", Tipo: " + Pedido.class.getSimpleName())
    );
  }

  public Pedido insert(Pedido obj) {
    obj.setId(null);
    obj.setInstante(new Date());
    obj.getPagamento().setEstado(EstadoPagamento.PENDENTE);
    obj.getPagamento().setPedido(obj);

    if (obj.getPagamento() instanceof PagamentoComBoleto) {
      PagamentoComBoleto pgto = (PagamentoComBoleto) obj.getPagamento();
      this.boletoService.preencherPagamentoComBoleto(pgto, obj.getInstante());
    }

    obj = this.repository.save(obj);
    this.pagamentoRepository.save(obj.getPagamento());

    for (ItemPedido ip : obj.getItens()) {
      ip.setDesconto(0.0);
      ip.setPreco(produtoService.find(ip.getProduto().getId()).getPreco());
      ip.setPedido(obj);
    }

    this.itemPedidoRepository.saveAll(obj.getItens());

    return obj;
  }
}
