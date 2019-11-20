package com.venge.neuralnetwork.activators;

public class ReLu extends Activation {

	@Override
	public Double activate(Double input) {
		return Math.max(0, input);
	}

	@Override
	public Double activateDerivative(Double input) {
		// TODO Auto-generated method stub
		return null;
	}

}
