package dio;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        SudokuGame game = new SudokuGame();

        boolean executando = true;

        while (executando) {
            System.out.println("""
                
                1 - Iniciar novo jogo
                2 - Colocar número
                3 - Remover número
                4 - Ver jogo
                5 - Ver status
                6 - Limpar
                7 - Finalizar
                """);

            int opcao = sc.nextInt();

            switch (opcao) {

                case 1 -> {
                    game.iniciarJogo(args);
                    game.mostrar();
                }

                case 2 -> {
                    System.out.print("Linha: ");
                    int linha = sc.nextInt();

                    System.out.print("Coluna: ");
                    int coluna = sc.nextInt();

                    System.out.print("Número: ");
                    int valor = sc.nextInt();

                    game.colocarNumero(linha, coluna, valor);
                }

                case 3 -> {
                    System.out.print("Linha: ");
                    int linha = sc.nextInt();

                    System.out.print("Coluna: ");
                    int coluna = sc.nextInt();

                    game.removerNumero(linha, coluna);
                }

                case 4 -> game.mostrar();

                case 5 -> System.out.println("Status: " + game.getStatus());

                case 6 -> game.limpar();

                case 7 -> {
                    game.finalizar();
                    executando = false;
                }

                default -> System.out.println("Opção inválida.");
            }
        }

        sc.close();
    }
}
