package lp2g34.biblioteca;

import java.util.ArrayList;
import java.util.GregorianCalendar;

public class Usuario extends Pessoa {
    //Attributes
    private String endereco;
    private ArrayList<Emprest> hist;

    public Usuario (String nome, String sobreNome, int dia, int mes, int ano, String endereco){
		super(nome,sobreNome,dia,mes,ano);
        this.endereco = endereco;
	}

	public Usuario (String nome, String sobreNome, int dia, int mes, int ano,long cpf, String endereco){
		super(nome,sobreNome,dia,mes,ano,cpf);
        this.endereco = endereco;
	}

    //methods
    public void addLivroHist(int codLivro,GregorianCalendar data){
        Emprest livroEmprestado = new Emprest(data,codLivro);
        hist.add(livroEmprestado);
    }

    public int getLivrosEmprestados(){
        int cont = 0;
        for(Emprest emprestimo : this.hist){
            if(emprestimo.getDataDevolucao() == null) cont ++;
        }
        return cont;
    }

    //getters
    public String getEndereco(){
        return this.endereco;
    }

    //setters
    public void setEndereco(String novoEndereco){
        this.endereco = novoEndereco;
    }

    public String toString(){
        return super.toString() + "Endereco: " + this.endereco + "\n" + "Historico: " + "\n" + this.hist.toString();
    }
}
