package dio;

public class Celula { //Representa uma posição do Sudoku
	
	private Integer valor;        // null = vazio
	private final boolean fixa;   // true = número inicial
	
	public Celula(Integer valor, boolean fixa) {
		super();
		this.valor = valor;
		this.fixa = fixa;
	}

	public Integer getValor() {
		return valor;
	}

	public void setValor(Integer valor) {
		if (!fixa) {
			this.valor = valor;
		}
	}

	public boolean isFixa() {
		return fixa;
	}

	public boolean isVazia() {
	       return valor == null;
	}
	
	public void limpar() {
		if (!fixa) {
			this.valor = null;
		}
	}
}
