package br.com.gerafit.service;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import br.com.gerafit.acesso.Acesso;
import br.com.gerafit.acesso.TipoAcesso;
import br.com.gerafit.domain.AcessoRepository;
import br.com.gerafit.domain.Aluno;
import br.com.gerafit.domain.AlunoRepository;
import br.com.gerafit.util.StringUtils;
import br.com.gerafit.util.ValidationException;

@Stateless
public class AcessoService {

	@EJB
	private AcessoRepository acessoRepository;

	@EJB
	private AlunoRepository alunoRepository;

	// Metodo de registrar acesso, que tera como dados de entrada o RG ou a
	// MATRICULA.
	public TipoAcesso registrarAcesso(String matricula, Integer rg) {

		// Validar os dados de entradas.
		if (StringUtils.isEmpty(matricula) && rg == null) {
			throw new ValidationException("É preciso fornecer a MATRÍCULA ou o RG do aluno!");
		}

		Aluno aluno;// Em ambos os casos abaixo, eu preciso armazenar isso em uma variavel aluno.

		if (StringUtils.isEmpty(matricula)) {
			aluno = alunoRepository.findByRG(rg);
		} else {
			aluno = alunoRepository.findByMatricula(matricula);
		}

		if (aluno == null) {
			throw new ValidationException("O aluno não foi encontrado!");
		}

		// Pego o ultimo acesso registrado do aluno.
		Acesso ultimoAcesso = acessoRepository.findUltimoAcesso(aluno);
		// Armazeno em uma variavel.
		TipoAcesso tipoAcesso;

		// Verifica se teve acesso, senao ira registrar um novo acesso.
		if (ultimoAcesso == null || ultimoAcesso.isEntradaSaidaPreenchidas()) {
			ultimoAcesso = new Acesso();
			ultimoAcesso.setAluno(aluno);
			tipoAcesso = ultimoAcesso.registrarAcesso();
			acessoRepository.store(ultimoAcesso);

			// Apenas atualiza o acesso,pois ja tem registro de entrada no sistema.
		} else {
			tipoAcesso = ultimoAcesso.registrarAcesso();
		}

		return tipoAcesso;

	}

}
