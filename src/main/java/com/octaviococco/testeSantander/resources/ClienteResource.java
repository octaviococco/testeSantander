package com.octaviococco.testeSantander.resources;

import java.math.BigDecimal;
import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.octaviococco.testeSantander.domain.Cliente;
import com.octaviococco.testeSantander.services.ClienteService;

@RestController
@RequestMapping(value="/clientes")
public class ClienteResource {

	@Autowired
	private ClienteService service;
	
	@RequestMapping(value="/{id}", method=RequestMethod.GET)
	public ResponseEntity<Cliente> find(@PathVariable Integer id) {
		Cliente cliente = service.find(id);
		return ResponseEntity.ok().body(cliente);
	}
	
	@RequestMapping(method=RequestMethod.POST)
	public ResponseEntity<Void> insert(@RequestBody Cliente obj) {
		service.insert(obj);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj.getId()).toUri();
		return ResponseEntity.created(uri).build();
	}
	
	@RequestMapping(value="/{id}", method=RequestMethod.PUT)
	public ResponseEntity<Void> update(@RequestBody Cliente obj, @PathVariable Integer id) {
		obj = service.update(obj);
		return ResponseEntity.noContent().build();
	}
	
	@RequestMapping(value="/{id}", method=RequestMethod.DELETE)
	public ResponseEntity<Void> delete(@PathVariable Integer id) {
		service.delete(id);
		return ResponseEntity.noContent().build();
	}
	
	@RequestMapping(method=RequestMethod.GET)
	public ResponseEntity<List<Cliente>> findAll() {
		List<Cliente> list = service.findAll();
		return ResponseEntity.ok().body(list);
	}
	
	@RequestMapping(value="/{id}/sacar", method=RequestMethod.POST)
	public ResponseEntity<Cliente> sacar(@RequestParam Integer id, @RequestParam BigDecimal valor) {
		Cliente cliente = service.sacar(id, valor);
		return ResponseEntity.ok().body(cliente);
	}
	
	@RequestMapping(value="/{id}/depositar", method=RequestMethod.POST)
	public ResponseEntity<Cliente> depositar(@RequestParam Integer id, @RequestParam BigDecimal valor) {
		Cliente cliente = service.depositar(id, valor);
		return ResponseEntity.ok().body(cliente);
	}
	
}
