package com.tds.battlesim;

public class Troops {

	private String name;
	private int count;
	private double damageDealtPerTroop;
	private double toughnessPerTroop;
	private double hitPointsPerTroop;
	
	public Troops() {
		setDamageDealtPerTroop( 1 );
		setToughnessPerTroop( 1 );
		setHitPointsPerTroop( 1 );
	}
	
	/**
	 * Attack power maps to the damage done by this troop type	
	 * @return
	 */
	public double getAttackPower() {
		return getDamageDealtPerTroop() * getCount();
	}
	
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
