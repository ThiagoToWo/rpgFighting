package itens;

import javax.swing.JLabel;

import characters.Character;

public class Armour extends Item {
	public Armour(String nome, int pontos) {
		super(nome, pontos);		
	}
	
	public void equipar(Character personagem, JLabel parteDoCorpo) {
		int atualPoint = Integer.parseInt(parteDoCorpo.getText());
		personagem.setCorpo(super.getPontos() + atualPoint);
		parteDoCorpo.setText("" /*+ parteDoCorpo.getText() + " + "*/ + personagem.getCorpo());
	}
}
