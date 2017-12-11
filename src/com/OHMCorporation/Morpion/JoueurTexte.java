package com.OHMCorporation.Morpion;

import java.util.Scanner;


public class JoueurTexte extends Joueur{

//	private Scanner scInput = new Scanner(System.in);


	public JoueurTexte(GrilleHashmapMorpion grilleJeu,String nom, String id) {
		super(grilleJeu, nom, id);
	}
	

	@Override
	protected void perdu() {
		System.out.println("Le joueur "+this.getNom()+ " a perdu La partie.");
	}

	@Override
	protected void gagne() {
		System.out.println("Le joueur "+this.getNom()+ " a gangé La partie.");		
	}

	@Override
	protected void retourAttaque(Coordonnee c, int etat) {
		
//		if(etat == TOUCHE) {
//			System.out.println(this.getNom() + " touche un bateau en " + c);
//			} else if (etat == COULE) {
//				System.out.println(this.getNom() + " coule un bateau en " + c);
//			} else if (etat == A_L_EAU) { 
//				System.out.println(this.getNom() + " tire à l'eau en " + c );
//			} else {
//				System.out.println("état: " + etat);
//		}		
	}

	@Override
	protected void retourDefense(Coordonnee c, int etat) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public int evaluationGrille(GrilleHashmapMorpion grilleJeu) {
		// do nothing
		return 0;
	}


	@Override
	public double Max(GrilleHashmapMorpion grille, int profondeur) {
		// TODO Auto-generated method stub
		return 0;
	}


	@Override
	public double Min(GrilleHashmapMorpion grille, int profondeur) {
		// TODO Auto-generated method stub
		return 0;
	}


	@Override
	public Coordonnee returnBestCoord(GrilleHashmapMorpion grille, int profondeur) {
		// TODO Auto-generated method stub
		return null;
	}


	
	

}
