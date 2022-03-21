package com.octaviococco.testeSantander.domain;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.octaviococco.testeSantander.enums.TipoOperacao;

@Entity
public class Operacao implements Serializable{

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column
	private Integer id;
	
	@Column
	private Date dataOperacao;
	
	@Column
	private BigDecimal valorOperacao;
	
	@Column
	private TipoOperacao tipoOperacao;
	
	@ManyToOne
	@JoinColumn(name="cliente_id")
	@JsonManagedReference
	private Cliente cliente;
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Date getDataOperacao() {
		return dataOperacao;
	}
	public void setDataOperacao(Date dataOperacao) {
		this.dataOperacao = dataOperacao;
	}
	public BigDecimal getValorOperacao() {
		return valorOperacao;
	}
	public void setValorOperacao(BigDecimal valorOperacao) {
		this.valorOperacao = valorOperacao;
	}
	public TipoOperacao getTipoOperacao() {
		return tipoOperacao;
	}
	public void setTipoOperacao(TipoOperacao tipoOperacao) {
		this.tipoOperacao = tipoOperacao;
	}
	public Cliente getCliente() {
		return cliente;
	}
	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public Operacao(Integer id, Date dataOperacao, BigDecimal valorOperacao, TipoOperacao tipoOperacao,
			Cliente cliente) {
		super();
		this.id = id;
		this.dataOperacao = dataOperacao;
		this.valorOperacao = valorOperacao;
		this.tipoOperacao = tipoOperacao;
		this.cliente = cliente;
	}
	
	public Operacao() {
		super();
	}
	

	
}
