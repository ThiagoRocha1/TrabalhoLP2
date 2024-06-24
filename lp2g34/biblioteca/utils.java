package lp2g34.biblioteca;

import java.util.*;

class ValidaCPF{
	private static boolean isSeparadorDigitoValido(char separador){
		switch(separador){
			case '/':
					return true;
			case ' ':
					return true;
			case '-':
					return true;
			default:
					return false;
		}
	}

	private static boolean isCpfComMesmoDigito(String CPF){
		if(CPF.equals("00000000000") ||
			CPF.equals("11111111111") ||
			CPF.equals("22222222222") || CPF.equals("33333333333") ||
			CPF.equals("44444444444") || CPF.equals("55555555555") ||
			CPF.equals("66666666666") || CPF.equals("77777777777") ||
			CPF.equals("88888888888") || CPF.equals("99999999999")) 
			return true;
		else 
			return false;
	}

	private static boolean isOnlyNumbersInCPF(String CPF){
		for(int i=0 ; i< CPF.length(); i++){
			if(!Character.isDigit(CPF.charAt(i))) return false;
		}
		return true;
	}

	public static boolean isCPF(String CPF) {
		//Verificar se eh um formato valido
		//CPF com separador
		if(CPF.length() == 14){
			//Separador sendo todos pontos
			if((CPF.charAt(3) == '.') && (CPF.charAt(7) == '.') && isSeparadorDigitoValido(CPF.charAt(11))){
				CPF = CPF.replace(".", "");
				//Concatenar o digito de maneira correta
				CPF = CPF.replace("-", "");
				CPF = CPF.replace("/", "");
				CPF = CPF.replace(" ", "");
				if (isCpfComMesmoDigito(CPF)) return false;
				if(!isOnlyNumbersInCPF(CPF)) return false;
			}
			//Separador sendo todos espacos em branco
			else if(CPF.charAt(3) == ' ' && CPF.charAt(7) == ' ' && CPF.charAt(11) == ' '){
				CPF = CPF.replace(" ", "");
				if (isCpfComMesmoDigito(CPF)) return false;
				if(!isOnlyNumbersInCPF(CPF)) return false;
			}
			else{
				return false; //Separadores estao em um formato invalido
			}
		}else{
			if( isCpfComMesmoDigito(CPF) || (CPF.length() != 11)) return false; //Numero de CPF sem separador ou com numero faltando ou caracter em excesso
			if(!isOnlyNumbersInCPF(CPF)) return false;
		}
		
		char dig10, dig11;
		int sm, i, r, num, peso;

		// "try" - protege o codigo para eventuais erros de conversao de tipo (int)
		try {
		// Calculo do 1o. Digito Verificador
		sm = 0;
		peso = 10;
		for (i=0; i<9; i++) {
			// converte o i-esimo caractere do CPF em um numero:
			// por exemplo, transforma o caractere "0" no inteiro 0
			// (48 eh a posicao de "0" na tabela ASCII)
			num = (int)(CPF.charAt(i) - 48);
			sm = sm + (num * peso);
			peso = peso - 1;
		}

		r = 11 - (sm % 11);
		if ((r == 10) || (r == 11))
			dig10 = '0';
		else dig10 = (char)(r + 48); // converte no respectivo caractere numerico

		// Calculo do 2o. Digito Verificador
		sm = 0;
		peso = 11;
		for(i=0; i<10; i++) {
			num = (int)(CPF.charAt(i) - 48);
			sm = sm + (num * peso);
			peso = peso - 1;
		}

		r = 11 - (sm % 11);
		if ((r == 10) || (r == 11))
			dig11 = '0';
		else dig11 = (char)(r + 48);

		// Verifica se os digitos calculados conferem com os digitos informados.
		if ((dig10 == CPF.charAt(9)) && (dig11 == CPF.charAt(10)))
			return(true);
		else return(false);
			} catch (InputMismatchException erro) {
			return(false);
		}
	}

	public static String imprimeCPF(String CPF) {
		if((CPF.charAt(3) == '.') && (CPF.charAt(7) == '.') && isSeparadorDigitoValido(CPF.charAt(11))){
			CPF = CPF.replace(".", "");
			//Concatenar o digito de maneira correta
			CPF = CPF.replace("-", "");
			CPF = CPF.replace("/", "");
			CPF = CPF.replace(" ", "");
		}
		//Separador sendo todos espacos em branco
		else if(CPF.charAt(3) == ' ' && CPF.charAt(7) == ' ' && CPF.charAt(11) == ' '){
			CPF = CPF.replace(" ", "");
		}

		return(CPF.substring(0, 3) + "." + CPF.substring(3, 6) + "." +
		CPF.substring(6, 9) + "-" + CPF.substring(9, 11));
	}

	public static long toLong(String CPF){
		if(ValidaCPF.isCPF(CPF)) {
			if((CPF.charAt(3) == '.') && (CPF.charAt(7) == '.') && isSeparadorDigitoValido(CPF.charAt(11))){
				CPF = CPF.replace(".", "");
				//Concatenar o digito de maneira correta
				CPF = CPF.replace("-", "");
				CPF = CPF.replace("/", "");
				CPF = CPF.replace(" ", "");
			}
			//Separador sendo todos espacos em branco
			else if(CPF.charAt(3) == ' ' && CPF.charAt(7) == ' ' && CPF.charAt(11) == ' '){
				CPF = CPF.replace(" ", "");
			}
			return Long.parseLong(CPF);
		}
		return -1;
	}
}

class FormataObjetos{
	public static String formataData (GregorianCalendar data) {
		int ano = data.get(GregorianCalendar.YEAR);
		int mes = data.get(GregorianCalendar.MONTH)+1;
		int dia = data.get(GregorianCalendar.DAY_OF_MONTH);
		return Integer.toString(ano) + "/" +  String.format("%02d",mes) + "/" +  String.format("%02d", dia);
	}
}
