package com.tds.battlesim;

import static org.junit.Assert.*;

import org.junit.Test;

public class RoundResultsTest {

	@Test
	public void testRoundResultsAreInitializedWithStartingTroopCounts() {
		Side side = new Side( "Side", 10 );
		Side anotherSide = new Side( "Another Side", 5 );		
		RoundResults results = new RoundResults( side, anotherSide );
		assertEquals( "Results should be initialized with starting troops from first side", 10, results.getSideTroopCountAtStartOfRound() );
		assertEquals( "Results should be initialized with starting troops from second side", 5, results.getAnotherSideTroopCountAtStartOfRound() );
	}

}
