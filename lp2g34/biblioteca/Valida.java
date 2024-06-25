package lp2g34.biblioteca;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.GregorianCalendar;
import java.util.*;

public class Valida {

	//Valida categoria
	public static String validaEntradaCategoria (String categoria,BufferedReader input) throws IOException{
		while(true){
			if(ValidacaoAuxiliares.isNome(categoria)) return categoria;
			else{
				System.out.println("Voce digitou uma categoria com numeros no meio, apenas caracteres nao numericos sao aceitos");
				String aux = input.readLine();
				if(ValidacaoAuxiliares.isNome(aux)){
					return aux;
				}
			} 
		}
	}

	//Valida Codigo
	public static String validaEntradaCodigo (String cod,BufferedReader input) throws IOException{
		while(true){
			if(!ValidacaoAuxiliares.isOnlyNumeric(cod)){
				System.out.println("Voce digitou um valor que nao eh apenas numerico. Por favor, digite um valor apenas numerico");
				String aux = input.readLine();
				if((ValidacaoAuxiliares.isOnlyNumeric(aux)))return aux;
			} 
			else{
				return cod;
			} 
		}
	}

    //Valida Nome
	public static String validaEntradaNome (String nome,BufferedReader input) throws IOException{
		while(true){
			if(ValidacaoAuxiliares.isNome(nome)) return nome;
			else{
				System.out.println("Voce digitou um nome invalido. Por favor, tente novamente com um nome valido");
				String aux = input.readLine();
				if(ValidacaoAuxiliares.isNome(aux)){
					return aux;
				}
			} 
		}
	}

	//Valida Sobrenome
	public static String validaEntradaSobreNome (String nome,BufferedReader input) throws IOException{
		while(true){
			if(ValidacaoAuxiliares.isNome(nome)) return nome;
			else{
				System.out.println("Voce digitou um sobrenome invalido. Por favor, tente novamente com um nome valido");
				String aux = input.readLine();
				if(ValidacaoAuxiliares.isNome(aux)){
					return aux;
				}
			} 
		}
	}

	//Valida entrada dia
	public static String validaEntradaDia (String dia,BufferedReader input) throws IOException{
		while(true){
			if(ValidaData.isDia(dia)) return dia;
			else{
				System.out.println("Voce digitou um dia invalido. Por favor, digite um dia no intervalo entre 1 e 31.");
				String aux = input.readLine();
				if(ValidaData.isDia(aux))return aux;
			} 
		}
	}

	//Validacao entrada mes
	public static String validaEntradaMes (String mes,BufferedReader input) throws IOException{
		while(true){
			int mesInt;
			String aux;
			if(!ValidacaoAuxiliares.isOnlyNumeric(mes)){
				mesInt = ValidaData.parseMesInt(mes);
			} 
			else{
				mesInt = Integer.parseInt(mes);
			}
			if(ValidaData.isMes(mesInt)) return mes;
			else{
				System.out.println("Voce digitou um mes invalido. Por favor, digite um mes no intervalo entre 1 e 12 ou com um nome valido.");
				aux = input.readLine();
				int auxInt;
				if(!ValidacaoAuxiliares.isOnlyNumeric(aux)){
					auxInt = ValidaData.parseMesInt(aux);
				} 
				else{
					auxInt = Integer.parseInt(aux);
				}
				if(ValidaData.isMes(auxInt))return Integer.toString(auxInt);
			} 
		}
	}

	//Validacao entrada ano
	public static String validaEntradaAno (String ano,BufferedReader input) throws IOException{
		while(true){
			if(ValidaData.isAno(ano)) return ano;
			else{
				System.out.println("Voce digitou um ano invalido. Por favor, digite um ano até 120 anos atrás da data corrente.");
				String aux = input.readLine();
				if(ValidaData.isAno(aux))return aux;
			} 
		}
	}

	//Validacao Data
	public static String validaEntradaData (String dia,String mes, String ano,BufferedReader input) throws IOException{
		while(true){
			if(ValidaData.isDataValida(dia, mes, ano)) return dia;
			else{
				System.out.println("Voce digitou um dia invalido. Por favor, digite um dia existente para o mes referente.");
				String aux = input.readLine();
				if(ValidaData.isDataValida(aux, mes, ano))return aux;
			} 
		}
	}

	//Validacao do cpf
	public static String validaEntradaCPF(String CPF,BufferedReader input) throws IOException{
		while(true){
			if(ValidaCPF.isCPF(CPF))return CPF;
			else{
				System.out.println("Voce digitou um CPF invalido. Por favor, digite um cpf em algum dos formatos validos abaixo ou verifique os numeros inseridos:");
				System.out.println("000.000.000-00");
				System.out.println("000.000.000/00");
				System.out.println("000.000.000 00");
				System.out.println("000 000 000 00");
				String aux = input.readLine();
				if(ValidaCPF.isCPF(aux))return aux;
			} 
		}
	}
}

class ValidaData{
	public static int parseMesInt(String mes){
		mes = mes.toLowerCase();
		switch (mes) {
			case "janeiro":
				return 1;
			case "fevereiro":
				return 2;
			case "marco":
				return 3;
			case "abril":
				return 4;
			case "maio":
				return 5;
			case "junho":
				return 6;
			case "julho":
				return 7;
			case "agosto":
				return 8;
			case "setembro":
				return 9;
			case "outubro":
				return 10;
			case "novembro":
				return 11;
			case "dezembro":
				return 12;
			default:
				return -1;
		}
	}

	public static boolean isDia(int dia){
		return dia >= 1 && dia <= 31;
	}

	public static boolean isDia(String dia){
		for(int i = 0; i<dia.length(); i++){
			if(!Character.isDigit(dia.charAt(i))) return false;
		}
		int diaInt = Integer.parseInt(dia);
		return diaInt >= 1 && diaInt <= 31;
	}

	public static boolean isMes(int mes){
		return mes >= 1 && mes <= 12;
	}

	public static boolean isMes(String mes){
		if(Character.isLetter(mes.charAt(0))){
			int mesInt = parseMesInt(mes);
			return mesInt >= 1 && mesInt <= 12;	
		} 
		else{
			int mesInt = Integer.parseInt(mes);
			return mesInt >= 1 && mesInt <= 12;
		}
	}

	public static boolean isAno(int ano){
		int anoRef = GregorianCalendar.getInstance().get(GregorianCalendar.YEAR);
		return ano > anoRef-120 && ano <= anoRef;
	}

	public static boolean isAno(String ano){
		for(int i = 0; i<ano.length(); i++){
			if(!Character.isDigit(ano.charAt(i))) return false;
		}
		int anoInt = Integer.parseInt(ano);
		int anoRef = GregorianCalendar.getInstance().get(GregorianCalendar.YEAR);
		return anoInt > anoRef-120 && anoInt <= anoRef;
	}

	public static boolean isDataValida(int dia, int mes, int ano){
		if(!isDia(dia) || !isMes(mes) || !isAno(ano)) return false;
		GregorianCalendar data = new GregorianCalendar(ano, mes-1, dia);
		
		return data.get(GregorianCalendar.DAY_OF_MONTH) == dia;
	}

	public static boolean isDataValida(int dia, String mes, int ano){
		int mesInt = parseMesInt(mes);
		
		if(!isDia(dia) || !isMes(mesInt) || !isAno(ano)) return false;
		GregorianCalendar data = new GregorianCalendar(ano, mesInt-1, dia);
		
		return data.get(GregorianCalendar.DAY_OF_MONTH) == dia; 
	}

	public static boolean isDataValida(String dia, String mes, String ano){
		int diaInt = Integer.parseInt(dia);
		int mesInt;
		if(Character.isLetter(mes.charAt(0))){
			mesInt = parseMesInt(mes);
		} 
		else{
			mesInt = Integer.parseInt(mes);
		}

		int anoInt = Integer.parseInt(ano);

		if(!isDia(diaInt) || !isMes(mesInt) || !isAno(anoInt)) return false;
		GregorianCalendar data = new GregorianCalendar(anoInt, mesInt-1, diaInt);

		return data.get(GregorianCalendar.DAY_OF_MONTH) == diaInt;
	}
}

class ValidacaoAuxiliares{
	public static boolean isNome(String nome){
		for(int i = 0; i < nome.length(); i++){
			if(!Character.isLetter(nome.charAt(i))) return false;
		}
		return true;
	}

	public static boolean isPeso(float peso){
		if(peso <= 0 || peso >600) return false;
		return true;
	}

	public static boolean isAltura(float altura){
		if(altura <= 0 || altura > 3.5) return false;
		return true;
	}

	public static boolean isOnlyNumeric(String string){
		for(int i = 0; i < string.length(); i++){
			if(!Character.isDigit(string.charAt(i))) return false;
		}
		return true;
	}
}
