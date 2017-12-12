package com.OHMCorporation.Morpion;

public class JoueurTexte extends Joueur{

//	private Scanner scInput = new Scanner(System.in);


	public JoueurTexte(GrilleHashmapMorpion grilleJeu,String nom, String id) {
		super(grilleJeu, nom, id);
	}

	@Override
	public int evaluationGrille(GrilleHashmapMorpion grilleJeu) {
		// do nothing
		return 0;
	}


	@Override
	public int findMax(GrilleHashmapMorpion grille, int profondeur) {
		// TODO Auto-generated method stub
		return 0;
	}


	@Override
	public int findMin(GrilleHashmapMorpion grille, int profondeur) {
		// TODO Auto-generated method stub
		return 0;
	}


	@Override
	public Coordonnee returnBestCoord(GrilleHashmapMorpion grille, int profondeur) {
		// TODO Auto-generated method stub
		return null;
	}


	
	

}
