package com.octaviococco.testeSantander;

import java.math.BigDecimal;
import java.util.Calendar;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.octaviococco.testeSantander.domain.Cliente;
import com.octaviococco.testeSantander.repositories.ClienteRepository;

@SpringBootApplication
public class TesteSantanderApplication implements CommandLineRunner{
	
	@Autowired
	private ClienteRepository clienteRepository;
	
	public static void main(String[] args) {
		SpringApplication.run(TesteSantanderApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		
		Cliente cli1 = new Cliente();
		cli1.setNome("Fulano");
		cli1.setNumeroConta("12345-6");
		cli1.setExclusive(true);
		cli1.setDataNasc(Calendar.getInstance().getTime());
		cli1.setSaldo(new BigDecimal(12000));
		clienteRepository.save(cli1);
		
		Cliente cli2 = new Cliente();
		cli2.setNome("Ciclano");
		cli2.setNumeroConta("98765-4");
		cli2.setExclusive(false);
		cli2.setDataNasc(Calendar.getInstance().getTime());
		cli2.setSaldo(new BigDecimal(300));
		clienteRepository.save(cli2);
		
		Cliente cli3 = new Cliente();
		cli3.setNome("Ciclano");
		cli3.setNumeroConta("98765-4");
		cli3.setExclusive(false);
		cli3.setDataNasc(Calendar.getInstance().getTime());
		cli3.setSaldo(new BigDecimal(10547.55));
		clienteRepository.save(cli3);
		
	}

}
