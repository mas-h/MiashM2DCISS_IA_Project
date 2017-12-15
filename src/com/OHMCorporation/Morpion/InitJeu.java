package com.OHMCorporation.Morpion;

import java.util.Scanner;

public class InitJeu {
	
	private static GrilleHashmapMorpion grilleJeu;
	private static Joueur j1;
	private static Joueur j2;
	private static Scanner scInput = new Scanner(System.in);
	
	

	public static GrilleHashmapMorpion getGrilleJeu() {
		return grilleJeu;
	}

	public static void setGrilleJeu(GrilleHashmapMorpion grilleJeu) {
		InitJeu.grilleJeu = grilleJeu;
	}

	public static void initJoueurs() throws IllegalArgumentException {

		String nomJoueur = "";
		int profondeur = 1;
		
		System.out.println("------------------------------------------");
		System.out.println("|  Bienvenue sur le jeu du morpion "+getGrilleJeu().getTaille()+" x "+getGrilleJeu().getTaille()+" |");
		System.out.println("------------------------------------------\n");

		for (int i = 1; i <= 2; i++) {
		
			// Setup joueur 1
			System.out.println("------   SETUP JOUEUR "+i+"   ------");
			// Definition du nom du joueur
			System.out.println("Quel est le pseudo du joueur "+i+" ?\n");
			nomJoueur = scInput.nextLine();
			// Definition du type de joueur: Physique ou IA
			System.out.println("Choix du type du joueur n°"+i+": \n1) Joueur\n2) IA\n");
			String choixJoueur = scInput.nextLine();
			// Determination de l'ID 1 = joueur, 2 = IA
			if (choixJoueur.equals("1") || choixJoueur.equals("2")) {
				if (choixJoueur.equals("1")) {
					if (i == 1) {
						// nouvelle instance de tye JoueurTexte
						j1 = new JoueurTexte(getGrilleJeu(), nomJoueur+" ( J"+i+" )", choixJoueur);
						// identificateur en tant que joueur 1
						j1.setNumeroDeJoueur(i);
						System.out.println("Le Type du joueur "+i+" est: Joueur");
					} else {
						// nouvelle instance de tye JoueurTexte
						j2 = new JoueurTexte(getGrilleJeu(), nomJoueur+" ( J"+i+" )", choixJoueur);
						// identificateur en tant que joueur 1
						j2.setNumeroDeJoueur(i);
						System.out.println("Le Type du joueur "+i+" est: Joueur");
					}
				} else {
					if (i == 1) {
						// nouvelle instance de tye JoueurAuto
						j1 = new JoueurAuto(getGrilleJeu(), nomJoueur+" ( IA )", choixJoueur); 
						j1.setNumeroDeJoueur(i);
						System.out.println("Le Type du joueur "+i+" est: IA");
					} else {
						// nouvelle instance de tye JoueurAuto
						j2 = new JoueurAuto(getGrilleJeu(), nomJoueur+" ( IA )", choixJoueur); 
						j2.setNumeroDeJoueur(i);
						System.out.println("Le Type du joueur "+i+" est: IA");
					}
					System.out.println("Difficulté / Profondeur de l' IA ? ( >= 1 et <= 9 )");
					profondeur = Integer.parseInt(scInput.nextLine());
					if (profondeur < 1 || profondeur > 9) {
						throw new IllegalArgumentException("Error >> Choix incorrecte pour la Profondeur de l'IA "+i+" !");
					} else {
						if (i == 1) { j1.setProfondeur(profondeur);} 
						else { j2.setProfondeur(profondeur);}
					}
				}
			} else {throw new IllegalArgumentException("Error >> Choix incorrecte pour le Joueur "+i+" !");}
		}
	}
	
	public static void initGrille() throws IllegalArgumentException {
		System.out.println("Choisissez la taille de la grille:");
		String strTailleGrille = scInput.nextLine();
		// Regex qui vérifie si la chaine ne contient que des chiffres
		if (strTailleGrille.matches("^-?\\d+$")) {
			int tailleGrille = Integer.parseInt(strTailleGrille);
			if (tailleGrille >= 3 && tailleGrille <= 25) { setGrilleJeu(new GrilleHashmapMorpion(tailleGrille));} 
			else { throw new IllegalArgumentException(">> Error: La valeur entrée doit être comprise entre 3 et 25 !");}
		} else { throw new IllegalArgumentException(">> Error: La valeur entrée c'est pas un chiffre !");}
	}
	
	
	public static void main(String[] args) {
		
		initGrille();
		initJoueurs();
		System.out.println(getGrilleJeu().toString());
		
		j1.jouerAvec(j2);
		j1.debutAttaque();
	}
}
