package com.OHMCorporation.Morpion;

import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;

public class GrilleHashmapMorpion {

	// ATTRIBUTS DE LA CLASSE
	private int taille; // la taille de la grille

	private Coordonnee[] caseOccupeesJ1;	// servira à ranger les cases utilisées par le J1
	private Coordonnee[] caseOccupeesJ2;	// servira à ranger les cases utilisées par le J2
//	private int caseOcpJ1 = 0;
//	private int caseOcpJ2 = 0;
	
	private int nbToursJoues; // permettra de définir quand la partie est finie en cas d'égalité

	// la grille est représentée dans un map. CaseGrille initialisé par une
	// Coordonnée,null car auccun joueur n'a encore joué la case
	// on pourra mettre différents types de joueurs, JoueurTexte, JoueurIA, etc ..
	// private HashMap <CaseGrille, Joueur> grille = new HashMap<>();
	private LinkedHashMap<Coordonnee, Joueur> grille = new LinkedHashMap<>();

	private int nbCoupsJ1 = 0;
	private int nbCoupsJ2 = 0;

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
				// Version avec la classe CaseGrille
				// CaseGrille caseG = new CaseGrille(c, false);
				// grille.put(caseG, null);
				// System.out.println(c);
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
			casesGrille = casesGrille + "\t" + c;
		}

		int nbCol = 0;
		int nbLigne = 0;

		for (Map.Entry<Coordonnee, Joueur> entry : this.grille.entrySet()) {

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
					// System.out.println("j1");
					casesGrille = casesGrille + "\tX";
					nbCol += 1;
				} else {
					// System.out.println("j2");
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
		for (Map.Entry<Coordonnee, Joueur> entry : this.grille.entrySet()) {
			if (entry.getKey().equals(c) && entry.getValue() != null) {
				// System.out.println(entry.getKey() + " = " + entry.getValue()+" est deja ocp")
				return true;
			}
		}
		return false;
	}

	// compliqué avec 2 grilles à revoir si il reste du temps ( 1 seule grille avec
	// valeurs J1=1 et J2=2 par exemple )
	// on ajoute dans le tableau de coord, du joueur effectuant son moov, la
	// coordonnée choisie si cela est possible
	private boolean ajouteDansCaseUse(Joueur joueur, Coordonnee c) { 
		if (!estDansGrille(c) || estDejaOcp(c)) { // TODO: c'était le estDansGrille
			System.out.println("Case déjà utilisée ou est en dehors des limites de la grille");
			return false;
		} else {
			for (Map.Entry<Coordonnee, Joueur> entry : this.grille.entrySet()) {
				if (entry.getKey().equals(c)) {
//					System.out.println("get: " + entry.getKey());
					entry.setValue(joueur);
					if (joueur.getID().equals("1")) {
						nbCoupsJ1 ++;
					} else if (joueur.getID().equals("2")) { 
						nbCoupsJ2 ++;
					}
					return true;
				}
			}
			return false;
		}
	}

	/**
	 * Verifie que le nombre de coups joués par les 2 joueurs soit égaux
	 * @return
	 */
	public boolean tourJoue() { 
		for (Map.Entry<Coordonnee, Joueur> entry : this.grille.entrySet()) {
			if(entry.getValue() == null ) {
			}
			else { 	
				if (entry.getValue().getID().equals("1")) {
					nbCoupsJ1 += 1;
				}
				if (entry.getValue().getID().equals("2")) {
					nbCoupsJ2 += 1;}	
			}			
		}
		return (nbCoupsJ1 == nbCoupsJ2);
	}

	public boolean tourSuivant() {
		if (!tourJoue()) {
			return false;
		} else {
			nbToursJoues += 1;
			return true;
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
		for (Map.Entry<Coordonnee, Joueur> entry : this.grille.entrySet()) {
			if(j.equals(entry.getValue()) && entry.getKey().getLigne() == numLigne) {
				nbPionDansLigne++;
			}		
		}
		return nbPionDansLigne;
	}
	
	public int getNbPionDansColonne(int numColone, Joueur j){ // permet de retourner le nombre de pion(s) dans une colone donnée d'un joueur
		int nbPionDansColonne = 0;		
		for (Map.Entry<Coordonnee, Joueur> entry : this.grille.entrySet()) {
			if(j.equals(entry.getValue()) && entry.getKey().getColonne() == numColone) {
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
		
		for (Map.Entry<Coordonnee, Joueur> entry : this.grille.entrySet()) {
			if(j.equals(entry.getValue()) && ( entry.getKey().equals(A0) || entry.getKey().equals(B1) 
					|| entry.getKey().equals(C2) || entry.getKey().equals(D3)) ) {
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
		
		for (Map.Entry<Coordonnee, Joueur> entry : this.grille.entrySet()) {
			if(j.equals(entry.getValue()) && ( entry.getKey().equals(D0) || entry.getKey().equals(C1) 
					|| entry.getKey().equals(B2) || entry.getKey().equals(A3)) ) {
				nbPionDansDiagonale++;
			}		
		}
		return nbPionDansDiagonale;
	}
	

	// Fonctionne pour les cas d'égalités, les cas de victoire horizontaux et verticaux
	public boolean finDujeu(Joueur j1, Joueur j2){ 
		int maxCoupJoue = grille.size()/2;
		// cas d'égalité entre les joueurs
		if(nbCoupsJ1 == maxCoupJoue || nbCoupsJ2 == maxCoupJoue) {
//		if (this.grille.si==(nb)caseOccupeesJ1.length && tourJoue()) { 
			System.out.println("Egalité !");
			System.out.println("Partie terminée !");
			return true;
		} else {   // on fait les test pour toutes les lignes et les colonnes (4 itérations ici au plus, taille de la grille)
			for (int i = 0; i<this.taille;i++) { 
				// si 4 cases avec la même lignes ou 4 cases avec la même colonne alors c'est gagné
				if (this.getNbPionDansColonne(i, j1) == this.taille || this.getNbPionDansLigne(i, j1) == this.taille 		
						|| this.getnbPionDansDiagonaleA0(j1) == this.taille || this.getnbPionDansDiagonaleD0(j1) == this.taille) { 
					System.out.println("Victoire du joueur 1");																				  		// vérif pour le Joueur1
					System.out.println("Partie terminée !");
					return true;
				}
				if (this.getNbPionDansColonne(i, j2) == this.taille || this.getNbPionDansLigne(i, j2) == this.taille 		// même chose pour le Joueur 2
						|| this.getnbPionDansDiagonaleA0(j2) == this.taille || this.getnbPionDansDiagonaleD0(j2) == this.taille) {
					System.out.println("Victoire du joueur 2");
					System.out.println("Partie terminée !");
					return true;
				}
				
			}
		}
							   
		
		
	 
		return false;
	}
	// TODO Continuer à implémenter les méthodes pour pouvoir tester ça

	public static void main(String[] args) { // test

		GrilleHashmapMorpion testGrille = new GrilleHashmapMorpion(4);

		// instanciation de joueurTexte de type abstrait Joueur
		JoueurTexte joueur1 = new JoueurTexte(testGrille, "Joueur 1", "1");
		JoueurTexte joueur2 = new JoueurTexte(testGrille, "Joueur 2", "2");

		Coordonnee test1 = new Coordonnee("A0");
		Coordonnee test2 = new Coordonnee("A1");
		Coordonnee test3 = new Coordonnee("A2");
		Coordonnee test4 = new Coordonnee("A3");
		Coordonnee testJ2 = new Coordonnee("B1");


		// System.out.println("coordonnee deja occupée ??: "+
		// testGrille.estDejaOcp(testJ1));
		// testGrille.ajouteDansCaseUse(joueur1, testJ1);
		// System.out.println("coordonnee deja occupée ??: "+
		// testGrille.estDejaOcp(testJ1));
		// System.out.println("coordonnee deja occupée ??: "+
		// testGrille.estDejaOcp(testJ2));
		// testGrille.ajouteDansCaseUse(joueur2, testJ2);
		// System.out.println("coordonnee deja occupée ??: "+
		// testGrille.estDejaOcp(testJ2));
		// testGrille.tourSuivant();

		testGrille.ajouteDansCaseUse(joueur1, test1);
		testGrille.ajouteDansCaseUse(joueur1, test2);
		testGrille.ajouteDansCaseUse(joueur1, test3);
		testGrille.ajouteDansCaseUse(joueur1, test4);
//		System.out.println(testGrille.tourJoue());
		
		System.out.println(">>nb pionCol"+testGrille.getNbPionDansColonne(0, joueur1));
		System.out.println(testGrille.finDujeu(joueur1, joueur2));
		
		


		System.out.println(testGrille.toString());
	}

}