package lp2g34.biblioteca;

import java.util.GregorianCalendar;

public class Emprest {
    private GregorianCalendar dataEmprestimo;
    private GregorianCalendar dataDevolucao = null;
    private int codigoLivro;

    public Emprest(GregorianCalendar data, int codigo){
        this.dataEmprestimo = data;
        this.codigoLivro = codigo; 
    }

    //getters
    public int getCodigoLivro() {
        return codigoLivro;
    }

    public GregorianCalendar getDataDevolucao() {
        return dataDevolucao;
    }

    public GregorianCalendar getDataEmprestimo() {
        return dataEmprestimo;
    }

    //setters
    public void setDataDevolucao(GregorianCalendar dataDevolucao) {
        this.dataDevolucao = dataDevolucao;
    }

    public void setCodigoLivro(int newCodigo) {
        this.codigoLivro = newCodigo;
    }

    public String toString(){
        String stringDataDevolucao;
        if (this.dataDevolucao == null) { stringDataDevolucao = "Pendente";}
        else{
            stringDataDevolucao = FormataObjetos.formataData(dataDevolucao); 
        }
        return "Codigo do livro: " + Integer.toString(codigoLivro) + "\n" + "Data de emprestimo: " + FormataObjetos.formataData(dataEmprestimo) + "\n" + "Data devolucao: " + stringDataDevolucao;  
    }
}   
