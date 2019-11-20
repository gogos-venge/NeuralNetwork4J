package gr.neural.handwritten;

import com.venge.neuralnetwork.*;
import com.venge.neuralnetwork.activators.*;

public class Main {
	
	public static void main(String[] args) {
		
		//Create Network
		DeepNetwork nw = new DeepNetwork();
		Activation a = new Sigmoid();

		nw.addInputLayer(784, a);
		nw.addHiddenLayer(16, a);
		nw.addHiddenLayer(16, a);
		nw.addOutputLayer(10, a);
		
		try {
			//Prepare network
			System.out.println("Preparing network...");
			nw.prepare();
			
			System.out.println("Network has "+ nw.getSize() + " nodes");
			
			System.out.println("Network has "+ nw.getWeightCount() + " weights");
			
			//Load Mnist train sets
			System.out.println("Loading training sets...");
			//List<TrainSet> trainingSet = loadTrainSets("D:\\mnist_train.csv");
			MINSTDatasetStream mds = new MINSTDatasetStream("D:\\mnist");
			
			Layer input = nw.getInputLayer();
			Layer output = nw.getOutputLayer();
			
			input.setNodeValues(mds.getImage());
			for(int i = 0; i < output.getSize(); i++) {
				System.out.println(output.getNodeValueAt(i));
			}
			double CostFunctionAverage = 0.0;
			int n = 100;
			for(int i = 0; i < n; i++) {
				input.setNodeValues(mds.getImage());
				
				int label = mds.getLabel();
				Double result = nw.calculateCost(label);
				CostFunctionAverage += result;
				System.out.println(i + ") Cost function for " + label + " is " + result);
			}
			
			System.out.println("Cost average is: "+ CostFunctionAverage / n);
			//Feed first input
			
			mds.close();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
}
