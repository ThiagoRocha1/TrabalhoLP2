package lp2g34.biblioteca;

import java.util.ArrayList;
import java.util.GregorianCalendar;

public class Livro {
    private int codigo;
    private String titulo;
    private String categoria;
    private int quantidadeDeCopias;
    private int Emprestados;
    private ArrayList<EmprestPara> hist;

    public Livro (int newCodigo, String newTitulo, String newCategoria, int newQuantidadeDeCopias ){
        this.codigo = newCodigo;
        this.titulo = newTitulo;
        this.categoria = newCategoria;
        this.quantidadeDeCopias = newQuantidadeDeCopias;
        this.Emprestados = 0;
    }


    //methods
    public void empresta() throws CopiaNaoDisponivelEx{
        int verificacaoQuantidade = this.quantidadeDeCopias - this.Emprestados;
        if(verificacaoQuantidade <=0){
            throw new CopiaNaoDisponivelEx(this.titulo);
        }else{
            this.Emprestados += 1;
        }
    }

    public void devolve() throws NenhumaCopiaEmprestadaEx{
        if(this.Emprestados == 0){
            throw new NenhumaCopiaEmprestadaEx();
        }else{
            this.Emprestados -= 1;
        }
    }

    public void addUsuarioHist(GregorianCalendar dataLocacao,GregorianCalendar dataDevolucao, long cpf){
        EmprestPara livroEmprestado = new EmprestPara(dataLocacao,dataDevolucao,cpf);
        hist.add(livroEmprestado);
    }

    //getters
    public int getCodigo() {
        return codigo;
    }

    public String getTitulo() {
        return titulo;
    }

    public String getCategoria() {
        return categoria;
    }

    public int getQuantidadeDeCopias() {
        return quantidadeDeCopias;
    }

    public int getEmprestados() {
        return Emprestados;
    }

    public ArrayList<EmprestPara> getHist() {
        return hist;
    }

    //setters
    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public void setQuantidadeDeCopias(int quantidadeDeCopias) {
        this.quantidadeDeCopias = quantidadeDeCopias;
    }

    public void setEmprestados(int Emprestados) {
        this.Emprestados = Emprestados;
    }

    public String toString(){
        return "Titulo: " + this.titulo + "\n" +
                "Codigo: " + Integer.toString(this.codigo) + "\n" +
                "Categoria: " + this.categoria + "\n" + 
                "Quantidade de copias: " + Integer.toString(this.quantidadeDeCopias) + "\n" +
                "Quantidade de emprestimos: " + Integer.toString(this.Emprestados) + "\n"+ 
                "Historico: " + "\n" + this.hist.toString();

    }

}
