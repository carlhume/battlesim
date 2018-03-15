package com.tds.battlesim;

public interface BattleResultsSubscriber {

	public void receiveRoundResults( RoundResults results );
	public void sideIsRetreating( Side side );
	
}
