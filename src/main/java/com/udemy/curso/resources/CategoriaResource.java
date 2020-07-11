package com.udemy.curso.resources;

import java.net.URI;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.udemy.curso.domain.Categoria;
import com.udemy.curso.services.CategoriaService;


@RestController
@RequestMapping(value = "/categorias")
public class CategoriaResource {
	
	@Autowired
	private CategoriaService service;

	@GetMapping(value = "/{id}")
	public ResponseEntity<Categoria> buscarPorId(@PathVariable Integer id) {
		
		Categoria obj = service.buscarPorId(id);
		
		return ResponseEntity.ok().body(obj);
	}
	

	@PostMapping
	public ResponseEntity<Void> inserirCategoria(@RequestBody Categoria cat){
		
		cat = service.inserirCategoria(cat);
		
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(cat.getId()).toUri();
		
		return ResponseEntity.created(uri).build();
	}
	
	@PutMapping(value = "/{id}")
	public ResponseEntity<Void> atualizarCategoria(@PathVariable Integer id, @RequestBody Categoria cat){
		
		cat.setId(id);
		cat = service.atualizarCategoria(cat);
		
		
		return ResponseEntity.noContent().build();
	}

	@DeleteMapping(value = "/{id}")
	public ResponseEntity<Void> apagarCategoria(@PathVariable Integer id) {
		
		service.apagarCategoria(id);
		
		return ResponseEntity.noContent().build();
	}
}
