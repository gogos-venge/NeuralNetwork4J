package com.venge.neuralnetwork;

import com.venge.neuralnetwork.activators.Activation;

public abstract class AbstractNode {
	
	private Double bias = 0.0;
	private Double[] weights;

	private Layer backLayer; //one layer closer to input.
	
	private Activation activator;
	
	protected AbstractNode(Activation a) {
		this.activator = a;
	}

	public abstract Double getValue();
	
	public abstract void setValue(Double value);
	
	protected abstract void randomize();
	
	protected void setBackLayer(Layer backLayer) {
		this.backLayer = backLayer;
		weights = new Double[backLayer.getSize()];
	}
	
	protected Layer getBackLayer() {
		return backLayer;
	}
	
	public Double getBias() {
		return bias;
	}

	public void setBias(Double bias) {
		this.bias = bias;
	}

	public Double[] getWeights() {
		return weights;
	}

	public void setWeights(Double[] weights) {
		this.weights = weights;
	}

	protected double activate(double input) {
		return activator.activate(input);
	}
	
}
