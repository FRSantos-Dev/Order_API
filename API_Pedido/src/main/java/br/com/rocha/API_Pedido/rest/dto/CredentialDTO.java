package br.com.rocha.API_Pedido.rest.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CredentialDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    private String email;
    private String senha;

}
