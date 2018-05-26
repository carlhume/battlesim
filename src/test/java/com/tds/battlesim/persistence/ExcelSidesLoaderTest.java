package com.tds.battlesim.persistence;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.Collection;

import org.apache.poi.ss.usermodel.Workbook;
import org.junit.Test;

import com.tds.battlesim.Side;
import com.tds.battlesim.persistence.poistubs.WorkbookStub;

public class ExcelSidesLoaderTest {

	@Test
	public void testExcelSidesLoaderCanBeCreated() {
		assertNotNull( "Excel Sides Loader was not created safely", new ExcelSidesLoader() );
	}

	@Test
	public void testThatAnEmptyCollectionIsReturnedFromAnEmptyWorkbook() {
		ExcelSidesLoader loader = new ExcelSidesLoader();
		Workbook emptyWorkbook = new WorkbookStub();
		Collection<Side> sides = loader.loadFromWorkbook( emptyWorkbook );
		assertEquals( "There should not have been any sides loaded fom an empty workbook", 0, sides.size() );
	}
	
}