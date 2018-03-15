package com.tds.battlesim;

public interface RoundResultsPublisher {

	public void publishRoundResultsTo( RoundResultsSubscriber subscriber );
	public void publishRoundResults( RoundResults results );
	
}
