package br.com.gerafit.bean;

import java.io.Serializable;
import javax.enterprise.context.RequestScoped;
import javax.inject.Named;

import br.com.gerafit.domain.Aluno;



@Named//Para que esse bean seja encontrado.
@RequestScoped//Significa que so vai durante o fim da requisicao.
public class AlunoBean implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	private Aluno aluno = new Aluno();
	
	//getters e setters de aluno
	public Aluno getAluno() {
		return aluno;
	}
	
	public void setAluno(Aluno aluno) {
		this.aluno = aluno;
	}
	
	public String gravar() {
		//alunoService.createOrUpdate(aluno);
		//facesContext.addMessage(null, new FacesMessage("Dados gravados com sucesso"));
		System.out.println("ALUNO ==>" + aluno);
		return null;
	}



}
