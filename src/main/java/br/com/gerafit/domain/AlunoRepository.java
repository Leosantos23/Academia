package br.com.gerafit.domain;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

//Classe de repositorio de dados

@Stateless//EJB
public class AlunoRepository {
	
	@PersistenceContext
	private EntityManager em;//Crio um EntityManager
	
	//Criar
	public void store(Aluno aluno) {
		em.persist(aluno);
	}
	//Atualizar
	public void update(Aluno aluno) {
		em.merge(aluno);
	}
	
	//Metodo para buscar por matricula
	public Aluno findByMatricula(String matricula) {
		return em.find(Aluno.class, matricula);
	}
	
	//Delete
	public void delete (String matricula) {
		//Para trabalhar com exclusao, primeiro carrego o objeto, para depois excluir.
		Aluno aluno = findByMatricula(matricula);
		
		//Verifica se o aluno existe no banco, senao retorna null.
		if (aluno != null) {
			em.remove(aluno);
		}

	}
	
	


	
	


}
