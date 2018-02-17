package com.tds.battlesim;

public class Side {

	public static Side SMALL = new Side( "Small Side", 50 );
	public static Side LARGE = new Side( "Large Side", 500 );
	
	private String name;
	private int troopCount;
	private double retreatThreashhold;
	
	/**
	 * Stores how many troops were available at the start of the battle
	 * Used to help determine when a side will want to retreat
	 */
	private transient int startingSize = 0;
	
	public Side() {}
	
	public Side( int troops ) {
		this( "No name", troops );
	}
	
	public Side( String name, int troops ) {
		this.name = name;
		this.troopCount = troops;
		this.startingSize = troops;
	}
	
	public boolean isRetreating() {
		return size() <= troopsThatCanBeLostBeforeSideWantsToRetreat();
	}

	private double troopsThatCanBeLostBeforeSideWantsToRetreat() {
		return this.startingSize * getRetreatThreashhold();
	}
	
	public int getAttackValue() {
		return this.troopCount;
	}
	
	public int getDefenseValue() {
		return 2;
	}
	
	public void sufferDamage( double damage ) {
		this.troopCount = this.troopCount - (int)damage;
	}
	
	public int size() {
		return this.troopCount;
	}
	
	public String toString() {
		return this.name;
	}

	public double getRetreatThreashhold() {
		return retreatThreashhold;
	}

	public void setRetreatThreashhold(double retreatThreashhold) {
		this.retreatThreashhold = retreatThreashhold;
	}

}