package br.com.gerafit.bean;

import java.io.Serializable;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;

import br.com.gerafit.domain.Aluno;
import br.com.gerafit.service.AlunoService;
import br.com.gerafit.util.StringUtils;

@Named // Para que esse bean seja encontrado.
@RequestScoped // Significa que so vai durante o fim da requisicao.
public class AlunoBean implements Serializable {

	private static final long serialVersionUID = 1L;

	@EJB
	private AlunoService alunoService;

	// Para tratamento da mensagem de erro ou sucesso na tela.
	@Inject
	private FacesContext facesContext;

	private Aluno aluno = new Aluno();

	private String matricula;

	private String titulo = "Cadastrar aluno";// Para tratamento do metodo carregar.

	// Metodo carregar.
	public void carregar() {

		if (!StringUtils.isEmpty(matricula)) {
			aluno = alunoService.findByMatricula(matricula);
			titulo = "Alterar aluno";// Para alterar o titulo na pagina, inves de cadastrar aluno.
		}

	}

	// Getters e setters de matricula.
	public String getMatricula() {
		return matricula;
	}

	public void setMatricula(String matricula) {
		this.matricula = matricula;
	}

	// getters e setters de aluno.
	public Aluno getAluno() {
		return aluno;
	}

	public void setAluno(Aluno aluno) {
		this.aluno = aluno;
	}

	// Getters ew setters de Titulo.
	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public String salvar() {

		alunoService.createOrUpdate(aluno);
		facesContext.addMessage(null, new FacesMessage("Dados salvos com sucesso!"));
		alunoService.createOrUpdate(aluno);
		return null;
	}

}
