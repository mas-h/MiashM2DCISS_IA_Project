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
		this.caseOccupeesJ1 = new Coordonnee[(taille * taille)/ 2]; // On créé un
																// tableau vide
																// de la taille
																// de la grille divisé par 2 (nbr de coup qu'il pourra jouer)
																// qui
																// contiendra
																// des
																// coordonnées de J1
		
		this.caseOccupeesJ2 = new Coordonnee[(taille * taille)/2]; // On créé un
																// tableau vide
																// de la taille
																// de la grille divisé par 2 (nbr de coup qu'il pourra jouer)
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
				Coordonnee coord = new Coordonnee(j, i);
				// on regarde si la case correspond à une case ayant pris un
				// obus
				for (int k = 0; k < caseOccupeesJ1.length; k++) {
					if (caseOccupeesJ1[k]!= null){
						if (caseOccupeesJ1[k].equals(coord)) {
							// si une coordonnée a été utilisé par J1 on marque un X

							cases = cases + "\tX";
							bool = true;}
						// si la case est utilisée par le J2 on marque 0
					} 
					else if (caseOccupeesJ2[k]!=null){
							if (caseOccupeesJ2[k].equals(coord)) {
								cases = cases + "\t0";
								bool = true;
							}
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
	
	//on verifie que les coordonées en paramètres rentre dans la grille
		private boolean estDansGrille(Coordonnee c) {
			if (c.getLigne() <= taille && c.getColonne() <= taille && c.getLigne() > 0 && c.getColonne() > 0)
				return true;
			return false;
		}
		
	//On verifie si la coordonnées a déjà été utilisée par l'un des joueurs
		private boolean estDejaUse(Coordonnee c) {
			for (int i = 0; i <= nbCoupJoue; i++)
				if (caseOccupeesJ1[i]==null && caseOccupeesJ2[i]==null )
					return false;
				else{
					if (caseOccupeesJ1[i].equals(c)||caseOccupeesJ2[i].equals(c))
						return true;}
			
			return false;
		}
		
		//on ajoute dans le tableau de coord, du joueur effectuant son moov, la coordonnée choisie si cela est possible 
		private boolean ajouteDansCaseUse(int numJoueur,Coordonnee c) { // numJoueur = 1 pour j1 et 2 pour J2 //// solution à discuter car pas le mieux 
			
			if ( !(estDansGrille(c)) || estDejaUse(c)  ) {
				System.out.println("Case déjà utilisée ou est en dehors des limites de la grille");
				return false;
				}
			
			else {
				if (numJoueur == 1){ // si c'est pour le joueur1 on ajoute dans son tableau
					caseOccupeesJ1[nbCoupJoue] = c;
				}
				else 				// sinon dans le tableau J2
					caseOccupeesJ2[nbCoupJoue] = c;
				return true;
				}
		}
		public boolean tourJoue () { // petite méthode pour s'assurer que les deux joueurs ont bien joué et incrémente le nbr de coup joué (commun au deux participants)
			if (caseOccupeesJ1[nbCoupJoue]==null || caseOccupeesJ2[nbCoupJoue]==null){
				System.out.println("Le tour n'est pas terminé, l'un des joueur n'a pas fait son moov");
				return false;
			}
			else {
				System.out.println("Tour de jeu "+nbCoupJoue+" terminé");
				
				return true;
			}
			
		}
		
		public boolean tourSuivant(){
			if  (!tourJoue())
				return false;
			else
				nbCoupJoue += 1;
				return true;
		}
		
		public boolean finDujeu(){ // TODO définir les règles de victoires 
			
			if (nbCoupJoue==caseOccupeesJ1.length && tourJoue()) // cas d'égalité entre les joueurs
				return true;
			else 
				return false;
		}
		
	// TODO Continuer à implémenter les méthodes pour pouvoir tester ça

	public static void main(String[] args) { // test
		// TODO Auto-generated method stub
		// problème de null pointer exception en testant ça, parce que mes tableaux sont vides quand je fais mes vérif du début
		GrilleMorpion testGrille = new GrilleMorpion(4);
		Coordonnee testJ1 = new Coordonnee("B2");
		Coordonnee testJ2 = new Coordonnee("C2");
		testGrille.ajouteDansCaseUse(1, testJ1);
		//testGrille.ajouteDansCaseUse(2, testJ2);
		System.out.println(testGrille.caseOccupeesJ1[0]);
		System.out.println(testGrille.toString());
	}
	
	

}
