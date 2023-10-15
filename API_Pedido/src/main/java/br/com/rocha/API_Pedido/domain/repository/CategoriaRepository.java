package br.com.rocha.API_Pedido.domain.repository;

import br.com.rocha.API_Pedido.domain.entity.Categoria;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoriaRepository extends JpaRepository<Categoria, Integer> {

}
