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
		troopsForSide.setCount( 100 );
		troopsForSide.setDamageDealtPerTroop( 1 );
		side.addTroops( troopsForSide );
		
		Side anotherSide = new Side( "Second Side", 100 );
		Battle battle = new Battle( side, anotherSide );
		battleSim.simulate( battle );
	}
		
	
}
