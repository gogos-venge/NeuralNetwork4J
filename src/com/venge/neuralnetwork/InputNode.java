package com.venge.neuralnetwork;

import java.util.Random;

import com.venge.neuralnetwork.activators.Activation;

public class InputNode extends AbstractNode {
	
	protected InputNode(Activation a) {
		super(a);
		setWeights(new Double[0]);
	}
	
	private Double value = 0.0;

	public Double getValue() {
		return value;
	}
	
	public void setValue(Double value) {
		this.value = value;
	}
	
	protected void randomize() {
		Random r = new Random();
		value = r.nextDouble();
	}
	
}
