package br.com.gerafit.service;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import br.com.gerafit.domain.Aluno.Sexo;
import br.com.gerafit.domain.Aluno.Situacao;
import br.com.gerafit.domain.Estado;
import br.com.gerafit.domain.EstadoRepository;

//Este service sera chamado pela classe DataBean

@Stateless//Faz parte de um EJB, Componente de negocios e transacionais em banco de dados.
//Lembrando de nao se deve criar atributos em EJB, pois podem gerar incosistencia de dados.

//Servico de busca em EstadoRepository
public class DataService {
	
	@EJB
	private EstadoRepository estadoRepository;//Aqui comunico com EstadoRepository.
	
	//Servico que retornara a lista de estados.
	public List<Estado> listEstados() {
		List<Estado> estados = estadoRepository.listEstados();
		return estados;
	}

	//Servico que retornara a lista de sexos.
	public Sexo[] getSexos() {
     
		return Sexo.values();
		
	}

	//Servico que retornara a lista de situacoes.
	public Situacao[] getSituacoes() {

		return Situacao.values();
	}



}
