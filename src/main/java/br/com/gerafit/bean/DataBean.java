package br.com.gerafit.bean;

import java.io.Serializable;
import java.util.List;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Named;

import br.com.gerafit.domain.Aluno.Sexo;
import br.com.gerafit.domain.Aluno.Situacao;
import br.com.gerafit.domain.Estado;



@Named
@ApplicationScoped
public class DataBean implements Serializable {
	
	private static final long serialVersionUID = 1L;

	public Sexo[] getSexos() {
		return Sexo.values();
	}
	
	public Situacao[] getSituacoes(){
		
		return Situacao.values();
		
	}
	
	public List<Estado> getEstados() {
		//return dataService.listEstados();
		return null;
	}

}
