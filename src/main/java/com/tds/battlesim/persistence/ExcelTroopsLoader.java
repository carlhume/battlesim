package com.tds.battlesim.persistence;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedList;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;

import com.tds.battlesim.Side;
import com.tds.battlesim.Troops;

public class ExcelTroopsLoader {

	private static final String EXCEL_SHEET_NAME_TROOPS = "Troops";

	public void loadTroopsForSidesFromWorkbook( Collection<Side> sides, Workbook workbook ) {
		Sheet troopsDefinedInExcel = findSheetDefiningTroops( workbook );
		for( Side side : sides ) {
			loadTroopsForSideFromSheet( side, troopsDefinedInExcel );
		}
	}

	private void loadTroopsForSideFromSheet( Side side, Sheet troopsDefinedInExcel) {
		Collection<Troops> troops = new ArrayList<Troops>();
		Collection<Row> rows = findRowsWithTroopsForSideFromSheet( side, troopsDefinedInExcel );
		loadTroopsFromRows( troops, rows );	
		side.addTroops( troops );
	}

	private Collection<Row> findRowsWithTroopsForSideFromSheet( Side side, Sheet troopsDefinedInExcel ) {		
		Collection<Row> rowsWithTroops = new LinkedList<Row>();
		Iterator<Row> rowIterator = troopsDefinedInExcel.rowIterator();

		// This method assumes that the worksheet has a properly formed header row
		// TODO:  Add a test and safety code to ensure we are not throwing away an intended side definition
		discardHeader( rowIterator );
		while( rowIterator.hasNext() ) {
			Row row = rowIterator.next();
			if( rowIsForSide( row, side ) ) {
				rowsWithTroops.add( row );
			}
		}
		return rowsWithTroops;
	}
	
	private boolean rowIsForSide( Row row, Side side ) {
		return row.getCell( 1 ).getStringCellValue().equals( side.getName() );
	}
	
	private void loadTroopsFromRows( Collection<Troops> troops, Collection<Row> rowsDefiningTroops ) {
		for( Row row : rowsDefiningTroops ) {
			troops.add( loadTroopsFromRow( row ) );
		}
	}
	
	private Troops loadTroopsFromRow( Row rowDefiningTroops ) {
		
		Troops troops = new Troops();
		
		// TODO:  Replace Magic Number with Constant
		troops.setName( rowDefiningTroops.getCell( 0 ).getStringCellValue() );
		troops.setCount( (int)rowDefiningTroops.getCell( 2 ).getNumericCellValue() );
		troops.setDamageDealtPerTroop( (int)rowDefiningTroops.getCell( 3 ).getNumericCellValue() );
		
		return troops;
	}

	private void discardHeader(Iterator<Row> rowIterator) {
		if( rowIterator.hasNext() ) {
			rowIterator.next();
		}
	}
	
	private Sheet findSheetDefiningTroops( Workbook workbook ) {
		Sheet sheetDefiningSides = workbook.getSheet( EXCEL_SHEET_NAME_TROOPS );
		if( sheetDefiningSides == null ) {
			sheetDefiningSides = new EmptySheet();
		}
		return sheetDefiningSides;
	}

}
