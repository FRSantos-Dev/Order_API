package br.com.rocha.API_Pedido.service;

import br.com.rocha.API_Pedido.domain.entity.Categoria;
import br.com.rocha.API_Pedido.domain.entity.Produto;
import br.com.rocha.API_Pedido.domain.repository.CategoriaRepository;
import br.com.rocha.API_Pedido.domain.repository.ProdutoRepository;
import br.com.rocha.API_Pedido.exception.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProdutoService {

    @Autowired
    private ProdutoRepository produtoRepository;

    @Autowired
    private CategoriaRepository categoriaRepository;

    public Produto find(Integer id) {
        Optional<Produto> produto = produtoRepository.findById(id);

        return produto.orElseThrow(() -> new ObjectNotFoundException(
                "Obj não encontrado! Id: " + id + ", Tipo " + Produto.class.getName()));
    }

    public Page<Produto> search(
            String nome,
            List<Integer> ids,
            Integer page,
            Integer linesPerPage,
            String orderBy,
            String direction) {
        PageRequest pageRequest = PageRequest.of(page, linesPerPage, Sort.Direction.valueOf(direction), orderBy);
        List<Categoria> categorias = categoriaRepository.findAllById(ids);

        return produtoRepository.search(nome, categorias, pageRequest);
    }

}
