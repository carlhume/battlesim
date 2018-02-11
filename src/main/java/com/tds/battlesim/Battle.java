package com.tds.battlesim;

public class Battle {

	private Side aSide;
	private Side anotherSide;
	
	public Battle() {}
	
	public Battle( Side aCombatant, Side anotherCombatant ) {
		this.aSide = aCombatant;
		this.anotherSide = anotherCombatant;
	}
	
	public void simulate() {
		while( shouldContinue() ) {
			//b.shortRangeArcheryAttackValue * b.currentStrengthPct() / a.getDefense() * b.getAttackValueMultiplierForCurrentPhase();
			double aSideDamageCaused = Math.random() * this.aSide.getAttackValue() / this.anotherSide.getDefenseValue();
			double otherSideDamageCaused = Math.random() * this.anotherSide.getAttackValue() / this.aSide.getDefenseValue();
			
			this.aSide.sufferDamage( otherSideDamageCaused );
			this.anotherSide.sufferDamage( aSideDamageCaused );
			
			System.out.println( "After this round, " + aSide + " troops remaining:  " + aSide.size() );
			System.out.println( "After this round, " + anotherSide + " troops remaining:  " + anotherSide.size() );
		}
	}
	
	public boolean shouldContinue() {
		return this.aSide.isRetreating() == false &&
				this.anotherSide.isRetreating() == false;
	}
	
}
