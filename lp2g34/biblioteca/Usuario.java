package lp2g34.biblioteca;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.GregorianCalendar;

public class Usuario extends Pessoa implements Serializable{
    //Attributes
    private String endereco;
    private ArrayList<Emprest> hist;
    private int totalMultas;

    // public Usuario (String nome, String sobreNome, int dia, int mes, int ano, String endereco){
	// 	super(nome,sobreNome,dia,mes,ano);
    //     this.endereco = endereco;
	// }

	public Usuario (String nome, String sobreNome, int dia, int mes, int ano,long cpf, String endereco){
		super(nome,sobreNome,dia,mes,ano,cpf);
        this.endereco = endereco;
        this.hist = new ArrayList<Emprest>();
        this.totalMultas = 0;
	}

    //methods
    public void addLivroHist(int codLivro,GregorianCalendar data){
        Emprest livroEmprestado = new Emprest(data,codLivro);
        this.hist.add(livroEmprestado);
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

    public ArrayList<Emprest> getHist(){
        return this.hist;
    }
    public int getTotalMultas() {
        return totalMultas;
    }

    //setters
    public void setEndereco(String novoEndereco){
        this.endereco = novoEndereco;
    }
    public void setTotalMultas(int valor) {
        this.totalMultas += valor;
    }

    public String toString(){
        String histLivros;
        if(this.hist == null){
            histLivros = "Nenhum livro alugado";
        }else{
            histLivros = this.hist.toString();
        }
        return super.toString() + "Endereco: " + this.endereco + "\n" + "Historico: " + "\n" + histLivros + "\n";
    }
}
