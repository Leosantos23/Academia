package br.com.gerafit.util;

public class Validation {

	// Metodo validador.
	public static void assertNotEmpty(Object obj) {
		if (obj instanceof String) {
			String s = (String) obj;// Operacao de casting.
			if (StringUtils.isEmpty(s)) {// Chamei o metodo string utils, verificando se ele esta vazio.
				throw new ValidationException("O texto não pode ser nulo!");
			}
		}

		if (obj == null) {
			throw new ValidationException("O valor não pode ser nulo!");
		}
	}

}
