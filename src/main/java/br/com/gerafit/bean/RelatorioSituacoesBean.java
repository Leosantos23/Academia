package br.com.gerafit.bean;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.faces.annotation.RequestParameterMap;
import javax.inject.Inject;
import javax.inject.Named;

import br.com.gerafit.domain.Aluno;
import br.com.gerafit.domain.Aluno.Situacao;
import br.com.gerafit.service.AlunoService;

@Named
@SessionScoped
public class RelatorioSituacoesBean implements Serializable {

	private static final long serialVersionUID = 1L;

	@EJB
	private AlunoService alunoService;

	@Inject
	@RequestParameterMap
	private Map<String, String> requestParamsMap;

	private Situacao situacao;

	private List<Aluno> alunos;

	// Getters e setters.
	public Situacao getSituacao() {
		return situacao;
	}

	public void setSituacao(Situacao situacao) {
		this.situacao = situacao;
	}

	public List<Aluno> getAlunos() {
		return alunos;
	}

	public void setAlunos(List<Aluno> alunos) {
		this.alunos = alunos;
	}

	// Metodo de checar e limpar a tela relatorio de situacoes.
	public void check() {
		String clear = requestParamsMap.get("clear");

		if (clear != null && Boolean.valueOf(clear)) {
			situacao = null;
			alunos = null;
		}
	}

	// Metodo de gerar relatorio.
	public String gerarRelatorio() {
		alunos = alunoService.listSituacoesAlunos(situacao);
		return null;
	}

}
