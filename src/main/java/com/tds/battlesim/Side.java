package com.tds.battlesim;

public class Side {

	public static Side SMALL = new Side( "Small Side", 50 );
	public static Side LARGE = new Side( "Large Side", 500 );
	
	private String name;
	private Troops troops;
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
	
	public Side( String name, int numberOfTroops ) {
		this.name = name;
		troops = new Troops();
		troops.setCount( numberOfTroops );
		this.startingSize = numberOfTroops;
	}
	
	public boolean isRetreating() {
		return size() <= troopsThatCanBeLostBeforeSideWantsToRetreat();
	}

	private double troopsThatCanBeLostBeforeSideWantsToRetreat() {
		return this.startingSize * getRetreatThreashhold();
	}
	
	public int getAttackValue() {
		return troops.getCount();
	}
	
	public int getDefenseValue() {
		return 2;
	}
	
	public void sufferDamage( double damage ) {
		troops.setCount( troops.getCount() - (int)damage );
	}
	
	public int size() {
		return troops.getCount();
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
