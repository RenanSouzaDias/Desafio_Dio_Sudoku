package dio;

/**
 * Classe responsável pelas regras do jogo Sudoku
 */
public class SudokuGame {

    private final Tabuleiro tabuleiro = new Tabuleiro();
    private StatusJogo status = StatusJogo.NAO_INICIADO;

       //INICIAR JOGO (OPÇÃO 1)
    public void iniciarJogo(String[] args) {

        // args no formato: linha,coluna,valor
        for (String arg : args) {
            String[] p = arg.split(",");

            int linha = Integer.parseInt(p[0]);
            int coluna = Integer.parseInt(p[1]);
            int valor = Integer.parseInt(p[2]);

            tabuleiro.definirFixo(linha, coluna, valor);
        }

        status = StatusJogo.INCOMPLETO;
        System.out.println("Jogo iniciado!");
        tabuleiro.imprimir();
    }

      // OPÇÃO 2 - COLOCAR NÚMERO
    public void colocarNumero(int linha, int coluna, int valor) {

        if (status == StatusJogo.NAO_INICIADO) {
            System.out.println("O jogo ainda não foi iniciado.");
            return;
        }

        boolean sucesso = tabuleiro.colocarNumero(linha, coluna, valor);

        if (sucesso) {
            atualizarStatus();
            System.out.println("Número inserido com sucesso.");
        }
    }

      // OPÇÃO 3 - REMOVER NÚMERO
    public void removerNumero(int linha, int coluna) {

        if (status == StatusJogo.NAO_INICIADO) {
            System.out.println("O jogo ainda não foi iniciado.");
            return;
        }

        boolean sucesso = tabuleiro.removerNumero(linha, coluna);

        if (sucesso) {
            atualizarStatus();
            System.out.println("Número removido.");
        }
    }

      // OPÇÃO 4 - MOSTRAR JOGO
    public void mostrar() {
        tabuleiro.imprimir();
    }

       //OPÇÃO 5 - STATUS DO JOGO
    public StatusJogo getStatus() {
        return status;
    }

    // OPÇÃO 6 - LIMPAR JOGADAS DO USUÁRIO
    public void limpar() {
        tabuleiro.limparJogadas();
        status = StatusJogo.INCOMPLETO;
        System.out.println("Jogadas do usuário removidas.");
    }
 // OPÇÃO 7 - FINALIZAR JOGO
    public boolean finalizar() {

        atualizarStatus(); // garante status atualizado

        if (status != StatusJogo.COMPLETO) {
            System.out.println("O jogo não está completo.");
            return false;
        }

        if (tabuleiro.temErros()) {
            System.out.println("O jogo contém erros.");
            return false;
        }

        System.out.println("Parabéns! Jogo finalizado corretamente.");
        return true;
    }

 // MÉTODO AUXILIAR
    private void atualizarStatus() {
        if (tabuleiro.estaCompleto()) {
            status = StatusJogo.COMPLETO;
        } else {
            status = StatusJogo.INCOMPLETO;
        }
    }

}
