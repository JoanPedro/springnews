package reviews.main.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import reviews.main.services.email.EmailService;
import reviews.main.services.email.MockEmailService;

@Configuration
@Profile("test") // spring.profiles.active=test
public class TestConfig {

  @Bean
  public EmailService emailService() {
    return new MockEmailService();
  }
}
