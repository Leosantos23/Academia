package br.com.gerafit.domain;

import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

//Classe que acessara o banco de dados.

@Stateless // EJB.
public class EstadoRepository {

	@PersistenceContext // Aqui o cdi entra em acao.
	private EntityManager em;// Porta de entrada para a JPA.

	// Metodo que retornara a lista de estados.
	public List<Estado> listEstados() {

		// createQuery cria um select em JPQL, que lida com objetos e seus atributos.
		return em.createQuery("SELECT e FROM Estado e ORDER BY e.nome", Estado.class).getResultList();
	}
}
