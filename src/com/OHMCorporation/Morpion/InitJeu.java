package com.OHMCorporation.Morpion;

public class InitJeu {
	


	public static void main(String[] args) { // test
		GrilleHashmapMorpion grille = new GrilleHashmapMorpion(4);
		System.out.println(grille.toString());
		JoueurTexte j1 = new JoueurTexte(grille, "Joueur 1", "1");
//		JoueurTexte j2 = new JoueurTexte(grille, "Joueur IA", "2");
		JoueurAuto ia = new JoueurAuto(grille, "Joueur IA", "2");


		
		j1.jouerAvec(ia);
		j1.debutAttaque();
	}

}
