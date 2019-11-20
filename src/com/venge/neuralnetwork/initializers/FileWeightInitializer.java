package com.venge.neuralnetwork.initializers;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class FileWeightInitializer extends WeightInitializer {
	
	private List<Double> values;

	public FileWeightInitializer(String path) throws IOException {
		values = new ArrayList<>();
		FileInputStream fis = new FileInputStream(path);
		BufferedReader br = new BufferedReader(new InputStreamReader(fis));
		String line;
		while((line = br.readLine()) != null) {
			values.add(Double.parseDouble(line));
		}
		br.close();
		fis.close();
	}
	
	@Override
	public Double getWeightAt(int index) {
		return values.get(index);
	}
}
