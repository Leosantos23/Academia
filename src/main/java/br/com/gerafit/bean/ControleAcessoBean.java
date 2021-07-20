package br.com.gerafit.bean;

import java.io.Serializable;

import javax.enterprise.context.RequestScoped;
import javax.inject.Named;

@Named
@RequestScoped
public class ControleAcessoBean implements Serializable {

	private static final long serialVersionUID = 1L;

	private String matricula;
	private Integer rg;

	// Getters e setters
	public String getMatricula() {
		return matricula;
	}

	public void setMatricula(String matricula) {
		this.matricula = matricula;
	}

	public Integer getRg() {
		return rg;
	}

	public void setRg(Integer rg) {
		this.rg = rg;
	}
	
	//Metodo atribuido ao botao Registrar acesso
	public String registrarAcesso() {
		
		return null;
	}

}
