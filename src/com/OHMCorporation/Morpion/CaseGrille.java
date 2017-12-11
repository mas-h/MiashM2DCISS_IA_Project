package com.OHMCorporation.Morpion;

public class CaseGrille {
	
	private Coordonnee coord;
	private boolean caseOccupee = false;
	private int poidsCase = 1;
	private int poidsLigne = 0;
	private String numLigne = "";
	
	
	@Override
	public String toString() {
		return "CaseGrille [poidsLigne=" + poidsLigne + ", numLigne=" + numLigne + "]";
	}

	public CaseGrille(Coordonnee c, boolean statusCase, int poids) {
		this.coord = c;
		this.caseOccupee = statusCase;
		this.poidsCase = poids;
	}
	
	public CaseGrille(String ligneColone, int poidsline) {
		this.numLigne = ligneColone;
		this.poidsLigne = poidsline;
	}
	
	
	
	public int getPoidsCase() {
		return poidsCase;
	}


	public int getPoidsLigne() {
		return poidsLigne;
	}



	public void setPoidsLigne(int poidsLigne) {
		this.poidsLigne = poidsLigne;
	}



	public void setPoidsCase(int poidsCase) {
		this.poidsCase = poidsCase;
	}


	public Coordonnee getCoordonnee() {
		return coord;
	}
	
	public void setCoordonnee(Coordonnee c) {
		this.coord = c;
	}
	
	public boolean getIsOccupied() {
		return caseOccupee;
	}
	
	public void setOccupied(boolean occupied) {
		this.caseOccupee = occupied;
	};
	
	

}
