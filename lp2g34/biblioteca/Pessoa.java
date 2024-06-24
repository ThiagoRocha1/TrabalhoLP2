package lp2g34.biblioteca;
import java.io.Serializable;
import java.util.*;

class Pessoa implements Serializable{
	//Atributos de classe
	private static int numTotalDePessoas;

	//Atributos de Instancia
	private String nome;
	private String sobreNome;
	private GregorianCalendar dataNasc;
	private long numCpf;
	

	//Metodos de Instancia
	public Pessoa(String nome, String sobreNome, int dia, int mes, int ano){
		this.nome = nome;
		this.sobreNome = sobreNome;
		this.dataNasc = new GregorianCalendar(ano,mes-1,dia);
		Pessoa.numTotalDePessoas++;
	}

	public Pessoa(String nome, String sobreNome, int dia, int mes, int ano,long cpf){
		this.nome = nome;
		this.sobreNome = sobreNome;
		this.numCpf = cpf;
		this.dataNasc = new GregorianCalendar(ano,mes-1,dia);
		Pessoa.numTotalDePessoas++;
	}

	//Idade
	public int getIdade(){
		GregorianCalendar dataNasc = this.getDataNasc();
		GregorianCalendar dataAtual = new GregorianCalendar();

		int anoNasc = dataNasc.get(GregorianCalendar.YEAR);
		int anoAtual = dataAtual.get(GregorianCalendar.YEAR);

		int mesNasc = dataNasc.get(GregorianCalendar.MONTH);
		int mesAtual = dataAtual.get(GregorianCalendar.MONTH)+1;

		int diaNasc = dataNasc.get(GregorianCalendar.DAY_OF_MONTH);
		int diaAtual = dataAtual.get(GregorianCalendar.DAY_OF_MONTH);

		int idade = anoAtual - anoNasc;

		if( mesAtual > mesNasc ){
			return idade; 
		}else if (mesAtual == mesNasc ){
			if(diaAtual >= diaNasc){
				return idade;
			}else{
				return idade-1;
			}
		}else{
			return idade-1;
		}
	}

	//Getters
	public String getNome(){
		return this.nome;
	}

	public String getSobreNome(){
		return this.sobreNome;
	}

	public GregorianCalendar getDataNasc(){
		return this.dataNasc;
	}

	public long getNumCPF(){
		return this.numCpf;
	}

	//Setters
	public void setNome(String novoNome){
		this.nome = novoNome;
	}

	public void setSobreNome(String novoSobreNome){
		this.sobreNome = novoSobreNome;
	}

	public void setDataNasc(int dia, int mes, int ano){
		GregorianCalendar data = new GregorianCalendar(ano,mes-1,dia);
		this.dataNasc = data;
	}

	public void setCPF(String cpf){
		this.numCpf = ValidaCPF.toLong(cpf);
	}

	//Metodos de classe
	public static int numPessoas(){
		return Pessoa.numTotalDePessoas;
	}

	//toString
	public String toString (){
		int ano = this.dataNasc.get(GregorianCalendar.YEAR);
		int mes = this.dataNasc.get(GregorianCalendar.MONTH);
		int dia = this.dataNasc.get(GregorianCalendar.DAY_OF_MONTH);

		String [] nomeDosMeses = {
			"janeiro", "fevereiro", "março", "abril", "maio", "junho",
			"julho", "agosto", "setembro", "outubro", "novembro", "dezembro"
		};

		String campos = 
		"Nome: " + this.nome + "\n" + 
		"Sobrenome: " + this.sobreNome + "\n" +
		"Data de nascimento: " + dia + " de " + nomeDosMeses[mes] + " de " + ano +  "\n" +
		"CPF: " + this.numCpf + "\n";
		return campos;
	}
}
