package com.tds.battlesim;

import static org.junit.Assert.*;

import org.junit.Test;

public class TroopTest {

	@Test
	public void testTroopAttackPowerIsDependentOnCount() {
		Troops troops = new Troops();
		troops.setCount( 1 );
		troops.setDamageDealtPerTroop( 10 );
		assertEquals( "Attack power was calculated wrong", 10, troops.getAttackPower(), 0 );
	}
	
	@Test
	public void testTroopToughnessIsInitializedTo1() {
		Troops troops = new Troops();
		assertEquals( "Troop toughness was not initalized", 1, troops.getToughnessPerTroop(), 0 );
	}
	
	@Test
	public void testTroopAttackPowerIsInitializedTo1() {
		Troops troops = new Troops();
		assertEquals( "Troop attack power was not initalized", 1, troops.getDamageDealtPerTroop(), 0 );
	}
	
}
