package com.octaviococco.testeSantander.domain;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Cliente implements Serializable{

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name= "cliente_id")
	private Integer id;
	
	@Column
	private String nome;
	
	@Column
	private boolean exclusive;
	
	@Column
	private BigDecimal saldo;
	
	@Column
	private String numeroConta;
	
	@Column
	private Date dataNasc;
	
	@OneToMany(mappedBy = "cliente")
	@JsonBackReference
	@JsonIgnore
	private List<Operacao> operacoes;
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public boolean isExclusive() {
		return exclusive;
	}
	public void setExclusive(boolean exclusive) {
		this.exclusive = exclusive;
	}
	public BigDecimal getSaldo() {
		return saldo;
	}
	public void setSaldo(BigDecimal saldo) {
		this.saldo = saldo;
	}
	public String getNumeroConta() {
		return numeroConta;
	}
	public void setNumeroConta(String numeroConta) {
		this.numeroConta = numeroConta;
	}
	public Date getDataNasc() {
		return dataNasc;
	}
	public void setDataNasc(Date dataNasc) {
		this.dataNasc = dataNasc;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((dataNasc == null) ? 0 : dataNasc.hashCode());
		result = prime * result + (exclusive ? 1231 : 1237);
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((nome == null) ? 0 : nome.hashCode());
		result = prime * result + ((numeroConta == null) ? 0 : numeroConta.hashCode());
		result = prime * result + ((saldo == null) ? 0 : saldo.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Cliente other = (Cliente) obj;
		if (dataNasc == null) {
			if (other.dataNasc != null)
				return false;
		} else if (!dataNasc.equals(other.dataNasc))
			return false;
		if (exclusive != other.exclusive)
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (nome == null) {
			if (other.nome != null)
				return false;
		} else if (!nome.equals(other.nome))
			return false;
		if (numeroConta == null) {
			if (other.numeroConta != null)
				return false;
		} else if (!numeroConta.equals(other.numeroConta))
			return false;
		if (saldo == null) {
			if (other.saldo != null)
				return false;
		} else if (!saldo.equals(other.saldo))
			return false;
		return true;
	}
	public Cliente(Integer id, String nome, boolean exclusive, BigDecimal saldo, String numeroConta, Date dataNasc) {
		super();
		this.id = id;
		this.nome = nome;
		this.exclusive = exclusive;
		this.saldo = saldo;
		this.numeroConta = numeroConta;
		this.dataNasc = dataNasc;
	}
	public Cliente() {
		super();
	}

	
}
