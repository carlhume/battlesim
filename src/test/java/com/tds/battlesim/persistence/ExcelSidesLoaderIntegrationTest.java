package com.tds.battlesim.persistence;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.Collection;
import java.util.Iterator;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.tds.battlesim.Side;

public class ExcelSidesLoaderIntegrationTest {

	private Workbook sidesWorkbook;
	private ExcelSidesLoader loader;
	private Side firstSide;
	private Side secondSide;
	
	@Before
	public void setUp() throws URISyntaxException, EncryptedDocumentException, InvalidFormatException, IOException {
		loader = new ExcelSidesLoader();
		URL url = ExcelSidesLoaderIntegrationTest.class.getResource( "/sides.xlsx" );
		assertNotNull( "Could not find the test file.", url );
		File sidesFile = new File( url.toURI() );
		assertTrue( "The test file does not exist!", sidesFile.exists() );
		sidesWorkbook = WorkbookFactory.create( sidesFile );	
		Collection<Side> sides = loader.loadFromWorkbook( sidesWorkbook );
		Iterator<Side> sideIterator = sides.iterator();
		firstSide = sideIterator.next();
		secondSide = sideIterator.next();
	}
	
	@After
	public void tearDown() throws IOException {
		sidesWorkbook.close();
	}
	
	@Test
	public void testThatTheNameOfTheFirstSideIsLoaded()  {
		assertEquals( "The name of the side should have been loaded", "First Test Side", firstSide.getName() );
	}
	
	@Test
	public void testThatTheNameOfTheSecondSideIsLoaded()  {
		assertEquals( "The name of the side should have been loaded", "Second Test Side", secondSide.getName() );
	}
	
}
