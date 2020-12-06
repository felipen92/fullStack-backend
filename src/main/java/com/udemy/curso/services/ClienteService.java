package com.udemy.curso.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.udemy.curso.domain.Cidade;
import com.udemy.curso.domain.Cliente;
import com.udemy.curso.domain.Endereco;
import com.udemy.curso.domain.dto.ClienteDTO;
import com.udemy.curso.domain.dto.ClienteNewDTO;
import com.udemy.curso.domain.enums.TipoCliente;
import com.udemy.curso.repositories.ClienteRepository;
import com.udemy.curso.repositories.EnderecoRepository;
import com.udemy.curso.services.exceptions.DataIntegrityException;
import com.udemy.curso.services.exceptions.ObjectNotFoundException;

@Service
public class ClienteService {

	@Autowired
	private ClienteRepository repo;

	@Autowired
	private EnderecoRepository repoEnd;

	public Cliente findById(Integer id) {

		Optional<Cliente> obj = repo.findById(id);

		return obj.orElseThrow(() -> new ObjectNotFoundException(
				"Objeto não encontrado! Id: " + id + ", Tipo: " + Cliente.class.getName()));
	}

	public List<Cliente> findAll() {
		return repo.findAll();
	}

	@Transactional
	public Cliente createCli(Cliente cli) {
		cli.setId(null);

		repoEnd.saveAll(cli.getEnderecos());

		cli = repo.save(cli);

		return cli;
	}

	public Cliente updateCli(Cliente cli) {

		Cliente newObj = findById(cli.getId());

		updateData(newObj, cli);

		return repo.save(newObj);
	}

	public void deleteCli(Integer id) {
		findById(id);

		try {

			repo.deleteById(id);

		} catch (DataIntegrityViolationException e) {
			throw new DataIntegrityException("Não é possível excluir um cliente que possui pedidos!!");
		}

	}

	public Page<Cliente> findPage(Integer page, Integer linesPerPage, String direction, String orderBy) {

		PageRequest pageCli = PageRequest.of(page, linesPerPage, Direction.valueOf(direction), orderBy);

		return repo.findAll(pageCli);
	}

	public Cliente fromDTO(ClienteDTO cliDTO) {
		return new Cliente(cliDTO.getId(), cliDTO.getNome(), cliDTO.getEmail(), null, null);
	}

	public Cliente fromDTO(ClienteNewDTO cliDTO) {
		Cliente cli = new Cliente(null, cliDTO.getNome(), cliDTO.getEmail(), cliDTO.getCpfOrCnpf(),
				TipoCliente.toEnum(cliDTO.getTipo()));
		Cidade cid = new Cidade(cliDTO.getCidadeId(), null, null);
		Endereco end = new Endereco(null, cliDTO.getLogradouro(), cliDTO.getNumero(), cliDTO.getComplemento(),
				cliDTO.getBairro(), cliDTO.getCep(), cli, cid);
		cli.getEnderecos().add(end);
		cli.getTelefones().add(cliDTO.getTelefone1());
		if (cliDTO.getTelefone2() != null) {
			cli.getTelefones().add(cliDTO.getTelefone2());
		}
		if (cliDTO.getTelefone3() != null) {
			cli.getTelefones().add(cliDTO.getTelefone3());
		}
		return cli;
	}

	private void updateData(Cliente newObj, Cliente obj) {
		newObj.setNome(obj.getNome());
		newObj.setEmail(obj.getEmail());
	}

}
