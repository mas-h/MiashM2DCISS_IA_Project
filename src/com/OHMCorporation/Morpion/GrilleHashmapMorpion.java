package com.OHMCorporation.Morpion;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class GrilleHashmapMorpion {

	// ATTRIBUTS DE LA CLASSE
	private int taille; // la taille de la grille
	
	private Coordonnee[] caseOccupeesJ1; // servira à ranger les cases utilisées
											// par le J1
	private Coordonnee[] caseOccupeesJ2; // servira à ranger les cases utilisées
											// par le J2
	private int nbCoupJoue; // permettra de définir quand la partie est finie en
							// cas d'égalité
	
	// la grille est représentée dans un map. CaseGrille initialisé par une Coordonnée,null car auccun joueur n'a encore joué
	// la case
	// on pourra mettre différents types de joueurs, JoueurTexte, JoueurIA, etc ..
//	private HashMap <CaseGrille, Joueur> grille = new HashMap<>();
	private HashMap <Coordonnee, Joueur> grille = new HashMap<>();
	
	//	|	(Type)Coordonnées	|	(Abstract obj of type) Joueur	|
	//	|			0,0			|				null				|
	//	|			0,1			|				Joueur1				|
	//	|			0,2			|				Joueur2				|
	
	// CONSTRUCTEUR
	public GrilleHashmapMorpion(int taille) {
		// taille de la grille (largeur ou hauteur)
		this.taille = taille; 
				
		// initialisation de la grille
		for (int i = 0; i < taille; i++) {
			for(int j=0; j < taille; j++) {
				Coordonnee c = new Coordonnee(j, i);
				// Version avec la classe CaseGrille
//				CaseGrille caseG = new CaseGrille(c, false);
//				grille.put(caseG, null);
				grille.put(c, null);
			}
		}
	}

	//
	public String toString() { // permet d'afficher la grille dans la console

		// Ecriture des colones avec le un référencement alphabétique
		String casesGrille = "";
		for (int iAlphabetique = 0; iAlphabetique < this.taille; iAlphabetique++) {
			char c = (char) (iAlphabetique + 'A');
			casesGrille = casesGrille + "\t" +  c;
		}
				
		int nbCol = 0;
		int nbLigne = 0;
		
		System.out.println("taille du map: " + this.grille.size());
		
		Iterator<Map.Entry<Coordonnee, Joueur>> it = this.grille.entrySet().iterator();
		while (it.hasNext()) {
			Map.Entry<Coordonnee, Joueur> entry = it.next();
			// cas de la première ligne: 0
			if (nbCol == 0 && nbLigne == 0 ) {
				casesGrille = casesGrille + "\n" + nbLigne;
				nbLigne += 1;
			}
			
			// on regarde si on est à la colone 4
			if (nbCol == 4) {
				casesGrille = casesGrille + "\n" + nbLigne;
				nbCol = 0; 		// on remet à 0 nbCol à la fin de la ligne
				nbLigne += 1; 	// on incrémente nbLigne à la fin d'une colone
			}
			// sinon on rempli la chaine en fonction des verifications
			// Si la valeur associée à la clée vaut nulle
			if (entry.getValue() == null) {
				casesGrille = casesGrille + "\t-";
				nbCol += 1;
			} 
			else {
				if (entry.getValue().getNom().equals("Joueur 1")) {
					
					casesGrille = casesGrille + "\tX";
					nbCol += 1;
				} else { 
					casesGrille = casesGrille + "\tO";
					nbCol += 1;
				}	
			}
			it.remove(); // avoids a ConcurrentModificationException
		}
		// On revoie le tout ! MAIS TANT MIEUX ! MAIS TANT MIEUX !
		return (casesGrille);

	}
	
		//on verifie que les coordonées en paramètres rentre dans la grille
		private boolean estDansGrille(Coordonnee c) {
			// return ((c.getLigne() <= taille && c.getColonne() <= taille && c.getLigne() > 0 && c.getColonne() > 0));
			return ((c.getLigne() < taille && c.getColonne() < taille && c.getLigne() >= 0 && c.getColonne() >= 0));
		}
		
		
		// On est certain que la coordonnée (la clé) existe, on vérifie donc juste si la valeur est != de null
		private boolean estDejaOcp(Coordonnee c) {
			for (Map.Entry<Coordonnee, Joueur> entry : this.grille.entrySet()) {
			    if (entry.getKey().equals(c) && entry.getValue() != null) {
					// System.out.println(entry.getKey() + " = " + entry.getValue()+" est deja ocp")
					return true;
				}
			}
			return false;	
		}
		
		// compliqué avec 2 grilles à revoir si il reste du temps ( 1 seule grille avec valeurs J1=1 et J2=2 par exemple )
		//on ajoute dans le tableau de coord, du joueur effectuant son moov, la coordonnée choisie si cela est possible 
		private boolean ajouteDansCaseUse(Joueur joueur,Coordonnee c) { // numJoueur = 1 pour j1 et 2 pour J2 //// solution à discuter car pas le mieux 
			// TODO: En fait il est peut etre bien ici le bug...
			if ( !estDansGrille(c) || estDejaOcp(c)  ) { // TODO: c'était le estDansGrille
				System.out.println("Case déjà utilisée ou est en dehors des limites de la grille");
				return false;
			} else {		
				System.out.println("on a bien ajouté le couple "+ c + " et "+ joueur.getNom());
				this.grille.replace(c, joueur);
				return true;
			}
		}
		
		// TODO: la méthode verifie seulement si J1 OU J2 à joué, et non les 2 => remplacement de || par && 
		public boolean tourJoue () { // petite méthode pour s'assurer que les deux joueurs ont bien joué et incrémente le nbr de coup joué (commun au deux participants)
//			if (caseOccupeesJ1[nbCoupJoue]==null || caseOccupeesJ2[nbCoupJoue]==null){
			if (caseOccupeesJ1[nbCoupJoue]==null && caseOccupeesJ2[nbCoupJoue]==null){
				System.out.println("Le tour n'est pas terminé, l'un des joueur n'a pas fait son moov");
				return false;
			}
			else {
				// TODO: ajouté: +1 pour avoir le numéro du tour réel
				System.out.println("Tour de jeu n°"+(nbCoupJoue+1)+" terminé");
				return true;
			}
			
		}
		
		public boolean tourSuivant(){
			if  (!tourJoue()){ 
				return false;
			} else {
				nbCoupJoue += 1;
				return true;
			}
		}
		
		public boolean finDujeu(){ // TODO définir les règles de victoires 
			
			if (nbCoupJoue==caseOccupeesJ1.length && tourJoue()) // cas d'égalité entre les joueurs
				return true;
			else 
				return false;
		}
		
	// TODO Continuer à implémenter les méthodes pour pouvoir tester ça

	public static void main(String[] args) { // test

		GrilleHashmapMorpion testGrille = new GrilleHashmapMorpion(4);
		
		// instanciation de joueurTexte de type abstrait Joueur 
		JoueurTexte joueur1 = new JoueurTexte(testGrille, "Joueur 1");
		JoueurTexte joueur2 = new JoueurTexte(testGrille, "Joueur 2");
		
		Coordonnee testJ1 = new Coordonnee("A0");
		Coordonnee testJ2 = new Coordonnee("A1");
		Coordonnee testJ12 = new Coordonnee("D2");
		Coordonnee testJ22 = new Coordonnee("B1");
		Coordonnee testJ13 = new Coordonnee("A3");
		Coordonnee testJ23 = new Coordonnee("C1");
		
//		System.out.println("coordonnee deja occupée ??: "+ testGrille.estDejaOcp(testJ1));
//		testGrille.ajouteDansCaseUse(joueur1, testJ1);
//		System.out.println("coordonnee deja occupée ??: "+ testGrille.estDejaOcp(testJ1));
//		System.out.println("coordonnee deja occupée ??: "+ testGrille.estDejaOcp(testJ2));
//		testGrille.ajouteDansCaseUse(joueur2, testJ2);		
//		System.out.println("coordonnee deja occupée ??: "+ testGrille.estDejaOcp(testJ2));
//		testGrille.tourSuivant();
		
		
		
		
		testGrille.ajouteDansCaseUse(joueur1, testJ1);
		testGrille.ajouteDansCaseUse(joueur2, testJ2);		
//		testGrille.tourSuivant();
//		
//		testGrille.ajouteDansCaseUse(1, testJ12);
//		testGrille.ajouteDansCaseUse(2, testJ22);
//		testGrille.tourSuivant();
//		
//		testGrille.ajouteDansCaseUse(1, testJ13);
//		testGrille.ajouteDansCaseUse(2, testJ23);
//		testGrille.tourSuivant();

//		System.out.println(testGrille.caseOccupeesJ1[0]);
//		System.out.println(testGrille.caseOccupeesJ2[0]);
//		System.out.println(testGrille.caseOccupeesJ1[1]);
//		System.out.println(testGrille.caseOccupeesJ2[1]);
//		System.out.println(testGrille.caseOccupeesJ1[2]);
//		System.out.println(testGrille.caseOccupeesJ2[2]);

		System.out.println(testGrille.toString());
	}
	
	

}