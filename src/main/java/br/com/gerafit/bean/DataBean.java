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



@Named
@ApplicationScoped
public class DataBean implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	@EJB
	private DataService dataService;


	public Sexo[] getSexos() {
		return dataService.getSexos();
	}
	
	public Situacao[] getSituacoes() {
		return dataService.getSituacoes();
	}

	
	public List<Estado> getEstados() {
		
		return dataService.listEstados();
		
	}

}
