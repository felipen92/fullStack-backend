package com.udemy.curso.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.udemy.curso.domain.Produto;
import com.udemy.curso.repositories.ProdutoRepository;

@Service
public class ProdutoService {
	
	@Autowired
	private ProdutoRepository repo;
	
	public Produto buscarPorId(Integer id) {
		
		Optional<Produto> obj = repo.findById(id);
		
		return obj.orElse(null);
	}

}
