package com.octaviococco.testeSantander.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.octaviococco.testeSantander.domain.Operacao;

@Repository
public interface OperacaoRepository extends JpaRepository<Operacao, Integer>{

}