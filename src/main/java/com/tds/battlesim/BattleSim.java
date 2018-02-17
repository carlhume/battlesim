package com.tds.battlesim;

public class BattleSim {

	public void simulate( Battle battle ) {
		battle.simulate();
	}
	
	public static void main( String [] args ) {
		BattleSim battleSim = new BattleSim();
		Side side = new Side( "First Side", 100 );
		side.setRetreatThreashhold( 0.9 );
		Side anotherSide = new Side( "Second Side", 100 );
		Battle battle = new Battle( side, anotherSide );
		battleSim.simulate( battle );
	}
		
	
}
