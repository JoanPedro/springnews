package reviews.main.services;

import org.springframework.stereotype.Service;
import reviews.main.domain.PagamentoComBoleto;

import java.util.Calendar;
import java.util.Date;

@Service
public class BoletoService {
  public void preencherPagamentoComBoleto(PagamentoComBoleto pgto, Date instante) {
    Calendar calendar = Calendar.getInstance();
    calendar.setTime(instante);
    calendar.add(Calendar.DAY_OF_MONTH, 7);

    pgto.setDataVencimento(calendar.getTime());
  }
}
