package com.tds.battlesim.persistence;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.Collection;
import java.util.Iterator;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.junit.Before;
import org.junit.Test;

import com.tds.battlesim.Side;

public class ExcelSidesLoaderIntegrationTest {

	private ExcelSidesLoader loader;
	private Side firstSide;
	private Side secondSide;
	
	@Before
	public void setUp() throws URISyntaxException, EncryptedDocumentException, InvalidFormatException, IOException {
		loader = new ExcelSidesLoader();
		URL url = ExcelSidesLoaderIntegrationTest.class.getResource( "/sides.xlsx" );
		assertNotNull( "Could not find the test file.", url );
		Collection<Side> sides = loader.loadFromUrl( url );
		Iterator<Side> sideIterator = sides.iterator();
		firstSide = sideIterator.next();
		secondSide = sideIterator.next();
	}
	
	@Test
	public void testThatTheNameOfTheFirstSideIsLoaded()  {
		assertEquals( "The name of the side should have been loaded", "First Test Side", firstSide.getName() );
	}
	
	@Test
	public void testThatTheNameOfTheSecondSideIsLoaded()  {
		assertEquals( "The name of the side should have been loaded", "Second Test Side", secondSide.getName() );
	}
	
	@Test
	public void testThatTroopsWereLoadedForFirstSide() {
		assertTrue( "No troops were loaded for the first side", firstSide.size() > 0 );
	}

	@Test
	public void testThatTroopsWereLoadedForSecondSide() {
		assertTrue( "No troops were loaded for the second side", secondSide.size() > 0 );
	}
	
	@Test
	public void testThatDamageDealtPerTroopWasLoadedForFirstSide() {
		assertEquals( "Troop Damage was not loaded for first side", 10000, firstSide.getAttackValue(), Double.MIN_VALUE );
	}

	@Test
	public void testThatDamageDealtPerTroopWasLoadedForSecondSide() {
		assertEquals( "Troop Damage was not loaded for second side", 10000, secondSide.getAttackValue(), Double.MIN_VALUE );
	}
}
