package com.udemy.curso.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import com.udemy.curso.domain.Categoria;
import com.udemy.curso.domain.Produto;
import com.udemy.curso.repositories.CategoriaRepository;
import com.udemy.curso.repositories.ProdutoRepository;

@Service
public class ProdutoService {
	
	@Autowired
	private ProdutoRepository repo;
	
	@Autowired
	private CategoriaRepository catRepo;
	
	public Produto findById(Integer id) {
		
		Optional<Produto> obj = repo.findById(id);
		
		return obj.orElse(null);
	}
	
	public Page<Produto> search(String nome, List<Integer> ids, Integer page, Integer linesPerPage, String direction, String orderBy) {
		
		PageRequest pageRequest = PageRequest.of(page, linesPerPage, Direction.valueOf(direction), orderBy);
		
		List<Categoria> categorias = catRepo.findAllById(ids);
		
		return repo.search(nome, categorias, pageRequest);
		
		
	}

}
