package com.OHMCorporation.Morpion;

//import batailleNavale.Coordonnee;

public abstract class Joueur {
	
	private GrilleHashmapMorpion grilleDeJeu;
	private String nomJoueur;
	private String idJoueur;
	private Joueur adversaire;
	
	public Joueur(GrilleHashmapMorpion grilleJeu, String nom) {
		this.grilleDeJeu = grilleJeu;
		this.nomJoueur = nom;
	}
	
	private GrilleHashmapMorpion getGrille() {
		return this.grilleDeJeu;
	}
	
	public String getNom() {
		return this.nomJoueur;
	}
	
	public String getID() {
		return this.idJoueur;
	}
	
	public void joueAvec(Joueur j) {
		 this.adversaire = j;
		 j.adversaire = this;
	}
	
	public void attaque(Coordonnee c) {
		
		if(adversaire.defense(c)) {
			adversaire.debutAttaque();
			}
	}
	
	/*
	 * Retourne false si et seulement si la partie est terminée (le tir en c coule le dernier navire dans la grille de
	 * this). Cette méthode est invoquée par le joueur adverse lors d'une attaque de sa part. Un tir en c sur la grille de
	 * this est pris en compte et les conditions de victoire/défaite sont vérifiées. 
	 */
	/**
	 * Retourne false SEULEMENT si un joueur à gagné la partie ou si tous les coups on été joués et qu'il y a égalité.
	 * Cette méthode est invoquée par le joueur adverse lors d'une attaque de sa part.
	 * 
	 * @param c
	 * @return
	 */
	public boolean defense(Coordonnee c) {
		
		int etat = 0;
		
//		if (this.grilleDeJeu.) {
//			
//		}
//		this.grille.recoitTir(c);
		
//		// conditions de victoires vérifiées
//		if(this.grilleDeJeu.perdu()){
//			this.perdu();
//			this.adversaire.gagne();
//			return false;
//		}
		
//		// Si elles ne sont pas vérifiées
//		if(this.grilleDeJeu.estTouche(c))
//			etat = this.grilleDeJeu.estCoule(c) ? COULE : TOUCHE;
//		else
//			etat = A_L_EAU;
//		
		retourDefense(c, etat);
		adversaire.retourAttaque(c, etat);
		return true;	
	}
	
	
	
	
	// invoqué lorsque this a perdu la partie. Agit sur l'interface.
	protected abstract void perdu();
	
	// invoqué lorsque this a gagné la partie. Agit sur l'interface.
	protected abstract void gagne();	
	
	// donne le droit d'attaquer à l'autre.
	protected abstract void retourAttaque(Coordonnee c, int etat);	
	
	// donne le droit de défendre à l'autre.
	protected abstract void retourDefense(Coordonnee c, int etat);	
	
	public abstract void debutAttaque();

}
