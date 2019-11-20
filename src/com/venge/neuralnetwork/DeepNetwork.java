package com.venge.neuralnetwork;

import java.util.ArrayList;
import java.util.List;

import com.venge.neuralnetwork.activators.Activation;

public class DeepNetwork {
	
	public enum WeightInitializer {
		Random,
		Xavier,
		HeEtAl
	}
	
	private Layer InputLayer;
	private Layer OutputLayer;
	private boolean Prepared = false;
	
	private List<Layer> Layers;
	private List<Layer> Hidden;
	
	public DeepNetwork() {
		Layers = new ArrayList<Layer>();
		Hidden = new ArrayList<Layer>();
	}

	public DeepNetwork addInputLayer(int size, Activation activator) {
		Layer input = new Layer(size, activator);
		InputLayer = input;
		return this;
	}
	
	public DeepNetwork addOutputLayer(int size, Activation activator) {
		Layer output = new Layer(size, activator);
		OutputLayer = output;
		return this;
	}
	
	public DeepNetwork addHiddenLayer(int size, Activation activator) {
		Layer hidden = new Layer(size, activator);
		Hidden.add(hidden);
		return this;
	}
	
	public DeepNetwork prepare() throws Exception {
		InputLayer.fillLayer(InputNode.class);
		Layers.add(InputLayer);
		for(int i = 0; i < Hidden.size(); i++) {
			Layer l = Hidden.get(i);
			l.fillLayer(Node.class);
			if(i == 0) {
				l.connect(InputLayer);
			} else {
				l.connect(Hidden.get(i - 1));
			}
			l.randomizeLayer();
			Layers.add(l);
		}
		OutputLayer.fillLayer(Node.class);
		if(Hidden.size() > 0) {
			OutputLayer.connect(Hidden.get(Hidden.size() - 1));
		} else {
			OutputLayer.connect(InputLayer);
		}
		
		OutputLayer.randomizeLayer();
		Layers.add(OutputLayer);
		Prepared = true;
		return this;
	}
	
	public boolean isPrepared() {
		return Prepared;
	}
	
	public Layer getInputLayer() {
		if(!Prepared) {
			throw new NetworkNotPreparedException();
		}
		
		return InputLayer;
	}
	
	public Layer getOutputLayer() {
		if(!Prepared) {
			throw new NetworkNotPreparedException();
		}
		
		return OutputLayer;
	}
	
	public Double calculateCost(Double[] expectedValues) {
		Double sum = 0.0;
		for(int i = 0; i < OutputLayer.getSize(); i++) {
			sum += Math.pow(OutputLayer.getNodeValueAt(i) - expectedValues[i], 2);
		}
		return sum;
	}
	
	public double calculateCost(int index) {
		double sum = 0.0;
		for(int i = 0; i < OutputLayer.getSize(); i++) {
			int value = index == i ? 1 : 0;
			double outputValue = OutputLayer.getNodeValueAt(i);
			double resultPow = Math.pow(outputValue - value, 2);
			sum += resultPow;
		}
		return sum;
	}
	
	public int getSize() {
		if(!Prepared) {
			throw new NetworkNotPreparedException();
		}
		int sum = 0;
		for(Layer l : Layers) {
			sum += l.getSize();
		}
		return sum;
	}
	
	public int getWeightCount() {
		if(!Prepared) {
			throw new NetworkNotPreparedException();
		}
		int sum = 0;
		for(Layer l : Layers) {
			for(int i = 0; i < l.getSize(); i++) {
				sum += l.getNodeAt(i).getWeights().length;
			}
		}
		return sum;
	}
	
	//TODO: add weight initialization (load save options)
}
