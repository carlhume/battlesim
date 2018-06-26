package com.tds.battlesim.persistence;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedList;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;

import com.tds.battlesim.Side;
import com.tds.battlesim.Troops;

public class ExcelTroopsLoader {

	public static final String EXCEL_SHEET_NAME_TROOPS = "Troops";
	public static final String COLUMN_NAME_NAME = "Name";
	private static final int COLUMN_TROOPS_NAME = 0;

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
		Iterator<Row> rowIterator = findStartOfTroopsInSheet( troopsDefinedInExcel );
		while( rowIterator.hasNext() ) {
			Row row = rowIterator.next();
			if( rowIsForSide( row, side ) ) {
				rowsWithTroops.add( row );
			}
		}
		return rowsWithTroops;
	}
	
	// TODO: Remove duplication with ExcelSidesLoader
	private Iterator<Row> findStartOfTroopsInSheet( Sheet troopsDefinedInExcel ) {		
		Iterator<Row> rowIterator = troopsDefinedInExcel.rowIterator();		
		Row possibleHeaderRow = troopsDefinedInExcel.getRow( troopsDefinedInExcel.getFirstRowNum() );
		if( isHeader( possibleHeaderRow ) ) {
			discardHeader( rowIterator );
		}
		return rowIterator;
	}
	
	private boolean isHeader( Row possibleHeaderRow ) {
		boolean isHeader = false;
		if( possibleHeaderRow != null ) {
			Cell possibleHeaderCell = possibleHeaderRow.getCell( COLUMN_TROOPS_NAME );
			if( possibleHeaderCell != null ) {
				String contents = possibleHeaderCell.getStringCellValue();
				isHeader = contents.equals( COLUMN_NAME_NAME );
			}
		}
		return isHeader;
	}

	private void discardHeader(Iterator<Row> rowIterator) {
		if( rowIterator.hasNext() ) {
			rowIterator.next();
		}
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
		troops.setHitPointsPerTroop( (int)rowDefiningTroops.getCell( 4 ).getNumericCellValue() );
		
		return troops;
	}

	private Sheet findSheetDefiningTroops( Workbook workbook ) {
		Sheet sheetDefiningSides = workbook.getSheet( EXCEL_SHEET_NAME_TROOPS );
		if( sheetDefiningSides == null ) {
			sheetDefiningSides = new EmptySheet();
		}
		return sheetDefiningSides;
	}

}
