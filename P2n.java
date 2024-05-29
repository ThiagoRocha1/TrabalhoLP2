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

class Pessoa{
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
		"Dia de nascimento: " + dia + "\n" +
		"Mes de nascimento: " + nomeDosMeses[mes-1] + "\n" +
		"Ano de nascimento: " + ano + "\n" +
		"CPF: " + this.numCpf + "\n";
		return campos;
	}
}

abstract class PessoaIMC extends Pessoa {
	protected float peso;
	protected float altura;

	public PessoaIMC(String nome, String sobreNome, int dia, int mes, int ano, float peso, float altura){
		super(nome,sobreNome,dia,mes,ano);
		this.peso = peso;
		this.altura = altura;
	}

	public PessoaIMC(String nome, String sobreNome, int dia, int mes, int ano,long cpf, float peso, float altura){
		super(nome,sobreNome,dia,mes,ano,cpf);
		this.peso = peso;
		this.altura = altura;
	}

	//Getters
	public float getPeso() {
		return this.peso;
	}

	public float getAltura (){
		return this.altura;
	}

	//Setters
	public void setPeso (float novoPeso){
		this.peso = novoPeso;
	}

	public void setAltura(float novaAltura){
		this.altura = novaAltura;
	}

	//Funcoes de instancia
	public float calculaIMC(){
		float altura = this.altura;
		float peso = this.peso;
		float imc = (peso / (altura*altura));
		return imc;
	}

	//Funcoes abstract
	abstract String resultIMC();

	//To string
	public String toString (){
		return super.toString() + '\n' +
		"Peso: " + this.peso + '\n' +
		"Altura: " + this.altura;
	}
}

class Homem extends PessoaIMC{
	//Construtores
	public Homem(String nome, String sobreNome, int dia, int mes, int ano,float peso, float altura){
		super(nome,sobreNome,dia,mes,ano,peso,altura);
	}

	public Homem(String nome, String sobreNome, int dia, int mes, int ano,long cpf, float peso, float altura){
		super(nome, sobreNome, dia, mes ,ano, cpf,peso,altura);
	}

	//Idade
	private int getIdade(){
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

	//Classificacao IMC
	public String resultIMC(){
		Float imc = this.calculaIMC();

		if(imc < 20.7) return "Abaixo do peso ideal";
		else if (imc>= 20.7 && imc <= 26.4) return "Peso ideal";
		else return "Acima do peso ideal"; 
	}

	//toString
	public String toString (){
		int idade = this.getIdade();
		String cpfString = ""+this.getNumCPF();

		String campos = 
		"Nome: " + this.getNome() + " (Homem)" + "\n" + 
		"Sobrenome: " + this.getSobreNome() + "\n" +
		"Idade: " + idade + "\n" +
		"CPF: " + ValidaCPF.imprimeCPF(cpfString) + "\n" +
		"IMC: " + Float.toString(this.calculaIMC()) + " " + this.resultIMC() + "\n";		
		return campos;
	}

}

class Mulher extends PessoaIMC{
	//Construtores
	public Mulher(String nome, String sobreNome, int dia, int mes, int ano,float peso, float altura){
		super(nome,sobreNome,dia,mes,ano,peso,altura);
	}

	public Mulher(String nome, String sobreNome, int dia, int mes, int ano,long cpf, float peso, float altura){
		super(nome, sobreNome, dia, mes ,ano, cpf,peso,altura);
	}

	//Idade
	private int getIdade(){
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


	//Classificacao IMC
	public String resultIMC(){
		Float imc = this.calculaIMC();

		if(imc < 19) return "Abaixo do peso ideal";
		else if (imc>= 19 && imc <= 25.8) return "Peso ideal";
		else return "Acima do peso ideal"; 
	}

	//toString
	public String toString (){
		int idade = this.getIdade();
		String cpfString = ""+this.getNumCPF();

		String campos = 
		"Nome: " + this.getNome() + " (Mulher)" + "\n" + 
		"Sobrenome: " + this.getSobreNome() + "\n" +
		"Idade: " + idade + "\n" +
		"CPF: " + ValidaCPF.imprimeCPF(cpfString) + "\n" +
		"IMC: " + Float.toString(this.calculaIMC()) + " " + this.resultIMC() + "\n";		
		return campos;
	}
}

class MinhaListaOrdenavel{
	private ArrayList<PessoaIMC> list;

	private String ALFABETICA_DECRESC = "1.Alfabetica (A-Z) – nome da pessoa";
	private String ALFABETICA_CRES = "2.Alfabetica (Z-A) – nome da pessoa"; 
	private String PESO_CRES = "3.Menor Peso - crescente";
	private String PESO_DECRES = "4.Maior Peso - decrescente";
	private String ALTURA_CRESC = "5.Menor Altura – crescente, do mais baixo para o mais alto";
	private String IMC_CRESC = "6.Menor IMC - crescente, do mais baixo para o mais alto";
	private String GENERO = "6.Homem / Mulher – ordenar por gênero";
	

	public MinhaListaOrdenavel (){
		this.list = new ArrayList<>();
	}

	//Instance methods
	public void add (PessoaIMC p){
		this.list.add(p);
	}

	public PessoaIMC get (int i){
		return (PessoaIMC) this.list.get(i);
	}

	//Inner Class
	public Comparator pesoC = new Comparator () {
		public int compare (Object p1, Object p2){
		PessoaIMC pessoaP1 = (PessoaIMC) p1;
		PessoaIMC pessoaP2 = (PessoaIMC) p2;
		float pf1, pf2;
		pf2 = pessoaP2.getPeso();
		pf1 = pessoaP1.getPeso();
		return (int) Math.round (pf2 - pf1);
		}
	};

	public Comparator nomeC;
	public Comparator imcC;
	public Comparator pessoaC;
	public Comparator idadeC;
	public Comparator dataNascC;
	public Comparator cpfC;

}

class Programa{
	public static void main(String []args){
		// PessoaIMC p = new PessoaIMC("Thiago","Rocha",9,11,2000,86,176);
	}
}