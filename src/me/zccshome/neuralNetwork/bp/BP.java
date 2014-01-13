package me.zcchome.neuralNetwork.bp;

import me.zcchome.neuralNetwork.layerStructure.InputLayerNode;
import me.zcchome.neuralNetwork.layerStructure.HiddenLayerNode;
import me.zcchome.neuralNetwork.layerStructure.OutputLayerNode;
import me.zcchome.neuralNetwork.layerStructure.Layers;

public class BP
{
	public static final int hiddenLayer = 20;
	public double[][] inputHiddenWeight = new double[ReadData.inputLayer][hiddenLayer];
	public double[][] hiddenOutputWeight = new double[hiddenLayer][ReadData.outputLayer];
	public Layers layers = new Layers();
	public double learningRate = 0.7;
	public double learningRate2 = learningRate;
	
	public void initializeNetwork()
	{
		for(int i = 0; i < ReadData.inputLayer; i++)
			for(int j = 0; j < hiddenLayer; j++)
				inputHiddenWeight[i][j] = Math.random() * 2 - 1;
		for(int i = 0; i < hiddenLayer; i++)
			for(int j = 0; j < ReadData.outputLayer; j++)
				hiddenOutputWeight[i][j] = Math.random() * 2 - 1;
		
		for(int i = 0; i < ReadData.inputLayer; i++)
		{
			InputLayerNode temp = new InputLayerNode(inputHiddenWeight[i]);
			temp.setOutput(new double[hiddenLayer]);
			layers.inputLayer.add(temp);
		}
		for(int i = 0; i < hiddenLayer; i++)
		{
			HiddenLayerNode temp = new HiddenLayerNode(hiddenOutputWeight[i]);
			temp.setOutput(new double[ReadData.outputLayer]);
			temp.setBias(Math.random() * 2 - 1);
			layers.hiddenLayer.add(temp);
		}
		for(int i = 0; i < ReadData.outputLayer; i++)
		{
			OutputLayerNode temp = new OutputLayerNode();
			temp.setBias(Math.random() * 2 - 1);
			layers.outputLayer.add(temp);
		}
	}
	public double function(double input)
	{
		return 1 / (1 + Math.exp(-input));
	}
	public double[] buildBPNetwork(int index)
	{
		String[] rawData = ReadData.inputData.get(index);
		for(int i = 0; i < rawData.length; i++)
		{
			double d = Double.parseDouble(rawData[i]);
			layers.inputLayer.get(i).setInput(d);
		}
		
		for(InputLayerNode inputLayerNode: layers.inputLayer)
			inputLayerNode.calculateOutput();
		
		for(int i = 0; i < hiddenLayer; i++)
		{
			HiddenLayerNode hiddenLayerNode = layers.hiddenLayer.get(i);
			double temp = 0;
			for(int j = 0; j < ReadData.inputLayer; j++)
			{
				temp += layers.inputLayer.get(j).getOutput(i);
			}
			temp += hiddenLayerNode.getBias();
			temp = function(temp);
			hiddenLayerNode.setInput(temp);
		}
		
		for(HiddenLayerNode hiddenLayerNode: layers.hiddenLayer)
			hiddenLayerNode.calculateOutput();
		
		for(int i = 0; i < ReadData.outputLayer; i++)
		{
			OutputLayerNode outputLayerNode = layers.outputLayer.get(i);
			double temp = 0;
			for(int j = 0; j < hiddenLayer; j++)
			{
				temp += layers.hiddenLayer.get(j).getOutput(i);
			}
			temp += outputLayerNode.getBias();
			temp = function(temp);
			outputLayerNode.setInput(temp);
		}
		
		double[] answer = new double[ReadData.outputLayer];
		for(int i = 0; i < ReadData.outputLayer; i++)
		{
			OutputLayerNode outputLayerNode = layers.outputLayer.get(i);
			outputLayerNode.calculateOutput();
			answer[i] = outputLayerNode.getOutput();
		}
		return answer;
	}
	
	public void modifyWeight1(double[] answer, int index)
	{
		double[] outputDelta = new double[ReadData.outputLayer];
		for (int i = 0; i < ReadData.outputLayer; i++)
		{
			double d = answer[i];
			double actualAnswer = Double.parseDouble(ReadData.outputData.get(index)[i]);
			outputDelta[i] = d * (1 - d) * (actualAnswer - d);
		}
		for(int i = 0; i < ReadData.outputLayer; i++)
		{
	    	for(int j = 0; j < hiddenLayer; j++)
	    	{
	    		double weight = layers.hiddenLayer.get(j).getWeight(i);
	    		double input = layers.hiddenLayer.get(j).getInput();
	    		double value = weight + learningRate * outputDelta[i] * input;
	    		layers.hiddenLayer.get(j).setWeight(value, i);
	    	}
	    	layers.outputLayer.get(i).setBias(layers.outputLayer.get(i).getBias() + learningRate * outputDelta[i]);
		}
		double[] hiddenDelta = new double[hiddenLayer];
	 	for (int i = 0; i < hiddenLayer; i++)
	 	{
	 		double d = layers.hiddenLayer.get(i).getInput();
	 		double sum = 0;
	 		for(int j = 0; j < ReadData.outputLayer; j++)
	 		{
	 			sum += outputDelta[j] * layers.hiddenLayer.get(i).getWeight(j);
	 		}
	 		hiddenDelta[i] = d * (1 - d) * sum;
	 	}
		for(int i = 0; i < hiddenLayer; i++)
		{
	    	for(int j = 0; j < ReadData.inputLayer; j++)
	    	{
	    		double weight = layers.inputLayer.get(j).getWeight(i);
	    		double input = layers.inputLayer.get(j).getInput();
	    		double value = weight + learningRate * hiddenDelta[i] * input;
	    		layers.inputLayer.get(j).setWeight(value, i);
	    	}
	    	layers.hiddenLayer.get(i).setBias(layers.hiddenLayer.get(i).getBias() + learningRate * hiddenDelta[i]);
		}
	}
	public void modifyWeight2(double[] answer, int index)
	{
		for(int i = 0; i < ReadData.outputLayer; i++)
		{
			double actualAnswer = Double.parseDouble(ReadData.outputData.get(index)[i]);
			double inputHiddenFactor = (actualAnswer - answer[i]) * answer[i] * (1 - answer[i]) * learningRate;
			for(int j = 0; j < ReadData.inputLayer; j++)
			{
				double factor = inputHiddenFactor * layers.inputLayer.get(j).getInput();
				for(int k = 0; k < hiddenLayer; k++)
				{
					double delta = factor * layers.hiddenLayer.get(k).getWeight(i) * layers.hiddenLayer.get(k).getInput() * (1-layers.hiddenLayer.get(k).getInput());
					double weight = layers.inputLayer.get(j).getWeight(k);
					layers.inputLayer.get(j).setWeight(weight + delta, k);
				}
			}
			for(int j = 0; j < hiddenLayer; j++)
			{
				double delta = inputHiddenFactor * layers.hiddenLayer.get(j).getWeight(i) * layers.hiddenLayer.get(j).getInput() * (1 - layers.hiddenLayer.get(j).getInput());
				double bias = layers.hiddenLayer.get(j).getBias();
				layers.hiddenLayer.get(j).setBias(bias + delta);
			}
			for(int j = 0; j < hiddenLayer; j++)
			{
				double delta = inputHiddenFactor * layers.hiddenLayer.get(j).getInput();
				double weight = layers.hiddenLayer.get(j).getWeight(i);
				layers.hiddenLayer.get(j).setWeight(weight + delta, i);
			}
			double bias = layers.outputLayer.get(i).getBias();
			layers.outputLayer.get(i).setBias(bias + inputHiddenFactor);
		}
	}
	public void modifyWeight3(double[] answer, int index)
	{
		double error = 0;
		for(int i = 0; i < ReadData.outputLayer; i++)
		{
			double actualAnswer = Double.parseDouble(ReadData.outputData.get(index)[i]);
			error += (actualAnswer - answer[i])*(actualAnswer - answer[i]);
			double diff = learningRate2 * (actualAnswer - answer[i]) * answer[i] * (1 - answer[i]);
			for(int j = 0; j < hiddenLayer; j++)
			{
				double hiddenOutput = layers.hiddenLayer.get(j).getInput();
				double adjust = diff * hiddenOutput;
				double weight = layers.hiddenLayer.get(j).getWeight(i);
				layers.hiddenLayer.get(j).setWeight(weight + adjust, i);
			}
			layers.outputLayer.get(i).setBias(layers.outputLayer.get(i).getBias() + diff);
		}
		error /= 2;
		//System.out.println("��"+index+"��: "+error);
		double half[] = new double[hiddenLayer];
		for(int j = 0; j < hiddenLayer; j++)
		{
			double halfAll = 0;
			for(int i = 0; i < ReadData.outputLayer; i++)
			{
				double actualAnswer = Double.parseDouble(ReadData.outputData.get(index)[i]);
				double diff = (actualAnswer - answer[i]) * answer[i] * (1 - answer[i]);
				double weight = layers.hiddenLayer.get(j).getWeight(i);
				halfAll += (diff * weight);
			}
			half[j] = halfAll;
		}
		for(int j = 0; j < hiddenLayer; j++)
		{
			double weight = layers.hiddenLayer.get(j).getInput();
			double diff = weight * (1 - weight) * learningRate * half[j];
			for(int k = 0; k < ReadData.inputLayer; k++)
			{
				double kWeight = layers.inputLayer.get(k).getWeight(j);
				double output = layers.inputLayer.get(k).getInput();
				layers.inputLayer.get(k).setWeight(kWeight + diff * output, j);
			}
			layers.hiddenLayer.get(j).setBias(layers.hiddenLayer.get(j).getBias() + diff);
		}
	}
}
