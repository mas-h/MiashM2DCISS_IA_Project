package com.OHMCorporation.Morpion;

public class CaseGrille {
	
	private Coordonnee position;
	private boolean caseOccupee = false;
	
	
	public CaseGrille(Coordonnee c, boolean statusCase) {
		this.position = c;
		this.caseOccupee = statusCase;
	}
	
	
	public Coordonnee getPosition() {
		return position;
	}
	
	public void setPosition(Coordonnee position) {
		this.position = position;
	}
	
	public boolean isOccupied() {
		return caseOccupee;
	}
	
	public void setOccupied(boolean occupied) {
		this.caseOccupee = occupied;
	};
	
	

}
