package com.tds.battlesim;

import java.util.ArrayList;
import java.util.Collection;

public class Battle implements BattleResultsPublisher {

	private Side aSide;
	private Side anotherSide;
	private Collection<BattleResultsSubscriber> roundResutlsSubscribers = new ArrayList<BattleResultsSubscriber>();
	
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
		
		publishSideThatIsRetreating();
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
	
	private void publishSideThatIsRetreating() {		
		Side sideThatIsRetreating = findSideThatIsRetreating();			
		publishSideThatIsRetreating(sideThatIsRetreating);
	}	

	public void publishSideThatIsRetreating(Side sideThatIsRetreating) {
		for( BattleResultsSubscriber roundResultsSubscriber : roundResutlsSubscribers ) {
			roundResultsSubscriber.sideIsRetreating( sideThatIsRetreating );
		}
	}

	private Side findSideThatIsRetreating() {
		// >> cnh >> Risk of Returning Null 
		// TODO: Refactor
		Side sideThatIsRetreating = null;
		if( aSide.isRetreating() ) {
			sideThatIsRetreating = aSide;
		}
		if( anotherSide.isRetreating() ) {
			sideThatIsRetreating = anotherSide;
		}
		return sideThatIsRetreating;
	}

	@Override
	public void publishRoundResultsTo(BattleResultsSubscriber subscriber) {
		roundResutlsSubscribers.add( subscriber );
	}

	@Override
	public void publishRoundResults(RoundResults results) {
		for( BattleResultsSubscriber roundResultsSubscriber : roundResutlsSubscribers ) {
			roundResultsSubscriber.receiveRoundResults( results );
		}
	}
	
}
