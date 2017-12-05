package com.OHMCorporation.Morpion;



public class GrilleMorpion {

	// ATTRIBUTS DE LA CLASSE
	private int taille; // la taille de la grille
	private Coordonnee[] caseOccupeesJ1; // servira à ranger les cases utilisées
											// par le J1
	private Coordonnee[] caseOccupeesJ2; // servira à ranger les cases utilisées
											// par le J2
	private int nbCoupJoue; // permettra de définir quand la partie est finie en
							// cas d'égalité

	// CONSTRUCTEUR
	public GrilleMorpion(int taille) {
		// TODO Auto-generated constructor stub
		this.taille = taille; // On fabrique une grille de la taille donnée en
								// argument
		this.caseOccupeesJ1 = new Coordonnee[taille * taille]; // On créé un
																// tableau vide
																// de la taille
																// de la grille
																// qui
																// contiendra
																// des
																// coordonnées de J2
		
		this.caseOccupeesJ2 = new Coordonnee[taille * taille]; // On créé un
																// tableau vide
																// de la taille
																// de la grille
																// qui
																// contiendra
																// des
																// coordonnées de J2
		this.nbCoupJoue = 0;
	}

	// METHODES
	public String toString() { // permet d'afficher la grille dans la console

		// Ecriture de la première ligne avec les lettres de l'alphabeth
		String cases = "\t";
		for (int i = 0; i < this.taille; i++) {
			char c = (char) (i + 'A');
			cases = cases + c + "\t";
		}

		for (int i = 0; i < this.taille; i++) {
			// ecriture de la première ligne avec les nombres
			cases = cases + "\n" + i;
			for (int j = 0; j < this.taille; j++) {
				boolean bool = false;
				Coordonnee coord = new Coordonnee(i, j);
				// on regarde si la case correspond à une case ayant pris un
				// obus
				for (int k = 0; k < nbCoupJoue; k++) {
					if (caseOccupeesJ1[k].equals(coord)) {
						// si une coordonnée a été utilisé par J1 on marque un X

						cases = cases + "\tX";
						bool = true;
						// si la case est utilisée par le J2 on marque 0
					} else if (caseOccupeesJ2[k].equals(coord)) {
						cases = cases + "\t0";
						bool = true;
					}
				}

				// Si ni utilisé par J1 ou J2 dans la case alors mettre un point
				if (bool == false) {
					cases = cases + "\t-";
				}
			}
		}
		return (cases); // On revoie le tout ! MAIS TANT MIEUX ! MAIS TANT MIEUX !

	}
	
	// TODO Continuer à implémenter les méthodes pour pouvoir tester ça

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}
	
	

}
