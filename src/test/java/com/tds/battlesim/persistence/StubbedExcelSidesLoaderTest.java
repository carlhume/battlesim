package com.tds.battlesim.persistence;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.ArrayList;
import java.util.Collection;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.junit.Before;
import org.junit.Test;

import com.tds.battlesim.Side;
import com.tds.battlesim.persistence.poistubs.CellStub;
import com.tds.battlesim.persistence.poistubs.RowStub;
import com.tds.battlesim.persistence.poistubs.SheetStub;
import com.tds.battlesim.persistence.poistubs.WorkbookStub;

public class StubbedExcelSidesLoaderTest {
	
	private ExcelSidesLoader loader;
	
	@Before
	public void setUp() {
		loader = new ExcelSidesLoader();		
	}

	@Test
	public void testExcelSidesLoaderCanBeCreated() {
		assertNotNull( "Excel Sides Loader was not created safely", new ExcelSidesLoader() );
	}

	@Test
	public void testThatAnEmptyCollectionIsReturnedFromAnEmptyWorkbook() {
		Workbook emptyWorkbook = new WorkbookStub();
		Collection<Side> sides = loader.loadFromWorkbook( emptyWorkbook );
		assertEquals( "There should not have been any sides loaded fom an empty workbook", 0, sides.size() );
	}
	
	@Test
	public void testSidesAreLoadedFromSheetsWithTheRightName() {
		SheetStub sheetDefiningSide = new SheetStub();
		sheetDefiningSide.addRow( createHeaderRow() );
		sheetDefiningSide.addRow( createRowDefiningSide() );
		WorkbookStub workbookWithSheetWithRightName = new WorkbookStub();
		workbookWithSheetWithRightName.addNamedSheet( ExcelSidesLoader.EXCEL_SHEET_NAME_SIDES, sheetDefiningSide );
		Collection<Side> sides = loader.loadFromWorkbook( workbookWithSheetWithRightName );
		assertEquals( "A Side should have been loaded from the workbook", 1, sides.size() );
	}

	@Test
	public void testSidesAreNotLoadedFromSheetsWithTheWrongName() {
		SheetStub sheetDefiningSideWithWrongName = new SheetStub();
		sheetDefiningSideWithWrongName.addRow( createRowDefiningSide() );
		WorkbookStub workbookWithSheetWithWrongName = new WorkbookStub();
		workbookWithSheetWithWrongName.addNamedSheet( "Wrong Name", sheetDefiningSideWithWrongName );
		Collection<Side> sides = loader.loadFromWorkbook( workbookWithSheetWithWrongName );
		assertEquals( "There should not have been any sides loaded fom a workbook that does not have a Sides worksheet", 0, sides.size() );
	}

	@Test
	public void testNoSidesAreLoadedFromAnEmptySheet() {
		Sheet emptySheet = new SheetStub();
		Collection<Side> sides = new ArrayList<Side>();
		loader.loadSidesFromSheet( sides, emptySheet );
		assertEquals( "Sides were loaded from an empty sheet", 0, sides.size() );
	}
	
	@Test
	public void testLoaderDoesNotThrowAwaySideIfThereIsNoHeader() {
		SheetStub sheetDefiningSide = new SheetStub();
		sheetDefiningSide.addRow( createRowDefiningSide() );
		WorkbookStub workbookWithSheetWithRightName = new WorkbookStub();
		workbookWithSheetWithRightName.addNamedSheet( ExcelSidesLoader.EXCEL_SHEET_NAME_SIDES, sheetDefiningSide );
		Collection<Side> sides = loader.loadFromWorkbook( workbookWithSheetWithRightName );
		assertEquals( "A Side should have been loaded from the workbook", 1, sides.size() );
	}
	
	private Row createHeaderRow() {
		RowStub headerRow = new RowStub();
		CellStub nameCell = new CellStub();
		nameCell.setCellValue( ExcelSidesLoader.COLUMN_NAME_NAME );
		headerRow.setCellAt( nameCell, 0 );
		return headerRow;
	}
	
	private Row createRowDefiningSide() {
		RowStub rowDefiningSide = new RowStub();
		CellStub nameCell = new CellStub();
		nameCell.setCellValue( "Test Side Name" );
		rowDefiningSide.setCellAt( nameCell, 0 );
		return rowDefiningSide;
	}
}