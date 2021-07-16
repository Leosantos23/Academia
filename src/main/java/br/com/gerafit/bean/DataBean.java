package br.com.gerafit.bean;

import java.io.Serializable;
import java.util.List;

import javax.ejb.EJB;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Named;

import br.com.gerafit.domain.Aluno.Sexo;
import br.com.gerafit.domain.Aluno.Situacao;
import br.com.gerafit.domain.Estado;
import br.com.gerafit.service.DataService;

//A funcao desta classe e carregar os dados geralmemnte especificas a caixas de selecoes,como sexo, situacao e estados.

@Named//
@ApplicationScoped
public class DataBean implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	@EJB
	private DataService dataService;

	//Metodo que retornara a lista de sexos.
	public Sexo[] getSexos() {
		return dataService.getSexos();
	}
	//Metodo que retornara a lista de situacoes.
	public Situacao[] getSituacoes() {
		return dataService.getSituacoes();
	}

	//Metodo que retornara a lista de estados.
	public List<Estado> getEstados() {
		
		return dataService.listEstados();
		
	}

}
