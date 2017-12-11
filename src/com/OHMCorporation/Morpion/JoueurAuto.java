package com.OHMCorporation.Morpion;

import java.util.LinkedHashMap;
import java.util.Map;

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
	public int evaluation() {
		double minDangereux = 0;
		double maxMeilleurCoup = 0;
		int res = 0;
		Joueur adv = this.getAdversaire();
		LinkedHashMap<CaseGrille, Joueur> mapPoids = new LinkedHashMap<>();	// |	Joueur	|	Poids d'une ligne

		// ////////// Pour les lignes et colones //////////
		for (int jLigne = 0; jLigne < grilleDeJeu.getTaille(); jLigne++) {
			
			int nbPionsLigneSelf = 0;	 int nbPionsLigneAdv = 0;
			int nbPionsColSelf = 0;		 int nbPionsColAdv = 0;
			int poidsLigneSelf = 0;		 int poidsLigneAdv = 0;
			int poidsColSelf = 0; 		 int poidsColAdv = 0;
			
			// L -> pas une coordonnée mais une ligne
//				CaseGrille cgLigne = new CaseGrille(new Coordonnee("L"+jNumLigne), false, 1);
			// C -> pas une coordonnée mais une Colone
//				CaseGrille cgCol = new CaseGrille(new Coordonnee("C"+jNumLigne), false, 1);
			
			// pour lui meme: nbPions LIGNES IA
			nbPionsLigneSelf = grilleDeJeu.getNbPionDansLigne(jLigne,this);	
//				System.out.println("nb Pions de joueur "+this.getNom()+" >>>" + nbPionsLigneSelf);
			if (nbPionsLigneSelf == 0) { poidsLigneSelf = nbPionsLigneSelf * 10; }	
			if (nbPionsLigneSelf == 1) { poidsLigneSelf = nbPionsLigneSelf * 10; }	// calcul poidsLigne, pour 1 pion 
			if (nbPionsLigneSelf == 2) { poidsLigneSelf = nbPionsLigneSelf * 30; } 	// calcul poidsLigne, pour 2 pions
			if (nbPionsLigneSelf == 3) { poidsLigneSelf = nbPionsLigneSelf * 40; }	// calcul poidsLigne, pour 3 pions
			mapPoids.put(new CaseGrille("Ligne IA n°"+jLigne, poidsLigneSelf), this);	// ajout dans le map
			
			// pour adversaire: nbPions  LIGNE ADVERSAIRE
			nbPionsLigneAdv = grilleDeJeu.getNbPionDansLigne(jLigne,adv);	
			if (nbPionsLigneAdv == 0) { poidsLigneAdv = (nbPionsLigneAdv * -1); }	// calcul poidsLigne, pour 1 pion 
			if (nbPionsLigneAdv == 1) { poidsLigneAdv = (nbPionsLigneAdv * 10 * -1); }	// calcul poidsLigne, pour 1 pion 
			if (nbPionsLigneAdv == 2) { poidsLigneAdv = (nbPionsLigneAdv * 30 * -1); } 	// calcul poidsLigne, pour 2 pions
			if (nbPionsLigneAdv == 3) { poidsLigneAdv = (nbPionsLigneAdv * 40 * -1); }	// calcul poidsLigne, pour 3 pions
			mapPoids.put(new CaseGrille("Ligne IA n°"+jLigne, poidsLigneAdv), adv);	// ajout dans le map

			
			// pour IA: nbPions COLONES IA
			nbPionsColSelf = grilleDeJeu.getNbPionDansColonne(jLigne,this);	
			if (nbPionsColSelf == 0) { poidsColSelf = nbPionsColSelf * 10; }	
			if (nbPionsColSelf == 1) { poidsColSelf = nbPionsColSelf * 10; }	// calcul poidsLigne, pour 1 pion 
			if (nbPionsColSelf == 2) { poidsColSelf = nbPionsColSelf * 30; } 	// calcul poidsLigne, pour 2 pions
			if (nbPionsColSelf == 3) { poidsColSelf = nbPionsColSelf * 40; }	// calcul poidsLigne, pour 3 pions
			mapPoids.put(new CaseGrille("Colone IA n°"+jLigne, poidsColSelf), this);	// ajout dans le map
			
			nbPionsColAdv = grilleDeJeu.getNbPionDansColonne(jLigne,adv);	
			if (nbPionsColAdv == 0) { poidsColAdv = nbPionsColAdv * 10 * -1; }	
			if (nbPionsColAdv == 1) { poidsColAdv = nbPionsColAdv * 10 * -1; }	// calcul poidsLigne, pour 1 pion 
			if (nbPionsColAdv == 2) { poidsColAdv = nbPionsColAdv * 30 * -1; } 	// calcul poidsLigne, pour 2 pions
			if (nbPionsColAdv == 3) { poidsColAdv = nbPionsColAdv * 40 * -1; }	// calcul poidsLigne, pour 3 pions
			mapPoids.put(new CaseGrille("Colone Adverse n°"+jLigne, poidsColAdv), adv);	// ajout dans le map	
		}
		
		// 
		// ////////// Pour les diagonales //////////  
		// POUR LA DIAGONALE AO
		int nbPionsDiagonaleSelfA0 = 0;	 int nbPionsDiagonaleAdvA0 = 0;
		int poidsDiagonaleSelfA0 = 0;	 int poidsDiagonaleAdvA0 = 0;
		
		// pour lui meme: nbPions DIAGONALE IA AO
		nbPionsDiagonaleSelfA0 = grilleDeJeu.getnbPionDansDiagonaleA0(this);	
		if (nbPionsDiagonaleSelfA0 == 0) { poidsDiagonaleSelfA0 = nbPionsDiagonaleSelfA0 * 10; }	
		if (nbPionsDiagonaleSelfA0 == 1) { poidsDiagonaleSelfA0 = nbPionsDiagonaleSelfA0 * 10; }	// calcul poidsLigne, pour 1 pion 
		if (nbPionsDiagonaleSelfA0 == 2) { poidsDiagonaleSelfA0 = nbPionsDiagonaleSelfA0 * 30; } 	// calcul poidsLigne, pour 2 pions
		if (nbPionsDiagonaleSelfA0 == 3) { poidsDiagonaleSelfA0 = nbPionsDiagonaleSelfA0 * 40; }	// calcul poidsLigne, pour 3 pions
		mapPoids.put(new CaseGrille("Diagonale IA n° A0", poidsDiagonaleSelfA0), this);				// ajout dans le map
		
		// pour lui meme: nbPions DIAGONALE ADVERSAIRE A0
		nbPionsDiagonaleAdvA0 = grilleDeJeu.getnbPionDansDiagonaleA0(adv);	
		if (nbPionsDiagonaleAdvA0 == 0) { poidsDiagonaleAdvA0 = nbPionsDiagonaleAdvA0 * 10 * -1; }	
		if (nbPionsDiagonaleAdvA0 == 1) { poidsDiagonaleAdvA0 = nbPionsDiagonaleAdvA0 * 10 * -1; }	// calcul poidsLigne, pour 1 pion 
		if (nbPionsDiagonaleAdvA0 == 2) { poidsDiagonaleAdvA0 = nbPionsDiagonaleAdvA0 * 30 * -1; } 	// calcul poidsLigne, pour 2 pions
		if (nbPionsDiagonaleAdvA0 == 3) { poidsDiagonaleAdvA0 = nbPionsDiagonaleAdvA0 * 40 * -1; }	// calcul poidsLigne, pour 3 pions
		mapPoids.put(new CaseGrille("Diagonale adverse n° A0", poidsDiagonaleAdvA0), adv);		// ajout dans le map
		
		// POUR LA DIAGONALE DO
		int nbPionsDiagonaleSelfD0 = 0;	 int nbPionsDiagonaleAdvD0 = 0;
		int poidsDiagonaleSelfD0 = 0;	 int poidsDiagonaleAdvD0 = 0;
		
		// pour lui meme: nbPions DIAGONALE IA DO
		nbPionsDiagonaleSelfD0 = grilleDeJeu.getnbPionDansDiagonaleD0(this);	
		if (nbPionsDiagonaleSelfD0 == 0) { poidsDiagonaleSelfD0 = nbPionsDiagonaleSelfD0 * 10; }	
		if (nbPionsDiagonaleSelfD0 == 1) { poidsDiagonaleSelfD0 = nbPionsDiagonaleSelfD0 * 10; }	// calcul poidsLigne, pour 1 pion 
		if (nbPionsDiagonaleSelfD0 == 2) { poidsDiagonaleSelfD0 = nbPionsDiagonaleSelfD0 * 30; } 	// calcul poidsLigne, pour 2 pions
		if (nbPionsDiagonaleSelfD0 == 3) { poidsDiagonaleSelfD0 = nbPionsDiagonaleSelfD0 * 40; }	// calcul poidsLigne, pour 3 pions
		mapPoids.put(new CaseGrille("Diagonale IA n° D0", poidsDiagonaleSelfD0), this);				// ajout dans le map
		
		// pour lui meme: nbPions DIAGONALE ADVERSAIRE D0
		nbPionsDiagonaleAdvD0 = grilleDeJeu.getnbPionDansDiagonaleD0(adv);	
		if (nbPionsDiagonaleAdvD0 == 0) { poidsDiagonaleAdvD0 = nbPionsDiagonaleAdvD0 * 10 * -1; }	
		if (nbPionsDiagonaleAdvD0 == 1) { poidsDiagonaleAdvD0 = nbPionsDiagonaleAdvD0 * 10 * -1; }	// calcul poidsLigne, pour 1 pion 
		if (nbPionsDiagonaleAdvD0 == 2) { poidsDiagonaleAdvD0 = nbPionsDiagonaleAdvD0 * 30 * -1; } 	// calcul poidsLigne, pour 2 pions
		if (nbPionsDiagonaleAdvD0 == 3) { poidsDiagonaleAdvD0 = nbPionsDiagonaleAdvD0 * 40 * -1; }	// calcul poidsLigne, pour 3 pions
		mapPoids.put(new CaseGrille("Diagonale adverse n° D0", poidsDiagonaleAdvD0), adv);		// ajout dans le map
	
		System.out.println("pour test");
		for (Map.Entry<CaseGrille, Joueur> entry : mapPoids.entrySet()) {
			System.out.println("> test mapPoids: "+entry.getKey().toString());			
		}
		
		
		// TODO faire la somme res entre les pions Joueur et les pions IA pour les lignes , les colones et les diagonales, 
		
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
