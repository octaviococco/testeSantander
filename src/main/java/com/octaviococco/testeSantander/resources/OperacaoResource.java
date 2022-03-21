package com.octaviococco.testeSantander.resources;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.octaviococco.testeSantander.domain.Operacao;
import com.octaviococco.testeSantander.services.OperacaoService;

@RestController
@RequestMapping(value="/operacoes")
public class OperacaoResource {

	@Autowired
	private OperacaoService service;
	
	@RequestMapping(value="/{id}", method=RequestMethod.GET)
	public ResponseEntity<Operacao> find(@PathVariable Integer id) {
		Operacao operacao = service.find(id);
		return ResponseEntity.ok().body(operacao);
	}
	
	@RequestMapping(method=RequestMethod.POST)
	public ResponseEntity<Void> insert(@RequestBody Operacao obj) {
		service.insert(obj);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj.getId()).toUri();
		return ResponseEntity.created(uri).build();
	}
	
	@RequestMapping(value="/{id}", method=RequestMethod.PUT)
	public ResponseEntity<Void> update(@RequestBody Operacao obj, @PathVariable Integer id) {
		obj = service.update(obj);
		return ResponseEntity.noContent().build();
	}
	
	@RequestMapping(value="/{id}", method=RequestMethod.DELETE)
	public ResponseEntity<Void> delete(@PathVariable Integer id) {
		service.delete(id);
		return ResponseEntity.noContent().build();
	}
	
	@RequestMapping(method=RequestMethod.GET)
	public ResponseEntity<List<Operacao>> findAll() {
		List<Operacao> list = service.findAll();
		return ResponseEntity.ok().body(list);
	}
	
}