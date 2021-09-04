package unisinos.eadgb;

import java.util.LinkedList;


public class Dicionario {
	protected String palavra;
	protected LinkedList<String> definicoes;
	
	public Dicionario(String palavra, LinkedList<String> definicoes) {
		super();
		this.palavra = palavra;
		this.definicoes = definicoes;
	}
	public Dicionario(String palavra) {
		super();
		this.palavra = palavra;
	
	}

	public String getPalavra() {
		return palavra;
	}

	public void setPalavra(String palavra) {
		this.palavra = palavra;
	}

	public LinkedList<String> getDefinicoes() {
		return definicoes;
	}

	public void setDefinicoes(LinkedList<String> definicoes) {
		this.definicoes = definicoes;
	}
	
	
	
	
	
}
