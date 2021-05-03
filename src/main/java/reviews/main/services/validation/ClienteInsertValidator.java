package reviews.main.services.validation;

import reviews.main.domain.enums.TipoCliente;
import reviews.main.dto.ClienteNewDto;
import reviews.main.resources.exceptions.helpers.FieldMessage;
import reviews.main.services.validation.utils.BR;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.ArrayList;
import java.util.List;

public class ClienteInsertValidator implements ConstraintValidator<ClienteInsert, ClienteNewDto> {

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
