package com.venge.neuralnetwork;

import com.venge.neuralnetwork.activators.Activation;

public class Layer {
	
	private AbstractNode[] nodes;
	private Activation activator;
	
	protected Layer(int size, Activation activator) {
		nodes = new AbstractNode[size];
		this.activator = activator;
	}
	
	protected void connect(Layer backLayer) {
		for(int i = 0; i < nodes.length; i++) {
			nodes[i].setBackLayer(backLayer);
		}
	}
	
	public AbstractNode getNodeAt(int index) {
		return nodes[index];
	}
	
	public void setNodeAt(int index, AbstractNode node) {
		nodes[index] = node;
	}
	
	public Double getNodeValueAt(int index) {
		return getNodeAt(index).getValue();
	}
	
	public void setNodeValueAt(int index, Double value) {
		getNodeAt(index).setValue(value);
	}
	
	public void setNodeValues(Double[] nodeValues) {
		for(int i = 0; i < nodes.length; i++) {
			setNodeValueAt(i, nodeValues[i]);
		}
	}
	
	public int getSize() {
		return nodes.length;
	}
	
	protected void fillLayer(Class<? extends AbstractNode> NodeType) throws Exception {
		for(int i = 0; i < nodes.length; i++) {
			nodes[i] = NodeType.getDeclaredConstructor(Activation.class).newInstance(activator);
		}		
	}
	
	//TODO: change randomize to accept fan in fan out
	public void randomizeLayer() {
		for(int i = 0; i < nodes.length; i++) {
			nodes[i].randomize();
		}
	}
}
