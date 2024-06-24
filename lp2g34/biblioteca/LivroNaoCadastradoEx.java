package lp2g34.biblioteca;

public class LivroNaoCadastradoEx extends Exception{
    public LivroNaoCadastradoEx(){
        super();
    }

    public String toString(){
       return "Livro nao cadastrado.";
    }
}
