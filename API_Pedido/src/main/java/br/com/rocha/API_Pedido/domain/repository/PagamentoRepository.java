package br.com.rocha.API_Pedido.domain.repository;

import br.com.rocha.API_Pedido.domain.entity.Pagamento;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PagamentoRepository extends JpaRepository<Pagamento, Integer> {

}
