package br.com.rocha.API_Pedido.domain.repository;

import br.com.rocha.API_Pedido.domain.entity.Endereco;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EnderecoRepository extends JpaRepository<Endereco, Integer> {

}
