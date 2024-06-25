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
        final int CADASTRAR_USUARIO = 1;
        final int CADASTRAR_LIVRO = 2;
        final int SALVAR_ARQUIVOS = 3;
        final int LOCACAO_LIVRO = 1;
        final int DEVOLVE_LIVRO = 2;
        final int IMPRIMIR_ACERVO = 3;
        final int MOSTRAR_REGISTRO_DE_LIVROS = 1;
        final int MOSTRAR_REGISTRO_DE_USUARIOS = 2;
        final int MOSTRAR_INFORMACAO_LIVRO = 3;
        final int MOSTRAR_INFORMACAO_USUARIO = 4;
        final int SAIR = 0;
        
        BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
        int opt;
        
        //Loop principal do programa
        Boolean running = true;
        while(running){
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
                        Boolean runningBiblioteca = true;
                            while (runningBiblioteca) {
                                P3nX.showBibliotecaOptions();
                                System.out.print("Digite sua opção: ");
                                //Capturar a opcao do usuario de utilizacao da biblioteca
                                try {
                                    int optBiblioteca = Integer.parseInt(input.readLine());

                                    switch (optBiblioteca) {
                                        case MANUTENCAO:
                                            Boolean runningManutencao = true;
                                            while (runningManutencao) {
                                                P3nX.showManutencaoOptions();
                                                System.out.print("Digite sua opção: ");
                                                try {
                                                    int optManutencao = Integer.parseInt(input.readLine());
                                                    switch (optManutencao) {
                                                        case(CRIAR_ARQUIVOS):
                                                            try {
                                                                P3nX.rotinaCriaArquivos(b,input);
                                                                break;
                                                            } catch (Exception e) {
                                                                System.out.println(e);
                                                                break;
                                                            }
                                                        case(SALVAR_ESTADO):
                                                            try {
                                                               String arqLivros = "bancoLivros.dat";
                                                               String arqUsuarios = "bancoUsuarios.dat";
                                                                P3nX.rotinaSalvaEstado(b, input, arqLivros, arqUsuarios);
                                                                break;                                                                
                                                            } catch (Exception e) {
                                                                System.out.println(e);
                                                                break;
                                                            }    
                                                        case(ABRIR_ARQUIVOS):
                                                            try {
                                                                P3nX.rotinaLeArquivo(b, input);
                                                                break;
                                                            } catch (Exception e) {
                                                                System.out.println(e);
                                                                break;
                                                            }
                                                        case(SAIR):
                                                            runningManutencao = false;
                                                            break;
                                                        default:
                                                            System.out.println("Opcao invalida.");
                                                            break;
                                                        }    
                                                } catch (Exception e) {
                                                    System.out.println(e);
                                                }
                                            }
                                        break;
                                        case CADASTRO:
                                            Boolean runningCadastro = true;
                                            while (runningCadastro) {
                                                P3nX.showCadastroOptions();
                                                System.out.print("Digite sua opção: ");
                                                try {
                                                    int optCadastro = Integer.parseInt(input.readLine());
                                                    switch (optCadastro) {
                                                        case(CADASTRAR_USUARIO):
                                                                P3nX.rotinaCriaUsuario(b, input);                                                                
                                                                break;
                                                        case(CADASTRAR_LIVRO):
                                                            try {
                                                                P3nX.rotinaCadastraLivro(b, input);
                                                                break;                                                                
                                                            } catch (Exception e) {
                                                                System.out.println(e);
                                                                break;
                                                            }    
                                                        case(SALVAR_ARQUIVOS):
                                                            try {
                                                                P3nX.rotinaCriaArquivos(b, input);
                                                                break;
                                                            } catch (Exception e) {
                                                                System.out.println(e);
                                                                break;
                                                            }
                                                        case(SAIR):
                                                            runningCadastro = false;
                                                            break;
                                                        default:
                                                            System.out.println("Opcao invalida.");
                                                            break;
                                                    }
                                                }catch (Exception e) {
                                                    System.out.println(e);
                                                }
                                            }
                                            break;
                                        case EMPRESTIMO:
                                          Boolean runningEmprestimo = true;
                                          while (runningEmprestimo) {
                                             P3nX.showEmprestimoOptions();
                                             System.out.print("Digite sua opção: ");
                                             try {
                                                   int optEmprestimo = Integer.parseInt(input.readLine());
                                                   switch (optEmprestimo) {
                                                      case(LOCACAO_LIVRO):
                                                         try{
                                                            P3nX.rotinaLocacaoLivro(b, input);
                                                            break;                                                                
                                                         }catch (Exception e) {
                                                            System.out.println(e);
                                                            break;
                                                         }
                                                      case(DEVOLVE_LIVRO):
                                                         try {
                                                            P3nX.rotinaDevolucaoLivro(b, input);
                                                            break;                                                                
                                                         } catch (Exception e) {
                                                               System.out.println(e);
                                                               break;
                                                         }    
                                                      case(IMPRIMIR_ACERVO):
                                                         try {
                                                               System.out.println(b.getLivros().toString());
                                                               break;
                                                         } catch (Exception e) {
                                                               System.out.println(e);
                                                               break;
                                                         }
                                                      case(SAIR):
                                                         runningEmprestimo = false;
                                                         break;
                                                      default:
                                                         System.out.println("Opcao invalida.");
                                                         break;
                                                   }
                                             }catch (Exception e) {
                                                   System.out.println(e);
                                             }
                                          }
                                          break;
                                        case RELATORIO:
                                          Boolean runningRelatorio = true;
                                          while (runningRelatorio) {
                                             P3nX.showRelatorioOptions();
                                             System.out.print("Digite sua opção: ");
                                             try {
                                                   int optRelatorio = Integer.parseInt(input.readLine());
                                                   switch (optRelatorio) {
                                                      case(MOSTRAR_REGISTRO_DE_LIVROS):
                                                         try{
                                                            System.out.println(b.getLivros().toString());
                                                            break;                                                                
                                                         }catch (Exception e) {
                                                            System.out.println(e);
                                                            break;
                                                         }
                                                      case(MOSTRAR_REGISTRO_DE_USUARIOS):
                                                         try {
                                                            System.out.println(b.getUsuarios().toString());
                                                            break;                                                                
                                                         } catch (Exception e) {
                                                               System.out.println(e);
                                                               break;
                                                         }    
                                                      case(MOSTRAR_INFORMACAO_LIVRO):
                                                         try {
                                                            P3nX.rotinaMostraInformacaoLivro(b, input);
                                                            break;
                                                         } catch (Exception e) {
                                                            break;
                                                         }
                                                      case(MOSTRAR_INFORMACAO_USUARIO):
                                                      try {
                                                         P3nX.rotinaMostraInformacaoUsuario(b, input);   
                                                         break;
                                                      } catch (Exception e) {
                                                            System.out.println(e);
                                                            break;
                                                      }
                                                      case(SAIR):
                                                         runningRelatorio = false;
                                                         break;
                                                      default:
                                                         System.out.println("Opcao invalida.");
                                                         break;
                                                   }
                                             }catch (Exception e) {
                                                   System.out.println(e);
                                             }
                                          }
                                            break;
                                        case SAIR:
                                            runningBiblioteca = false;
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
                        //Erro de tentar criar uma nova biblioteca
                        System.out.println("Erro de criacao da biblioteca.\n" + e );
                        break;
                    }
                //Carregar nova biblioteca
                case CARREGA_BIBLIOTECA:
                    System.out.println("Passe o nome dos arquivos que você deseja carregar:");
                    String arqUsuarios;
                    String arqLivros;
                    try{
                        //Pega o nome do arq de usuariso
                        System.out.print("Nome do arquivo de usuarios: ");
                        arqUsuarios = input.readLine();

                        //Pega o nome do arquivo de livros
                        System.out.print("Nome do arquivo de livros: ");
                        arqLivros = input.readLine();

                        //Tentativa de leitura do arquivo
                        try{
                            Biblioteca b = new Biblioteca(arqUsuarios, arqLivros);

                            //Inicializa o programa da biblioteca
                            Boolean runningBiblioteca = true;
                            while (runningBiblioteca) {
                                P3nX.showBibliotecaOptions();
                                System.out.print("Digite sua opção: ");
                                //Capturar a opcao do usuario de utilizacao da biblioteca
                                try {
                                    int optBiblioteca = Integer.parseInt(input.readLine());

                                    switch (optBiblioteca) {
                                        case MANUTENCAO:
                                            Boolean runningManutencao = true;
                                            while (runningManutencao) {
                                                P3nX.showManutencaoOptions();
                                                System.out.print("Digite sua opção: ");
                                                try {
                                                    int optManutencao = Integer.parseInt(input.readLine());
                                                    switch (optManutencao) {
                                                        case(CRIAR_ARQUIVOS):
                                                            try {
                                                                P3nX.rotinaCriaArquivos(b,input);
                                                                break;
                                                            } catch (Exception e) {
                                                                System.out.println(e);
                                                                break;
                                                            }
                                                        case(SALVAR_ESTADO):
                                                            try {
                                                                P3nX.rotinaSalvaEstado(b, input, arqLivros, arqUsuarios);
                                                                break;                                                                
                                                            } catch (Exception e) {
                                                                System.out.println(e);
                                                                break;
                                                            }    
                                                        case(ABRIR_ARQUIVOS):
                                                            try {
                                                                P3nX.rotinaLeArquivo(b, input);
                                                                break;
                                                            } catch (Exception e) {
                                                                System.out.println(e);
                                                                break;
                                                            }
                                                        case(SAIR):
                                                            runningManutencao = false;
                                                            break;
                                                        default:
                                                            System.out.println("Opcao invalida.");
                                                            break;
                                                        }    
                                                } catch (Exception e) {
                                                    System.out.println(e);
                                                }
                                            }
                                        break;
                                        case CADASTRO:
                                            Boolean runningCadastro = true;
                                            while (runningCadastro) {
                                                P3nX.showCadastroOptions();
                                                System.out.print("Digite sua opção: ");
                                                try {
                                                    int optCadastro = Integer.parseInt(input.readLine());
                                                    switch (optCadastro) {
                                                        case(CADASTRAR_USUARIO):
                                                                P3nX.rotinaCriaUsuario(b, input);                                                                
                                                                break;
                                                        case(CADASTRAR_LIVRO):
                                                            try {
                                                                P3nX.rotinaCadastraLivro(b, input);
                                                                break;                                                                
                                                            } catch (Exception e) {
                                                                System.out.println(e);
                                                                break;
                                                            }    
                                                        case(SALVAR_ARQUIVOS):
                                                            try {
                                                                P3nX.rotinaCriaArquivos(b, input);
                                                                break;
                                                            } catch (Exception e) {
                                                                System.out.println(e);
                                                                break;
                                                            }
                                                        case(SAIR):
                                                            runningCadastro = false;
                                                            break;
                                                        default:
                                                            System.out.println("Opcao invalida.");
                                                            break;
                                                    }
                                                }catch (Exception e) {
                                                    System.out.println(e);
                                                }
                                            }
                                            break;
                                        case EMPRESTIMO:
                                          Boolean runningEmprestimo = true;
                                          while (runningEmprestimo) {
                                             P3nX.showEmprestimoOptions();
                                             System.out.print("Digite sua opção: ");
                                             try {
                                                   int optEmprestimo = Integer.parseInt(input.readLine());
                                                   switch (optEmprestimo) {
                                                      case(LOCACAO_LIVRO):
                                                         try{
                                                            P3nX.rotinaLocacaoLivro(b, input);
                                                            break;                                                                
                                                         }catch (Exception e) {
                                                            System.out.println(e);
                                                            break;
                                                         }
                                                      case(DEVOLVE_LIVRO):
                                                         try {
                                                            P3nX.rotinaDevolucaoLivro(b, input);
                                                            break;                                                                
                                                         } catch (Exception e) {
                                                               System.out.println(e);
                                                               break;
                                                         }    
                                                      case(IMPRIMIR_ACERVO):
                                                         try {
                                                               System.out.println(b.getLivros().toString());
                                                               break;
                                                         } catch (Exception e) {
                                                               System.out.println(e);
                                                               break;
                                                         }
                                                      case(SAIR):
                                                         runningEmprestimo = false;
                                                         break;
                                                      default:
                                                         System.out.println("Opcao invalida.");
                                                         break;
                                                   }
                                             }catch (Exception e) {
                                                   System.out.println(e);
                                             }
                                          }
                                          break;
                                        case RELATORIO:
                                          Boolean runningRelatorio = true;
                                          while (runningRelatorio) {
                                             P3nX.showRelatorioOptions();
                                             System.out.print("Digite sua opção: ");
                                             try {
                                                   int optRelatorio = Integer.parseInt(input.readLine());
                                                   switch (optRelatorio) {
                                                      case(MOSTRAR_REGISTRO_DE_LIVROS):
                                                         try{
                                                            System.out.println(b.getLivros().toString());
                                                            break;                                                                
                                                         }catch (Exception e) {
                                                            System.out.println(e);
                                                            break;
                                                         }
                                                      case(MOSTRAR_REGISTRO_DE_USUARIOS):
                                                         try {
                                                            System.out.println(b.getUsuarios().toString());
                                                            break;                                                                
                                                         } catch (Exception e) {
                                                               System.out.println(e);
                                                               break;
                                                         }    
                                                      case(MOSTRAR_INFORMACAO_LIVRO):
                                                         try {
                                                            P3nX.rotinaMostraInformacaoLivro(b, input);
                                                            break;
                                                         } catch (Exception e) {
                                                            break;
                                                         }
                                                      case(MOSTRAR_INFORMACAO_USUARIO):
                                                      try {
                                                         P3nX.rotinaMostraInformacaoUsuario(b, input);   
                                                         break;
                                                      } catch (Exception e) {
                                                            System.out.println(e);
                                                            break;
                                                      }
                                                      case(SAIR):
                                                         runningRelatorio = false;
                                                         break;
                                                      default:
                                                         System.out.println("Opcao invalida.");
                                                         break;
                                                   }
                                             }catch (Exception e) {
                                                   System.out.println(e);
                                             }
                                          }
                                            break;
                                        case SAIR:
                                            runningBiblioteca = false;
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
                            System.out.println("Erro de inicializacao da biblioteca.\n" + e);
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
   
    //Rotinas
    private static void rotinaCriaArquivos(Biblioteca b, BufferedReader input) throws IOException{
        String salvArqUsuario;
        String salvaArqLivro;
        String opt;
        Boolean runningUser = true;
        Boolean runningLivro = true;

        while (runningUser) {
            //Pega o nome do arq de usuariso
            System.out.println("Deseja salvar o arquivo de usuarios? (s/n) ");
            opt = input.readLine();
            opt.toLowerCase();
            switch (opt) {
                case "s":
                    System.out.println("Nome do arquivo de usuarios ");
                    salvArqUsuario = input.readLine();
                    b.salvaArquivo(b.getUsuarios(), salvArqUsuario);
                    System.out.println("Arquivo usuario salvo com sucesso! ");
                    runningUser = false;         
                    break;
                case "n":
                    runningUser = false;
                    break;
                default:
                    System.out.println("Opcao invalida");
                    continue;
                }    
        }

        while (runningLivro) {
            //Pega o nome do arq de livro
            System.out.println("Deseja salvar o arquivo de livro? (s/n) ");
            opt = input.readLine();
            opt.toLowerCase();
            switch (opt) {
                case "s":
                    //Pega o nome do arquivo de livros
                    System.out.println("Nome do arquivo de livros ");
                    salvaArqLivro = input.readLine();
                    b.salvaArquivo(b.getLivros(), salvaArqLivro);
                    System.out.println("Arquivo de livro salvo com sucesso! ");
                    runningLivro = false;
                case "n":
                    runningLivro = false;
                    break;
                default:
                    System.out.println("Opcao invalida");
                    continue;
                }    
        }
    }

    private static void rotinaSalvaEstado(Biblioteca b, BufferedReader input,String arqLivros, String arqUsuarios) throws IOException{
        try {
            //Salva o estado
            b.salvaArquivo(b.getLivros(), arqLivros);
            b.salvaArquivo(b.getUsuarios(), arqUsuarios);
            System.out.print("Estado salvo no banco com sucesso!");
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    private static void rotinaLeArquivo(Biblioteca b, BufferedReader input) throws IOException{
        try {
            String leArqUsuario;
            String leArqLivro;

            //Pega o nome do arq de usuariso
            System.out.print("Nome do arquivo de usuarios para abrir ");
            leArqUsuario = input.readLine();
            //Pega o nome do arquivo de livros
            System.out.print("Nome do arquivo de livros para abrir ");
            leArqLivro = input.readLine();

            //altera os arquivos da biblioteca
            b.leArqUsu(leArqUsuario);
            b.leArqLiv(leArqLivro);
            System.out.print("Sistema atualizado com sucesso!");
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    private static void rotinaCriaUsuario(Biblioteca b, BufferedReader input){
        try {
            String nome;
            String sobreNome;
            String endereco;
            String diaStr;
            String mesStr;
            String anoStr;
            String cpfStr;

            //Recebe nome
            System.out.println("Digite seu nome: ");
            nome = input.readLine();
            nome = Valida.validaEntradaNome(nome, input);

            //Recebe sobrenome
            System.out.println("Digite seu sobrenome: ");
            sobreNome = input.readLine();
            sobreNome = Valida.validaEntradaSobreNome(sobreNome, input);

            //Recebe endereco
            System.out.println("Digite seu endereco: ");
            endereco = input.readLine();

            //Recebe dia
            System.out.println("Digite o dia do nascimento: ");
            diaStr = input.readLine();
            diaStr = Valida.validaEntradaDia(diaStr, input);
            int dia = Integer.parseInt(diaStr);

            //Recebe mes
            System.out.println("Digite o mes de nascimento: ");
            mesStr = input.readLine();
            mesStr = Valida.validaEntradaDia(mesStr, input);
            int mes = Integer.parseInt(mesStr);

            //Recebe ano
            System.out.println("Digite o ano de nascimento: ");
            anoStr = input.readLine();
            anoStr = Valida.validaEntradaAno(anoStr, input);
            int ano = Integer.parseInt(anoStr);

            //Recebe o cpf
            System.out.println("Digite o seu cpf: ");
            cpfStr = input.readLine();
            cpfStr = Valida.validaEntradaCPF(cpfStr, input);
            long cpf = ValidaCPF.toLong(cpfStr);
            
            //Cria o usuario na biblioteca
            try{
                Usuario user = new Usuario(nome, sobreNome, dia, mes, ano, cpf, endereco);
                b.cadastraUsuario(user);
                System.out.println("Usuario cadastrado com sucesso!\n" + b.getUsuario(user.getNumCPF()));
            }catch(Exception e){
                System.out.println(e);
            }
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    private static void rotinaCadastraLivro(Biblioteca b, BufferedReader input){
      try {
         String codigoStr; // Só números
         String titulo; // Qualquer coisa
         String categoria; // Pode ser o valida nome
         String copiasStr;
      
         //Recebe o codigo
         System.out.println("Digite o codigo: ");
         codigoStr = input.readLine();
         codigoStr = Valida.validaEntradaCodigo(codigoStr, input);
         int codigo = Integer.parseInt(codigoStr);

         //Recebe a quantidade de copias
         System.out.println("Digite a quantidade de copias: ");
         copiasStr = input.readLine();
         copiasStr = Valida.validaEntradaCodigo(copiasStr, input);
         int copias = Integer.parseInt(copiasStr);

         //Recebe o titulo
         System.out.println("Digite o titulo: ");
         titulo = input.readLine();

         //Recebe a categoria
         System.out.println("Digite a categoria: ");
         categoria = input.readLine();
         categoria = Valida.validaEntradaCategoria(categoria, input);
         
         //Cria o usuario na biblioteca
         try{
             Livro livro = new Livro(codigo, titulo, categoria, copias);
             b.cadastraLivro(livro);
             System.out.println("Livro cadastrado com sucesso!\n" + b.getLivro(livro.getCodigo()));
         }catch(Exception e){
             System.out.println(e);
         }
     } catch (Exception e) {
         System.out.println(e);
     }
    }

    private static void rotinaLocacaoLivro(Biblioteca b, BufferedReader input){
      try{
         //Recebe o codigo
         System.out.println("Digite o codigo do livro: ");
         String codigoStr;
         codigoStr = input.readLine();
         codigoStr = Valida.validaEntradaCodigo(codigoStr, input);
         int codigo = Integer.parseInt(codigoStr);

         //Recebe cpf
         String cpfStr;
         System.out.println("Digite o cpf: ");
         cpfStr = input.readLine();
         cpfStr = Valida.validaEntradaCPF(cpfStr, input);
         long cpf = ValidaCPF.toLong(cpfStr);

         Usuario user = b.getUsuario(cpf);
         Livro livro = b.getLivro(codigo);

         b.emprestaLivro(user, livro);
         System.out.println(livro.toString());
         System.out.println(user.toString());
         System.out.println("Quantidade de copias atualizadas " + livro.getQuantidadeDeCopias());
      }catch(IOException e1){
         System.out.println(e1);
      }catch(UsuarioNaoCadastradoEx e2){
         System.out.println("Usuario nao cadastrado.");
      }catch(LivroNaoCadastradoEx e3){
         System.out.println("Livro nao cadastrado");
      }catch(CopiaNaoDisponivelEx e4){
         System.out.println("Copia nao disponivel");
      }catch(Exception e){
         System.out.println(e);
      }
    }

    private static void rotinaDevolucaoLivro(Biblioteca b, BufferedReader input){
      try{
         //Recebe o codigo
         System.out.println("Digite o codigo do livro: ");
         String codigoStr;
         codigoStr = input.readLine();
         codigoStr = Valida.validaEntradaCodigo(codigoStr, input);
         int codigo = Integer.parseInt(codigoStr);

         //Recebe cpf
         String cpfStr;
         System.out.println("Digite o cpf: ");
         cpfStr = input.readLine();
         cpfStr = Valida.validaEntradaCPF(cpfStr, input);
         long cpf = ValidaCPF.toLong(cpfStr);

         Usuario user = b.getUsuario(cpf);
         Livro livro = b.getLivro(codigo);

         b.devolveLivro(user, livro);
         if(user.getTotalMultas() != 0){
            System.out.println("Multa por atraso a pagar: R$" + user.getTotalMultas());
         }else{
            System.out.println("Sem multas pendentes.");
         }

         System.out.println(livro.toString());
         System.out.println(user.toString());
      }catch(IOException e1){
         System.out.println(e1);
      }catch(UsuarioNaoCadastradoEx e2){
         System.out.println("Usuario nao cadastrado.");
      }catch(LivroNaoCadastradoEx e3){
         System.out.println("Livro nao cadastrado");
      }catch(NenhumaCopiaEmprestadaEx e4){
         System.out.println("Nenhuma copia emprestada");
      }catch(Exception e){
         System.out.println(e);
      }
    }

    private static void rotinaMostraInformacaoLivro(Biblioteca b, BufferedReader input){
      try{
         //Recebe o codigo
         System.out.println("Digite o codigo do livro: ");
         String codigoStr;
         codigoStr = input.readLine();
         codigoStr = Valida.validaEntradaCodigo(codigoStr, input);
         int codigo = Integer.parseInt(codigoStr);

         Livro livro = b.getLivro(codigo);
         b.getLivro(codigo);
         System.out.println(livro.toString());
      }catch(IOException e1){
         System.out.println(e1);
      }catch(LivroNaoCadastradoEx e3){
         System.out.println("Livro nao cadastrado");
      }catch(Exception e){
         System.out.println(e);
      }
    }

    private static void rotinaMostraInformacaoUsuario(Biblioteca b, BufferedReader input){
      try{
         //Recebe cpf
         String cpfStr;
         System.out.println("Digite o cpf: ");
         cpfStr = input.readLine();
         cpfStr = Valida.validaEntradaCPF(cpfStr, input);
         long cpf = ValidaCPF.toLong(cpfStr);

         Usuario user = b.getUsuario(cpf);

         b.getUsuario(cpf);
         System.out.println(user.toString());
      }catch(IOException e1){
         System.out.println(e1);
      }catch(UsuarioNaoCadastradoEx e2){
         System.out.println("Usuario nao cadastrado.");
      }catch(Exception e){
         System.out.println(e);
      }
    }

    //Mostrar menus
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

    private static void showCadastroOptions(){
            System.out.println("\nMenu de Cadastro:");
            System.out.println("1 - Cadastrar Usuário");
            System.out.println("2 - Cadastrar Livro");
            System.out.println("3 - Salvar usuarios e/ou livros");
            System.out.println("0 - Voltar ao Menu Principal");
    }

    private static void showEmprestimoOptions(){
      System.out.println("\nMenu de Emprestimo:");
      System.out.println("1 - Locacao de livro");
      System.out.println("2 - Devolucao de livro");
      System.out.println("3 - Mostrar acervo de livro");
      System.out.println("0 - Voltar ao Menu Principal");
    }

    private static void showRelatorioOptions(){
      System.out.println("\nMenu de Relatorio:");
      System.out.println("1 - Mostrar registro de todos livros");
      System.out.println("2 - Mostrar registro de todos os usuarios");
      System.out.println("3 - Mostrar informacao de livro");
      System.out.println("4 - Mostrar informacao usuario");
      System.out.println("0 - Voltar ao Menu Principal");
   }
}
