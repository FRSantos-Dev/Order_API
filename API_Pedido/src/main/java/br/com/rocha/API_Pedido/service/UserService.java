package br.com.rocha.API_Pedido.service;

import br.com.rocha.API_Pedido.security.UserSS;
import org.springframework.security.core.context.SecurityContextHolder;

public class UserService {

    public static UserSS authenticated() {
        try {
            return (UserSS) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        } catch (Exception E) {
            return null;
        }
    }

}
