package com.tds.battlesim;

import java.util.ArrayList;
import java.util.Collection;

public class Battle implements RoundResultsPublisher {

	private Side aSide;
	private Side anotherSide;
	private Collection<RoundResultsSubscriber> roundResutlsSubscribers = new ArrayList<RoundResultsSubscriber>();
	
	public Battle() {}
	
	public Battle( Side aCombatant, Side anotherCombatant ) {
		this.aSide = aCombatant;
		this.anotherSide = anotherCombatant;
	}
		
	public void simulate() {
		while( shouldContinue() ) {
			RoundResults roundResults = simulateRound();
			publishRoundResults( roundResults );
		}
		
		disaplySideThatIsRetreating();
	}

	private RoundResults simulateRound() {
		RoundResults results = new RoundResults( aSide, anotherSide );

		double aSideDamageCaused = Math.random() * this.aSide.getAttackValue() / this.anotherSide.getAverageToughness();
		double anotherSideDamageCaused = Math.random() * this.anotherSide.getAttackValue() / this.aSide.getAverageToughness();
		
		this.aSide.sufferDamage( anotherSideDamageCaused );
		this.anotherSide.sufferDamage( aSideDamageCaused );
		
		results.setSideDamageCaused( aSideDamageCaused );
		results.setAnotherSideDamageCaused( anotherSideDamageCaused );
		
		return results;
	}
	
	public boolean shouldContinue() {
		return this.aSide.isRetreating() == false &&
				this.anotherSide.isRetreating() == false;
	}
	
	private void disaplySideThatIsRetreating() {
		if( aSide.isRetreating() ) {
			System.out.println( "After this round, " + aSide + " is retreating from battle" );
		}
		if( anotherSide.isRetreating() ) {
			System.out.println( "After this round, " + anotherSide + " is retreating from battle" );
		}
	}

	@Override
	public void publishRoundResultsTo(RoundResultsSubscriber subscriber) {
		roundResutlsSubscribers.add( subscriber );
	}

	@Override
	public void publishRoundResults(RoundResults results) {
		for( RoundResultsSubscriber roundResultsSubscriber : roundResutlsSubscribers ) {
			roundResultsSubscriber.receiveRoundResults( results );
		}
	}
	
}
