package com.tds.battlesim;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class TroopTest {
	
	private Troops troops;
	
	@Before
	public void setUp() {
		troops = new Troops();
	}

	@Test
	public void testTroopAttackPowerIsDependentOnCount() {
		troops.setCount( 1 );
		troops.setDamageDealtPerTroop( 10 );
		assertEquals( "Attack power was calculated wrong", 10, troops.getAttackPower(), 0 );
	}
	
	@Test
	public void testTroopToughnessIsInitializedTo1() {
		assertEquals( "Troop toughness was not initalized", 1, troops.getToughnessPerTroop(), 0 );
	}
	
	@Test
	public void testTroopAttackPowerIsInitializedTo1() {
		assertEquals( "Troop damage was not initalized", 1, troops.getDamageDealtPerTroop(), 0 );
	}
	
	@Test
	public void testTroopHitpointsAreInitializedTo1() {
		assertEquals( "Troop hit points were not initalized", 1, troops.getHitPointsPerTroop(), 0 );
	}
	
	@Test
	public void testDamageThatDoesNotKillTroopsIsIgnored() {
		Troops troops = new Troops();
		troops.setCount( 10 );
		troops.setHitPointsPerTroop( 10 );		
		troops.sufferDamage( 5 );
		assertEquals( "No one should have died", 10, troops.getCount() );
	}
	
	@Test
	public void testTroopsWithHigherThan1HitPointAreStillKilled() {
		Troops troops = new Troops();
		troops.setCount( 10 );
		troops.setHitPointsPerTroop( 10 );
		troops.sufferDamage( 20 );
		assertEquals( "Two should have died", 8, troops.getCount() );
	}

}
