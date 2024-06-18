package lp2g34.biblioteca;

public class Livro {
    private int codigo;
    private String titulo;
    private String categoria;
    private int quantidadeDeCopias;
    private int quantidadeDeEmprestimos;

    public Livro (int newCodigo, String newTitulo, String newCategoria, int newQuantidadeDeCopias ){
        this.codigo = newCodigo;
        this.titulo = newTitulo;
        this.categoria = newCategoria;
        this.quantidadeDeCopias = newQuantidadeDeCopias;
        this.quantidadeDeEmprestimos = 0;
    }


    //methods
    public void empresta() throws CopiaNaoDisponivelEx{
        int verificacaoQuantidade = this.quantidadeDeCopias - this.quantidadeDeEmprestimos;
        if(verificacaoQuantidade <=0){
            throw new CopiaNaoDisponivelEx(this.titulo);
        }else{
            this.quantidadeDeEmprestimos += 1;
        }
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

    public int getQuantidadeDeEmprestimos() {
        return quantidadeDeEmprestimos;
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

    public void setQuantidadeDeEmprestimos(int quantidadeDeEmprestimos) {
        this.quantidadeDeEmprestimos = quantidadeDeEmprestimos;
    }

    public String toString(){
        return "Titulo: " + this.titulo + "\n" +
                "Codigo: " + Integer.toString(this.codigo) + "\n" +
                "Categoria: " + this.categoria + "\n" + 
                "Quantidade de copias: " + Integer.toString(this.quantidadeDeCopias) + "\n" +
                "Quantidade de emprestimos: " + Integer.toHexString(this.quantidadeDeEmprestimos);

    }

}
