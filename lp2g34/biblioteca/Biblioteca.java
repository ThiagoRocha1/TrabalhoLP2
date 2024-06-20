package lp2g34.biblioteca;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Hashtable;

public class Biblioteca {
    private Hashtable usuarios;
    private Hashtable livros; 

    public Biblioteca(){
        this.usuarios = new Hashtable<>();
        this.livros = new Hashtable<>();
    }
    
    public Biblioteca(String arquivoUsuarios, String arquivoLivro){
        try{
            ObjectInputStream arqUsuarios = new ObjectInputStream(new FileInputStream(arquivoUsuarios));
            ObjectInputStream arqLivros = new ObjectInputStream(new FileInputStream(arquivoUsuarios));

            this.usuarios = (Hashtable) arqUsuarios.readObject();
            this.livros = (Hashtable) arqLivros.readObject();
            arqUsuarios.close();
            arqLivros.close();
        }catch(IOException e){
            System.out.println("Erro de leitura de arquivos. " + e.toString());
        }catch(ClassNotFoundException e){
            System.out.println("Classe nao achada error. " + e);
        }
    }

    //setters
    public void cadastraUsuario(Usuario usuario){
        if(this.usuarios.get(usuario.getNumCPF()) == null){
            this.usuarios.put(usuario.getNumCPF(), usuario);
            return;
        }else{
            System.out.println("Usuario ja cadastrado.");
            return;
        }
    }
    
    public void cadastraLivro(Livro livro){
        if(this.livros.get(livro.getCodigo()) == null){
            this.livros.put(livro.getCodigo(), livro);
            return;
        }else{
            System.out.println("Livro ja cadastrado.");
            return;
        }
    }
   
    //getters
    public Hashtable getLivros() {
        return livros;
    }

    public Hashtable getUsuarios() {
        return usuarios;
    }

    //Continuar o codigo a partir daq - Ultima data: 19/06
    public Usuario getUsuario(long key){

    }

    public Livro getLivro(int key){

    }
    
    //methods
    public void salvaArquivo (Hashtable tabela, String nomeDoArquivo){
        try{
            ObjectOutputStream output = new ObjectOutputStream(new FileOutputStream(nomeDoArquivo));
            output.writeObject(tabela);
            output.close();
        }catch(IOException e ){
            System.out.println("Erro ao escrever o arquivo." + e.toString());
        }
    }

    public void leArqUsu(String nomeDoArquivo){
        try{
            ObjectInputStream arqUsuarios = new ObjectInputStream(new FileInputStream(nomeDoArquivo));
            this.usuarios = (Hashtable) arqUsuarios.readObject();
            arqUsuarios.close();
        }catch(IOException e){
            System.out.println("Erro de leitura de arquivos. " + e.toString());
        }catch(ClassNotFoundException e){
            System.out.println("Classe nao achada error. " + e);
        }
    }

    public void leArqLiv(String nomeDoArq){
        try{
            ObjectInputStream arqLivros = new ObjectInputStream(new FileInputStream(nomeDoArq));
            this.livros = (Hashtable) arqLivros.readObject();
            arqLivros.close();
        }catch(IOException e){
            System.out.println("Erro de leitura de arquivos. " + e.toString());
        }catch(ClassNotFoundException e){
            System.out.println("Classe nao achada error. " + e);
        }
    }

}