package br.com.gerafit.acesso;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import br.com.gerafit.domain.Aluno;



@Entity//Aqui falo que estado sera uma entidade ou tabela do banco de dados.
@Table(name= "ENTRADAS_SAIDAS")//Aqui faco o mapeamento da classe para a tabela.
public class Acesso implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	//atributos
	@Id
	@GeneratedValue(strategy= GenerationType.IDENTITY)//Aqui falo que o ID sera sequencial e automatico.
	@Column(name= "ID", nullable= false)//Aqui defino de como tem que ser no banco
	private Integer id;
	
	@ManyToOne//Aqui falo que o aluno e muitos pra um e vice versa.
	@JoinColumn(name= "ALUNO_ID", nullable= false)//Aqui vou unir as colunas.
	private Aluno aluno;
	
	@Column(name= "ENTRADA", nullable= false)//Aqui defino de como tem que ser no banco
	private LocalDateTime entrada;
	
	@Column(name= "SAIDA", nullable= true)//Aqui defino de como tem que ser no banco
	private LocalDateTime saida;
	
	//getters e setters
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Aluno getAluno() {
		return aluno;
	}
	public void setAluno(Aluno aluno) {
		this.aluno = aluno;
	}
	public LocalDateTime getEntrada() {
		return entrada;
	}
	public void setEntrada(LocalDateTime entrada) {
		this.entrada = entrada;
	}
	public LocalDateTime getSaida() {
		return saida;
	}
	public void setSaida(LocalDateTime saida) {
		this.saida = saida;
	}
	
	//metodo to string
	@Override
	public String toString() {
		return "Acesso [id=" + id + ", aluno=" + aluno + ", entrada=" + entrada + ", saida=" + saida + "]";
	}
	
	//hascode and equals, para que dois objetos possam ser comparados pelo seu conteudo, e nao pela memoria.
	@Override
	public int hashCode() {
		return Objects.hash(id);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Acesso other = (Acesso) obj;
		return Objects.equals(id, other.id);
	}
	
}
