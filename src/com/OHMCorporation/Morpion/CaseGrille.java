package com.OHMCorporation.Morpion;

public class CaseGrille {
	
	private Coordonnee coord;
	private boolean caseOccupee = false;
	private int poidsCase = 1;
	private String ligneCol = "";
	
	
	@Override
	public String toString() {
		return "CaseGrille [coord=" + coord + ", caseOccupee=" + caseOccupee + ", poidsCase=" + poidsCase + "]" +
				" ligneCol: " + ligneCol;
	}



	public CaseGrille(Coordonnee c, boolean statusCase, int poids) {
		this.coord = c;
		this.caseOccupee = statusCase;
		this.poidsCase = poids;
	}
	
	public CaseGrille(String ligneColone, int poids) {
		this.ligneCol = ligneColone;
		this.poidsCase = poids;
	}
	
	public int getPoidsCase() {
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
