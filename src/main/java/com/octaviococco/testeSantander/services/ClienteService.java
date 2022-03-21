package com.octaviococco.testeSantander.services;

import java.math.BigDecimal;
import java.util.Calendar;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.octaviococco.testeSantander.domain.Cliente;
import com.octaviococco.testeSantander.domain.Operacao;
import com.octaviococco.testeSantander.enums.TipoOperacao;
import com.octaviococco.testeSantander.repositories.ClienteRepository;
import com.octaviococco.testeSantander.repositories.OperacaoRepository;

@Service
public class ClienteService {
	
	@Autowired
	ClienteRepository repo;
	
	@Autowired
	OperacaoRepository opRepo;
	
	public List<Cliente> findAll() {
		return repo.findAll();
	}
	
	public Cliente find(Integer id) throws RuntimeException {
		Optional<Cliente> cliente = repo.findById(id);
		return cliente.orElseThrow(() -> new RuntimeException("Objeto não encontrado:" + this.getClass().getName() + " ID: " + id));
	}
	
	public Cliente insert(Cliente obj) throws RuntimeException {
		obj.setId(null);
		return repo.save(obj);
	}

	public Cliente update(Cliente obj) {
		find(obj.getId());
		return repo.save(obj);
	}

	public void delete(Integer id) {
		find(id);
		repo.deleteById(id);
	}
	
	public Cliente sacar(Integer id, BigDecimal valor) throws RuntimeException {
		Cliente cliente = find(id);
		BigDecimal novoSaldo = cliente.getSaldo();
		novoSaldo = novoSaldo.subtract(valor);
		if(!cliente.isExclusive())
			novoSaldo.subtract(calculaTaxaAdm(valor));
		if(novoSaldo.compareTo(new BigDecimal(0)) < 0)
			throw new RuntimeException("Saldo para o cliente: " + cliente.getNumeroConta() + " " + cliente.getNome() + " insuficiente! Saldo atual: " + cliente.getSaldo());
		cliente.setSaldo(novoSaldo);
		Operacao operacao = new Operacao(null, Calendar.getInstance().getTime(), valor, TipoOperacao.SAQUE, cliente);
		opRepo.save(operacao);
		return update(cliente);
		
	}
	
	public Cliente depositar(Integer id, BigDecimal valor) {
		Cliente cliente = find(id);
		cliente.setSaldo(cliente.getSaldo().add(valor));
		Operacao operacao = new Operacao(null, Calendar.getInstance().getTime(), valor, TipoOperacao.DEPOSITO, cliente);
		opRepo.save(operacao);
		return update(cliente);
	}
	
	private BigDecimal calculaTaxaAdm(BigDecimal valor) {
		BigDecimal valorDescontado = BigDecimal.ZERO;
		if (valor.compareTo(new BigDecimal(100)) <= 0)
			valorDescontado = valor.subtract(new BigDecimal(0));
		else if (valor.compareTo(new BigDecimal(100)) > 0 && valor.compareTo(new BigDecimal(300)) <= 0)
			valorDescontado = valor.subtract(new BigDecimal(0.4/100));
		else if (valor.compareTo(new BigDecimal(300)) > 0 && valor.compareTo(new BigDecimal(300)) == 1)
			valorDescontado = valor.subtract(new BigDecimal(1/100));
		return valorDescontado;
	}

}
