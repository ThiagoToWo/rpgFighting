package itens;

import javax.swing.JLabel;

import characters.Character;

public class Weapon extends Item {

	public Weapon(String nome, int pontos) {
		super(nome, pontos);		
	}
	
	public void equipar(Character personagem, JLabel parteDoCorpo) {
		int atualPoint = Integer.parseInt(parteDoCorpo.getText());
		personagem.setMembrosAtaque(super.getPontos() + atualPoint);
		parteDoCorpo.setText("" /*+ parteDoCorpo.getText() + " + "*/ + personagem.getMembrosAtaque());
	}
}
