package com.tds.battlesim;

import java.util.ArrayList;
import java.util.Collection;

public class Side {

	public static Side SMALL = new Side( "Small Side", 50 );
	public static Side LARGE = new Side( "Large Side", 500 );
	
	private String name;
	private Collection<Troops> troops = new ArrayList<Troops>();
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
		Troops newTroops = new Troops();
		newTroops.setCount( numberOfTroops );
		addTroops( newTroops );
		this.startingSize = numberOfTroops;
	}
	
	public void addTroops( Troops troopsToAdd ) {
		troops.add( troopsToAdd );
	}
	
	public boolean isRetreating() {
		return size() <= troopsThatCanBeLostBeforeSideWantsToRetreat();
	}

	private double troopsThatCanBeLostBeforeSideWantsToRetreat() {
		return this.startingSize * getRetreatThreashhold();
	}
	
	public double getAttackValue() {
		double attackValue = 0;
		for( Troops someTroops : troops ) {
			attackValue = attackValue + someTroops.getAttackPower();
		}
		return attackValue;
	}
	
	public double getAverageToughness() {
		double toughness = 0;
		for( Troops someTroops : troops ) {
			toughness = toughness + someTroops.getToughnessPerTroop();
		}
		return toughness / troops.size();
	}
	
	public void sufferDamage( double damage ) {
		for( Troops someTroops : troops ) {		
			someTroops.setCount( someTroops.getCount() 
					- calculateDamageToApplyToEachTroopType( damage ) );
		}
	}

	/**
	 * Damage is applied evenly to all different troop types that make up the side.
	 * @param damage
	 * @return
	 */
	private int calculateDamageToApplyToEachTroopType(double damage) {
		return (int)damage / troops.size();
	}
	
	public int size() {
		int size = 0;
		for( Troops someTroops : troops ) {
			size = size + someTroops.getCount();
		}
		return size;
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

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}
