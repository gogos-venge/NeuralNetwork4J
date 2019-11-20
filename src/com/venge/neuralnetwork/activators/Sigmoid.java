package com.venge.neuralnetwork.activators;

public class Sigmoid extends Activation {

	@Override
	public Double activate(Double input) {
		return 1 / (1 + Math.exp(-input));
	}

	@Override
	public Double activateDerivative(Double input) {
		// TODO Auto-generated method stub
		return null;
	}

}
