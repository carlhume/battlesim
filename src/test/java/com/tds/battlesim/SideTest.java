package com.tds.battlesim;

import static org.junit.Assert.*;

import org.junit.Test;

public class SideTest {

	@Test
	public void testSizeIsTheSameAsTroopCount() {
		Side side = new Side( 100 );
		assertEquals( "The size of a side should equal the number of troops", 100, side.size() );
	}

	@Test
	public void testSideIsConsideredRetreatingWhenThereAreNoTroopsLeft() {
		Side side = new Side( 0 );
		assertTrue( side.isRetreating() );
	}
	
	@Test
	public void testSideRetreatsWhenThereAreFewerTroopsThanTheRetreatThreashhold() {
		Side side = new Side( 1000 );
		side.setRetreatThreashhold( 0.5 );
		assertFalse( side.isRetreating() );
		side.sufferDamage( side.size() * side.getRetreatThreashhold() + 1 );
		assertTrue( side.isRetreating() );
	}
	
	@Test
	public void testEachPointOfDamageDirectlyRemovesATroop() {
		Side side = new Side( 50 );
		side.sufferDamage( 5 );
		assertEquals( 45, side.size() );
	}
	
	@Test
	public void testDamageRemovesTroopsAcrossAllTypes() {
		Troops troops = new Troops();
		troops.setCount( 50 );
		
		Troops otherTroops = new Troops();
		otherTroops.setCount( 50 );
		
		Side side = new Side();
		side.addTroops( troops );
		side.addTroops( otherTroops );
		side.sufferDamage( 10 );
		
		assertEquals( 45, troops.getCount() );
		assertEquals( 45, otherTroops.getCount() );
	}
	
	@Test
	public void testUsingTwoDifferentTypesOfTroopsAddsSizeCorrectly() {		
		Troops troops = new Troops();
		troops.setCount( 50 );
		
		Troops otherTroops = new Troops();
		otherTroops.setCount( 50 );
		
		Side side = new Side();
		side.addTroops( troops );
		side.addTroops( otherTroops );
		assertEquals( "Size is not calculated correctly with multiple troop types", 100, side.size() );
	}
	
	@Test
	public void testUsingTwoDifferentTypesOfTroopsDeterminesAttackValueUsingBoth() {		
		Troops troops = new Troops();
		troops.setCount( 50 );
		troops.setDamageDealtPerTroop( 1 );
		
		Troops otherTroops = new Troops();
		otherTroops.setCount( 50 );
		otherTroops.setDamageDealtPerTroop( 2 );
		
		Side side = new Side();
		side.addTroops( troops );
		side.addTroops( otherTroops );
		assertEquals( "Attack power is not calculated correctly with multiple troop types", 150, side.getAttackValue(), 0 );
	}
	
	@Test
	public void testToughnessIsAveragedFromDifferentTroopTypes() {
		Troops troops = new Troops();
		troops.setCount( 50 );
		troops.setDamageDealtPerTroop( 1 );
		troops.setToughnessPerTroop( 2 );
		
		Troops otherTroops = new Troops();
		otherTroops.setCount( 50 );
		otherTroops.setDamageDealtPerTroop( 2 );
		otherTroops.setToughnessPerTroop( 4 );
		
		Side side = new Side();
		side.addTroops( troops );
		side.addTroops( otherTroops );
		assertEquals( "Toughness should be an average", 3, side.getAverageToughness(), 0 );
	}
	
}
