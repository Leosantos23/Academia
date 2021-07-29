package br.com.gerafit.domain;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.Year;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import br.com.gerafit.acesso.Acesso;
import br.com.gerafit.domain.Aluno.Situacao;
import br.com.gerafit.util.StringUtils;

//Classe de repositorio de dados.

@Stateless // EJB.
public class AlunoRepository {

	@PersistenceContext
	private EntityManager em;// Crio um EntityManager.

	// Criar.
	public void store(Aluno aluno) {
		em.persist(aluno);
	}

	// Atualizar.
	public void update(Aluno aluno) {
		em.merge(aluno);
	}

	// Metodo para buscar por matricula.
	public Aluno findByMatricula(String matricula) {
		return em.find(Aluno.class, matricula);
	}

	// Delete
	public void delete(String matricula) {
		// Para trabalhar com exclusao, primeiro carrego o objeto, para depois excluir.
		Aluno aluno = findByMatricula(matricula);

		// Verifica se o aluno existe no banco, senao retorna null.
		if (aluno != null) {
			em.remove(aluno);
		}

	}

	// Metodo para tratamento do numero da matricula
	public String getMaxMatriculaAno() {// Pega a maior matricula do ano.
		// Executo o JPQL
		return em.createQuery("SELECT MAX(a.matricula) FROM Aluno a WHERE a.matricula LIKE :ano", String.class)

				.setParameter("ano", Year.now() + "%").getSingleResult();// Retorna diretamente somente o que eu quero.
	}

	// Metodo lista de alunos
	public List<Aluno> listAlunos(String matricula, String nome, Integer rg, Integer telefone) {

		// Crio a string de jpql
		StringBuilder jpql = new StringBuilder("SELECT a FROM Aluno a WHERE ");

		// Condicoes
		if (!StringUtils.isEmpty(matricula)) {
			jpql.append("a.matricula = :matricula AND ");
		}

		if (!StringUtils.isEmpty(nome)) {
			jpql.append("a.nome LIKE :nome AND ");
		}

		if (rg != null) {
			jpql.append("a.rg = :rg AND ");
		}

		if (telefone != null) {
			jpql.append("(a.telefone.numeroCelular LIKE :celular OR a.telefone.numeroFixo LIKE :fixo) AND ");
		}

		/*
		 * Isso e um truque muito usado em querys montadas dinamicamente com base
		 * explicitamente de pesquisas, com tantos AND , no final acaba sobrando um, ou
		 * se faz uma logica para remover o AND, ou se concatena com 1=1, Pois o mesmo �
		 * uma condicao sempre VERDADEIRA.
		 */
		jpql.append("1 = 1");

		// Aqui crio a query, lembrando que o TOSTRING, retorna a string do
		// stringbuilder.
		TypedQuery<Aluno> q = em.createQuery(jpql.toString(), Aluno.class);

		// Nesse if seto os parametros.
		if (!StringUtils.isEmpty(matricula)) {
			q.setParameter("matricula", matricula);
		}

		if (!StringUtils.isEmpty(nome)) {
			q.setParameter("nome", "%" + nome + "%");
		}

		if (rg != null) {
			q.setParameter("rg", rg);
		}

		if (telefone != null) {
			q.setParameter("celular", telefone);
			q.setParameter("fixo", telefone);
		}

		return q.getResultList();
	}

	// Busca por RG
	public Aluno findByRG(Integer rg) {
		try {
			return em.createQuery("SELECT a FROM Aluno a WHERE a.rg = :rg", Aluno.class).setParameter("rg", rg)
					.getSingleResult();
		} catch (NoResultException e) {
			return null;
		}
	}

	// Metodo que lista as situacoes dos alunos
	public List<Aluno> listSituacoesAlunos(Situacao situacao) {
		return em.createQuery("SELECT a FROM Aluno a WHERE a.situacao = :situacao ORDER BY a.nome", Aluno.class)
				.setParameter("situacao", situacao).getResultList();
	}

	// Metodo que vai fazer a busca no banco de dados
	public List<Acesso> listAcessosAlunos(String matricula, LocalDate dataInicial, LocalDate dataFinal) {

		// Abaixo terei de montar a query JPQL dinamicamente
		StringBuilder jpql = new StringBuilder("SELECT a FROM Acesso a WHERE ");

		// Ver se a matricula foi fornecida, verifica se a matricula nao esta vazia
		if (!StringUtils.isEmpty(matricula)) {
			jpql.append("a.aluno.matricula = :matricula AND ");
		}
		// Verifica se a data inicial e diferente de nula
		if (dataInicial != null) {
			jpql.append("a.entrada >= :entradaInicio AND ");
		}
		// Verifica se a data final e diferente de nula
		if (dataFinal != null) {
			jpql.append("a.saida <= :saidaFim AND ");
		}
		// Usando a estrategia do 1=1, e ordenando por ordem de data de entrada
		jpql.append("1 = 1 ORDER BY a.entrada");

		// Crio minha typed query
		TypedQuery<Acesso> q = em.createQuery(jpql.toString(), Acesso.class);

		// Setar os parametros com estas condicoes
		if (!StringUtils.isEmpty(matricula)) {
			q.setParameter("matricula", matricula);
		}

		/*
		 * Lembrando que o local date time pega a data e o horario, ja a local time pega
		 * apenas o horario, e terei de colocar os parametros de horas
		 */

		// Comparar as datas e horarios
		if (dataInicial != null) {
			LocalDateTime ldt = LocalDateTime.of(dataInicial, LocalTime.of(0, 0, 0));
			q.setParameter("entradaInicio", ldt);
		}

		if (dataFinal != null) {
			LocalDateTime ldt = LocalDateTime.of(dataFinal, LocalTime.of(23, 59, 59));
			q.setParameter("saidaFim", ldt);
		}

		return q.getResultList();
	}

}
