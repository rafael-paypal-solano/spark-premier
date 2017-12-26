package com.pibicloud.io;

import java.util.Map;

import org.apache.commons.collections.map.UnmodifiableMap;



public class ProcessContext {
	private String currentStateName;
	private Map<String,State> states;	
	private State currentState = null;
	
	public void reset() {
		(this.currentState = states.get(currentStateName)).hashCode();
		
	}
	
	@SuppressWarnings("unchecked")
	public ProcessContext(Map<String,State> states, String currentStateName) {
		this.states = (Map<String,State>)UnmodifiableMap.decorate(states);
		this.currentStateName = currentStateName;
		reset();
	}

	public Map<String, State> getStates() {
		return states;
	}
	
	
	public State getCurrentState() {
		return currentState;
	}
	
	public void setCurrentState(String currentStateName) {
		(this.currentState = states.get(this.currentStateName = currentStateName)).hashCode();
	}
	
}
