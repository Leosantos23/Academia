package br.com.gerafit.service;

import java.time.LocalDate;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;

import br.com.gerafit.acesso.Acesso;
import br.com.gerafit.domain.Aluno;
import br.com.gerafit.domain.Aluno.Situacao;
import br.com.gerafit.domain.AlunoRepository;
import br.com.gerafit.util.StringUtils;
import br.com.gerafit.util.Validation;
import br.com.gerafit.util.ValidationException;

//Classe que o alunoBean vai interagir.
@Stateless // EJB.
public class AlunoService {

	@EJB
	private AlunoRepository alunoRepository;

	// Metodo criar ou atualizar.
	public void createOrUpdate(Aluno aluno) {
		if (StringUtils.isEmpty(aluno.getMatricula())) {

			create(aluno);
		} else {

			update(aluno);
		}
	}

	// Metodos privados para usar apenas aqui nesta classe.
	// Criar.
	private void create(Aluno aluno) {
		Validation.assertNotEmpty(aluno);

		String maxMatricula = alunoRepository.getMaxMatriculaAno();

		aluno.gerarMatricula(maxMatricula);
		alunoRepository.store(aluno);
	}

	// Atualizar.
	public void update(Aluno aluno) {

		Validation.assertNotEmpty(aluno);
		Validation.assertNotEmpty(aluno.getMatricula());

		alunoRepository.update(aluno);

	}

	// Busca por matricula.
	public Aluno findByMatricula(String matricula) {
		return alunoRepository.findByMatricula(matricula);
	}

	// Lista de alunos para funcionar a pesquisa.
	public List<Aluno> listAlunos(String matricula, String nome, Integer rg, Integer telefone) {

		/*
		 * Pequena validacao, pois nao quero permitir que nenhum criterio seja
		 * estabelecido, sera obrigatorio colocar algum parametro de pesquisa.
		 */
		if (StringUtils.isEmpty(matricula) && StringUtils.isEmpty(nome) && rg == null && telefone == null) {
			throw new ValidationException("Pelo menos um critério de pesquisa deve ser fornecido!");
		}

		return alunoRepository.listAlunos(matricula, nome, rg, telefone);
	}

	// Deletar.
	public void delete(String matricula) {

		alunoRepository.delete(matricula);

	}

	// Metodo que pegara as situacoes.
	public List<Aluno> listSituacoesAlunos(Situacao situacao) {

		// Validacao para garantir que a situacao nao esta nula.
		Validation.assertNotEmpty(situacao);
		return alunoRepository.listSituacoesAlunos(situacao);
	}

	// Metodo que recebera a chamada do aluno bean, um metodo que retornara uma lista de alunos.
	public List<Acesso> listAcessosAlunos(String matricula, LocalDate dataInicial, LocalDate dataFinal) {

		if (StringUtils.isEmpty(matricula) && dataInicial == null && dataFinal == null) {
			throw new ValidationException("Pelo menos um critério de pesquisa deve ser fornecido!");
		}

		return alunoRepository.listAcessosAlunos(matricula, dataInicial, dataFinal);
	}

}
