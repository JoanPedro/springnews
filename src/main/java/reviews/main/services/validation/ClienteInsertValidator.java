package reviews.main.services.validation;

import org.springframework.beans.factory.annotation.Autowired;
import reviews.main.domain.Cliente;
import reviews.main.domain.enums.TipoCliente;
import reviews.main.dto.ClienteNewDto;
import reviews.main.repositories.ClienteRepository;
import reviews.main.resources.exceptions.helpers.FieldMessage;
import reviews.main.services.validation.utils.BR;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class ClienteInsertValidator implements ConstraintValidator<ClienteInsert, ClienteNewDto> {

  @Autowired
  private ClienteRepository repository;

  @Override
  public void initialize(ClienteInsert ann) {
    // Does not implemented.
  }

  @Override
  public boolean isValid(ClienteNewDto objDto, ConstraintValidatorContext context) {
    List<FieldMessage> fields = new ArrayList<>();

    if (objDto.getTipo().equals(TipoCliente.PESSOAFISICA.getCod()) && !BR.isValidCPF(objDto.getCpfOuCnpj()))
      fields.add(new FieldMessage("cpfOuCnpj", "CPF inválido!"));

    if (objDto.getTipo().equals(TipoCliente.PESSOAJURIDICA.getCod()) && !BR.isValidCNPJ(objDto.getCpfOuCnpj()))
      fields.add(new FieldMessage("cpfOuCnpj", "CNPJ inválido!"));

    Cliente aux = this.repository.findByEmail(objDto.getEmail());

    if (!Objects.isNull(aux))
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
