package com.octaviococco.testeSantander.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.octaviococco.testeSantander.domain.Operacao;
import com.octaviococco.testeSantander.repositories.OperacaoRepository;

@Service
public class OperacaoService {
		
	@Autowired
	OperacaoRepository repo;
	
	public List<Operacao> findAll() {
		return repo.findAll();
	}
	
	public Operacao find(Integer id) throws RuntimeException {
		Optional<Operacao> operacao = repo.findById(id);
		return operacao.orElseThrow(() -> new RuntimeException("Objeto não encontrado:" + this.getClass().getName() + " ID: " + id));
	}
	
	public Operacao insert(Operacao obj) throws RuntimeException {
		obj.setId(null);
		return repo.save(obj);
	}

	public Operacao update(Operacao obj) {
		find(obj.getId());
		return repo.save(obj);
	}

	public void delete(Integer id) {
		find(id);
		repo.deleteById(id);
	}
}