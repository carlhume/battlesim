package com.tds.battlesim.persistence;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.ArrayList;
import java.util.Collection;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Workbook;
import org.junit.Before;
import org.junit.Test;

import com.tds.battlesim.Side;
import com.tds.battlesim.persistence.poistubs.CellStub;
import com.tds.battlesim.persistence.poistubs.RowStub;
import com.tds.battlesim.persistence.poistubs.SheetStub;
import com.tds.battlesim.persistence.poistubs.WorkbookStub;

public class StubbedExcelTroopsLoaderTest {

	private static final String TEST_SIDE_NAME = "Test Side Name";
	private ExcelTroopsLoader loader;
	
	@Before
	public void setUp() {
		loader = new ExcelTroopsLoader();
	}
	
	@Test
	public void testExcelSidesLoaderCanBeCreated() {
		assertNotNull( "Excel Troops Loader was not created safely", new ExcelTroopsLoader() );
	}

	@Test
	public void testThatNoTroopsAreLoadedFromAnEmptyWorkbook() {
		Collection<Side> sides = new ArrayList<Side>();
		Side side = new Side();
		side.setName( TEST_SIDE_NAME );
		sides.add( side );
		Workbook emptyWorkbook = new WorkbookStub();
		loader.loadTroopsForSidesFromWorkbook( sides, emptyWorkbook );
		assertEquals( "There should not have been any troops loaded fom an empty workbook", 0, side.size() );
	}
	
	@Test
	public void testThatNoTroopsAreLoadedWhenSidesAreNotSpecified() {
		Collection<Side> noSides = new ArrayList<Side>();
		Workbook workbookWithTroops = new WorkbookStub();
		SheetStub troopsSheet = new SheetStub();
		troopsSheet.addRow( createRowDefiningTroops() );
		loader.loadTroopsForSidesFromWorkbook( noSides, workbookWithTroops );
		// This is not the best test ... it shows that a side was not created when none are passed in,
		// but does not show that troops were not loaded ... 
		assertEquals( "There should not have been any troops loaded when no sides are defined", 0, noSides.size()  );
	}		
	
	@Test
	public void testTroopsAreLoadedFromWorkbookWithTroops() {
		Collection<Side> sides = new ArrayList<Side>();
		Side side = new Side();
		side.setName( TEST_SIDE_NAME );
		sides.add( side );
		WorkbookStub workbookWithTroops = new WorkbookStub();
		SheetStub troopsSheet = new SheetStub();		
		troopsSheet.addRow( createHeaderRow() );
		troopsSheet.addRow( createRowDefiningTroops() );
		workbookWithTroops.addNamedSheet( ExcelTroopsLoader.EXCEL_SHEET_NAME_TROOPS, troopsSheet );
		loader.loadTroopsForSidesFromWorkbook( sides, workbookWithTroops );
		assertEquals( "Troops should have been loaded", 100, side.size() );
	}
	
	@Test
	public void testTroopsAreNotLostWhenThereIsNoHeaderRow() {
		Collection<Side> sides = new ArrayList<Side>();
		Side side = new Side();
		side.setName( TEST_SIDE_NAME );
		sides.add( side );
		WorkbookStub workbookWithTroops = new WorkbookStub();
		SheetStub troopsSheet = new SheetStub();		
		troopsSheet.addRow( createRowDefiningTroops() );
		workbookWithTroops.addNamedSheet( ExcelTroopsLoader.EXCEL_SHEET_NAME_TROOPS, troopsSheet );
		loader.loadTroopsForSidesFromWorkbook( sides, workbookWithTroops );
		assertEquals( "Troops should have been loaded", 100, side.size() );
	}
	
	private Row createHeaderRow() {
		RowStub headerRow = new RowStub();

		CellStub nameCell = new CellStub();
		nameCell.setCellValue( ExcelSidesLoader.COLUMN_NAME_NAME );
		headerRow.setCellAt( nameCell, 0 );
				
		CellStub sideCell = new CellStub();
		sideCell.setCellValue( "Side" );
		headerRow.setCellAt( sideCell, 1 );
		
		CellStub countCell = new CellStub();
		countCell.setCellValue( "Troop Count" );
		headerRow.setCellAt( countCell, 2 );
		
		CellStub damageCell = new CellStub();
		damageCell.setCellValue( "Damage Dealt Per Troop" );
		headerRow.setCellAt( damageCell, 3 );
		
		CellStub healthCell = new CellStub();
		healthCell.setCellValue( "Hit Points Per Troop" );
		headerRow.setCellAt( healthCell, 4 );
		
		return headerRow;
	}
	
	private Row createRowDefiningTroops() {
		RowStub rowDefiningTroops = new RowStub();

		CellStub nameCell = new CellStub();
		nameCell.setCellValue( "Test Troops Name" );
		rowDefiningTroops.setCellAt( nameCell, 0 );
		
		CellStub sideCell = new CellStub();
		sideCell.setCellValue( TEST_SIDE_NAME );
		rowDefiningTroops.setCellAt( sideCell, 1 );
		
		CellStub countCell = new CellStub();
		countCell.setCellValue( "100" );
		rowDefiningTroops.setCellAt( countCell, 2 );
		
		CellStub attackCell = new CellStub();
		attackCell.setCellValue( "10" );
		rowDefiningTroops.setCellAt( attackCell, 3 );
		
		CellStub healthCell = new CellStub();
		healthCell.setCellValue( "1" );
		rowDefiningTroops.setCellAt( healthCell, 4 );
		
		return rowDefiningTroops;
	}

		
}
