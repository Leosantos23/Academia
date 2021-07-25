package br.com.gerafit.bean;

import java.io.Serializable;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import javax.faces.application.FacesMessage;

import br.com.gerafit.acesso.TipoAcesso;
import br.com.gerafit.service.AcessoService;
import br.com.gerafit.util.ValidationException;

@Named
@RequestScoped
public class ControleAcessoBean implements Serializable {

	private static final long serialVersionUID = 1L;

	@EJB
	private AcessoService acessoService;

	@Inject
	private FacesContext facesContext;

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

	// Metodo atribuido ao botao Registrar acesso
	public String registrarAcesso() {

		TipoAcesso tipoAcesso;
		
		try {
			tipoAcesso = acessoService.registrarAcesso(matricula, rg);
		} catch (ValidationException e) {
			facesContext.addMessage(null, new FacesMessage(e.getMessage()));
			return null;
		}

		String msg;
		if (tipoAcesso == TipoAcesso.Entrada) {
			msg = "ENTRADA registrada!";
		} else if (tipoAcesso == TipoAcesso.Saida) {
			msg = "SAÍDA registrada!";
		} else {
			msg = "Dados de registro de acesso inconsistentes!";
		}

		facesContext.addMessage(null, new FacesMessage(msg));
		return null;

	}
}
