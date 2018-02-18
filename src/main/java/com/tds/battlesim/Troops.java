package com.tds.battlesim;

public class Troops {

	private String name;
	private int count;
	private double attackPowerPerTroop;
	
	public Troops() {
		setAttackPowerPerTroop( 1 );
	}
	
	public double getAttackPower() {
		return getAttackPowerPerTroop() * getCount();
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

	public double getAttackPowerPerTroop() {
		return attackPowerPerTroop;
	}

	public void setAttackPowerPerTroop(double attackPowerPerTroop) {
		this.attackPowerPerTroop = attackPowerPerTroop;
	}

}
