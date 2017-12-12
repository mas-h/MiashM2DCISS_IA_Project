package com.OHMCorporation.Morpion;

import java.util.Scanner;

public class InitJeu {
	
	public static void main(String[] args) {
		Scanner scInput = new Scanner(System.in);
		GrilleHashmapMorpion grille = new GrilleHashmapMorpion(4);
//		String typeJoueur1 ="";
//		String typeJoueur2 ="";
		
		System.out.println("------------------------------------------");
		System.out.println("|  Bienvenue sur le jeu du morpion 4x4   |");
		System.out.println("------------------------------------------\n");
		
		System.out.println("Choix du type du joueur n°1: \n1) Joueur\n2) IA\n");
		String choixJoueur1 = scInput.nextLine();
		
		
		
		
		if (choixJoueur1.equals("1") || choixJoueur1.equals("2")) {
			if (choixJoueur1.equals("1")) { 
				JoueurTexte j1 = new JoueurTexte(grille, "Joueur 1", choixJoueur1);
				System.out.println("Le Type du joueur 1 est: Joueur");
			} else {
				JoueurAuto j1 = new JoueurAuto(grille, "Joueur IA", choixJoueur1); 
				System.out.println("Le Type du joueur 1 est: IA");
			}
		} else {
		       throw new IllegalArgumentException("Choix incorrecte pour le Joueur 1!");
		}
		
		System.out.println("Choix du type du joueur n°1: \n1) Joueur\n2) IA\n");
		String choixJoueur2 = scInput.nextLine();
		
		if (choixJoueur2.equals("1") || choixJoueur2.equals("2")) {
			if (choixJoueur2.equals("1")) {
				JoueurTexte j2 = new JoueurTexte(grille, "Joueur 2", choixJoueur2);
				System.out.println("Le Type du joueur 2 est: Joueur\n\n");
			} else { 
				JoueurAuto j2 = new JoueurAuto(grille, "Joueur IA", choixJoueur2); 
				System.out.println("Le Type du joueur 2 est: IA\n\n");
			}
		} else {
		       throw new IllegalArgumentException("Choix incorrecte pour le Joueur 2!");

		}
		
		System.out.println("Le match va commencer entre les 2 joueurs, c'est partit !\n\n");

		System.out.println(grille.toString());
		
		JoueurTexte j1 = new JoueurTexte(grille, "Joueur 1", choixJoueur1);
//		JoueurTexte j2 = new JoueurTexte(grille, "Joueur IA", "2");
		JoueurAuto ia = new JoueurAuto(grille, "Joueur IA", choixJoueur2);
		
		//profondeur équivalent au niveau de difficulté: 0 à 
		int profondeur = 1;
		
		ia.setProfondeur(profondeur);
		
		j1.jouerAvec(ia);
		j1.debutAttaque();
	}

}
