package com.OHMCorporation.Morpion;

import java.util.LinkedHashMap;
import java.util.Map;

public class GrilleHashmapMorpion {

	// ATTRIBUTS DE LA CLASSE
	private int taille; // la taille de la grille


	// la grille est représentée dans un map. CaseGrille initialisé par une
	// Coordonnée,null car auccun joueur n'a encore joué la case
	// on pourra mettre différents types de joueurs, JoueurTexte, JoueurIA, etc ..
	// private HashMap <CaseGrille, Joueur> grille = new HashMap<>();
	private LinkedHashMap<CaseGrille, Joueur> grille = new LinkedHashMap<>();

//	private int nbCoupsJ1 = 0;
//	private int nbCoupsJ2 = 0;
	
	private int maxCoupJoue = 0;


	// | (Type)Coordonnées 	| (Abstract obj of type) Joueur |
	// | 0,0 				| null							|
	// | 0,1 				| Joueur1 						|
	// | 0,2				| Joueur2 						|

	// CONSTRUCTEUR
	public GrilleHashmapMorpion(int taille) {
		// taille de la grille (largeur ou hauteur)
		this.taille = taille;

		// initialisation de la grille
		for (int i = 0; i < taille; i++) {
			for (int j = 0; j < taille; j++) {
				Coordonnee c = new Coordonnee(j, i);
				CaseGrille caseG = new CaseGrille(c, false, 1);
				grille.put(caseG, null);
			}
		}
		this.maxCoupJoue = grille.size()/2;
	}

	
	public int getTaille() {
		return taille;
	}
	
	public void setTaille(int taille) {
		this.taille = taille;
	}
	
	public String toString() { // permet d'afficher la grille dans la console

		// Ecriture des colones avec le un référencement alphabétique
		String casesGrille = "";
		for (int iAlphabetique = 0; iAlphabetique < this.taille; iAlphabetique++) {
			char c = (char) (iAlphabetique + 'A');
			casesGrille = casesGrille + "\t" + c;
		}

		int nbCol = 0;
		int nbLigne = 0;

		for (Map.Entry<CaseGrille, Joueur> entry : this.grille.entrySet()) {

			// cas de la première ligne: 0
			if (nbCol == 0 && nbLigne == 0) {
				casesGrille = casesGrille + "\n" + nbLigne;
				nbLigne += 1;
			}

			if (nbCol == 4) {	// on regarde si on est à la colone 4
				casesGrille = casesGrille + "\n" + nbLigne;
				nbCol = 0; // on remet à 0 nbCol à la fin de la ligne
				nbLigne += 1; // on incrémente nbLigne à la fin d'une colone
			}
			// sinon on rempli la chaine en fonction des verifications
			if (entry.getValue() == null) {	// Si la valeur associée à la clée vaut nulle
				casesGrille = casesGrille + "\t-";
				nbCol += 1;
			} else {
				if (entry.getValue().getID().equals("1")) {
					casesGrille = casesGrille + "\tX";
					nbCol += 1;
				} else {
					casesGrille = casesGrille + "\tO";
					nbCol += 1;
				}
			}
		}
		// On revoie le tout ! MAIS TANT MIEUX ! MAIS TANT MIEUX !
		return (casesGrille);
	}

	// on verifie que les coordonées en paramètres rentre dans la grille
	private boolean estDansGrille(Coordonnee c) {
		return ((c.getLigne() < taille && c.getColonne() < taille && c.getLigne() >= 0 && c.getColonne() >= 0));
	}

	// On est certain que la coordonnée (la clé) existe, on vérifie donc juste si la
	// valeur est != de null
	private boolean estDejaOcp(Coordonnee c) {
		for (Map.Entry<CaseGrille, Joueur> entry : this.grille.entrySet()) {
			if (entry.getKey().getCoordonnee().equals(c) && entry.getValue() != null) {
				// System.out.println(entry.getKey() + " = " + entry.getValue()+" est deja ocp")
				return true;
			}
		}
		return false;
	}


	// on ajoute dans le tableau de coord, du joueur effectuant son moov, la coordonnée choisie si cela est possible
	public boolean ajoutePionParJoueur(Joueur joueur, Coordonnee c) { 
		if (!estDansGrille(c) || estDejaOcp(c)) { 
			System.out.println("Case déjà utilisée ou est en dehors des limites de la grille");
			return false;
		} else {
			for (Map.Entry<CaseGrille, Joueur> entry : this.grille.entrySet()) {
				if (entry.getKey().getCoordonnee().equals(c)) {
					entry.setValue(joueur);
					entry.getKey().setOccupied(true);
					entry.getKey().setPoidsCase(0);
					return true;
				}
			}
			return false;
		}
	}

	/**
	 *  permet de retourner le nombre de pion dans une ligne donnée d'un joueur
	 * @param numLigne
	 * @param Joueur
	 * @return nb pions sur ligne
	 */
	public int getNbPionDansLigne(int numLigne, Joueur j){// Vérifiée, ça marche 
		int nbPionDansLigne = 0;		
		for (Map.Entry<CaseGrille, Joueur> entry : this.grille.entrySet()) {
			if(j.equals(entry.getValue()) && entry.getKey().getCoordonnee().getLigne() == numLigne) {
				nbPionDansLigne++;
//				System.out.println("joueur: "+ j.getNom() + " nbPionDansLigne "+ nbPionDansLigne);
			}		
		}
		return nbPionDansLigne;
	}
	
	public int getNbPionDansColonne(int numColone, Joueur j){ // permet de retourner le nombre de pion(s) dans une colone donnée d'un joueur
		int nbPionDansColonne = 0;		
		for (Map.Entry<CaseGrille, Joueur> entry : this.grille.entrySet()) {
			if(j.equals(entry.getValue()) && entry.getKey().getCoordonnee().getColonne() == numColone) {
				nbPionDansColonne++;
			}		
		}
		return nbPionDansColonne;
	}
	
	public int getnbPionDansDiagonaleA0(Joueur j){ // méthode pour les verif de pion dans la diagonale qui débute en A0 et finie en D4
		int nbPionDansDiagonale = 0;
		Coordonnee A0 = new Coordonnee("A0");
		Coordonnee B1 = new Coordonnee("B1");
		Coordonnee C2 = new Coordonnee("C2");
		Coordonnee D3 = new Coordonnee("D3");
		
		for (Map.Entry<CaseGrille, Joueur> entry : this.grille.entrySet()) {
			if(j.equals(entry.getValue()) && ( entry.getKey().getCoordonnee().equals(A0) || entry.getKey().getCoordonnee().equals(B1) 
					|| entry.getKey().getCoordonnee().equals(C2) || entry.getKey().getCoordonnee().equals(D3)) ) {
				nbPionDansDiagonale++;
			}		
		}
		return nbPionDansDiagonale;
	}
	
	public int getnbPionDansDiagonaleD0(Joueur j){ // même chose pour la dagionale qui débute en D0 et finie en A3
		int nbPionDansDiagonale = 0;
		Coordonnee D0 = new Coordonnee("D0");
		Coordonnee C1 = new Coordonnee("C1");
		Coordonnee B2 = new Coordonnee("B2");
		Coordonnee A3 = new Coordonnee("A3");
		
		for (Map.Entry<CaseGrille, Joueur> entry : this.grille.entrySet()) {
			if(j.equals(entry.getValue()) && ( entry.getKey().getCoordonnee().equals(D0) || entry.getKey().getCoordonnee().equals(C1) 
					|| entry.getKey().getCoordonnee().equals(B2) || entry.getKey().getCoordonnee().equals(A3)) ) {
				nbPionDansDiagonale++;
			}		
		}
		return nbPionDansDiagonale;
	}
	

	// Fonctionne pour les cas d'égalités, les cas de victoire horizontaux et verticaux
	public boolean finDujeu(Joueur j){ 
		// cas d'égalité entre les joueurs
		if(j.getNbJoue() >= maxCoupJoue && j.getAdversaire().getNbJoue() >= maxCoupJoue) {
//		if (this.grille.si==(nb)caseOccupeesJ1.length && tourJoue()) { 
			System.out.println("Partie terminée ! Egalité");
			return true;
		} else {   // on fait les test pour toutes les lignes et les colonnes (4 itérations ici au plus, taille de la grille)
			for (int i = 0; i<this.taille;i++) { 
				// si 4 cases avec la même lignes ou 4 cases avec la même colonne alors c'est gagné
				if (this.getNbPionDansColonne(i, j) == this.taille || this.getNbPionDansLigne(i, j) == this.taille 		
						|| this.getnbPionDansDiagonaleA0(j) == this.taille || this.getnbPionDansDiagonaleD0(j) == this.taille) { 
					System.out.println("Victoire du joueur "+j.getID());
					System.out.println("Partie terminée !");
					return true;
				}
			}
		} 
		return false;
	}
	
	public static void main(String[] args) { // test

		GrilleHashmapMorpion testGrille = new GrilleHashmapMorpion(4);

		// instanciation de joueurTexte de type abstrait Joueur
		JoueurTexte joueur1 = new JoueurTexte(testGrille, "Joueur 1", "1");
	//	JoueurTexte joueur2 = new JoueurTexte(testGrille, "Joueur 2", "2");

		Coordonnee test1 = new Coordonnee("A0");
		Coordonnee test2 = new Coordonnee("A1");
		Coordonnee test3 = new Coordonnee("A2");
		Coordonnee test4 = new Coordonnee("A3");
	//	Coordonnee testJ2 = new Coordonnee("B1");

		testGrille.ajoutePionParJoueur(joueur1, test1);
		testGrille.ajoutePionParJoueur(joueur1, test2);
		testGrille.ajoutePionParJoueur(joueur1, test3);
		testGrille.ajoutePionParJoueur(joueur1, test4);
//		System.out.println(testGrille.tourJoue());
		
		System.out.println(">>nb pionCol"+testGrille.getNbPionDansColonne(0, joueur1));
//		System.out.println(testGrille.finDujeu(joueur1, joueur2));
		
		


		System.out.println(testGrille.toString());
	}


	public LinkedHashMap<CaseGrille, Joueur> getGrille() {
		return grille;
	}

}