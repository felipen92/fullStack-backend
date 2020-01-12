package com.udemy.curso.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.udemy.curso.domain.Endereco;
import com.udemy.curso.repositories.EnderecoRepository;
import com.udemy.curso.services.exceptions.ObjectNotFoundException;

@Service
public class EnderecoService {
	
	@Autowired
	private EnderecoRepository repo;
	
	public Endereco buscarPorId(Integer id) {
		
		Optional<Endereco> obj = repo.findById(id);
		
		return obj.orElseThrow(() -> 
		new ObjectNotFoundException("Objeto não encontrado! Id: " + id + ", Tipo: " + Endereco.class.getName()));
	}

}
