package com.tds.battlesim;

public class BattleSim {

	public void simulate( Battle battle ) {
		battle.simulate();
	}
	
	public static void main( String [] args ) {
		BattleSim battleSim = new BattleSim();
		Side side = Side.SMALL;		
		Side anotherSide = Side.LARGE;
		Battle battle = new Battle( side, anotherSide );
		battleSim.simulate( battle );
	}
	
	
	
}
