package com.OHMCorporation.Morpion;

import java.util.Scanner;


//import batailleNavale.Coordonnee;

public abstract class Joueur {
	
	protected GrilleHashmapMorpion grilleDeJeu;
	private String nomJoueur;
	private String typeDejoueur;
	private Joueur adversaire;
	private int nbJoue =0;
	private Scanner scInput = new Scanner(System.in);
	private int profondeur;
	private int numeroDeJoueur; // numero de joueur 1 ou 2 
	

	public Joueur(GrilleHashmapMorpion grilleJeu, String nom, String type) {
		this.grilleDeJeu = grilleJeu;
		this.nomJoueur = nom;
		this.typeDejoueur = type;
	}
	
	public int getProfondeur() {
		return profondeur;
	}

	public void setProfondeur(int difficulteProfondeur) {
		this.profondeur = difficulteProfondeur;
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
	
	public String getTypeDeJoueur() {
		return this.typeDejoueur;
	}
	
	public int getNbJoue() {
		return nbJoue;
	}
	

	public int getNumeroDeJoueur() {
		return numeroDeJoueur;
	}

	public void setNumeroDeJoueur(int numeroDeJoueur) {
		this.numeroDeJoueur = numeroDeJoueur;
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
			System.out.println("C'est au tour du joueur " + this.getNom() + " de jouer !");

			System.out.println("En quelle coordonnée allez-vous jouer ?: \n");
			
			// si on jour contre une IA
			if (this.getTypeDeJoueur().equals("2")) {
				System.out.println("Infos >> appel de l'algo de l'ia");
				inputCoordStr = returnBestCoord(grilleDeJeu, profondeur).toString(); // TODO: resultat d'exemple à changer par le retour de la methode
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
	
	
	
	

	
	public abstract int evaluationGrille(GrilleHashmapMorpion grille);
	
	public abstract int findMax(GrilleHashmapMorpion grille, int profondeur);
	
	public abstract int findMin(GrilleHashmapMorpion grille, int profondeur);
	
	public abstract Coordonnee returnBestCoord(GrilleHashmapMorpion grille, int profondeur);
//	public abstract void debutAttaque();

}
