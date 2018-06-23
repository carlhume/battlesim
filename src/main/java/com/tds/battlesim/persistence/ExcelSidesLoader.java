package com.tds.battlesim.persistence;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.tds.battlesim.Side;

public class ExcelSidesLoader {

	public static final String EXCEL_SHEET_NAME_SIDES = "Sides";	
	public static final String COLUMN_NAME_NAME = "Name";
	private static final int COLUMN_SIDE_NAME = 0;
	private static final Logger logger = LoggerFactory.getLogger( ExcelSidesLoader.class );
	
	public Collection<Side> loadFromUrl( URL url ) {
		Collection<Side> sides = new ArrayList<Side>();
		try {
			File sidesFile = new File( url.toURI() );
			sides = loadFromFile( sidesFile );
		} catch (URISyntaxException e) {
			logger.error( "Error loading sides from Excel", e );
		}
		return sides;
	}
	
	@SuppressWarnings("resource")
	public Collection<Side> loadFromFile( File file ) {
		Collection<Side> sides = new ArrayList<Side>();
		Workbook workbook = new EmptyWorkbook();
		try {
			workbook = WorkbookFactory.create( file );
			sides = loadFromWorkbook( workbook );
		} catch (EncryptedDocumentException | InvalidFormatException
				| IOException e) {
			logger.error( "Error loading sides from Excel", e );
		} finally {
			try {
				workbook.close();
			} catch (IOException e) {
				logger.error( "Error closing Excel workbook", e );
			}
		}
		return sides;
	}

	public Collection<Side> loadFromWorkbook( Workbook workbook ) {
		Collection<Side> sides = new ArrayList<Side>();
		Sheet sidesDefinedInExcel = findSheetDefiningSides( workbook );
		loadSidesFromSheet( sides, sidesDefinedInExcel );
		loadTroopsForSideFromWorkbook( sides, workbook );
		return sides;
	}

	private void loadTroopsForSideFromWorkbook( Collection<Side> sides, Workbook workbook ) {
		ExcelTroopsLoader troopLoader = new ExcelTroopsLoader();
		troopLoader.loadTroopsForSidesFromWorkbook( sides, workbook );		
	}
	
	private Sheet findSheetDefiningSides(Workbook workbook) {
		Sheet sheetDefiningSides = workbook.getSheet( EXCEL_SHEET_NAME_SIDES );
		if( sheetDefiningSides == null ) {
			sheetDefiningSides = new EmptySheet();
		}
		return sheetDefiningSides;
	}

	public void loadSidesFromSheet( Collection<Side> sides, Sheet sidesDefinedInExcel ) {
		
		Iterator<Row> rowIterator = findStartOfSidesInSheet( sidesDefinedInExcel );
		while( rowIterator.hasNext() ) {
			sides.add( loadSideFromRow( rowIterator.next() ) );
		}
	}

	private Iterator<Row> findStartOfSidesInSheet( Sheet sidesDefinedInExcel ) {		
		Iterator<Row> rowIterator = sidesDefinedInExcel.rowIterator();		
		Row possibleHeaderRow = sidesDefinedInExcel.getRow( sidesDefinedInExcel.getFirstRowNum() );
		if( isHeader( possibleHeaderRow ) ) {
			discardHeader( rowIterator );
		}
		return rowIterator;
	}
	
	private boolean isHeader( Row possibleHeaderRow ) {
		boolean isHeader = false;
		if( possibleHeaderRow != null ) {
			Cell possibleHeaderCell = possibleHeaderRow.getCell( COLUMN_SIDE_NAME );
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
	
	public Side loadSideFromRow( Row rowDefiningSide ) {
		Side side = new Side();
		side.setName( rowDefiningSide.getCell( COLUMN_SIDE_NAME ).getStringCellValue() );
		return side;
	}
	
}
