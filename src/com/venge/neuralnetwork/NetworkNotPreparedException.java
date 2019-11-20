package com.venge.neuralnetwork;

public class NetworkNotPreparedException extends RuntimeException {

	private static final long serialVersionUID = -2547654266889547359L;

	public NetworkNotPreparedException() {
		super("You must call prepare() before working with this Network");
	}

}
