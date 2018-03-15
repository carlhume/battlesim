package com.tds.battlesim;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class BattleTest {

	private RoundResultsTestSubscriber subscriber;
	
	@Before
	public void setUp() {
		subscriber = new RoundResultsTestSubscriber();
	}
	
	@Test
	public void testBattleCanBeCreated() {
		assertNotNull( "Battle was not created safely", new Battle() );
	}

	@Test
	public void testBattleShouldContinueIfSidesAreNotRetreating() {
		Battle battle = new Battle( new Side( 100 ), new Side( 100 ) );
		assertTrue( "The battle should have continued, but it did not", battle.shouldContinue() );
	}
	
	@Test
	public void testBattleShouldNotContinueWhenASideIsRetreating() {
		Battle battle = new Battle( new Side( 100 ), new RetreatingSide() );
		assertFalse( "The battle should have ended, but it did not", battle.shouldContinue() );
	}
	
	@Test
	public void testBattleGeneratesMultipleRoundsOfResults() {
		Battle battle = new Battle( new Side( 100 ), new Side( 100 ) );
		battle.publishRoundResultsTo( subscriber );
		battle.simulate();
		assertTrue( "The battle did not publish multiple rounds as expected", subscriber.resultsReceivedCount > 1 );		
	}

	@Test
	public void testBattleReportsWhenASideDecidesToRetreat() {
		Side retreatingSide = new RetreatingSide();
		Battle battle = new Battle( new Side( 100 ), retreatingSide );
		battle.publishRoundResultsTo( subscriber );
		battle.simulate();
		assertEquals( "The battle did not report the retreating side as we expected", retreatingSide, subscriber.retreatingSide );		
	}

	private class RoundResultsTestSubscriber implements RoundResultsSubscriber {

		private int resultsReceivedCount = 0;
		private Side retreatingSide;
		
		@Override
		public void receiveRoundResults(RoundResults results) {
			resultsReceivedCount++;
		}

		@Override
		public void sideIsRetreating(Side side) {
			this.retreatingSide = side;
		}
		
	}
	
	private class RetreatingSide extends Side {
		public boolean isRetreating() {
			return true;
		}		
	}
	
}
