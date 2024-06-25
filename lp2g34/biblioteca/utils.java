package lp2g34.biblioteca;

import java.util.*;

class FormataObjetos{
	public static String formataData (GregorianCalendar data) {
		int ano = data.get(GregorianCalendar.YEAR);
		int mes = data.get(GregorianCalendar.MONTH)+1;
		int dia = data.get(GregorianCalendar.DAY_OF_MONTH);
		return Integer.toString(ano) + "/" +  String.format("%02d",mes) + "/" +  String.format("%02d", dia);
	}
}
