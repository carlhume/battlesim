package com.tds.battlesim;

public class BattleSim {

	public void simulate( Battle battle ) {
		battle.simulate();
	}
	
	public static void main( String [] args ) {
		BattleSim battleSim = new BattleSim();
		Side side = new Side();
		side.setName( "First Side" );
		Troops troopsForSide = new Troops();
		troopsForSide.setCount( 1000 );
		troopsForSide.setDamageDealtPerTroop( 10 );
		troopsForSide.setHitPointsPerTroop( 10 );
		side.addTroops( troopsForSide );
		
		Side anotherSide = new Side( "Second Side", 10000 );
		Battle battle = new Battle( side, anotherSide );
		battle.publishRoundResultsTo( new RoundResultsReporter() );
		battleSim.simulate( battle );
	}
		
	private static class RoundResultsReporter implements RoundResultsSubscriber {

		@Override
		public void receiveRoundResults(RoundResults results) {
			System.out.println( results );
		}
		
	}
}
