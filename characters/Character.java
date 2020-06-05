package characters;

import java.io.Serializable;
import java.util.ArrayList;

import javax.swing.ImageIcon;

import itens.Item;
import itens.Weapon;

public class Character implements Serializable {
	private String nome;
	private ImageIcon imagem;
	private int cabeça;
	private int corpo;
	private int membrosAtaque;
	private int membrosDefesa;
	private ArrayList<Weapon> pakcDeArmas = new ArrayList<Weapon>();
	private ArrayList<Item> pakcDeProteções = new ArrayList<Item>();	

	public Character(int h, int b, int ma, int md) {
		nome = "Thiago";
		imagem = new ImageIcon(getClass().getResource("jogador1.jpg"));
		cabeça = h;
		corpo = b;
		membrosAtaque = ma;
		membrosDefesa = md;
	}
	
	public String getNome() {
		return nome;
	}
	
	public void setImagem(ImageIcon imagem) {
		this.imagem = imagem;
	}
	
	public ImageIcon getImagem() {
		return imagem;
	}
	
	public void setNome(String nome) {
		this.nome = nome;
	}

	public int getCabeça() {
		return cabeça;
	}
	
	public void setCabeça(int cabeça) {
		this.cabeça = cabeça;
	}

	public int getCorpo() {
		return corpo;
	}
	
	public void setCorpo(int corpo) {
		this.corpo = corpo;
	}

	public int getMembrosAtaque() {
		return membrosAtaque;
	}
	
	public void setMembrosAtaque(int membrosAtaque) {
		this.membrosAtaque = membrosAtaque;
	}
	
	public int getMembrosDefesa() {
		return membrosDefesa;
	}
	
	public void setMembrosDefesa(int membrosDefesa) {
		this.membrosDefesa = membrosDefesa;
	}
	
	public ArrayList<Weapon> getPakcDeArmas() {
		return pakcDeArmas;
	}

	public void setPakcDeArmas(ArrayList<Weapon> pakcDeArmas) {
		this.pakcDeArmas = pakcDeArmas;
	}

	public ArrayList<Item> getPakcDeProteções() {
		return pakcDeProteções;
	}

	public void setPakcDeProteções(ArrayList<Item> pakcDeProteções) {
		this.pakcDeProteções = pakcDeProteções;
	}	
}
