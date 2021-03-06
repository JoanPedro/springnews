package reviews.main.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;
import reviews.main.domain.PagamentoComBoleto;
import reviews.main.domain.PagamentoComCartao;

@Configuration
public class JacksonConfig {

  @Bean
  public Jackson2ObjectMapperBuilder objectMapperBuilder() {
    return new Jackson2ObjectMapperBuilder() {
      @Override
      public void configure(ObjectMapper objectMapper) {
        objectMapper.registerSubtypes(PagamentoComCartao.class);
        objectMapper.registerSubtypes(PagamentoComBoleto.class);
        super.configure(objectMapper);
      }
    };
  }

}
