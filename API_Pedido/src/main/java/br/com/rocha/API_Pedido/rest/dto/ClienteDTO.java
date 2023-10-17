package br.com.rocha.API_Pedido.rest.dto;

import br.com.rocha.API_Pedido.domain.entity.Cliente;
import br.com.rocha.API_Pedido.service.Validation.ClientUpdate;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import java.io.Serializable;

@Data
@ClientUpdate
@NoArgsConstructor
@AllArgsConstructor
public class ClienteDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    private Integer id;

    @NotEmpty(message = "Preenchimento obrigatorio.")
    @Length(min = 5, max = 120, message = "O tamanho deve estar entre 5 e 120 char.")
    private String nome;

    @NotEmpty(message = "Preenchimento obrigatorio.")
    @Email(message = "Email invalido! ")
    private String email;

    public ClienteDTO(Cliente cliente) {
        this.id = cliente.getId();
        this.nome = cliente.getNome();
        this.email = cliente.getEmail();
    }

}