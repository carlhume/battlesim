package com.tds.battlesim;

public interface RoundResultsSubscriber {

	public void receiveRoundResults( RoundResults results );
	public void sideIsRetreating( Side side );
	
}
