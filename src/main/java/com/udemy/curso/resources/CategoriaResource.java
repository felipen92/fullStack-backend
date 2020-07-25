package com.udemy.curso.resources;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.udemy.curso.domain.Categoria;
import com.udemy.curso.domain.dto.CategoriaDTO;
import com.udemy.curso.services.CategoriaService;


@RestController
@RequestMapping(value = "/categorias")
public class CategoriaResource {
	
	@Autowired
	private CategoriaService service;

	@GetMapping(value = "/{id}")
	public ResponseEntity<Categoria> findById(@PathVariable Integer id) {
		
		Categoria obj = service.findById(id);
		
		return ResponseEntity.ok().body(obj);
	}
	
	@GetMapping()
	public ResponseEntity<List<CategoriaDTO>> findAll(){
		
		List<Categoria> listCat = service.findAll();
		
		List<CategoriaDTO> listCatDTO = listCat.stream().map(cat -> new CategoriaDTO(cat)).collect(Collectors.toList());
		
		return ResponseEntity.ok().body(listCatDTO);
		
	}
	
	@GetMapping(value = "/page")
	public ResponseEntity<Page<CategoriaDTO>> findPage(
			@RequestParam(value = "page", defaultValue = "0") Integer page, 
			@RequestParam(value = "linesPerPage", defaultValue = "24")Integer linesPerPage, 
			@RequestParam(value = "direction", defaultValue = "ASC")String direction, 
			@RequestParam(value = "orderBy", defaultValue = "nome")String orderBy){
		
		Page<Categoria> listCat = service.findPage(page, linesPerPage, direction, orderBy);
		
		Page<CategoriaDTO> listCatDTO = listCat.map(cat -> new CategoriaDTO(cat));
		
		return ResponseEntity.ok().body(listCatDTO);
		
	}

	@PostMapping
	public ResponseEntity<Void> createCat(@Valid @RequestBody CategoriaDTO catDTO){
		
		Categoria cat = service.fromDTO(catDTO);
		
		cat = service.createCat(cat);
		
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(cat.getId()).toUri();
		
		return ResponseEntity.created(uri).build();
	}
	
	@PutMapping(value = "/{id}")
	public ResponseEntity<Void> updateCat(@PathVariable Integer id, @Valid @RequestBody CategoriaDTO catDTO){
		
		Categoria cat = service.fromDTO(catDTO);
		
		cat.setId(id);
		cat = service.updateCat(cat);
		
		
		return ResponseEntity.noContent().build();
	}

	@DeleteMapping(value = "/{id}")
	public ResponseEntity<Void> deleteCat(@PathVariable Integer id) {
		
		service.deleteCat(id);
		
		return ResponseEntity.noContent().build();
	}
}
