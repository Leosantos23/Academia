package br.com.gerafit.util;

public class StringUtils {
	
	//Metodo verificador se uma string esta vazia
	public static boolean isEmpty(String s) {
		if (s == null) {
			return true;
		}
		
		return s.trim().length() == 0;//O metodo trim corta os espaços em branco do inicio ao final, e o length devolve a string que sobrou
	}
	
	//Metodo que concatena os zeros a esquerda.
	public static String leftZeroes(int value, int finalSize) {
		return String.format("%0" + finalSize + "d", value);
	}

	//Main para teste.
	public static void main(String[] args) {
		String str = "  a  ";
		boolean b= StringUtils.isEmpty(str);
		System.out.println(b);
		
		int v = 12345;
		System.out.println(StringUtils.leftZeroes(v, 8));

	}
	
	
	
	


}
