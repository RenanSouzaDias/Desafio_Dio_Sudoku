package dio;

import java.util.stream.IntStream;

public class Tabuleiro { 
    // Representa o tabuleiro 9x9 do Sudoku
    private final Celula[][] jogo = new Celula[9][9];

    public Tabuleiro() {
        // Inicializa todas as células como vazias e não fixas
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                jogo[i][j] = new Celula(null, false);
            }
        }
    }

    public Celula getCelula(int linha, int coluna) {
        return jogo[linha][coluna];
    }

    // Define um número fixo (usado na inicialização do jogo)
    public void definirFixo(int linha, int coluna, int valor) {
        jogo[linha][coluna] = new Celula(valor, true);
    }

    // Coloca um número informado pelo jogador
    public boolean colocarNumero(int linha, int coluna, int valor) {

        // Validação básica
        if (linha < 0 || linha > 8 || coluna < 0 || coluna > 8 || valor < 1 || valor > 9) {
            System.out.println("Posição ou valor inválido.");
            return false;
        }

        Celula celula = jogo[linha][coluna];

        // Não permite alterar números fixos
        if (celula.isFixa()) {
            System.out.println("Essa posição possui um número fixo.");
            return false;
        }

        // Não permite sobrescrever números já informados
        if (celula.getValor() != null) {
            System.out.println("Essa posição já está preenchida.");
            return false;
        }

        // Verifica conflito na linha
        boolean conflitoLinha =
                IntStream.range(0, 9)
                        .anyMatch(c -> {
                            Integer v = jogo[linha][c].getValor();
                            return v != null && v == valor;
                        });	
                        

        // Verifica conflito na coluna
        boolean conflitoColuna =
                IntStream.range(0, 9)
                        .anyMatch(r -> {
                        	 Integer v = jogo[r][coluna].getValor();
                             return v != null && v == valor;
                         });	
                        

        // Verifica conflito no bloco 3x3
        int blocoLinha = (linha / 3) * 3;
        int blocoColuna = (coluna / 3) * 3;

        boolean conflitoBloco =
                IntStream.range(blocoLinha, blocoLinha + 3)
                        .anyMatch(r ->
                                IntStream.range(blocoColuna, blocoColuna + 3)
                                        .anyMatch(c -> {
                                            Integer v = jogo[r][c].getValor();
                                            return v != null && v == valor;
                                        })
                        );

        if (conflitoLinha || conflitoColuna || conflitoBloco) {
            System.out.println("Jogada inválida: número em conflito.");
            return false;
        }

        // Jogada válida
        celula.setValor(valor);
        return true;
    }

    // Remove um número (apenas se não for fixo)
    public boolean removerNumero(int linha, int coluna) {
        Celula celula = jogo[linha][coluna];

        if (celula.isFixa()) {
            System.out.println("Não é possível remover um número fixo.");
            return false;
        }

        celula.setValor(null);
        return true;
    }

    // Remove todos os números não fixos
    public void limpar() {
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                if (!jogo[i][j].isFixa()) {
                    jogo[i][j].setValor(null);
                }
            }
        }
    }
    
    public void limparJogadas() {

        for (int linha = 0; linha < 9; linha++) {
            for (int coluna = 0; coluna < 9; coluna++) {

                Celula celula = jogo[linha][coluna];

                // Se a célula NÃO for fixa, limpamos o valor
                if (!celula.isFixa()) {
                    celula.limpar();
                }
            }
        }
    }

    public boolean estaCompleto() {

        for (int linha = 0; linha < 9; linha++) {
            for (int coluna = 0; coluna < 9; coluna++) {

                if (jogo[linha][coluna].isVazia()) {
                    return false; // achou espaço vazio
                }
            }
        }
        return true; // não achou nenhum vazio
    }


    public boolean temErros() {

        // Percorre todas as células do tabuleiro
        for (int linha = 0; linha < 9; linha++) {
            for (int coluna = 0; coluna < 9; coluna++) {

                Integer valor = jogo[linha][coluna].getValor();

                // Ignora células vazias
                if (valor == null) continue;

                // Verifica a linha
                for (int c = 0; c < 9; c++) {
                    if (c != coluna && valor.equals(jogo[linha][c].getValor())) {
                        return true;
                    }
                }

                // Verifica a coluna
                for (int r = 0; r < 9; r++) {
                    if (r != linha && valor.equals(jogo[r][coluna].getValor())) {
                        return true;
                    }
                }

                // Verifica o bloco 3x3
                int inicioLinha = (linha / 3) * 3;
                int inicioColuna = (coluna / 3) * 3;

                for (int r = inicioLinha; r < inicioLinha + 3; r++) {
                    for (int c = inicioColuna; c < inicioColuna + 3; c++) {

                        if ((r != linha || c != coluna)
                                && valor.equals(jogo[r][c].getValor())) {
                            return true;
                        }
                    }
                }
            }
        }

        // Se percorreu tudo e não achou conflito
        return false;
    }

    
    // Impressão do tabuleiro no terminal
    public void imprimir() {
        System.out.println("\n    0 1 2   3 4 5   6 7 8");
        for (int i = 0; i < 9; i++) {

            if (i % 3 == 0) {
                System.out.println("  ---------------------");
            }

            System.out.print(i + " | ");

            for (int j = 0; j < 9; j++) {

                if (j % 3 == 0 && j != 0) {
                    System.out.print("| ");
                }

                Integer v = jogo[i][j].getValor();
                System.out.print((v == null ? "." : v) + " ");
            }
            System.out.println("|");
        }
        System.out.println("  ---------------------");
    }
}
