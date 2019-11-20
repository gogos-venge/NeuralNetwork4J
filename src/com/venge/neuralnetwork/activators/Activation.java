package com.venge.neuralnetwork.activators;

public abstract class Activation {
	
	public abstract Double activate(Double input);
	public abstract Double activateDerivative(Double input);
}
