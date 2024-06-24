package lp2g34.biblioteca;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Comparator;
import java.util.GregorianCalendar;
import java.util.Hashtable;

public class Biblioteca {
    private Hashtable usuarios;
    private Hashtable livros; 

    public static final int DIAS_MAXIMO_DE_LOCACAO = 30;
    public static final int VALOR_MULTA = 100;

    public Biblioteca(){
        this.usuarios = new Hashtable<>();
        this.livros = new Hashtable<>();
    }
    
    public Biblioteca(String arquivoUsuarios, String arquivoLivro) throws IOException{
        try{
            ObjectInputStream arqUsuarios = new ObjectInputStream(new FileInputStream(arquivoUsuarios));
            ObjectInputStream arqLivros = new ObjectInputStream(new FileInputStream(arquivoLivro));

            this.usuarios = (Hashtable) arqUsuarios.readObject();
            this.livros = (Hashtable) arqLivros.readObject();

            arqUsuarios.close();
            arqLivros.close();
        }catch(IOException e){
            System.out.println("Erro de leitura de arquivos. " + e.toString());
            throw new IOException();
        }catch(ClassNotFoundException e){
            System.out.println("Classe nao achada error. " + e);
            throw new IOException();
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


    public Usuario getUsuario(long cpf) throws UsuarioNaoCadastradoEx{
        if(this.usuarios.containsKey(cpf)){
            return (Usuario) this.usuarios.get(cpf);
        }else{
            throw new UsuarioNaoCadastradoEx();
        }
    }

    public Livro getLivro(int cod) throws LivroNaoCadastradoEx{
        if(this.livros.containsKey(cod)){
            return (Livro) this.livros.get(cod);
        }else{
            throw new LivroNaoCadastradoEx();
        }
    }
    
    //methods
    public void salvaArquivo (Hashtable tabela, String nomeDoArquivo) throws IOException{
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

    public void emprestaLivro(Usuario usuario, Livro livro) throws UsuarioNaoCadastradoEx,LivroNaoCadastradoEx,CopiaNaoDisponivelEx,Exception{
            //Verificando os usuarios
            Usuario usuarioAdd = getUsuario(usuario.getNumCPF());
            Livro livroAdd = getLivro(livro.getCodigo());

            //Verificar se o usuário possui o livro está pendente
            // ArrayList<Emprest> auxHist = usuarioAdd.getHist();
            // for( Emprest e : auxHist){
            //     if(e.getCodigoLivro() ==  livroAdd.getCodigo() && e.getDataDevolucao() == null){
            //         System.out.println("Ha um livro de mesmo codigo pendente, nao eh possivel alugar outro livro.");
            //         return;
            //     }
            // }
            
            //Adicionando o livro e usuario no historico
            livroAdd.empresta();
            GregorianCalendar dataAtual = new GregorianCalendar();
            livroAdd.addUsuarioHist(dataAtual, usuarioAdd.getNumCPF());
            usuarioAdd.addLivroHist(livroAdd.getCodigo(), dataAtual);
    }

    public void devolveLivro(Usuario usuario, Livro livro) throws UsuarioNaoCadastradoEx,LivroNaoCadastradoEx,NenhumaCopiaEmprestadaEx,Exception{
            //Verificando os usuarios
            Usuario usuarioAdd = getUsuario(usuario.getNumCPF());
            Livro livroAdd = getLivro(livro.getCodigo());
            
            //Corrigindo a data de devolução do livro
            GregorianCalendar dataAtual = new GregorianCalendar();
            ArrayList<Emprest> auxHist = usuarioAdd.getHist(); //Historico do usuario, objeto Emprest para
            ArrayList<EmprestPara> auxHist2 = livroAdd.getHist();

            //Modificar o emprest dentro do historico do aluno
            for( Emprest e : auxHist){
                if(e.getCodigoLivro() ==  livroAdd.getCodigo() && e.getDataDevolucao() == null){
                    //usuarioAdd.getHist().get(usuarioAdd.getHist().indexOf((Object)livroAdd)).setDataDevolucao(dataAtual);
                    //livroAdd.getHist().get(livroAdd.getHist().indexOf((Object)livroAdd)).setDataDevolucao(dataAtual);
                    e.setDataDevolucao(dataAtual);
                    break;                    
                }
            }

            //Modificar o historico dentro do livro
            for( EmprestPara e : auxHist2){
                if(e.getCpf() ==  usuarioAdd.getNumCPF() && e.getDataDevolucao() == null){
                    e.setDataDevolucao(dataAtual);
                }

                //Verificando se tá com data atrasada
                GregorianCalendar dataMaximaLocacao = (auxHist2.get(auxHist2.indexOf(e)).getDataLocacao());
                dataMaximaLocacao.add(Calendar.DAY_OF_MONTH, DIAS_MAXIMO_DE_LOCACAO);
                if(dataAtual.after(dataMaximaLocacao)){
                    usuarioAdd.setTotalMultas(VALOR_MULTA);
                }
                break;
            }
            livroAdd.devolve();
    }

    //Comparadores para imprimir a lista
    public Comparator codigoC = new Comparator () {
        public int compare (Object l1, Object l2){
            Livro livro1 = (Livro) l1;
            Livro livro2 = (Livro) l2;
            int cod1, cod2;
            cod1 = livro1.getCodigo();
            cod2 = livro2.getCodigo();
            return cod1 - cod2;   
        } 
    };

    public Comparator cpfC = new Comparator () {
        public int compare (Object o1, Object o2){
            Usuario user1 = (Usuario) o1;
            Usuario user2 = (Usuario) o2;
            long cpf1, cpf2;
            cpf1 = user1.getNumCPF();
            cpf2 = user2.getNumCPF();
            return (int) Math.round (cpf1 - cpf2);
        } 
    };

    public String imprimeLivros(){
        //Pegar os livros da hashtable
        ArrayList <Object> auxArray = new ArrayList<>();
        for(Object livro : this.livros.values()){
            auxArray.add(livro);
        }

        //Ordenar o array para imprimir os livros        
        auxArray.sort(codigoC);
        return auxArray.toString();
    }

    public String imprimeUsuarios(){
        //Pegar os usuariosda hashtable
        ArrayList <Object> auxArray = new ArrayList<>();
        for(Object usuario : this.usuarios.values()){
            auxArray.add(usuario);
        }

        //Ordenar o array para imprimir os livros        
        auxArray.sort(cpfC);
        return auxArray.toString();
    }

}