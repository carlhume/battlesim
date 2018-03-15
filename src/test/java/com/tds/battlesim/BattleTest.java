package com.tds.battlesim;

import static org.junit.Assert.*;

import org.junit.Test;

public class BattleTest {

	@Test
	public void testBattleCanBeCreated() {
		assertNotNull( new Battle() );
	}

	@Test
	public void testBattleShouldContinueIfSidesAreNotRetreating() {
		Battle battle = new Battle( new Side( 100 ), new Side( 100 ) );
		assertTrue( battle.shouldContinue() );
	}
	
	@Test
	public void testBattleShouldNotContinueWhenASideIsRetreating() {
		Battle battle = new Battle( new Side( 100 ), new RetreatingSide() );
		assertFalse( battle.shouldContinue() );
	}
	
	@Test
	public void testBattleGeneratesMultipleRoundsOfResults() {
		Battle battle = new Battle( new Side( 100 ), new Side( 100 ) );
		RoundResultsTestSubscriber listener = new RoundResultsTestSubscriber();
		battle.publishRoundResultsTo( listener );
		battle.simulate();
		assertTrue( listener.resultsReceivedCount > 1 );		
	}

	private class RoundResultsTestSubscriber implements RoundResultsSubscriber {

		private int resultsReceivedCount = 0;
		
		@Override
		public void receiveRoundResults(RoundResults results) {
			resultsReceivedCount++;
		}
		
	}
	
	private class RetreatingSide extends Side {
		public boolean isRetreating() {
			return true;
		}		
	}
	
}
