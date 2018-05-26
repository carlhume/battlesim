package com.tds.battlesim;

import java.io.File;
import java.io.IOException;
import java.util.Iterator;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

public class BattleSim {

	public void simulate( Battle battle ) {
		battle.simulate();
	}
	
	public static void main( String [] args ) throws EncryptedDocumentException, InvalidFormatException, IOException {
		Workbook workbook = WorkbookFactory.create(new File( args[0] ) );
				
		Sheet sides = workbook.getSheet( "Sides" );
		Iterator<Row> rowIterator = sides.rowIterator();
        
		BattleSim battleSim = new BattleSim();
		Side side = new Side();
        Row row = rowIterator.next();
        row = rowIterator.next();
		side.setName( row.getCell( 0 ).getStringCellValue() );		
		Troops troopsForSide = new Troops();
		troopsForSide.setCount( 1000 );
		troopsForSide.setDamageDealtPerTroop( 10 );
		troopsForSide.setHitPointsPerTroop( 10 );
		side.addTroops( troopsForSide );
		
        row = rowIterator.next();
		Side anotherSide = new Side( row.getCell( 0 ).getStringCellValue(), 10000 );
		Battle battle = new Battle( side, anotherSide );
		battle.publishRoundResultsTo( new RoundResultsReporter() );
		battleSim.simulate( battle );
		
		workbook.close();
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
