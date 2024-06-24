package lp2g34.biblioteca;

import java.util.GregorianCalendar;

public class EmprestPara {
    private long cpf;
    private GregorianCalendar dataLocacao;
    private GregorianCalendar dataDevolucao;

    public EmprestPara(GregorianCalendar dataLocacaoInicial,long cpfInicial){
        this.dataLocacao = dataLocacaoInicial;
        this.dataDevolucao = null;
        this.cpf = cpfInicial;
    }

    //getters
    public long getCpf() {
        return cpf;
    }

    public GregorianCalendar getDataDevolucao() {
        return dataDevolucao;
    }

    public GregorianCalendar getDataLocacao() {
        return dataLocacao;
    }

    //setters
    public void setCpf(long cpf) {
        this.cpf = cpf;
    }

    public void setDataDevolucao(GregorianCalendar dataDevolucao) {
        this.dataDevolucao = dataDevolucao;
    }

    public void setDataLocacao(GregorianCalendar dataLocacao) {
        this.dataLocacao = dataLocacao;
    }

    public String toString(){
        String stringDataDevolucao;
        if (this.dataDevolucao == null) { stringDataDevolucao = "Pendente";}
        else{
            stringDataDevolucao = FormataObjetos.formataData(dataDevolucao); 
        }
        String stringDataLocacao = FormataObjetos.formataData(this.dataLocacao); 
        return "Cpf do usuario: " + Long.toString(this.cpf) + "\n" + "Data de emprestimo: " + stringDataLocacao + "\n" + "Data devolucao: " + stringDataDevolucao;  
    }
}