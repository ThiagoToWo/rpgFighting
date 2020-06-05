package characters;

import java.io.Serializable;
import java.util.ArrayList;

import javax.swing.ImageIcon;

import itens.Item;
import itens.Weapon;

public class Character implements Serializable {
	private String nome;
	private ImageIcon imagem;
	private int cabe�a;
	private int corpo;
	private int membrosAtaque;
	private int membrosDefesa;
	private ArrayList<Weapon> pakcDeArmas = new ArrayList<Weapon>();
	private ArrayList<Item> pakcDeProte��es = new ArrayList<Item>();	

	public Character(int h, int b, int ma, int md) {
		nome = "Thiago";
		imagem = new ImageIcon(getClass().getResource("jogador1.jpg"));
		cabe�a = h;
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

	public int getCabe�a() {
		return cabe�a;
	}
	
	public void setCabe�a(int cabe�a) {
		this.cabe�a = cabe�a;
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

	public ArrayList<Item> getPakcDeProte��es() {
		return pakcDeProte��es;
	}

	public void setPakcDeProte��es(ArrayList<Item> pakcDeProte��es) {
		this.pakcDeProte��es = pakcDeProte��es;
	}	
}
