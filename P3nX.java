import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import lp2g34.biblioteca.*;

public class P3nX {   
    public static void main(String args[]){
        final int NOVA_BIBLIOTECA = 1;
        final int CARREGA_BIBLIOTECA = 2;
        final int MANUTENCAO = 1;
        final int CADASTRO = 2;
        final int EMPRESTIMO = 3;
        final int RELATORIO = 4;
        final int CRIAR_ARQUIVOS = 1;
        final int SALVAR_ESTADO = 2;
        final int ABRIR_ARQUIVOS = 3;

        final int SAIR = 0;

        BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
        int opt;
        
        //Loop principal do programa
        while(true){
            //Pegar a opcao de inicializacao do usuário
            System.out.println("Bem-vindo ao Sistema de Biblioteca!");
            System.out.println("Por favor, selecione uma opção do menu abaixo:");
            P3nX.showMenuOptions();
            
            try{
                opt = Integer.parseInt(input.readLine());

            }catch(IOException e ){
                System.out.println(e);
                opt = -1;
            }
            switch (opt) {
                //Nova biblioteca
                case NOVA_BIBLIOTECA:
                    try{
                        Biblioteca b = new Biblioteca();

                        //Inicializa o programa da biblioteca
                        while (true) {

                        }
                    }catch(Exception e){
                        System.out.println("Erro de leitura do arquivo, nome errado.");
                        break;
                    }
                //Carregar nova biblioteca
                case CARREGA_BIBLIOTECA:
                    System.out.println("Passe o nome dos arquivos que você deseja carregar:");
                    String arqUsuarios;
                    String arqLivros;
                    try{
                        //Pega o nome do arq de usuariso
                        System.out.print("Nome do arquivo de usuarios");
                        arqUsuarios = input.readLine();

                        //Pega o nome do arquivo de livros
                        System.out.print("Nome do arquivo de livros");
                        arqLivros = input.readLine();

                        //Tentativa de leitura do arquivo
                        try{
                            Biblioteca b = new Biblioteca(arqUsuarios, arqLivros);

                            //Inicializa o programa da biblioteca
                            while (true) {
                                P3nX.showBibliotecaOptions();
                                System.out.print("Digite sua opção: ");
                                //Capturar a opcao do usuario de utilizacao da biblioteca
                                try {
                                    int optBiblioteca = Integer.parseInt(input.readLine());

                                    switch (optBiblioteca) {
                                        case MANUTENCAO:
                                            P3nX.showManutencaoOptions();
                                            System.out.print("Digite sua opção: ");
                                            int optManutencao = Integer.parseInt(input.readLine());
                                            switch (optManutencao) {
                                                case(CRIAR_ARQUIVOS):
                                                    try {
                                                        String salvArqUsuario;
                                                        String salvaArqLivro;

                                                        //Pega o nome do arq de usuariso
                                                        System.out.print("Nome do arquivo de usuarios");
                                                        salvArqUsuario = input.readLine();
                                                        //Pega o nome do arquivo de livros
                                                        System.out.print("Nome do arquivo de livros");
                                                        salvaArqLivro = input.readLine();

                                                        //Salva os arquivos
                                                        b.salvaArquivo(b.getLivros(), salvaArqLivro);
                                                        b.salvaArquivo(b.getUsuarios(), salvArqUsuario);
                                                        break;
                                                    } catch (Exception e) {
                                                        System.out.println(e);
                                                        break;
                                                    }
                                                case(SALVAR_ESTADO):
                                                try {
                                                    //Salva o estado
                                                    b.salvaArquivo(b.getLivros(), arqLivros);
                                                    b.salvaArquivo(b.getUsuarios(), arqUsuarios);
                                                    break;
                                                } catch (Exception e) {
                                                    System.out.println(e);
                                                    break;
                                                }
                                                case(ABRIR_ARQUIVOS):
                                                    
                                                    break;
                                                default:
                                                    System.out.println("Opcao invalida.");
                                                    continue;
                                            }
                                            break;
                                        case CADASTRO:
                                            break;
                                        case EMPRESTIMO:
                                            break;
                                        case RELATORIO:
                                            break;
                                        case SAIR:
                                            break;
                                        default:
                                            System.out.println("Opcao invalida");
                                            break;
                                    }
                                } catch (Exception e) {
                                    System.out.println(e);
                                    
                                }
                            }
                        }catch(Exception e){
                            //Erro de inicializacao da biblioteca
                            System.out.println("Erro de leitura do arquivo, nome errado.");
                            break;
                        }
                    }catch(IOException e ){
                        //Erro por nome do arquivo errado ao tentar carregar biblioteca
                        System.out.println(e);
                        break;
                    }

                //Sair do programa
                case SAIR:
                    return;
                default:
                    System.out.println("Opcao invalida.");
                    break;
            }
        }
    }
   
    private void rotinaManutencao(){
        
    }

    private static void showMenuOptions(){
        System.out.println("\nMenu:");
        System.out.println("1 - Iniciar nova biblioteca");
        System.out.println("2 - Carregar biblioteca");
        System.out.println("0 - Sair do programa");
        return;
    }

    private static void showBibliotecaOptions(){
        System.out.println("\nMenu:");
        System.out.println("1 - Manutenção");
        System.out.println("2 - Cadastro");
        System.out.println("3 - Empréstimo");
        System.out.println("4 - Relatório");
        System.out.println("0 - Sair");
    }

    private static void showManutencaoOptions(){
        System.out.println("\nMenu de Manutenção:");
        System.out.println("1 - Criar arquivos de usuarios e livros de backup");
        System.out.println("2 - Salvar usuarios e livros no banco");
        System.out.println("3 - Abrir arquivos para atualizar o sistema");
        System.out.println("0 - Voltar ao menu principal");
    }
}
