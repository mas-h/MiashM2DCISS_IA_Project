package com.OHMCorporation.Morpion;

import java.util.Scanner;

public class InitJeu {
	
	
	public static void main(String[] args) {
		Scanner scInput = new Scanner(System.in);
		GrilleHashmapMorpion grille = new GrilleHashmapMorpion(4);
		
		Joueur j1 = null;
		Joueur j2 = null;
		String nomJoueur = "";
		int profondeur = 1;
		
		System.out.println("------------------------------------------");
		System.out.println("|  Bienvenue sur le jeu du morpion 4x4   |");
		System.out.println("------------------------------------------\n");
		
		// Setup joueur 1
		System.out.println("------   SETUP JOUEUR 1   ------");
		// Definition du nom du joueur
		System.out.println("Quel est le pseudo du joueur 1 ?\n");
		nomJoueur = scInput.nextLine();
		// Definition du type de joueur: Physique ou IA
		System.out.println("Choix du type du joueur n°1: \n1) Joueur\n2) IA\n");
		String choixJoueur1 = scInput.nextLine();
		// Determination de l'ID 1 = joueur, 2 = IA
		if (choixJoueur1.equals("1") || choixJoueur1.equals("2")) {
			if (choixJoueur1.equals("1")) { 
				// nouvelle instance de tye JoueurTexte
				j1 = new JoueurTexte(grille, nomJoueur+" ( J1 )", choixJoueur1);
				// identificateur en tant que joueur 1
				j1.setNumeroDeJoueur(1);
				System.out.println("Le Type du joueur 1 est: Joueur");
			} else {
				// nouvelle instance de tye JoueurAuto
				j1 = new JoueurAuto(grille, nomJoueur+" ( IA )", choixJoueur1); 
				j1.setNumeroDeJoueur(1);
				System.out.println("Le Type du joueur 1 est: IA");
				// On entre la profondeur en cas de choix d'une IA
				System.out.println("Difficulté / Profondeur de l' IA ? ( >= 1 et <= 9 )");
				profondeur = Integer.parseInt(scInput.nextLine());
				if (profondeur < 1 || profondeur > 9) {
					throw new IllegalArgumentException("Error >> Choix incorrecte pour la Profondeur de l'IA 1 !");
				} else {
					j1.setProfondeur(profondeur);
				}
			}
		} else {throw new IllegalArgumentException("Error >> Choix incorrecte pour le Joueur 1 !");}
		
		
		// Setup Joueur 2
		System.out.println("------   SETUP JOUEUR 2   ------");
		// Definition du nom du joueur
		System.out.println("Quel est le pseudo du joueur 1 ?\n");
		nomJoueur = scInput.nextLine();
		// Definition du type de joueur: Physique ou IA
		System.out.println("Choix du type du joueur n°1: \n1) Joueur\n2) IA\n");
		String choixJoueur2 = scInput.nextLine();
		// Determination de l'ID 1 = joueur, 2 = IA
		if (choixJoueur2.equals("1") || choixJoueur2.equals("2")) {
			if (choixJoueur2.equals("1")) {
				j2 = new JoueurTexte(grille, nomJoueur+" ( J2 )", choixJoueur2);
				// identificateur en tant que joueur 2
				j2.setNumeroDeJoueur(2);
				System.out.println("Le Type du joueur 2 est: Joueur\n");
			} else { 
				j2 = new JoueurAuto(grille, nomJoueur+" ( IA )", choixJoueur2); 
				j2.setNumeroDeJoueur(2);
				System.out.println("Le Type du joueur 2 est: IA\n");
				// On entre la profondeur en cas de choix d'une IA
				System.out.println("Difficulté / Profondeur de l' IA ? ( >= 1 et <= 9 )");
				profondeur = Integer.parseInt(scInput.nextLine());
				if (profondeur < 1 || profondeur > 9) {
					throw new IllegalArgumentException("Error >> Choix incorrecte pour la Profondeur de l'IA 2 !");
				} else {
					j2.setProfondeur(profondeur);
				}
			}
		} else { throw new IllegalArgumentException("Error >> Choix incorrecte pour le Joueur 2 !"); }
		

		
		System.out.println("Le match va commencer entre les 2 joueurs, c'est parti !\n");	
		// affichage de la grille
		System.out.println(grille.toString());

		
		j1.jouerAvec(j2);
		j1.debutAttaque();
		

		
	}

}
