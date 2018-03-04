package com.tds.battlesim;

import static org.junit.Assert.*;

import org.junit.Test;

public class RoundResultsTest {

	@Test
	public void testRoundResultsAreInitializedWithStartingTroopCounts() {
		Troops troops = new Troops();
		troops.setCount( 10 );
		Side side = new Side();
		side.addTroops( troops );
		
		Troops moreTroops = new Troops();
		moreTroops.setCount( 5 );
		Side anotherSide = new Side();
		anotherSide.addTroops( moreTroops );
		
		RoundResults results = new RoundResults( side, anotherSide );
		assertEquals( "Results should be initialized with starting troops from first side", 10, results.getSideTroopCountAtStartOfRound() );
		assertEquals( "Results should be initialized with starting troops from second side", 5, results.getAnotherSideTroopCountAtStartOfRound() );
	}

}
