package br.com.gerafit.service;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import br.com.gerafit.domain.Aluno;
import br.com.gerafit.domain.AlunoRepository;
import br.com.gerafit.util.StringUtils;
import br.com.gerafit.util.Validation;

//Classe que o alunoBean vai interagir.
@Stateless//EJB
public class AlunoService {
	
	@EJB
	private AlunoRepository alunoRepository;

	
	//Metodo criar ou atualizar
	public void createOrUpdate(Aluno aluno) {
		if (StringUtils.isEmpty(aluno.getMatricula())) {
			
			create(aluno);
			} else {
		
		update(aluno);
		}
	}
		
		//Metodos privados para usar apenas aqui nesta classe
	    //Criar
		private void create(Aluno aluno) {
			Validation.assertNotEmpty(aluno);
			
			String maxMatricula = alunoRepository.getMaxMatriculaAno();
			
			aluno.gerarMatricula(maxMatricula);
			alunoRepository.store(aluno);
		}
		
		//Atualizar
		public void update(Aluno aluno) {
			
			Validation.assertNotEmpty(aluno);
			Validation.assertNotEmpty(aluno.getMatricula());
			
			alunoRepository.update(aluno);
			
		}
		
		//Busca por matricula
		public Aluno findByMatricula(String matricula) {
			return alunoRepository.findByMatricula(matricula);
		}

		
			
	}


	


