package itens;

import javax.swing.JLabel;

import characters.Character;

public class Shield extends Item {
	public Shield(String nome, int pontos) {
		super(nome, pontos);		
	}
	
	public void equipar(Character personagem, JLabel parteDoCorpo) {
		int atualPoint = Integer.parseInt(parteDoCorpo.getText());
		personagem.setMembrosDefesa(super.getPontos() + atualPoint);
		parteDoCorpo.setText("" /*+ parteDoCorpo.getText() + " + "*/ + personagem.getMembrosDefesa());
	}
}
