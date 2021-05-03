package reviews.main.services.validation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.HandlerMapping;
import reviews.main.domain.Cliente;
import reviews.main.dto.ClienteDTO;
import reviews.main.repositories.ClienteRepository;
import reviews.main.resources.exceptions.helpers.FieldMessage;

import javax.servlet.http.HttpServletRequest;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;

public class ClienteUpdateValidator implements ConstraintValidator<ClienteUpdate, ClienteDTO> {

  @Autowired
  private ClienteRepository repository;

  @Autowired
  private HttpServletRequest request;

  @Override
  public void initialize(ClienteUpdate ann) {
    // Does not implemented.
  }

  @Override
  public boolean isValid(ClienteDTO objDto, ConstraintValidatorContext context) {
    @SuppressWarnings("unchecked")
    Map<String, String> attr = (Map<String, String>) this.request.getAttribute(HandlerMapping.URI_TEMPLATE_VARIABLES_ATTRIBUTE);
    Integer uriId = Integer.parseInt(attr.get("id"));

    List<FieldMessage> fields = new ArrayList<>();

    Cliente aux = this.repository.findByEmail(objDto.getEmail());

    if (!Objects.isNull(aux) && !aux.getId().equals(uriId))
      fields.add(new FieldMessage("email", "Email já existente!"));

    for (FieldMessage fieldMessage : fields) {
      context.disableDefaultConstraintViolation();
      context
          .buildConstraintViolationWithTemplate(fieldMessage.getMessage())
          .addPropertyNode(fieldMessage.getFieldName())
          .addConstraintViolation();
    }

    return fields.isEmpty();
  }
}
