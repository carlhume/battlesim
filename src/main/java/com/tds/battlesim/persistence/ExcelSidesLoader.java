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
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.tds.battlesim.Side;

public class ExcelSidesLoader {

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
