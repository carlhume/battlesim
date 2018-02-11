package com.tds.battlesim;

public class Side {

	public static Side SMALL = new Side( "Small Side", 50 );
	public static Side LARGE = new Side( "Large Side", 500 );
	
	private String name;
	private int troopCount;
	
	public Side() {}
	
	public Side( int troops ) {
		this( "No name", troops );
	}
	
	public Side( String name, int troops ) {
		this.name = name;
		this.troopCount = troops;
	}
	
	public boolean isRetreating() {
		return this.troopCount <= 0;
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
}
