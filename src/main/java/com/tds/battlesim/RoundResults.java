package com.tds.battlesim;

public class RoundResults {

	private Side aSide;
	private int aSideTroopCountAtStartOfRound;
	private int aSideTroopCountAtEndOfRound;
	private double aSideDamageCaused;
	
	private Side anotherSide;
	private int anotherSideTroopCountAtStartOfRound;
	private int anotherSideTroopCountAtEndOfRound;
	private double anotherSideDamageCaused;
	
	public RoundResults() {}
	
	public RoundResults( Side aSide, Side anotherSide ) {
		this.aSide = aSide;
		setSideTroopCountAtStartOfRound( aSide.size() );
		
		this.anotherSide = anotherSide;
		setAnotherSideTroopCountAtStartOfRound( anotherSide.size() );
	}
	
	/**
	 * Temporary reliance on this until we decide how to truly handle output from battle
	 */
	public String toString() {		
		StringBuilder results = new StringBuilder();
		results.append( "After this round, " + aSide + " troops remaining:  " + aSide.size() );
		results.append( "\t" );
		results.append( anotherSide + " troops remaining:  " + anotherSide.size() );
		return results.toString();
	}
	
	public Side getSide() {
		return aSide;
	}
	
	public void setSide(Side aSide) {
		this.aSide = aSide;
	}
	
	public int getSideTroopCountAtStartOfRound() {
		return aSideTroopCountAtStartOfRound;
	}
	
	public void setSideTroopCountAtStartOfRound(int aSideTroopCountAtStartOfRound) {
		this.aSideTroopCountAtStartOfRound = aSideTroopCountAtStartOfRound;
	}
	
	public int getSideTroopCountAtEndOfRound() {
		return aSideTroopCountAtEndOfRound;
	}
	
	public void setSideTroopCountAtEndOfRound(int aSideTroopCountAtEndOfRound) {
		this.aSideTroopCountAtEndOfRound = aSideTroopCountAtEndOfRound;
	}
	
	public double getSideDamageCaused() {
		return aSideDamageCaused;
	}
	
	public void setSideDamageCaused(double aSideDamageCaused) {
		this.aSideDamageCaused = aSideDamageCaused;
	}
	
	public Side getAnotherSide() {
		return anotherSide;
	}
	
	public void setAnotherSide(Side anotherSide) {
		this.anotherSide = anotherSide;
	}
	
	public int getAnotherSideTroopCountAtStartOfRound() {
		return anotherSideTroopCountAtStartOfRound;
	}
	
	public void setAnotherSideTroopCountAtStartOfRound(
			int anotherSideTroopCountAtStartOfRound) {
		this.anotherSideTroopCountAtStartOfRound = anotherSideTroopCountAtStartOfRound;
	}
	
	public int getAnotherSideTroopCountAtEndOfRound() {
		return anotherSideTroopCountAtEndOfRound;
	}
	
	public void setAnotherSideTroopCountAtEndOfRound(
			int anotherSideTroopCountAtEndOfRound) {
		this.anotherSideTroopCountAtEndOfRound = anotherSideTroopCountAtEndOfRound;
	}
	
	public double getAnotherSideDamageCaused() {
		return anotherSideDamageCaused;
	}
	
	public void setAnotherSideDamageCaused(double anotherSideDamageCaused) {
		this.anotherSideDamageCaused = anotherSideDamageCaused;
	}
		
}
