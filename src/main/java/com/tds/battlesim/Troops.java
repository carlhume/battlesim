package com.tds.battlesim;

/**
 * The Troops class encapsulates all variability of different troop types.
 * Values are meant to be configured by the game designer, with no code required.
 * 
 * @author Carl
 *
 */
public class Troops {

	/**
	 * The name for this collection of troops - the more descriptive, the better
	 */
	private String name;
	
	/**
	 * How many troops are part of this collection?
	 */
	private int count;
	
	/**
	 * How much damage can each troop inflict in one round of battle?
	 */
	private double damageDealtPerTroop;
	
	/**
	 * This number reduces damage applied by opposing troops in battle.
	 * A 2 means you are twice as tough as the average troop, and so damage will be halved.
	 * (damage / toughness)
	 */
	private double toughnessPerTroop;
	
	/**
	 * How much damage can one troop take before it is eliminated?
	 */
	private double hitPointsPerTroop;
	
	public Troops() {
		setDamageDealtPerTroop( 1 );
		setToughnessPerTroop( 1 );
		setHitPointsPerTroop( 1 );
	}
	
	/**
	 * Attack power maps to the damage done by this troop type during one round of the battle	
	 * @return
	 */
	public double getAttackPower() {
		return getDamageDealtPerTroop() * getCount();
	}
	
	/**
	 * Damage is applied to one troop at a time, instead of applied evenly throughout.
	 * This means that if there are 100 troops with 2 hit points each, and the troops take 100 damage,
	 * then 50 will die, and 50 will be uninjured.
	 * 
	 * @param damage
	 */
	public void sufferDamage( double damage ) {
		int troopsKilled = (int)(damage / getHitPointsPerTroop());
		reduceTroopCountBy( troopsKilled );
	}
	
	private void reduceTroopCountBy( int number ) {
		setCount( getCount() - number );
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

	public double getDamageDealtPerTroop() {
		return damageDealtPerTroop;
	}

	public void setDamageDealtPerTroop(double attackPowerPerTroop) {
		this.damageDealtPerTroop = attackPowerPerTroop;
	}

	public double getToughnessPerTroop() {
		return toughnessPerTroop;
	}

	public void setToughnessPerTroop(double toughness) {
		this.toughnessPerTroop = toughness;
	}

	public double getHitPointsPerTroop() {
		return hitPointsPerTroop;
	}

	public void setHitPointsPerTroop(double hitPointsPerTroop) {
		this.hitPointsPerTroop = hitPointsPerTroop;
	}

}
