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
	 * 	// score = coeff * ( nbPionIA - nbPionAdv )
	 * evalue pour chaque ligne, colone et diagonale: analyse une ligne et détermine un score. 
	 * si c'est 1 pions sur une ligne, on fait *10, 2pions *30 et 3 pions *40
	 * retourne le choix de jeu le plus intéréssant à jouer.
	 * @param GrilleHasmapMorpion à évaluer
	 * @return int res
	 */
	public int evaluationGrille(GrilleHashmapMorpion grille) {
	
		int res = 0;
		Joueur adv = this.getAdversaire();
		LinkedHashMap<CaseGrille, Joueur> mapPoids = new LinkedHashMap<>();	// |	Joueur	|	Poids d'une ligne

		// ////////// Pour les lignes et colones //////////
		for (int jLigne = 0; jLigne < grille.getTaille(); jLigne++) {
			
			// nbPion sur une ligne
			int nbPionsLigneSelf = grille.getNbPionDansLigne(jLigne,this); // nbPions IA
			int nbPionsLigneAdv = grille.getNbPionDansLigne(jLigne,adv); // nbPions ADVERSAIRE
			int nbPionsLigne = nbPionsLigneSelf + nbPionsLigneAdv;
			int scoreLigne = 0;
			
			if (nbPionsLigne == 0) { scoreLigne = (nbPionsLigneSelf - nbPionsLigneAdv) * 10; }
			if (nbPionsLigne == 1) { scoreLigne = (nbPionsLigneSelf - nbPionsLigneAdv) * 20; }
			if (nbPionsLigne == 2) { scoreLigne = (nbPionsLigneSelf - nbPionsLigneAdv) * 30; }
			if (nbPionsLigne == 3) { scoreLigne = (nbPionsLigneSelf - nbPionsLigneAdv) * 40; }
			mapPoids.put(new CaseGrille("Score Ligne n°"+jLigne, scoreLigne), null);	// ajout dans le map
		
			// nbPion sur une colone
			int nbPionsColSelf = grille.getNbPionDansColonne(jLigne,this);	
			int nbPionsColAdv = grille.getNbPionDansColonne(jLigne,adv);	
			int nbPionsColone = nbPionsColSelf + nbPionsColAdv;
			int scoreColone = 0;
			
			if (nbPionsColone == 0) { scoreColone = (nbPionsColSelf - nbPionsColAdv) * 10; }
			if (nbPionsColone == 1) { scoreColone = (nbPionsColSelf - nbPionsColAdv) * 20; }
			if (nbPionsColone == 2) { scoreColone = (nbPionsColSelf - nbPionsColAdv) * 30; }
			if (nbPionsColone == 3) { scoreColone = (nbPionsColSelf - nbPionsColAdv) * 40; }
			mapPoids.put(new CaseGrille("Score Colonne n°"+jLigne, scoreColone), null);	// ajout dans le map
		}
		
		// Diagonale A0
		int scoreDiagA0 = 0;
		int nbPionsDiagonaleSelfA0 = grille.getnbPionDansDiagonaleA0(this);	
		int nbPionsDiagonaleAdvA0 = grille.getnbPionDansDiagonaleA0(adv);
		int nbPionsDiagonaleA0 = nbPionsDiagonaleSelfA0 + nbPionsDiagonaleAdvA0;

		if (nbPionsDiagonaleA0 == 0) { scoreDiagA0 = (nbPionsDiagonaleSelfA0 - nbPionsDiagonaleAdvA0) * 10; }
		if (nbPionsDiagonaleA0 == 1) { scoreDiagA0 = (nbPionsDiagonaleSelfA0 - nbPionsDiagonaleAdvA0) * 20; }
		if (nbPionsDiagonaleA0 == 2) { scoreDiagA0 = (nbPionsDiagonaleSelfA0 - nbPionsDiagonaleAdvA0) * 30; }
		if (nbPionsDiagonaleA0 == 3) { scoreDiagA0 = (nbPionsDiagonaleSelfA0 - nbPionsDiagonaleAdvA0) * 40; }
		mapPoids.put(new CaseGrille("Score Diagonale A0: ", scoreDiagA0), null);	// ajout dans le map
		
		// Diagonale D0
		int scoreDiagD0 = 0;
		int nbPionsDiagonaleSelfD0 = grille.getnbPionDansDiagonaleD0(this);	
		int nbPionsDiagonaleAdvD0 = grille.getnbPionDansDiagonaleD0(adv);
		int nbPionsDiagonaleD0 = nbPionsDiagonaleSelfD0 + nbPionsDiagonaleAdvD0;
		
		if (nbPionsDiagonaleD0 == 0) { scoreDiagD0 = (nbPionsDiagonaleSelfD0 - nbPionsDiagonaleAdvD0) * 10; }
		if (nbPionsDiagonaleD0 == 1) { scoreDiagD0 = (nbPionsDiagonaleSelfD0 - nbPionsDiagonaleAdvD0) * 20; }
		if (nbPionsDiagonaleD0 == 2) { scoreDiagD0 = (nbPionsDiagonaleSelfD0 - nbPionsDiagonaleAdvD0) * 30; }
		if (nbPionsDiagonaleD0 == 3) { scoreDiagD0 = (nbPionsDiagonaleSelfD0 - nbPionsDiagonaleAdvD0) * 40; }
		mapPoids.put(new CaseGrille("Score Diagonale D0 ", scoreDiagD0), null);	// ajout dans le map
		
		System.out.println(">> contenu du map");
		for (Map.Entry<CaseGrille, Joueur> entry : mapPoids.entrySet()) {
			System.out.println("> test mapPoids: "+entry.getKey().toString());	
			res += entry.getKey().getPoidsLigne();
		}
		
		System.out.println("somme totale: "+ res);
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
