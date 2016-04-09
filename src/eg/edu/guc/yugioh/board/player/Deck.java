package eg.edu.guc.yugioh.board.player;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.management.MonitorInfo;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Random;
import java.util.Scanner;

import javax.print.DocFlavor.INPUT_STREAM;

import eg.edu.guc.yugioh.cards.Card;
import eg.edu.guc.yugioh.cards.Location;
import eg.edu.guc.yugioh.cards.MonsterCard;
import eg.edu.guc.yugioh.cards.spells.CardDestruction;
import eg.edu.guc.yugioh.cards.spells.ChangeOfHeart;
import eg.edu.guc.yugioh.cards.spells.DarkHole;
import eg.edu.guc.yugioh.cards.spells.GracefulDice;
import eg.edu.guc.yugioh.cards.spells.HarpieFeatherDuster;
import eg.edu.guc.yugioh.cards.spells.HeavyStorm;
import eg.edu.guc.yugioh.cards.spells.MagePower;
import eg.edu.guc.yugioh.cards.spells.MonsterReborn;
import eg.edu.guc.yugioh.cards.spells.PotOfGreed;
import eg.edu.guc.yugioh.cards.spells.Raigeki;
import eg.edu.guc.yugioh.cards.spells.SpellCard;
import eg.edu.guc.yugioh.exceptions.EmptyFieldException;
import eg.edu.guc.yugioh.exceptions.MissingFieldException;
import eg.edu.guc.yugioh.exceptions.UnexpectedFormatException;
import eg.edu.guc.yugioh.exceptions.UnknownCardTypeException;
import eg.edu.guc.yugioh.exceptions.UnknownSpellCardException;

public class Deck {
	private static ArrayList<Card> monsters;
	private static ArrayList<Card> spells;
	private ArrayList<Card> deck;
	private static String monstersPath = "Database-Monsters.csv";
	private static String spellsPath = "Database-Spells.csv";

	public static ArrayList<Card> getMonsters() {
		return monsters;
	}

	public static ArrayList<Card> getSpells() {
		return spells;
	}

	public static String getMonstersPath() {
		return monstersPath;
	}

	public static void setMonstersPath(String monstersPath) {
		Deck.monstersPath = monstersPath;
	}

	public static String getSpellsPath() {
		return spellsPath;
	}

	public static void setSpellsPath(String spellsPath) {
		Deck.spellsPath = spellsPath;
	}

	public Deck() throws IOException , FileNotFoundException , UnexpectedFormatException, CloneNotSupportedException  {
		Scanner sc = new Scanner(System.in);
		
			try {
				monsters = loadCardsFromFile(monstersPath);
			} catch (FileNotFoundException | UnexpectedFormatException e1) {
				System.out.println("please enter the right path");
				monstersPath = sc.nextLine();
				try {
					monsters = loadCardsFromFile(monstersPath);
				} catch (FileNotFoundException | UnexpectedFormatException e2) {
					System.out.println("please enter the right path");
					monstersPath = sc.nextLine();
					try {
						monsters = loadCardsFromFile(monstersPath);
					} catch (FileNotFoundException | UnexpectedFormatException e3) {
						System.out.println("please enter the right path");
						monstersPath = sc.nextLine();
						try {
							monsters = loadCardsFromFile(monstersPath);
						} catch (FileNotFoundException |UnexpectedFormatException e4) {
							e4.printStackTrace();
							throw e4;
					}
				}
			}
			}
			
			try {
				spells = loadCardsFromFile(spellsPath);
			} catch (FileNotFoundException | UnexpectedFormatException e1) {
				System.out.println("please enter the right path");
				spellsPath = sc.nextLine();
				try {
					spells = loadCardsFromFile(spellsPath);
				} catch (FileNotFoundException | UnexpectedFormatException e2) {
					System.out.println("please enter the right path");
					spellsPath = sc.nextLine();
					try {
						spells = loadCardsFromFile(spellsPath);
					} catch (FileNotFoundException | UnexpectedFormatException e3) {
						System.out.println("please enter the right path");
						spellsPath = sc.nextLine();
						try {
							spells = loadCardsFromFile(spellsPath);
						} catch (FileNotFoundException |UnexpectedFormatException e4) {
							e4.printStackTrace();
							throw e4;
					}
				}
			}
			}
			
		deck = new ArrayList<Card>();
		buildDeck(monsters, spells);
		shuffleDeck();
	}

	public ArrayList<Card> loadCardsFromFile(String path) throws IOException , FileNotFoundException , UnexpectedFormatException {
		ArrayList<Card> lc = new ArrayList<Card>();
		String currentLine = "";
		int currentLineNumber = 0;
		FileReader fileReader = new FileReader(path);
		BufferedReader br = new BufferedReader(fileReader);
		while ((currentLine = br.readLine()) != null) {
			currentLineNumber++;
			String[] s = currentLine.split(",");
			for (int i = 0; i < s.length; i++) {
				if ((s[i].length() == 0 || s[i].equals(" ")))
					throw new EmptyFieldException(path, currentLineNumber,
							i + 1);
			}
			 if (s[0].compareTo("Monster") == 0) {
				 if(s.length<6)
					 throw new MissingFieldException(path, currentLineNumber);
				int level = Integer.parseInt(s[5]);
				// System.out.println(level);
				int attack = Integer.parseInt(s[3]);
				// System.out.println(attack);
				int defence = Integer.parseInt(s[4]);
				// System.out.println(defence);
				MonsterCard c = new MonsterCard(s[1], s[2], level, attack,
						defence);
				lc.add(c);

			} else if (s[0].compareTo("Spell") == 0) {
				if(s.length<3)
					 throw new MissingFieldException(path, currentLineNumber);
				switch (s[1]) {
				case "Card Destruction":
					CardDestruction s1 = new CardDestruction(s[1], s[2]);
					lc.add(s1);
					break;
				case "Change Of Heart":
					ChangeOfHeart s2 = new ChangeOfHeart(s[1], s[2]);
					lc.add(s2);
					break;
				case "Dark Hole":
					DarkHole s3 = new DarkHole(s[1], s[2]);
					lc.add(s3);
					break;
				case "Graceful Dice":
					GracefulDice s4 = new GracefulDice(s[1], s[2]);
					lc.add(s4);
					break;
				case "Harpie's Feather Duster":
					HarpieFeatherDuster s5 = new HarpieFeatherDuster(s[1], s[2]);
					lc.add(s5);
					break;
				case "Heavy Storm":
					HeavyStorm s6 = new HeavyStorm(s[1], s[2]);
					lc.add(s6);
					break;
				case "Mage Power":
					MagePower s7 = new MagePower(s[1], s[2]);
					lc.add(s7);
					break;
				case "Monster Reborn":
					MonsterReborn s8 = new MonsterReborn(s[1], s[2]);
					lc.add(s8);
					break;
				case "Pot of Greed":
					PotOfGreed s9 = new PotOfGreed(s[1], s[2]);
					lc.add(s9);
					break;
				case "Raigeki":
					Raigeki s10 = new Raigeki(s[1], s[2]);
					lc.add(s10);
					break;
				default:
					throw new UnknownSpellCardException(path,
							currentLineNumber, s[1]);
				}
			} else if ((!s[0].equals("Monster") && !s[0].equals("Spell")))
				throw new UnknownCardTypeException(path, currentLineNumber,
						s[0]);
		}
		return lc;

	}

	private void buildDeck(ArrayList<Card> monsters, ArrayList<Card> spells)
			throws CloneNotSupportedException {
		Random r = new Random();
		for (int i = 0; i < 15; i++) {
			MonsterCard c = (MonsterCard) monsters.get(r.nextInt(monsters
					.size()));
			MonsterCard c1 = new MonsterCard(c.getName(), c.getDescription(),
					c.getLevel(), c.getAttackPoints(), c.getDefensePoints());
			deck.add(c1);

		}
		for (int i = 0; i < 5; i++) {
			SpellCard c2;
			try {
				c2 = (SpellCard) spells.get(r.nextInt(spells.size())).clone();
				deck.add(c2);
			} catch (CloneNotSupportedException e) {
				e.printStackTrace();
			}

		}

	}

	private void shuffleDeck() {
		Collections.shuffle(deck);
	}

	public ArrayList<Card> drawNCards(int n) {
		ArrayList<Card> alc = new ArrayList<Card>();
		for (int i = 0; i < n; i++) {
			Card c = drawOneCard();
			alc.add(c);
		}
		return alc;
	}

	public Card drawOneCard() {
		Card c = deck.remove(deck.size() - 1);
		c.setLocation(Location.HAND);
		return c;
	}

	public ArrayList<Card> getDeck() {
		return deck;
	}

}
