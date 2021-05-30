package reviews.main.services.email;

import org.springframework.mail.SimpleMailMessage;
import reviews.main.domain.Pedido;

public interface EmailService {
  void sendOrderConfirmationEmail(Pedido obj);

  void sendEmail(SimpleMailMessage msg);
}
