package itens;

import java.io.Serializable;

public class Item implements Serializable{
	private String nome;
	private int pontos;
	
	public Item(String nome, int pontos) {
		this.nome = nome;
		this.setPontos(pontos);
	}
	
	public String getNome() {
		return nome;
	}
	
	public void setNome(String nome) {
		this.nome = nome;
	}
	
	public int getPontos() {
		return pontos;
	}
	
	public void setPontos(int pontos) {
		this.pontos = pontos;
	}
	
	public String toString() {
		String nomeDoItem = null;
		if (pontos != 0) {
			nomeDoItem = nome + " + " + pontos;
		} else {
			nomeDoItem = nome;
		}
		return nomeDoItem;
	}
}
