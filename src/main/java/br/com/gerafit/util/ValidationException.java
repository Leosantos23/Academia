package br.com.gerafit.util;

import javax.ejb.ApplicationException;

@ApplicationException//Herda da classe exception, do pacote java lang
//Tambem aplico de que entrara no catch de tratamento de pesquisa de aluno.
public class ValidationException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public ValidationException() {
	}

	public ValidationException(String message) {
		super(message);
	}

	public ValidationException(Throwable cause) {
		super(cause);
	}

	public ValidationException(String message, Throwable cause) {
		super(message, cause);
	}

	public ValidationException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}
}
