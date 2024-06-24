package lp2g34.biblioteca;

public class UsuarioNaoCadastradoEx extends Exception {
    public UsuarioNaoCadastradoEx(){
        super();
    }

    public String toString(){
       return "Usuario nao cadastrado.";
    }
}
