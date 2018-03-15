package com.tds.battlesim;

public interface BattleResultsPublisher {

	public void publishRoundResultsTo( BattleResultsSubscriber subscriber );
	public void publishRoundResults( RoundResults results );
	public void publishSideThatIsRetreating( Side sideThatIsRetreating );
	
}
