package itens;

import javax.swing.JLabel;

import characters.Character;

public class Helmet extends Item {
	public Helmet(String nome, int pontos) {
		super(nome, pontos);		
	}
	
	public void equipar(Character personagem, JLabel parteDoCorpo) {
		int atualPoint = Integer.parseInt(parteDoCorpo.getText());
		personagem.setCabeça(super.getPontos() + atualPoint);
		parteDoCorpo.setText("" /*+ parteDoCorpo.getText() + " + "*/ + personagem.getCabeça());
	}
}
