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

import com.udemy.curso.domain.Cliente;
import com.udemy.curso.domain.dto.ClienteDTO;
import com.udemy.curso.services.ClienteService;


@RestController
@RequestMapping(value = "/clientes")
public class ClienteResource {
	
	@Autowired
	private ClienteService service;

	@GetMapping(value = "/{id}")
	public ResponseEntity<Cliente> findById(@PathVariable Integer id) {
		
		Cliente obj = service.findById(id);
		
		return ResponseEntity.ok().body(obj);
	}
	
	@GetMapping()
	public ResponseEntity<List<ClienteDTO>> findAll(){
		
		List<Cliente> listCli = service.findAll();
		
		List<ClienteDTO> listCliDTO = listCli.stream().map(cli -> new ClienteDTO(cli)).collect(Collectors.toList());
		
		return ResponseEntity.ok().body(listCliDTO);
		
	}
	
	@GetMapping(value = "/page")
	public ResponseEntity<Page<ClienteDTO>> findPage(
			@RequestParam(value = "page", defaultValue = "0") Integer page, 
			@RequestParam(value = "linesPerPage", defaultValue = "24")Integer linesPerPage, 
			@RequestParam(value = "direction", defaultValue = "ASC")String direction, 
			@RequestParam(value = "orderBy", defaultValue = "nome")String orderBy){
		
		Page<Cliente> listCli = service.findPage(page, linesPerPage, direction, orderBy);
		
		Page<ClienteDTO> listCliDTO = listCli.map(cli -> new ClienteDTO(cli));
		
		return ResponseEntity.ok().body(listCliDTO);
		
	}

	@PostMapping
	public ResponseEntity<Void> createCli(@Valid @RequestBody ClienteDTO cliDTO){
		
		Cliente cli = service.fromDTO(cliDTO);
		
		cli = service.createCli(cli);
		
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(cli.getId()).toUri();
		
		return ResponseEntity.created(uri).build();
	}
	
	@PutMapping(value = "/{id}")
	public ResponseEntity<Void> updateCli(@PathVariable Integer id, @Valid @RequestBody ClienteDTO cliDTO){
		
		Cliente cli = service.fromDTO(cliDTO);
		
		cli.setId(id);
		cli = service.updateCli(cli);
		
		
		return ResponseEntity.noContent().build();
	}

	@DeleteMapping(value = "/{id}")
	public ResponseEntity<Void> deleteCli(@PathVariable Integer id) {
		
		service.deleteCli(id);
		
		return ResponseEntity.noContent().build();
	}
}
