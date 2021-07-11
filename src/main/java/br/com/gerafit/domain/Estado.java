package br.com.gerafit.domain;

import java.io.Serializable;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity//Aqui falo que estado sera uma entidade ou tabela do banco de dados.
@Table(name= "ESTADO")//Aqui faco o mapeamento da classe para a tabela.
public class Estado implements Serializable {
	
	private static final long serialVersionUID = 1L;

	//atributos
	@Id//Aqui defino que sigla será o ID
	@Column(name= "SIGLA", nullable= false, length= 2)//Aqui defino de como tem que ser no banco
	private String sigla;
	
	@Column(name= "NOME", nullable= false, length= 30)//Aqui defino de como tem que ser no banco
	private String nome;
	
	//getters e setters
	public String getSigla() {
		return sigla;
	}
	public void setSigla(String sigla) {
		this.sigla = sigla;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	
	//metodo to string, e da classe OBJECT, que e uma superclasse de todas as classes do java
	@Override
	public String toString() {
		return "Estado [sigla=" + sigla + ", nome=" + nome + "]";
	}
	
	//hascode and equals, para que dois objetos possam ser comparados pelo seu conteudo, e nao pela memoria.
	@Override
	public int hashCode() {
		return Objects.hash(sigla);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Estado other = (Estado) obj;
		return Objects.equals(sigla, other.sigla);
	}
	
}
