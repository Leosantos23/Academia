package br.com.gerafit.bean;

import java.io.Serializable;

import java.time.LocalDate;
import java.util.List;

import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.inject.Named;

import br.com.gerafit.acesso.Acesso;
import br.com.gerafit.service.AlunoService;

@Named
@RequestScoped
public class RelatorioDeAcessoBean implements Serializable {

	private static final long serialVersionUID = 1L;

	@EJB
	private AlunoService alunoService;

	private String matricula;
	private LocalDate dataInicial;
	private LocalDate dataFinal;

	// O resultado sera uma lista de acessos
	private List<Acesso> acessos;

	// Getters e setters
	public String getMatricula() {
		return matricula;
	}

	public void setMatricula(String matricula) {
		this.matricula = matricula;
	}

	public LocalDate getDataInicial() {
		return dataInicial;
	}

	public void setDataInicial(LocalDate dataInicial) {
		this.dataInicial = dataInicial;
	}

	public LocalDate getDataFinal() {
		return dataFinal;
	}

	public void setDataFinal(LocalDate dataFinal) {
		this.dataFinal = dataFinal;
	}

	public List<Acesso> getAcessos() {
		return acessos;
	}

	// Metodo para gerar o relatorio de acessos
	public String gerarRelatorio() {

		// Aqui ficara a logica futura
		return null;
	}

}
