package com.tds.battlesim;

public class Side {

	public static Side SMALL = new Side( 50 );
	public static Side LARGE = new Side( 500 );
	
	private int troopCount;
	
	public Side() {}
	
	public Side( int troops ) {
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
	
	public void sufferDamage( int damage ) {
		this.troopCount = this.troopCount - damage;
	}
	
	public int size() {
		return this.troopCount;
	}
	
}
