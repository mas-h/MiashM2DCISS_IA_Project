package com.OHMCorporation.Morpion;

import java.util.LinkedHashMap;

public class JoueurAuto extends Joueur{

	private LinkedHashMap<CaseGrille, Joueur> simulationGrilleAnalyse = new LinkedHashMap<>();
	
	private int profondeur = 0; // profondeur à laquelle l'IA va analyser
	
	
	public JoueurAuto(GrilleHashmapMorpion grilleJeu,String nom, String id) {
		super(grilleJeu, nom, id);
		
	}
	

	/**
	 * 
	 * @return
	 */
	public double calculerScore() {
		
		
		return 0;
	}
	
	/**
	 * Elle retourne vraie si il est plus intéressant de jouer offensivement et faux si il est préférable de jouer défensivement
	 * 
	 * @return
	 */
	public boolean simulationJouerContre() {
		return true;
	}
	
	/**
	 * evalue pour chaque ligne, colone et diagonale: elle scan une ligne et elle détermine un score. adversaire = -1, allié = +1 
	 * si c'est 1 pions sur une ligne, on fait *10, 2pions *30 et 3 pions *40
	 * retourne le choix de jeu le plus intéréssant à jouer (comparaison entre valeur la plus négative et valeur positive la plus grande
	 * @return
	 */
	public double evaluation() {
		double minDangereux = 0;
		double maxMeilleurCoup = 0;
		double res = 0;
		Joueur adv = this.getAdversaire();
		
		for (int i = 0; i < grilleDeJeu.getTaille(); i++) {
			
		
			int nbPionsL0self = grilleDeJeu.getNbPionDansLigne(i,this);					// pour lui meme
			int nbPionsL0adv = grilleDeJeu.getNbPionDansLigne(i,adv);	 				// pour lui meme
		}
		
		
		
		
		return res;
	}
	
	public double analyserEnProfondeur(int profondeur) {
		return profondeur;
		
	}
	
	@Override
	protected void perdu() {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected void gagne() {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected void retourAttaque(Coordonnee c, int etat) {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected void retourDefense(Coordonnee c, int etat) {
		// TODO Auto-generated method stub
		
	}
	
	public static void main(String[] args) { // test
		
	}
}
