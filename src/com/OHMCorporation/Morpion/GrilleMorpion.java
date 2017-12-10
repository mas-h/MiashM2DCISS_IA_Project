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

		// Ecriture des colones avec le un référencement alphabétique
		String casesGrille = "";
		for (int iAlphabetique = 0; iAlphabetique < this.taille; iAlphabetique++) {
			char c = (char) (iAlphabetique + 'A');
			casesGrille = casesGrille + "\t" +  c;
		}

		// écriture des numéros de lignes + cases des grilles 
		for (int iLignes = 0; iLignes < this.taille; iLignes++) {
			// ecriture de la première ligne avec les nombres au coordonnées <col,ligne> : <0,1> 
			casesGrille = casesGrille + "\n" + iLignes; // rajouter i+1 pour le décallage
			//System.out.println("ligne n°: "+iLignes); // -> OK 
			for (int jColones = 0; jColones < this.taille; jColones++) {
				//System.out.println("colone n°: "+jColones); // -> OK
				boolean caseDejaJouee = false;
				// point de départ: <O;O>
				Coordonnee coord = new Coordonnee(jColones, iLignes);
				// System.out.println("coord: " + coord); // -> OK
				
				// on regarde si la case correspond à une case ayant été joué				
				for (int k = 0; k < caseOccupeesJ1.length; k++) {
//					System.out.println("test: "+ caseOccupeesJ1[k]);
					if (caseOccupeesJ1[k] != null) {
						// si une coordonnée a été utilisé par J1 on marque un X
						// Pourquoi tester si
						if (caseOccupeesJ1[k].equals(coord)) { // TODO: need explanations
//							System.out.println("testJ1  "+ caseOccupeesJ1[k].equals(coord));
							casesGrille = casesGrille + "\tX";
							caseDejaJouee = true;
						}
					}
					// si la case est utilisée par le J2 on marque 0
					if (caseOccupeesJ2[k] != null) {
						if (caseOccupeesJ2[k].equals(coord)) { // TODO: same
							System.out.println("testJ2  "+ caseOccupeesJ1[k].equals(coord));
							casesGrille = casesGrille + "\tO";
							caseDejaJouee = true;
						}
					}
				}

				// Si ni utilisé par J1 ou J2 dans la case alors mettre un point
				if (caseDejaJouee == false) {
					casesGrille = casesGrille + "\t-";
				}
			}
//			System.out.println("--------");
		}
		 // On revoie le tout ! 
		return (casesGrille);

	}
	
	//on verifie que les coordonées en paramètres rentre dans la grille
		private boolean estDansGrille(Coordonnee c) {
//			return ((c.getLigne() <= taille && c.getColonne() <= taille && c.getLigne() > 0 && c.getColonne() > 0));
			return ((c.getLigne() < taille && c.getColonne() < taille && c.getLigne() >= 0 && c.getColonne() >= 0));
		}
		
	//On verifie si la coordonnées a déjà été utilisée par l'un des joueurs
		private boolean estDejaOcp(Coordonnee c) {
			for (int i = 0; i <= nbCoupJoue; i++)
				if (caseOccupeesJ1[i]==null || caseOccupeesJ2[i]==null )
					return false;
				else{
					if (caseOccupeesJ1[i].equals(c)||caseOccupeesJ2[i].equals(c))
						return true;
					}
			
			return false;
		}
		
		// compliqué avec 2 grilles à revoir si il reste du temps ( 1 seule grille avec valeurs J1=1 et J2=2 par exemple )
		//on ajoute dans le tableau de coord, du joueur effectuant son moov, la coordonnée choisie si cela est possible 
		private boolean ajouteDansCaseUse(int numJoueur,Coordonnee c) { // numJoueur = 1 pour j1 et 2 pour J2 //// solution à discuter car pas le mieux 
			// TODO: En fait il est peut etre bien ici le bug...
			if ( !estDansGrille(c) || estDejaOcp(c)  ) { // TODO: c'était le estDansGrille

				System.out.println("Case déjà utilisée ou est en dehors des limites de la grille");
				return false;
			
			} else {		
//				System.out.println("Case déjà utilisée ou est en dehors des limites de la grille");
//				return false;
				if (numJoueur == 1){	// si c'est pour le joueur1 on ajoute dans son tableau 
					caseOccupeesJ1[nbCoupJoue] = c;
				} else { 	// sinon dans le tableau J2 				
					caseOccupeesJ2[nbCoupJoue] = c;
			
				}
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
		
		public int getNbPionDansLigne(Coordonnee [] tabJoueur, int numLigne){ // permet de retourner le nombre de pion dans une ligne donnée d'un joueur
			int nbPionDansLigne = 0;										  // Vérifiée, ça marche
			for (int i = 0; i<nbCoupJoue;i++){
				if (tabJoueur[i].getLigne()==numLigne){
					nbPionDansLigne += 1;
				}
			}
			return nbPionDansLigne;
		}
		
		public int getNbPionDansColonne(Coordonnee [] tabJoueur, int numColone){ // permet de retourner le nombre de pion(s) dans une colone donnée d'un joueur
			int nbPionDansColonne = 0;										  // Vérifiée, ça marche
			for (int i = 0; i<nbCoupJoue;i++){
				if (tabJoueur[i].getColonne()==numColone){
					nbPionDansColonne += 1;
				}
			}
			return nbPionDansColonne;
		}
		
		public int getnbPionDansDiagonaleA0(Coordonnee [] tabJoueur){ // méthode pour les verif de pion dans la diagonale qui débute en A0 et finie en D4
			int nbPionDansDiagonale = 0;
			Coordonnee A0 = new Coordonnee("A0");
			Coordonnee B1 = new Coordonnee("B1");
			Coordonnee C2 = new Coordonnee("C2");
			Coordonnee D3 = new Coordonnee("D3");
			
			for (int i = 0; i<nbCoupJoue;i++){
				if (tabJoueur[i].equals(A0)||tabJoueur[i].equals(B1)||tabJoueur[i].equals(C2)||tabJoueur[i].equals(D3)){
					nbPionDansDiagonale += 1;
				}
			}
			return nbPionDansDiagonale;
		}
		
		public int getnbPionDansDiagonaleD0(Coordonnee [] tabJoueur){ // même chose pour la dagionale qui débute en D0 et finie en A3
			int nbPionDansDiagonale = 0;
			Coordonnee D0 = new Coordonnee("D0");
			Coordonnee C1 = new Coordonnee("C1");
			Coordonnee B2 = new Coordonnee("B2");
			Coordonnee A3 = new Coordonnee("A3");
			
			for (int i = 0; i<nbCoupJoue;i++){
				if (tabJoueur[i].equals(D0)||tabJoueur[i].equals(C1)||tabJoueur[i].equals(B2)||tabJoueur[i].equals(A3)){
					nbPionDansDiagonale += 1;
				}
			}
			return nbPionDansDiagonale;
		}
		

		
		public boolean finDujeu(){ // TODO définir les règles de victoires pour les diagonales
								   // Fonctionne pour les cas d'égalités, les cas de victoire horizontaux et verticaux
			
			for (int i = 0; i<this.taille;i++){ // on fait les test pour toutes les lignes et les colonnes (4 itérations ici au plus, taille de la grille)
				if (this.getNbPionDansColonne(caseOccupeesJ1, i) == this.taille || this.getNbPionDansLigne(caseOccupeesJ1, i) == this.taille 		// si 4 cases avec la même lignes ou 4 cases avec la même colonne alors c'est gagné
					|| this.getnbPionDansDiagonaleA0(caseOccupeesJ1) == this.taille || this.getnbPionDansDiagonaleD0(caseOccupeesJ1) == this.taille){ 
					System.out.println("Victoire du joueur 1");																				  		// vérif pour le Joueur1
					System.out.println("Partie terminée !");
					return true;
				}
				if (this.getNbPionDansColonne(caseOccupeesJ2, i) == this.taille || this.getNbPionDansLigne(caseOccupeesJ2, i) == this.taille 		// même chose pour le Joueur 2
					|| this.getnbPionDansDiagonaleA0(caseOccupeesJ2) == this.taille || this.getnbPionDansDiagonaleD0(caseOccupeesJ2) == this.taille){
					System.out.println("Victoire du joueur 2");
					System.out.println("Partie terminée !");
					return true;
				}
				
			}
			if (nbCoupJoue==caseOccupeesJ1.length && tourJoue()) { // cas d'égalité entre les joueurs
				System.out.println("Egalité !");
				System.out.println("Partie terminée !");
				return true;}
		 
			return false;
		}
		
	// TODO Continuer à implémenter les méthodes pour pouvoir tester ça

	public static void main(String[] args) { // test
		// TODO Auto-generated method stub
		// problème de null pointer exception en testant ça, parce que mes tableaux sont vides quand je fais mes vérif du début
		GrilleMorpion testGrille = new GrilleMorpion(4);
		
		Coordonnee testJ1 = new Coordonnee("A0");
		Coordonnee testJ2 = new Coordonnee("D3");
		Coordonnee testJ12 = new Coordonnee("A1");
		Coordonnee testJ22 = new Coordonnee("B1");
		Coordonnee testJ13 = new Coordonnee("A2");
		Coordonnee testJ23 = new Coordonnee("C1");
		Coordonnee testJ14 = new Coordonnee("A3");
		Coordonnee testJ24 = new Coordonnee("C2");
		
		testGrille.ajouteDansCaseUse(1, testJ1);
		testGrille.ajouteDansCaseUse(2, testJ2);
		testGrille.tourSuivant();
		
		testGrille.ajouteDansCaseUse(1, testJ12);
		testGrille.ajouteDansCaseUse(2, testJ22);
		testGrille.tourSuivant();
		
		testGrille.ajouteDansCaseUse(1, testJ13);
		testGrille.ajouteDansCaseUse(2, testJ23);
		testGrille.tourSuivant();
		
		testGrille.ajouteDansCaseUse(1, testJ14);
		testGrille.ajouteDansCaseUse(2, testJ24);
		testGrille.tourSuivant();

		

		System.out.println(testGrille.caseOccupeesJ1[0]);
		System.out.println(testGrille.caseOccupeesJ2[0]);
//		System.out.println(testGrille.caseOccupeesJ1[1]);
//		System.out.println(testGrille.caseOccupeesJ2[1]);
//		System.out.println(testGrille.caseOccupeesJ1[2]);
//		System.out.println(testGrille.caseOccupeesJ2[2]);

		
		System.out.println(testGrille.toString());
		
		System.out.println("Nombre de pion(s) dans la ligne 1 pour le joueur 2 : "+testGrille.getNbPionDansLigne(testGrille.caseOccupeesJ2, 1));
		System.out.println("Nombre de pion(s) dans la colone 1 pour le joueur 1 : "+testGrille.getNbPionDansColonne(testGrille.caseOccupeesJ1, 0));
		System.out.println("Nombre de pion(s) dans la diago 1 pour le joueur 2 : "+testGrille.getnbPionDansDiagonaleA0(testGrille.caseOccupeesJ2));
		System.out.println("Nombre de pion(s) dans la diago 2 pour le joueur 1 : "+testGrille.getnbPionDansDiagonaleA0(testGrille.caseOccupeesJ1));
		System.out.println("Partie terminée : "+ testGrille.finDujeu());
	}
	
	

}