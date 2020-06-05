package fight;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.Random;

import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.SwingConstants;

import characters.Character;
import itens.Armour;
import itens.Helmet;
import itens.Item;
import itens.Shield;
import itens.Weapon;

public class TheGame {
	
	Character playerChar = new Character(50, 60, 110, 100);
	Character adversaryChar = new Character(new Random().nextInt(40) + 30, new Random().nextInt(40) + 40,
			                  new Random().nextInt(40) + 90, new Random().nextInt(40) + 90);	
	JLabel headPoint1;
	JLabel bodyPoint1;
	JLabel memberAttackPoint1;
	JLabel memberDefensePoint1;
	JComboBox<Weapon> weaponOptions1; 
	JComboBox<Item> armourOptions1;
	JComboBox<String> targetOptions1;
	JComboBox<String> defenseOptions1;	
	JLabel headPoint2;
	JLabel bodyPoint2;
	JLabel memberAttackPoint2;
	JLabel memberDefensePoint2;
	JLabel targetOptions2;
	JLabel defenseOptions2;
	String[] targets = {"cabeça", "tronco", "membros"};
	String[] defenses = {"nenhum local", "cabeça", "tronco"};	
	JButton confirmationButton;
	JTextArea battleInfo;
	String myTarget = "cabeça";
	String whereHeAttacks;
	int hisDamage;
	int myDamage;
	String whereIDefend;
	Weapon armaVazia = new Weapon("nenhuma", 0);
	Weapon faca = new Weapon("faca tática", 5);
	Weapon espada = new Weapon("Sabre Escarlate", 20);
	Weapon bastão = new Weapon("Bastão bo", 15);
	Item proteçãoVazia = new Item("nenhuma", 0);
	Item capacete = new Helmet("Elmo de prata", 20);
	Item armadura = new Armour("Colete de couro", 15);
	Item escudo = new Shield("Escudo simples", 30);
	Weapon[] weaponsList = {armaVazia, faca, espada, bastão};	
	Item[] armours = {proteçãoVazia, capacete, armadura, escudo};		
	
 	public static void main(String[] args) {
		new TheGame().go();		
	}

	private void go() {
		
		// Criar o frame.
		JFrame frame = new JFrame("DueloRPG");
		JPanel background = new JPanel();
		//background.setBackground(Color.BLACK);
		frame.getContentPane().add(background);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		// Criar a painel do jogador.
		JLabel nameLabel = new JLabel(playerChar.getNome());
		ImageIcon player = playerChar.getImagem();
		JLabel imageLabel1 = new JLabel(player, SwingConstants.CENTER);		
		JPanel backgroundPayer = new JPanel();
		JPanel itensPanel1 = new JPanel(new GridLayout(9,2));		
		backgroundPayer.setLayout(new BoxLayout(backgroundPayer, BoxLayout.Y_AXIS));				
		background.add(BorderLayout.WEST, backgroundPayer);
		backgroundPayer.add(nameLabel);
		backgroundPayer.add(imageLabel1);
		backgroundPayer.add(itensPanel1);
		JLabel headLabel1 = new JLabel("Cabeça");
		headPoint1 = new JLabel(Integer.toString(playerChar.getCabeça()));
		JLabel bodyLabel1 = new JLabel("Tronco");
		bodyPoint1 = new JLabel("" + playerChar.getCorpo());
		JLabel memberLabel1 = new JLabel("Membros:");
		JLabel nullLabel = new JLabel();
		JLabel memberAttackLabel1 = new JLabel("~ poder de ataque");
		memberAttackPoint1 = new JLabel("" + playerChar.getMembrosAtaque());
		JLabel memberDefenseLabel1 = new JLabel("~ poder de defesa");
		memberDefensePoint1 = new JLabel("" + playerChar.getMembrosDefesa());
		JLabel weaponLabel1 = new JLabel("Armas");
		JLabel armourLabel1 = new JLabel("Proteções");
		JLabel targetLabel1 = new JLabel("Onde atacar");
		JLabel defenseLabel1 = new JLabel("Onde defender");		
		weaponOptions1 = new JComboBox<Weapon>(weaponsList);
		weaponOptions1.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				if (weaponOptions1.getSelectedIndex() != 0) {
					Weapon arma = (Weapon) weaponOptions1.getSelectedItem();
					arma.equipar(playerChar, memberAttackPoint1);
					arma.setPontos(0);
				}				
			}
		});
		
		armourOptions1 = new JComboBox<Item>(armours);
		armourOptions1.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				if (armourOptions1.getSelectedIndex() != 0) {
					if (armourOptions1.getSelectedIndex() == 1) {
						Helmet cap = (Helmet) armourOptions1.getSelectedItem();
						cap.equipar(playerChar, headPoint1);
						cap.setPontos(0);
					}
					if (armourOptions1.getSelectedIndex() == 2) {
						Armour arm = (Armour) armourOptions1.getSelectedItem();
						arm.equipar(playerChar, bodyPoint1);
						arm.setPontos(0);;
					}
					if (armourOptions1.getSelectedIndex() == 3) {
						Shield esc = (Shield) armourOptions1.getSelectedItem();
						esc.equipar(playerChar, memberDefensePoint1);
						esc.setPontos(0);;
					}
				}				
			}
		});
		targetOptions1 = new JComboBox<String>(targets);
		targetOptions1.addItemListener(new ItemListener() {
			
			@Override
			public void itemStateChanged(ItemEvent e) {	
				
				if (e.getStateChange() == ItemEvent.SELECTED) {
					myTarget = targets[targetOptions1.getSelectedIndex()];					
				}				
			}
		});
		
		defenseOptions1 = new JComboBox<String>(defenses);
		defenseOptions1.addItemListener(new ItemListener() {
			
			@Override
			public void itemStateChanged(ItemEvent e) {
				
				if (e.getStateChange() == ItemEvent.SELECTED) {
					whereIDefend = defenses[defenseOptions1.getSelectedIndex()];
				}
				
			}
		});
		
		confirmationButton = new JButton("Estou pronto");	
		confirmationButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {				
				turnResult();
			}
		});
		
		itensPanel1.add(headLabel1);
		itensPanel1.add(headPoint1);
		itensPanel1.add(bodyLabel1);
		itensPanel1.add(bodyPoint1);
		itensPanel1.add(memberLabel1);
		itensPanel1.add(nullLabel);
		itensPanel1.add(memberAttackLabel1);
		itensPanel1.add(memberAttackPoint1);
		itensPanel1.add(memberDefenseLabel1);
		itensPanel1.add(memberDefensePoint1);
		itensPanel1.add(weaponLabel1);
		itensPanel1.add(weaponOptions1);
		itensPanel1.add(armourLabel1);
		itensPanel1.add(armourOptions1);
		itensPanel1.add(targetLabel1);
		itensPanel1.add(targetOptions1);
		itensPanel1.add(defenseLabel1);
		itensPanel1.add(defenseOptions1);		
		backgroundPayer.add(confirmationButton);
		
		//Adicionar área de informações.
		battleInfo = new JTextArea(20, 30);
		background.add(BorderLayout.CENTER, battleInfo);		
		
		// Criar painel do adversário.
		adversaryChar.setNome("Inimigo");
		JLabel adversaryName = new JLabel(adversaryChar.getNome());
		ImageIcon adversary = adversaryChar.getImagem();
		JLabel imageLabel2 = new JLabel(adversary, SwingConstants.CENTER);		
		JPanel backgroundAdversary = new JPanel();
		JPanel itensPanel2 = new JPanel(new GridLayout(9,2));		
		backgroundAdversary.setLayout(new BoxLayout(backgroundAdversary, BoxLayout.Y_AXIS));				
		background.add(BorderLayout.EAST, backgroundAdversary);
		backgroundAdversary.add(adversaryName);
		backgroundAdversary.add(imageLabel2);
		backgroundAdversary.add(itensPanel2);
		JLabel headLabel2 = new JLabel("Cabeça");
		headPoint2 = new JLabel("" + adversaryChar.getCabeça());
		JLabel bodyLabel2 = new JLabel("Tronco");
		bodyPoint2 = new JLabel("" + adversaryChar.getCorpo());
		JLabel memberLabel2 = new JLabel("Membros:");
		JLabel nullLabel2 = new JLabel();
		JLabel memberAttackLabel2 = new JLabel("~ poder de ataque");
		memberAttackPoint2 = new JLabel("" + adversaryChar.getMembrosAtaque());
		JLabel memberDefenseLabel2 = new JLabel("~ poder de defesa");
		memberDefensePoint2 = new JLabel("" + adversaryChar.getMembrosDefesa());
		JLabel weaponLabel2 = new JLabel("Armas");
		JLabel armourLabel2 = new JLabel("Proteções");
		JLabel targetLabel2 = new JLabel("Onde atacar");
		JLabel defenseLabel2 = new JLabel("Onde defender");
		JLabel weaponOptions2 = new JLabel("weapons");		
		JLabel armourOptions2 = new JLabel("armours");
		targetOptions2 = new JLabel("targets");
		defenseOptions2 = new JLabel("defenses");		
		itensPanel2.add(headLabel2);
		itensPanel2.add(headPoint2);
		itensPanel2.add(bodyLabel2);
		itensPanel2.add(bodyPoint2);
		itensPanel2.add(memberLabel2);
		itensPanel2.add(nullLabel2);
		itensPanel2.add(memberAttackLabel2);
		itensPanel2.add(memberAttackPoint2);
		itensPanel2.add(memberDefenseLabel2);
		itensPanel2.add(memberDefensePoint2);
		itensPanel2.add(weaponLabel2);
		itensPanel2.add(weaponOptions2);
		itensPanel2.add(armourLabel2);
		itensPanel2.add(armourOptions2);
		itensPanel2.add(targetLabel2);
		itensPanel2.add(targetOptions2);
		itensPanel2.add(defenseLabel2);
		itensPanel2.add(defenseOptions2);
				
		frame.setLocation(500,300);
		frame.setResizable(true);
		frame.pack();
		frame.setVisible(true);
	}
	
	public void buildAdversary() {
		int head = (int) (Math.random() * 40 + 30);
		int body = (int) (Math.random() * 40 + 40);
		int memberAttack = (int) (Math.random() * 40 + 90);
		int memberDefense = (int) (Math.random() * 40 + 90);
		
		adversaryChar.setCabeça(head);
		adversaryChar.setCorpo(body);
		adversaryChar.setMembrosAtaque(memberAttack);
		adversaryChar.setMembrosDefesa(memberDefense);
		headPoint2.setText("" + adversaryChar.getCabeça());
		bodyPoint2.setText("" + adversaryChar.getCorpo());
		memberAttackPoint2.setText("" + adversaryChar.getMembrosAtaque());
		memberDefensePoint2.setText("" + adversaryChar.getMembrosDefesa());
	}
	
	public void printResult(String a, int b, String c, int d) {
		String result = playerChar.getNome() + ": " + a + "-" + b + "\t" 
                       + adversaryChar.getNome() + ": " + c + "-" + d;
        battleInfo.setText(result);	
	}
		
	public void turnResult() {
				
		int randomAttack;
		int randomDefense;
			
		//meus resultados.	
		randomAttack = (int) (Math.random() * 3);
		whereHeAttacks = targets[randomAttack];	
		// se eu defender.
		if (whereHeAttacks.equals(whereIDefend)) {			
			myDamage =  adversaryChar.getMembrosAtaque() / playerChar.getMembrosDefesa();
			switch (randomAttack) {
			case 0:
				if (playerChar.getCabeça() - myDamage <= 0) {
					headPoint1.setText("0");
					//printResult(whereHeAttacks, myDamage, myTarget, hisDamage);
					battleInfo.setText(battleInfo.getText() + "\n Você perdeu!");
					confirmationButton.setEnabled(false);
					break;
				} else {					
					playerChar.setCabeça(playerChar.getCabeça() -  myDamage);
					headPoint1.setText("" + playerChar.getCabeça());
					bodyPoint1.setText("" + playerChar.getCorpo());
					memberAttackPoint1.setText("" + playerChar.getMembrosAtaque());
					memberDefensePoint1.setText("" + playerChar.getMembrosDefesa());
					//printResult(whereHeAttacks, myDamage, myTarget, hisDamage);
					break;					
				}
				
			case 1:
				if (playerChar.getCorpo() -  myDamage <= 0) {
					bodyPoint1.setText("0");
					//printResult(whereHeAttacks, myDamage, myTarget, hisDamage);
					battleInfo.setText(battleInfo.getText() + "\n Você perdeu!");
					confirmationButton.setEnabled(false);
					break;
				} else {					
					playerChar.setCorpo(playerChar.getCorpo() -  myDamage);
					headPoint1.setText("" + playerChar.getCabeça());
					bodyPoint1.setText("" + playerChar.getCorpo());
					memberAttackPoint1.setText("" + playerChar.getMembrosAtaque());
					memberDefensePoint1.setText("" + playerChar.getMembrosDefesa());
					//printResult(whereHeAttacks, myDamage, myTarget, hisDamage);
					break;
				}
				
			case 2:
				if (playerChar.getMembrosAtaque() -  myDamage <= 0 || playerChar.getMembrosDefesa() - myDamage <= 0) {
					memberAttackPoint1.setText("0");
					memberDefensePoint1.setText("0");
					//printResult(whereHeAttacks, myDamage, myTarget, hisDamage);
					battleInfo.setText(battleInfo.getText() + "\n Você perdeu!");
					confirmationButton.setEnabled(false);
					break;
				} else {
					playerChar.setMembrosAtaque(playerChar.getMembrosAtaque() -  myDamage);
					playerChar.setMembrosDefesa(playerChar.getMembrosDefesa() - myDamage);
					headPoint1.setText("" + playerChar.getCabeça());
					bodyPoint1.setText("" + playerChar.getCorpo());
					memberAttackPoint1.setText("" + playerChar.getMembrosAtaque());
					memberDefensePoint1.setText("" + playerChar.getMembrosDefesa());
					//printResult(whereHeAttacks, myDamage, myTarget, hisDamage);
				}
			}			
		} else {
			//se eu não defender.
			switch (randomAttack) {
			case 0:
				myDamage = adversaryChar.getMembrosAtaque() / playerChar.getCabeça();
				if (playerChar.getCabeça() - myDamage <= 0) {
					headPoint1.setText("0");
					//printResult(whereHeAttacks, myDamage, myTarget, hisDamage);
					battleInfo.setText(battleInfo.getText() + "\n Você perdeu!");
					confirmationButton.setEnabled(false);
					break;
				} else {
					playerChar.setCabeça(playerChar.getCabeça() -  myDamage);
					headPoint1.setText("" + playerChar.getCabeça());
					bodyPoint1.setText("" + playerChar.getCorpo());
					memberAttackPoint1.setText("" + playerChar.getMembrosAtaque());
					memberDefensePoint1.setText("" + playerChar.getMembrosDefesa());
					//printResult(whereHeAttacks, myDamage, myTarget, hisDamage);
					break;					
				}
				
			case 1:	
				myDamage = adversaryChar.getMembrosAtaque() / playerChar.getCorpo();
				if (playerChar.getCorpo() -  myDamage <= 0) {
					bodyPoint1.setText("0");
					//printResult(whereHeAttacks, myDamage, myTarget, hisDamage);
					battleInfo.setText(battleInfo.getText() + "\n Você perdeu!");
					confirmationButton.setEnabled(false);
					break;
				} else {					
					playerChar.setCorpo(playerChar.getCorpo() -  myDamage);
					headPoint1.setText("" + playerChar.getCabeça());
					bodyPoint1.setText("" + playerChar.getCorpo());
					memberAttackPoint1.setText("" + playerChar.getMembrosAtaque());
					memberDefensePoint1.setText("" + playerChar.getMembrosDefesa());
					//printResult(whereHeAttacks, myDamage, myTarget, hisDamage);
					break;
				}
				
			case 2:
				myDamage = adversaryChar.getMembrosAtaque() / ((playerChar.getMembrosAtaque() + playerChar.getMembrosDefesa()) / 2);
				if (playerChar.getMembrosAtaque() -  myDamage <= 0 || playerChar.getMembrosDefesa() - myDamage <= 0) {
					memberAttackPoint1.setText("0");
					memberDefensePoint1.setText("0");
					//printResult(whereHeAttacks, myDamage, myTarget, hisDamage);
					battleInfo.setText(battleInfo.getText() + "\n Você perdeu!");
					confirmationButton.setEnabled(false);
					break;
				} else {
					playerChar.setMembrosAtaque(playerChar.getMembrosAtaque() -  myDamage);
					playerChar.setMembrosDefesa(playerChar.getMembrosDefesa() - myDamage);
					headPoint1.setText("" + playerChar.getCabeça());
					bodyPoint1.setText("" + playerChar.getCorpo());
					memberAttackPoint1.setText("" + playerChar.getMembrosAtaque());
					memberDefensePoint1.setText("" + playerChar.getMembrosDefesa());
					//printResult(whereHeAttacks, myDamage, myTarget, hisDamage);
				}
				
				
			}
		}
			// resultados do adversário.	
			randomDefense = (int) (Math.random() * 3);
			String whereHeDefends = defenses[randomDefense];
			//se ele defender.
			if (whereHeDefends.equals(myTarget)) {
				hisDamage = playerChar.getMembrosAtaque() / adversaryChar.getMembrosDefesa();
				switch (targetOptions1.getSelectedIndex()) {
				case 0:
					if (adversaryChar.getCabeça() - hisDamage <= 0) {
						headPoint2.setText("0");
						printResult(whereHeAttacks, myDamage, myTarget, hisDamage);
						battleInfo.setText(battleInfo.getText() + "\n Você venceu!");
						buildAdversary();
						break;
					} else {
						adversaryChar.setCabeça(adversaryChar.getCabeça() -  hisDamage);
						headPoint2.setText("" + adversaryChar.getCabeça());
						bodyPoint2.setText("" + adversaryChar.getCorpo());
						memberAttackPoint2.setText("" + adversaryChar.getMembrosAtaque());
						memberDefensePoint2.setText("" + adversaryChar.getMembrosDefesa());
						printResult(whereHeAttacks, myDamage, myTarget, hisDamage);
						break;
					}
					
				case 1:
					if (adversaryChar.getCorpo() - hisDamage <= 0) {
						bodyPoint2.setText("0");
						printResult(whereHeAttacks, myDamage, myTarget, hisDamage);
						battleInfo.setText(battleInfo.getText() + "\n Você venceu!");
						buildAdversary();
						break;
					} else {
						adversaryChar.setCorpo(adversaryChar.getCorpo() -  hisDamage);
						headPoint2.setText("" + adversaryChar.getCabeça());
						bodyPoint2.setText("" + adversaryChar.getCorpo());
						memberAttackPoint2.setText("" + adversaryChar.getMembrosAtaque());
						memberDefensePoint2.setText("" + adversaryChar.getMembrosDefesa());
						printResult(whereHeAttacks, myDamage, myTarget, hisDamage);
						break;
					}
					
				case 2: 
					if (adversaryChar.getMembrosAtaque() - hisDamage <= 0 || adversaryChar.getMembrosDefesa() - hisDamage <= 0) {
						memberAttackPoint2.setText("0");
						memberDefensePoint2.setText("0");
						printResult(whereHeAttacks, myDamage, myTarget, hisDamage);
						battleInfo.setText(battleInfo.getText() + "\n Você venceu!");
						buildAdversary();
						break;
					} else {
						adversaryChar.setMembrosAtaque(adversaryChar.getMembrosAtaque() -  hisDamage);
						adversaryChar.setMembrosDefesa(adversaryChar.getMembrosDefesa() - hisDamage);
						headPoint2.setText("" + adversaryChar.getCabeça());
						bodyPoint2.setText("" + adversaryChar.getCorpo());
						memberAttackPoint2.setText("" + adversaryChar.getMembrosAtaque());
						memberDefensePoint2.setText("" + adversaryChar.getMembrosDefesa());
						printResult(whereHeAttacks, myDamage, myTarget, hisDamage);
					}
					
				}
			} else {
				//se ele não defender.
				switch (targetOptions1.getSelectedIndex()) {
				case 0:
					hisDamage = playerChar.getMembrosAtaque() / adversaryChar.getCabeça();
					if (adversaryChar.getCabeça() - hisDamage <= 0) {
						headPoint2.setText("0");
						printResult(whereHeAttacks, myDamage, myTarget, hisDamage);
						battleInfo.setText(battleInfo.getText() + "\n Você venceu!");
						buildAdversary();
						break;
					} else {
						adversaryChar.setCabeça(adversaryChar.getCabeça() -  hisDamage);
						headPoint2.setText("" + adversaryChar.getCabeça());
						bodyPoint2.setText("" + adversaryChar.getCorpo());
						memberAttackPoint2.setText("" + adversaryChar.getMembrosAtaque());
						memberDefensePoint2.setText("" + adversaryChar.getMembrosDefesa());
						printResult(whereHeAttacks, myDamage, myTarget, hisDamage);
						break;
					}
					
				case 1:	 
					hisDamage = playerChar.getMembrosAtaque() / adversaryChar.getCorpo();
					if (adversaryChar.getCorpo() - hisDamage <= 0) {
						bodyPoint2.setText("0");
						printResult(whereHeAttacks, myDamage, myTarget, hisDamage);
						battleInfo.setText(battleInfo.getText() + "\n Você venceu!");
						buildAdversary();
						break;
					} else {
						adversaryChar.setCorpo(adversaryChar.getCorpo() -  hisDamage);
						headPoint2.setText("" + adversaryChar.getCabeça());
						bodyPoint2.setText("" + adversaryChar.getCorpo());
						memberAttackPoint2.setText("" + adversaryChar.getMembrosAtaque());
						memberDefensePoint2.setText("" + adversaryChar.getMembrosDefesa());
						printResult(whereHeAttacks, myDamage, myTarget, hisDamage);
						break;
					}
					
				case 2: 
					hisDamage = playerChar.getMembrosAtaque() / ((adversaryChar.getMembrosAtaque() + adversaryChar.getMembrosDefesa()) / 2);
					if (adversaryChar.getMembrosAtaque() - hisDamage <= 0 || adversaryChar.getMembrosDefesa() - hisDamage <= 0) {
						memberAttackPoint2.setText("0");
						memberDefensePoint2.setText("0");
						printResult(whereHeAttacks, myDamage, myTarget, hisDamage);
						battleInfo.setText(battleInfo.getText() + "\n Você venceu!");
						buildAdversary();
						break;
					} else {
						adversaryChar.setMembrosAtaque(adversaryChar.getMembrosAtaque() -  hisDamage);
						adversaryChar.setMembrosDefesa(adversaryChar.getMembrosDefesa() - hisDamage);
						headPoint2.setText("" + adversaryChar.getCabeça());
						bodyPoint2.setText("" + adversaryChar.getCorpo());
						memberAttackPoint2.setText("" + adversaryChar.getMembrosAtaque());
						memberDefensePoint2.setText("" + adversaryChar.getMembrosDefesa());
						printResult(whereHeAttacks, myDamage, myTarget, hisDamage);
					}
					
				}
			}
		targetOptions2.setText(whereHeAttacks);
		defenseOptions2.setText(whereHeDefends);		
		/* diminuir código repetido nas informações de turno	 
		 * informações e contagens de nível
		 * implementar armas do adversário
		 */
		
	}
}

