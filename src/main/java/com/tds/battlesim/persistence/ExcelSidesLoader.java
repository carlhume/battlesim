package com.tds.battlesim.persistence;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;

import com.tds.battlesim.Side;

public class ExcelSidesLoader {

	public Collection<Side> loadFromWorkbook( Workbook workbook ) {
		Collection<Side> sides = new ArrayList<Side>();
		Sheet sidesDefinedInExcel = findSheetDefiningSides( workbook );
		loadSidesFromSheet( sides, sidesDefinedInExcel );
		return sides;
	}

	private Sheet findSheetDefiningSides(Workbook workbook) {
		Sheet sheetDefiningSides = workbook.getSheet( "Sides" );
		if( sheetDefiningSides == null ) {
			sheetDefiningSides = new EmptySheet();
		}
		return sheetDefiningSides;
	}

	private void loadSidesFromSheet(Collection<Side> sides, Sheet sidesDefinedInExcel) {
		Iterator<Row> rowIterator = sidesDefinedInExcel.rowIterator();		

		// This method assumes that the worksheet has a properly formed header row
		// TODO:  Add a test and safety code to ensure we are not throwing away an intended side definition
		discardHeader( rowIterator );
		while( rowIterator.hasNext() ) {
			sides.add( loadSideFromRow( rowIterator.next() ) );
		}
	}

	private void discardHeader(Iterator<Row> rowIterator) {
		if( rowIterator.hasNext() ) {
			rowIterator.next();
		}
	}
	
	public Side loadSideFromRow( Row rowDefiningSide ) {
		Side side = new Side();
		side.setName( rowDefiningSide.getCell( 0 ).getStringCellValue() );
		return side;
	}
	
}
