package com.OHMCorporation.Morpion;

import java.util.LinkedHashMap;
import java.util.Map;


public class JoueurAuto extends Joueur{
	
	public JoueurAuto(GrilleHashmapMorpion grilleJeu,String nom, String id) {
		super(grilleJeu, nom, id);
		
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
		
		// si la profondeur voulue a été atteinte ou si l'un des joueurs peut gagner 
		if (grille.finDujeu(this)){ return (1000 - this.grilleDeJeu.getnbPionsDansGrille(this));}
		if (grille.finDujeu(this.getAdversaire())) { return (-1000 + this.grilleDeJeu.getnbPionsDansGrille(this));}
		// égalité
//		if (grille.get) {
//			
//		}
		
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
		
		System.out.println(">> évalué ..\n");
		for (Map.Entry<CaseGrille, Joueur> entry : mapPoids.entrySet()) {
			res += entry.getKey().getPoidsLigne();
		}
		
		System.out.println("somme totale: "+ res);	
		return res;
	}
	
	public int findMin (GrilleHashmapMorpion grilleJeu,int profondeur){
			
			int valMin = Integer.MAX_VALUE;
			int tmpMin;
			
			if (profondeur == 0 || grilleJeu.finDujeu(this)|| grilleJeu.finDujeu(this.getAdversaire())){ // si la profondeur voulue a été atteinte ou si l'un des joueurs peut gagner 
//				System.out.println(">>> retour eval .. ");
				return evaluationGrille(grilleJeu);
			} // On retourne l'évaluation
			// get grille pas net
			for (Map.Entry<CaseGrille, Joueur> entry : grilleJeu.getGrille().entrySet()) { // sinon on parcours le jeu et à chaque case vide on simule 
	            if (entry.getKey().getIsOccupied()==false) {
	            	grilleJeu.ajoutePionParJoueur(getAdversaire(), entry.getKey().getCoordonnee()); //jeu->joue(i,j);
	            	
	            	tmpMin = findMax(grilleJeu, profondeur-1); // on calcule la valeur max et on compare si c'est plus petit que la valeur min
	            	if(tmpMin<valMin){
	            		valMin = tmpMin;
	            	}
	            	//On annule le coup car ce n'était qu'une simulation
	            	entry.setValue(null);
	            	entry.getKey().setOccupied(false);
	            }
	       }
	       return valMin; // on retourne la valeur minimal
	    }
	
	public int findMax(GrilleHashmapMorpion grilleJeu, int profondeur){
			
			int valMax = Integer.MIN_VALUE;
			int tmpMax;
			// si la profondeur voulue a été atteinte ou si l'un des joueurs peut gagner
			if (profondeur == 0 || grilleJeu.finDujeu(this)|| grilleJeu.finDujeu(this.getAdversaire())){  
				System.out.println(">>>Max est appelé !");
				return evaluationGrille(grilleJeu);} // On retourne l'évaluation
			
			for (Map.Entry<CaseGrille, Joueur> entry : grilleJeu.getGrille().entrySet()) { // sinon on parcours le jeu et à chaque case vide on simule 
	            if (entry.getKey().getIsOccupied()==false) {
	            	grilleJeu.ajoutePionParJoueur(this, entry.getKey().getCoordonnee());
	            	// on calcule la valeur min et on compare si c'est plus grand que la valeur max
	            	tmpMax = findMin(grilleJeu, profondeur-1); 
	            	if(tmpMax > valMax){
	            		valMax = tmpMax;
	            	}
	            	//On annule le coup car ce n'était qu'une simulation
	            	entry.setValue(null);
	            	entry.getKey().setOccupied(false);
	            }
	       }
	       return valMax; // on retourne la valeur maximale
	   }
	
	public Coordonnee returnBestCoord(GrilleHashmapMorpion grilleJeu, int profondeur){
		int valMax = Integer.MIN_VALUE;
		int tmpMax;
		Coordonnee bestChoice= new Coordonnee("E6");
		
		if (profondeur != 0 || !(grilleJeu.finDujeu(this))|| !(grilleJeu.finDujeu(this.getAdversaire() ) )){ // si la profondeur voulue a été atteinte ou si l'un des joueurs peut gagner 
//			System.out.println("on passe bien dans le if de bestcoord()");
			// sinon on parcours le jeu et à chaque case vide on simule
			for (Map.Entry<CaseGrille, Joueur> entry : grilleJeu.getGrille().entrySet()) {  
	            if (entry.getKey().getIsOccupied()==false) {
//	            	System.out.println("on verifie une case libre");
	            	//On simule le fait d'avoir jouer ce pion;
	            	grilleJeu.ajoutePionParJoueur(this, entry.getKey().getCoordonnee()); 
	            	// on calcule la valeur min et on compare si c'est plus grand que la valeur max
	            	tmpMax = findMin(grilleJeu, profondeur-1); 
	            	System.out.println("tmp: " +tmpMax);
	            	if (tmpMax==valMax){
	            		System.out.println(">> dans tmp==max");
	            		// si on obtient la même valeur que max, on choisi aléatoirement entre max et tmp
	            		boolean random = Math.random() < 0.5; 
	            		if (random){
	            			valMax = tmpMax;
	            			bestChoice = entry.getKey().getCoordonnee(); 
	            		}	
	            	}
	            	
	            	if(tmpMax>valMax){
	            		System.out.println(">> dans tmp>max");
	            		valMax = tmpMax;
	            		bestChoice = entry.getKey().getCoordonnee(); // on sauvegarde la coordonée, le meilleur coup pour le moment.
	            	}
	            	
	            	//On annule le coup car ce n'était qu'une simulation
	            	entry.setValue(null);
	            	entry.getKey().setOccupied(false);
	            }
	       }
			 
		}
		System.out.println("le meilleur coup à jouer est : "+bestChoice.toString());
		return bestChoice; // on retourne la Coordonnee à jouer
	}
	
	
	
	public static void main(String[] args) { // test
		
	}

}
