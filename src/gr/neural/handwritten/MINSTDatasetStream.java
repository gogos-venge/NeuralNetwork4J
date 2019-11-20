package gr.neural.handwritten;

import java.io.FileInputStream;
import java.io.IOException;

public class MINSTDatasetStream {
	private final int blockSize = 784;
	private final int overhead = 8;
	
	private FileInputStream imageFis;
	private FileInputStream labelFis;
	
	public MINSTDatasetStream(String path) throws Exception {
		imageFis = new FileInputStream(path + "\\train-images.idx3-ubyte");
		labelFis = new FileInputStream(path + "\\train-labels.idx1-ubyte");
		imageFis.skip(8); //???
		labelFis.skip(8); //???????? some shitty header i assume?
	}
	
	public void seek(int index) throws IOException {
		imageFis.getChannel().position(index * blockSize + overhead);
		labelFis.getChannel().position(index + overhead);
	}
	
	public Double[] getImage() throws IOException {
		byte[] buffer = new byte[blockSize];
		Double[] data = new Double[blockSize];
		
		imageFis.read(buffer);
		for(int i = 0; i < buffer.length; i++) {
			data[i] = (char)buffer[i] / (double)0xFF;
		}
		
		return data;
	}
	
	public int getLabel() throws IOException {
		return labelFis.read();
	}
	
	public long getSize() throws IOException {
		return labelFis.getChannel().size() - overhead;
	}
	
	public void close() throws IOException {
		imageFis.close();
		labelFis.close();
	}

}
