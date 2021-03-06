package br.com.codenation;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

public class Menu {

    public static void main(String[] args) {
        menuOptions();
    }

    private static void menu() {
        System.out.println("\tGERENCIADOR DE TIMES DE FUTEBOL\t");
        System.out.println("Digite a opção desejada: ");
        System.out.println("1 - Incluir Time");
        System.out.println("2 - Incluir Jogador");
        System.out.println("3 - Definir Capitão");
        System.out.println("4 - Buscar Capitão do Time");
        System.out.println("5 - Buscar Nome Jogador");
        System.out.println("6 - Buscar Nome Time");
        System.out.println("7 - Buscar Jogadores do Time");
        System.out.println("8 - Buscar Melhor Jogador do Time");
        System.out.println("9 - Buscar Jogador Mais Velho");
        System.out.println("10 - Buscar Times");
        System.out.println("11 - Buscar Maior Salário");
        System.out.println("12 - Buscar Salário do Jogador");
        System.out.println("13 - Buscar Top Jogadores");
        System.out.println("14 - Buscar Cor da Camiseta do Time de Fora");
        System.out.println("0 - Sair");
    }

    private static void menuOptions() {
        DesafioMeuTimeApplication desafioMeuTimeApplication = new DesafioMeuTimeApplication();
        int opcao;
        Scanner input = new Scanner(System.in);

        do {
            menu();
            opcao =  input.nextInt();
            switch(opcao){
                case 1:
                    System.out.println("Digite o ID do time: ");
                    Long id = input.nextLong();
                    input.nextLine();

                    System.out.println("Digite o nome do time: ");
                    String nome = input.nextLine();

                    System.out.println("Digite a data de criação do time: ");
                    DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
                    String dataCriacao = input.next();
                    LocalDate localDate = LocalDate.parse(dataCriacao,dateTimeFormatter);
                    input.nextLine();

                    System.out.println("Digite a cor do uniforme principal: ");
                    String corUniformePrincipal = input.nextLine();

                    System.out.println("Digite a cor do uniforme secundário: ");
                    String corUniformeSecundario = input.nextLine();
                    desafioMeuTimeApplication.incluirTime(id,nome,localDate,corUniformePrincipal,corUniformeSecundario);
                    break;

                case 2:
                    System.out.println("Digite o ID do jogador: ");
                    id = input.nextLong();
                    input.nextLine();

                    System.out.println("Digite o ID do time: ");
                    Long idTime = input.nextLong();
                    input.nextLine();

                    System.out.println("Digite o nome do jogador: ");
                    nome = input.nextLine();

                    System.out.println("Digite a data de nascimento do jogador ");
                    dateTimeFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
                    String dataNascimento = input.next();
                    localDate = LocalDate.parse(dataNascimento,dateTimeFormatter);
                    input.nextLine();

                    System.out.println("Digite o nível de habilidade (0 a 100) ");
                    Integer nivelHabilidade = input.nextInt();
                    input.nextLine();

                    System.out.println("Digite o salário do jogador: ");
                    BigDecimal salario = input.nextBigDecimal();

                    desafioMeuTimeApplication.incluirJogador(id,idTime,nome,localDate,nivelHabilidade,salario);
                    break;

                case 3:
                    System.out.println("Digite o id do jogador que será capitão: ");
                    id = input.nextLong();
                    input.nextLine();
                    desafioMeuTimeApplication.definirCapitao(id);
                    break;

                case 4:
                    System.out.println("Digite o id do time: ");
                    id = input.nextLong();
                    input.nextLine();
                    desafioMeuTimeApplication.buscarCapitaoDoTime(id);
                    break;

                case 5:
                    System.out.println("Digite o id do jogador: ");
                    id = input.nextLong();
                    input.nextLine();
                    desafioMeuTimeApplication.buscarNomeJogador(id);
                    break;

                case 6:
                    System.out.println("Digite o id do time: ");
                    id = input.nextLong();
                    input.nextLine();
                    desafioMeuTimeApplication.buscarNomeTime(id);
                    break;

                case 7:
                    System.out.println("Digite o id do time: ");
                    id = input.nextLong();
                    input.nextLine();
                    desafioMeuTimeApplication.buscarJogadoresDoTime(id);
                    break;

                case 8:
                    System.out.println("Digite o id do time: ");
                    id = input.nextLong();
                    input.nextLine();
                    desafioMeuTimeApplication.buscarMelhorJogadorDoTime(id);
                    break;

                case 9:
                    System.out.println("Digite o id do time: ");
                    id = input.nextLong();
                    input.nextLine();
                    desafioMeuTimeApplication.buscarJogadorMaisVelho(id);
                    break;

                case 10:
                    System.out.println("TIMES");
                    desafioMeuTimeApplication.buscarTimes();
                    break;

                case 11:
                    System.out.println("Digite o id do time: ");
                    id = input.nextLong();
                    input.nextLine();
                    desafioMeuTimeApplication.buscarJogadorMaiorSalario(id);
                    break;

                case 12:
                    System.out.println("Digite o id do jogador: ");
                    id = input.nextLong();
                    input.nextLine();
                    desafioMeuTimeApplication.buscarSalarioDoJogador(id);
                    break;

                case 13:
                    System.out.println("Jogadores TOP");
                    Integer top  = input.nextInt();
                    input.nextLine();
                    desafioMeuTimeApplication.buscarTopJogadores(top);
                    break;
            }
        }while (opcao!=0);
    }



}
