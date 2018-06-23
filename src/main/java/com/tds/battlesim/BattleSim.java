package com.tds.battlesim;

import java.io.File;
import java.io.IOException;
import java.util.Collection;
import java.util.Iterator;

import com.tds.battlesim.persistence.ExcelSidesLoader;

public class BattleSim {

	public void simulate( Battle battle ) {
		battle.simulate();
	}
	
	public static void main( String [] args ) throws IOException {
		ExcelSidesLoader excelSidesLoader = new ExcelSidesLoader();
		Collection<Side> sides = excelSidesLoader.loadFromFile( new File( args[0] ) );
		Iterator<Side> sideIterator = sides.iterator();
		
		BattleSim battleSim = new BattleSim();
		Side side = sideIterator.next();
		Side anotherSide = sideIterator.next(); 
		
		Battle battle = new Battle( side, anotherSide );
		battle.publishRoundResultsTo( new RoundResultsReporter() );
		battleSim.simulate( battle );
	}
		
	private static class RoundResultsReporter implements BattleResultsSubscriber {

		@Override
		public void receiveRoundResults(RoundResults results) {
			System.out.println( results );
		}

		@Override
		public void sideIsRetreating(Side side) {
			System.out.println( "After this round, " + side + " is retreating from battle" );
		}
		
	}
}
