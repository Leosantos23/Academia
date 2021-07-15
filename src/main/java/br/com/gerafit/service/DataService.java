package br.com.gerafit.service;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import br.com.gerafit.domain.Aluno.Sexo;
import br.com.gerafit.domain.Aluno.Situacao;
import br.com.gerafit.domain.Estado;
import br.com.gerafit.domain.EstadoRepository;

@Stateless
public class DataService {
	
	@EJB
	private EstadoRepository estadoRepository;
	
	public List<Estado> listEstados() {
		List<Estado> estados = estadoRepository.listEstados();
		return estados;
	}

	public Sexo[] getSexos() {
     
		return Sexo.values();
		
	}

	public Situacao[] getSituacoes() {

		return Situacao.values();
	}



}
