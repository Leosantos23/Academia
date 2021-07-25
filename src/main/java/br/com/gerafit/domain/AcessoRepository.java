package br.com.gerafit.domain;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;

import br.com.gerafit.acesso.Acesso;

//Classe responsavel em repositorios de acessos.

@Stateless
public class AcessoRepository {

	@PersistenceContext
	private EntityManager em;

	// Metodo que registra o ultimo acesso do aluno
	public Acesso findUltimoAcesso(Aluno aluno) {
		try {
			return em
					.createQuery("SELECT a FROM Acesso a WHERE a.aluno.matricula = :matricula ORDER BY a.id DESC",
							Acesso.class)
					.setParameter("matricula", aluno.getMatricula()).setMaxResults(1).getSingleResult();

		} catch (NoResultException e) {
			return null;
		}
	}

	// Metodo que manda gravar no banco de dados
	public void store(Acesso acesso) {
		em.persist(acesso);
	}

}
