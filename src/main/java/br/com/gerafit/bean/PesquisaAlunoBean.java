package br.com.gerafit.bean;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.faces.annotation.RequestParameterMap;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;

import br.com.gerafit.domain.Aluno;
import br.com.gerafit.service.AlunoService;
import br.com.gerafit.util.ValidationException;

@Named
@SessionScoped // Vai garantir que o bean vai ficar ativo enquanto o navegador do usuario
				// estiver aberto.
public class PesquisaAlunoBean implements Serializable {

	private static final long serialVersionUID = 1L;

	@EJB
	private AlunoService alunoService;

	@Inject
	private FacesContext facesContext;

	// Para auxiliar no metodo clear
	@Inject
	@RequestParameterMap
	private Map<String, String> requestParamsMap;

	// atributos.
	private String matricula;
	private String nome;
	private Integer rg;
	private Integer telefone;

	// Getters e setters.
	public String getMatricula() {
		return matricula;
	}

	public void setMatricula(String matricula) {
		this.matricula = matricula;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public Integer getRg() {
		return rg;
	}

	public void setRg(Integer rg) {
		this.rg = rg;
	}

	public Integer getTelefone() {
		return telefone;
	}

	public void setTelefone(Integer telefone) {
		this.telefone = telefone;
	}

	public List<Aluno> getAlunos() {
		return alunos;
	}

	// Lista de alunos para o metodo pesquisar.
	private List<Aluno> alunos;

	// Metodo pesquisar.
	public String pesquisar() {
		try {
			alunos = alunoService.listAlunos(matricula, nome, rg, telefone);
		} catch (ValidationException e) {
			facesContext.addMessage(null, new FacesMessage(e.getMessage()));
		}
		return null;
	}

	// Metodo excluir.
	public String excluir(String matricula) {
		alunoService.delete(matricula);
		return pesquisar();// Fara uma nova pesquisa apos a exclusao.
	}

	// Metodo para checar e limpar o formulario de pesquisa de aluno.
	public void check() {
		String clear = requestParamsMap.get("clear");

		if (clear != null && Boolean.valueOf(clear)) {
			
			// Faz o reset do formulario.
			matricula = null;
			nome = null;
			rg = null;
			telefone = null;
			alunos = null;
		}
	}

}
