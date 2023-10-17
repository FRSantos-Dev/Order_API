package br.com.rocha.API_Pedido.service.Validation;

import br.com.rocha.API_Pedido.domain.enums.TipoCliente;
import br.com.rocha.API_Pedido.domain.repository.ClienteRepository;
import br.com.rocha.API_Pedido.rest.dto.ClienteNewDTO;
import br.com.rocha.API_Pedido.rest.exception.FieldMessage;
import br.com.rocha.API_Pedido.service.Validation.util.BR;
import org.springframework.beans.factory.annotation.Autowired;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.ArrayList;
import java.util.List;

public class ClientInsertValidator implements ConstraintValidator<ClientInsert, ClienteNewDTO> {

    @Autowired
    private ClienteRepository clienteRepository;

    @Override
    public void initialize(ClientInsert constraintAnnotation) {
    }

    @Override
    public boolean isValid(ClienteNewDTO clienteNewDto, ConstraintValidatorContext constraintValidatorContext) {
        List<FieldMessage> list = new ArrayList<>();

        if (clienteNewDto.getTipo().equals(TipoCliente.PESSOAFISICA.getCod())
                && !BR.isValidCpf(clienteNewDto.getCpfCnpj())) {
            list.add(new FieldMessage("cpfCnpj", "CPF Invalido."));
        }

        if (clienteNewDto.getTipo().equals(TipoCliente.PESSOAJURIDICA.getCod())
                && !BR.isValidCnpj(clienteNewDto.getCpfCnpj())) {
            list.add(new FieldMessage("cpfCnpj", "CNPJ Invalido."));
        }

        Cliente cliente = clienteRepository.findByEmail(clienteNewDto.getEmail());
        if (cliente != null) {
            list.add(new FieldMessage("email", "E-mail já cadastrado"));
        }

        for (FieldMessage e : list) {
            constraintValidatorContext.disableDefaultConstraintViolation();
            constraintValidatorContext.buildConstraintViolationWithTemplate(e.getMessage())
                    .addPropertyNode(e.getFieldName())
                    .addConstraintViolation();
        }

        return list.isEmpty();
    }

}