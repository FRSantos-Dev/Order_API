package br.com.rocha.API_Pedido.domain.repository;

import br.com.rocha.API_Pedido.domain.entity.Cidade;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CidadeRepository extends JpaRepository<Cidade, Integer> {

}
