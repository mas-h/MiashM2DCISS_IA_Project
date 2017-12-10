package com.OHMCorporation.Morpion;

public class CaseGrille {
	
	private Coordonnee coord;
	private boolean caseOccupee = false;
	private double poidsCase = 1;
	
	
	public CaseGrille(Coordonnee c, boolean statusCase, double poids) {
		this.coord = c;
		this.caseOccupee = statusCase;
		this.poidsCase = poids;
	}
	
	
	public double getPoidsCase() {
		return poidsCase;
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
