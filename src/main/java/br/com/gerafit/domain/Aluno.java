package br.com.gerafit.domain;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.Year;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.persistence.Table;

import br.com.gerafit.util.StringUtils;

@Entity // Aqui falo que estado sera uma entidade ou tabela do banco de dados.
@Table(name = "ALUNO") // Aqui faco o mapeamento da classe para a tabela.
public class Aluno implements Serializable {

	private static final long serialVersionUID = 1L;

	// enums
	public enum Sexo {
		Masculino, Feminino;
	}

	public enum Situacao {
		Ativo, Inativo, Pendente;
	}

	// atributos.
	@Id
	@Column(name = "ID", nullable = false, length = 8)
	private String matricula;

	@Column(name = "NOME", nullable = false, length = 64)
	private String nome;

	@Enumerated
	@Column(name = "SEXO", nullable = false, length = 1)
	private Sexo sexo;

	@Column(name = "RG", nullable = false, length = 10)
	private Integer rg;

	@Column(name = "NASCIMENTO", nullable = false)
	private LocalDate dataNascimento;

	@Enumerated
	@Column(name = "SITUACAO", nullable = false, length = 1)
	private Situacao situacao;

	@Column(name = "EMAIL", nullable = true, length = 64)
	private String email;

	@Embedded
	private Endereco endereco = new Endereco();

	@Embedded
	private Telefone telefone = new Telefone();

	// getters e setters.
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

	public Sexo getSexo() {
		return sexo;
	}

	public void setSexo(Sexo sexo) {
		this.sexo = sexo;
	}

	public Integer getRg() {
		return rg;
	}

	public void setRg(Integer rg) {
		this.rg = rg;
	}

	public LocalDate getDataNascimento() {
		return dataNascimento;
	}

	public void setDataNascimento(LocalDate dataNascimento) {
		this.dataNascimento = dataNascimento;
	}

	public Situacao getSituacao() {
		return situacao;
	}

	public void setSituacao(Situacao situacao) {
		this.situacao = situacao;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Endereco getEndereco() {
		return endereco;
	}

	public void setEndereco(Endereco endereco) {
		this.endereco = endereco;
	}

	public Telefone getTelefone() {
		return telefone;
	}

	public void setTelefone(Telefone telefone) {
		this.telefone = telefone;
	}

	// metodo to string, e da classe OBJECT, que e uma superclasse de todas as
	// classes do java.
	@Override
	public String toString() {
		return "Aluno [matricula=" + matricula + ", nome=" + nome + ", sexo=" + sexo + ", rg=" + rg
				+ ", dataNascimento=" + dataNascimento + ", situacao=" + situacao + ", email=" + email + ", endereco="
				+ endereco + ", telefone=" + telefone + "]";
	}

	// hascode and equals, para que dois objetos possam ser comparados pelo seu
	// conteudo, e nao pela memoria.
	@Override
	public int hashCode() {
		return Objects.hash(matricula);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Aluno other = (Aluno) obj;
		return Objects.equals(matricula, other.matricula);
	}

	// Metodo de gerar a matricula, que tem uma logica especifica.

	public void gerarMatricula(String maxMatricula) {

		Year year = Year.now();

		// Tratar a situacao.
		if (maxMatricula == null) {

			maxMatricula = year + StringUtils.leftZeroes(0, 4);
		}
		// Tratamento do sequencial, convertendo a string em inteiro.
		int sequencial = Integer.parseInt(maxMatricula.substring(4));
		sequencial++;

		// Montar o novo numero de matricula.
		this.matricula = year + StringUtils.leftZeroes(sequencial, 4);

	}
}
