package br.com.rocha.API_Pedido.service;

import br.com.rocha.API_Pedido.domain.entity.Categoria;
import br.com.rocha.API_Pedido.domain.repository.CategoriaRepository;
import br.com.rocha.API_Pedido.exception.DataIntegratyException;
import br.com.rocha.API_Pedido.exception.ObjectNotFoundException;
import br.com.rocha.API_Pedido.rest.dto.CategoriaDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategoriaService {

    @Autowired
    private CategoriaRepository categoriaRepository;

    public Categoria find(Integer id) {
        Optional<Categoria> categoria = categoriaRepository.findById(id);
        return categoria.orElseThrow(() -> new ObjectNotFoundException("Objeto Não Encontrado! Id: " + id + ", tipo: "
                + Categoria.class.getName()));
    }

    public List<Categoria> findAll() {
        return categoriaRepository.findAll();
    }

    public Categoria Insert(Categoria categoria) {
        categoria.setId(null);

        return categoriaRepository.save(categoria);
    }

    public Page<Categoria> findPage(Integer page, Integer linerPerPage, String orderBy, String direction) {
        PageRequest pageRequest = PageRequest.of(page, linerPerPage, Sort.Direction.valueOf(direction), orderBy);

        return categoriaRepository.findAll(pageRequest);
    }

    public Categoria update(Categoria categoria) {
        find(categoria.getId());

        return categoriaRepository.save(categoria);
    }

    public void delete(Integer id) {
        find(id);

        try {
            categoriaRepository.deleteById(id);
        } catch (DataIntegrityViolationException e) {
            throw new DataIntegratyException("Não é possivel excluir uma categoria que possui produtos");
        }
    }

    public Categoria fromDto(CategoriaDTO categoriaDto) {
        return new Categoria(categoriaDto.getId(), categoriaDto.getNome());
    }

}
