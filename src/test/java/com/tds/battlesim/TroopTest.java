package com.tds.battlesim;

import static org.junit.Assert.*;

import org.junit.Test;

public class TroopTest {

	@Test
	public void testTroopAttackPowerIsDependentOnCount() {
		Troops troops = new Troops();
		troops.setCount( 1 );
		troops.setAttackPowerPerTroop( 10 );
		assertEquals( "Attack power was calculated wrong", 10, troops.getAttackPower(), 0 );
	}
	
}
