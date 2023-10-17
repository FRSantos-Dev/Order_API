package br.com.rocha.API_Pedido.service;

import br.com.rocha.API_Pedido.domain.entity.Cliente;
import br.com.rocha.API_Pedido.domain.repository.ClienteRepository;
import br.com.rocha.API_Pedido.security.UserSS;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsServiceImp implements UserDetailsService {

    @Autowired
    private ClienteRepository clienteRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Cliente cliente = clienteRepository.findByEmail(email);

        if (cliente == null) {
            throw new UsernameNotFoundException(email);
        }

        return new UserSS(
                cliente.getId(),
                cliente.getEmail(),
                cliente.getSenha(),
                cliente.getPerfis());
    }

}