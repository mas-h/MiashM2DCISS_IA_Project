package com.OHMCorporation.Morpion;

import java.util.Scanner;

//import batailleNavale.Coordonnee;

public abstract class Joueur {
	
	protected GrilleHashmapMorpion grilleDeJeu;
	private String nomJoueur;
	private String idJoueur;
	private Joueur adversaire;
	
	

	private int nbJoue =0;

	private Scanner scInput = new Scanner(System.in);

	
	public Joueur(GrilleHashmapMorpion grilleJeu, String nom, String id) {
		this.grilleDeJeu = grilleJeu;
		this.nomJoueur = nom;
		this.idJoueur = id;
	}
	
	public Joueur getAdversaire() {
		return adversaire;
	}
	
	
	public void setAdversaire(Joueur adversaire) {
		this.adversaire = adversaire;
	}
		
	public String getNom() {
		return this.nomJoueur;
	}
	
	public String getID() {
		return this.idJoueur;
	}
	
	public void jouerAvec(Joueur j) {
		 this.adversaire = j;
		 j.adversaire = this;
	}
	
	public void attaque(Coordonnee c) {
		grilleDeJeu.ajoutePionParJoueur(this, c);
		nbJoue ++;
		System.out.println(this.grilleDeJeu.toString());
		System.out.println("Infos >> Le joueur " +this.getNom()+" a joué en: "+ c);
		if(!this.grilleDeJeu.finDujeu(this)) {
			adversaire.debutAttaque();
		}
		
	}
	
	public void debutAttaque() {
		
		// vérifications des possibilités de jouer
		if (!this.grilleDeJeu.finDujeu(this)) {
			String inputCoordStr = "";
			Coordonnee inputCoord = new Coordonnee(0, 0);
			
			System.out.println("------------------------------------------");
			System.out.println("C'est au tour de " + this.getNom() + " de jouer !");

			System.out.println("En quelle coordonnée allez-vous jouer ?: \n");
			
			// si on jour contre une IA
			if (this.getNom().equals("Joueur IA")) {
				System.out.println(">> appel de l'algo de l'ia");
			
				// calcul de la meilleure coordonnée à jouer
//				System.out.println("typeof: "+ this.getClass());
				evaluationGrille(grilleDeJeu);
				inputCoordStr = "A1"; // TODO: resultat d'exemple à changer par le retour de la methode

				inputCoord = new Coordonnee(inputCoordStr);
				this.attaque(inputCoord);
			// sinon c'est un joueur	
			} else {			
				inputCoordStr = this.scInput.nextLine();
				inputCoord = new Coordonnee(inputCoordStr);
				this.attaque(inputCoord);
			}
		}
		
	}
	
	
	public boolean defense(Coordonnee c) {
		
		int etat = 0;

		retourDefense(c, etat);
		adversaire.retourAttaque(c, etat);
		return true;	
	}
	
	public int getNbJoue() {
		return nbJoue;
	}
	
	
	
	
	// invoqué lorsque this a perdu la partie. Agit sur l'interface.
	protected abstract void perdu();
	
	// invoqué lorsque this a gagné la partie. Agit sur l'interface.
	protected abstract void gagne();	
	
	// donne le droit d'attaquer à l'autre.
	protected abstract void retourAttaque(Coordonnee c, int etat);	
	
	// donne le droit de défendre à l'autre.
	protected abstract void retourDefense(Coordonnee c, int etat);	
	
	public abstract int evaluationGrille(GrilleHashmapMorpion grille);
	
//	public abstract void debutAttaque();

}
