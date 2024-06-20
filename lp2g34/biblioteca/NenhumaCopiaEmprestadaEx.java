package lp2g34.biblioteca;

public class NenhumaCopiaEmprestadaEx extends Exception{
    public NenhumaCopiaEmprestadaEx(){
        super();
    };

    public String toString(){
        return "Nao ha livros para serem devolvidos";
    }
}