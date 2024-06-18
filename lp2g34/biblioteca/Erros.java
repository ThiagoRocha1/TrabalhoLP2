package lp2g34.biblioteca;

class CopiaNaoDisponivelEx  extends Exception {
    private String titulo;

    public CopiaNaoDisponivelEx (String nomeDoLivro){
        this.titulo = nomeDoLivro;
    }

    public String getTitulo(){
        return this.titulo;
    }

    public String toString(){
        return "Nao ha mais copias disponiveis para emprestimo do livro: " + this.titulo;
    }
}
