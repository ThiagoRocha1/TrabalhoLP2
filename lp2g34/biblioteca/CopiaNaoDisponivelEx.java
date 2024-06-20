package lp2g34.biblioteca;

public class CopiaNaoDisponivelEx  extends Exception {
    private String titulo;

    public CopiaNaoDisponivelEx (String nomeDoLivro){
        super();
        this.titulo = nomeDoLivro;
    }

    public String getTitulo(){
        return this.titulo;
    }

    public String toString(){
        return "Nao ha mais copias disponiveis para emprestimo do livro: " + this.titulo;
    }
}