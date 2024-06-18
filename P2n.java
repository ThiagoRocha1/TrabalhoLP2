import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
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
		if (this instanceof Homem){
			return super.toString()+
			"Peso: " + this.peso + '\n' +
			"Altura: " + this.altura + '\n'+
			"Genero: Homem" + '\n'; 
		}else if (this instanceof Mulher){
			return super.toString()+
			"Peso: " + this.peso + '\n' +
			"Altura: " + this.altura + '\n'+
			"Genero: Mulher" + '\n';
		}else{
			return super.toString()+
			"Peso: " + this.peso + '\n' +
			"Altura: " + this.altura + '\n';
		}
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
		String campos = 
		"Idade: " + idade + "\n" +
		"IMC: " + Float.toString(this.calculaIMC()) + " " + this.resultIMC() + "\n";		
		return super.toString() + campos;
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
		String campos = 
		"Idade: " + idade + "\n" +
		"IMC: " + Float.toString(this.calculaIMC()) + " " + this.resultIMC() + "\n";		
		return super.toString() + campos;
	}
}

class MinhaListaOrdenavel{
	private ArrayList<PessoaIMC> list;

	final static private int ALFABETICA_CRESC = 1;
	final static private int ALFABETICA_DECRESC = 2; 
	final static private int PESO_CRESC = 3;
	final static private int PESO_DECRESC = 4;
	final static private int IMC_CRESC = 5;
	final static private int IMC_DECRESC = 6;
	final static private int GENERO_CRESC = 7;
	final static private int GENERO_DECRESC = 8;
	final static private int IDADE_CRESC = 9;
	final static private int IDADE_DECRESC = 10;
	final static private int DATA_NASC_CRESC = 11;
	final static private int DATA_NASC_DECRESC = 12;
	final static private int CPF_CRESC = 13;
	final static private int CPF_DECRESC = 14;

	static public void showListOptions(){
		System.out.println("Escolha uma opção de ordenação:");
		System.out.println(ALFABETICA_CRESC + ". Alfabetica (A-Z)");
		System.out.println(ALFABETICA_DECRESC + ". Alfabetica (Z-A)");
		System.out.println(PESO_CRESC + ". Menor Peso");
		System.out.println(PESO_DECRESC + ". Maior Peso");
		System.out.println(IMC_CRESC + ". Menor IMC");
		System.out.println(IMC_DECRESC + ". Maior IMC");
		System.out.println(GENERO_CRESC + ". Gênero (Mulher -> Homem)");
		System.out.println(GENERO_DECRESC + ". Gênero (Homem -> Mulher)");
		System.out.println(IDADE_CRESC + ". Menor Idade");
		System.out.println(IDADE_DECRESC + ". Maior Idade");
		System.out.println(DATA_NASC_CRESC + ". Data de Nascimento (Crescente)");
		System.out.println(DATA_NASC_DECRESC + ". Data de Nascimento (Decrescente)");
		System.out.println(CPF_CRESC + ". CPF (Crescente)");
		System.out.println(CPF_DECRESC + ". CPF (Decrescente)");
	}

	public MinhaListaOrdenavel (){
		this.list = new ArrayList<PessoaIMC>();
	}

	//Instance methods
	public void add (PessoaIMC p){
		this.list.add(p);
	}

	public PessoaIMC get (int i){
		return (PessoaIMC) this.list.get(i);
	}

	public ArrayList ordena(int modo){
		switch (modo) {
			case ALFABETICA_CRESC:
				Collections.sort(this.list , nomeC);	
				break;
			case ALFABETICA_DECRESC:
				Collections.sort(this.list , nomeC.reversed());	
				break;
			case PESO_CRESC:
				Collections.sort(this.list , pesoC);
				break;
			case PESO_DECRESC:
				Collections.sort(this.list , pesoC.reversed());	
				break;		
			case IMC_CRESC:
				Collections.sort(this.list ,imcC );	
				break;		
			case IMC_DECRESC:
				Collections.sort(this.list , imcC.reversed());	
				break;		
			case GENERO_CRESC:
				Collections.sort(this.list , pessoaC);
			
				ArrayList<PessoaIMC> arrayMulheres = new ArrayList<PessoaIMC>();
				ArrayList<PessoaIMC> arrayHomens = new ArrayList<PessoaIMC>();

				//Separar mulheres de Homens 
				for(PessoaIMC p : this.list){
					if(p instanceof Mulher)arrayMulheres.add(p);
				}
				//Separar Homens das mulheres
				for(PessoaIMC p : this.list){
					if(p instanceof Homem)arrayHomens.add(p);
				}

				Collections.sort(arrayMulheres,nomeC);
				Collections.sort(arrayHomens,nomeC);
				ArrayList<PessoaIMC> sortedArray = new ArrayList<PessoaIMC>();

				sortedArray.addAll(arrayMulheres);
				sortedArray.addAll(arrayHomens);
				this.list = sortedArray;
				break;		
			case GENERO_DECRESC:
				Collections.sort(this.list , pessoaC.reversed());

				ArrayList<PessoaIMC> arrayMulheres2 = new ArrayList<PessoaIMC>();
				ArrayList<PessoaIMC> arrayHomens2 = new ArrayList<PessoaIMC>();

				//Separar mulheres de Homens 
				for(PessoaIMC p : this.list){
					if(p instanceof Mulher)arrayMulheres2.add(p);
				}
				//Separar Homens das mulheres
				for(PessoaIMC p : this.list){
					if(p instanceof Homem)arrayHomens2.add(p);
				}

				Collections.sort(arrayMulheres2,nomeC);
				Collections.sort(arrayHomens2,nomeC);
				ArrayList<PessoaIMC> sortedArray2 = new ArrayList<PessoaIMC>();
				
				sortedArray2.addAll(arrayHomens2);
				sortedArray2.addAll(arrayMulheres2);
				this.list = sortedArray2;
				break;	
			case IDADE_CRESC:
				Collections.sort(this.list , idadeC);
				break;	
			case IDADE_DECRESC:
				Collections.sort(this.list , idadeC.reversed());
				break;	
			case DATA_NASC_CRESC:
				Collections.sort(this.list , dataNascC);
				break;	
			case DATA_NASC_DECRESC:
				Collections.sort(this.list , dataNascC.reversed());
				break;
			case CPF_CRESC:
				Collections.sort(this.list , cpfC);
				break;
			case CPF_DECRESC:
				Collections.sort(this.list , cpfC.reversed());
				break;		
		}
		return this.list;
	}

	//Inner Class
	public Comparator pesoC = new Comparator () {
		public int compare (Object p1, Object p2){
		PessoaIMC pessoaP1 = (PessoaIMC) p1;
		PessoaIMC pessoaP2 = (PessoaIMC) p2;
		float pf1, pf2;
		pf2 = pessoaP2.getPeso();
		pf1 = pessoaP1.getPeso();
		return (int) Math.round (pf1 - pf2);
		}
	};

	public Comparator nomeC = new Comparator () {
		public int compare (Object p1, Object p2){
		PessoaIMC pessoaP1 = (PessoaIMC) p1;
		PessoaIMC pessoaP2 = (PessoaIMC) p2;
		String nome1, nome2;
		nome2 = pessoaP2.getNome();
		nome1 = pessoaP1.getNome();
		return nome1.compareToIgnoreCase(nome2);
		}
	};

	public Comparator imcC = new Comparator () {
		public int compare (Object p1, Object p2){
		PessoaIMC pessoaP1 = (PessoaIMC) p1;
		PessoaIMC pessoaP2 = (PessoaIMC) p2;
		float pf1, pf2;
		pf2 = pessoaP2.calculaIMC();
		pf1 = pessoaP1.calculaIMC();
		return (int) Math.round (pf1 - pf2);
		}
	};

	public Comparator pessoaC = new Comparator () {
		public int compare (Object p1, Object p2){
		PessoaIMC pessoaP1 = (PessoaIMC) p1;
		PessoaIMC pessoaP2 = (PessoaIMC) p2;
		if(pessoaP1 instanceof Homem && pessoaP2 instanceof Homem) return 0;//Homem e Homem
		else if (pessoaP1 instanceof Homem && pessoaP2 instanceof Mulher) return 11;//Homem e Mulher
		else return -1;//Mulher e Homem
		}
	};

	public Comparator idadeC = new Comparator () {
		public int compare (Object p1, Object p2){
		PessoaIMC pessoaP1 = (PessoaIMC) p1;
		PessoaIMC pessoaP2 = (PessoaIMC) p2;
		int pf1, pf2;
		pf2 = pessoaP2.getIdade();
		pf1 = pessoaP1.getIdade();
		return (int) Math.round (pf1 - pf2);
		}
	};

	public Comparator dataNascC = new Comparator () {
		public int compare (Object p1, Object p2){
		PessoaIMC pessoaP1 = (PessoaIMC) p1;
		PessoaIMC pessoaP2 = (PessoaIMC) p2;
		GregorianCalendar date1, date2;
		date1 = pessoaP1.getDataNasc();
		date2 = pessoaP2.getDataNasc();
		return date1.compareTo(date2);
		}
	};

	public Comparator cpfC = new Comparator () {
		public int compare (Object p1, Object p2){
		PessoaIMC pessoaP1 = (PessoaIMC) p1;
		PessoaIMC pessoaP2 = (PessoaIMC) p2;
		Long cpf1, cpf2;
		cpf1 = pessoaP1.getNumCPF();
		cpf2 = pessoaP2.getNumCPF();
		return (int) Math.round (cpf1 - cpf2);
		}
	};

	public String toString(){
		String stringToBePrinted = "";
		for(PessoaIMC p : this.list){
			stringToBePrinted += p.toString() + '\n';
		}
		return stringToBePrinted;
	}
}

class P2nX{
	public static void showUserOptions(){
		System.out.println("1.Imprimir Lista");
		System.out.println("2.Sair");
	}

	public static void main (String []args) throws IOException{
		PessoaIMC m1 = new Mulher("Malu","Gomes",15,1,1999,Long.parseLong("18011156721"),57,1.60f);
		PessoaIMC m2 = new Mulher("Ana", "Silva", 22, 3, 1985, Long.parseLong("31081862807"), 62, 1.65f);
        PessoaIMC m3 = new Mulher("Bianca", "Santos", 10, 5, 1990, Long.parseLong("33065979888"), 70, 1.70f);
        PessoaIMC m4 = new Mulher("Carla", "Oliveira", 8, 9, 2000, Long.parseLong("26050431850"), 55, 1.55f);
		PessoaIMC m5 = new Mulher("Julia", "Nascimento", 18, 6, 1993, Long.parseLong("18011156729"), 60, 1.68f);

		PessoaIMC h1 = new Homem("Thiago","Rocha",9,11,2000,Long.parseLong("14534317727"),86,(float)1.76);
		PessoaIMC h2 = new Homem("Carlos", "Pereira", 20, 4, 1988, Long.parseLong("29130385814"), 80, 1.80f);
        PessoaIMC h3 = new Homem("Lucas", "Ferreira", 13, 7, 1995, Long.parseLong("13254573889"), 75, 1.75f);
        PessoaIMC h4 = new Homem("Ricardo", "Lima", 30, 11, 1980, Long.parseLong("16180096805"), 85, 1.85f);
        PessoaIMC h5 = new Homem("Pedro", "Almeida", 5, 12, 1992, Long.parseLong("12256563898"), 78, 1.77f);


		MinhaListaOrdenavel lista = new MinhaListaOrdenavel();

		//Adicionar elementos a inscancia da lista ordenavel
		lista.add(m1);
		lista.add(m2);
		lista.add(m3);
		lista.add(m4);
		lista.add(m5);
		lista.add(h1);
		lista.add(h2);		
		lista.add(h3);
		lista.add(h4);
		lista.add(h5);

		//Iniciar interacao com o usuario
		BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
		
		//Intercao com o usuario
		while (true) {
			P2nX.showUserOptions();
			String aux = input.readLine();

			if(aux.equals(""))return; //Apertou ENTER,saiu do sistema
			else if(aux.equals("2")) return; //Saiu do sistema
			else if(aux.equals("1")){
				//Escolher o modo de ordenacao
				while(true){
					MinhaListaOrdenavel.showListOptions();
					aux = input.readLine();
					if(aux.equals("")) return;//Apertou ENTER, saiu do sistema
					else if(aux.equals("1")){
						lista.ordena(1);
						System.out.println(lista); //Imprime a lista no modo Selecionado
						break; //Sai do Loop de escolher metodo de ordenacao
					}
					else if(aux.equals("2")){
						lista.ordena(2);
						System.out.println(lista); //Imprime a lista no modo Selecionado
						break; //Sai do Loop de escolher metodo de ordenacao
					}
					else if(aux.equals("3")){
						lista.ordena(3);
						System.out.println(lista); //Imprime a lista no modo Selecionado
						break; //Sai do Loop de escolher metodo de ordenacao
					}
					else if(aux.equals("4")){
						lista.ordena(4);
						System.out.println(lista); //Imprime a lista no modo Selecionado
						break; //Sai do Loop de escolher metodo de ordenacao
					}
					else if(aux.equals("5")){
						lista.ordena(5);
						System.out.println(lista); //Imprime a lista no modo Selecionado
						break; //Sai do Loop de escolher metodo de ordenacao
					}
					else if(aux.equals("6")){
						lista.ordena(6);
						System.out.println(lista); //Imprime a lista no modo Selecionado
						break; //Sai do Loop de escolher metodo de ordenacao
					}
					else if(aux.equals("7")){
						lista.ordena(7);
						System.out.println(lista); //Imprime a lista no modo Selecionado
						break; //Sai do Loop de escolher metodo de ordenacao
					}
					else if(aux.equals("8")){
						lista.ordena(8);
						System.out.println(lista); //Imprime a lista no modo Selecionado
						break; //Sai do Loop de escolher metodo de ordenacao
					}
					else if(aux.equals("9")){
						lista.ordena(9);
						System.out.println(lista); //Imprime a lista no modo Selecionado
						break; //Sai do Loop de escolher metodo de ordenacao
					}
					else if(aux.equals("10")){
						lista.ordena(10);
						System.out.println(lista); //Imprime a lista no modo Selecionado
						break; //Sai do Loop de escolher metodo de ordenacao
					}
					else if(aux.equals("11")){
						lista.ordena(11);
						System.out.println(lista); //Imprime a lista no modo Selecionado
						break; //Sai do Loop de escolher metodo de ordenacao
					}
					else if(aux.equals("12")){
						lista.ordena(12);
						System.out.println(lista); //Imprime a lista no modo Selecionado
						break; //Sai do Loop de escolher metodo de ordenacao
					}
					else if(aux.equals("13")){
						lista.ordena(13);
						System.out.println(lista); //Imprime a lista no modo Selecionado
						break; //Sai do Loop de escolher metodo de ordenacao
					}
					else if(aux.equals("14")){
						lista.ordena(14);
						System.out.println(lista); //Imprime a lista no modo Selecionado
						break; //Sai do Loop de escolher metodo de ordenacao
					}else{
						System.out.println("Opcao invalida!!");
						continue;
					}
				}
			}
			else{
				System.out.println("Opcao inválida!");
				continue;
			}
		}
	}
}