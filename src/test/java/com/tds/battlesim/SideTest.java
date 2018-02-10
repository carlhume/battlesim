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
	public void testEachPointOfDamageDirectlyRemovesATroop() {
		Side side = new Side( 50 );
		side.sufferDamage( 5 );
		assertEquals( 45, side.size() );
	}
	
}
