package com.venge.neuralnetwork;

import java.util.Random;

import com.venge.neuralnetwork.activators.Activation;

public class Node extends AbstractNode {
	
	protected Node(Activation a) {
		super(a);
	}

	public Double getValue() {
		//activation function sigmoid
		return activate(getZ());
	}
	
	protected Double getZ() {
		Double[] weights = getWeights();
		Layer backLayer = getBackLayer();
		Double bias = getBias();
		
		Double weightProductSum = 0.0;
		//Weights have the same length as parent->getSize
		for(int i = 0; i < weights.length; i++) {
			weightProductSum += weights[i] * backLayer.getNodeAt(i).getValue();
		}
		
		return weightProductSum + bias;
	}
	
	public void setValue(Double value) {
		throw new IllegalArgumentException("Only input nodes can have their values set.");
	}
	
	protected void randomize() {
		Random r = new Random();
		Double[] weights = getWeights();
		for(int i = 0; i < weights.length; i++) {
			weights[i] = -2 + r.nextDouble() + r.nextInt(4);
		}
	}

}
