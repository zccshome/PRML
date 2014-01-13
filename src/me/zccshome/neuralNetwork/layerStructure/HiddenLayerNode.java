package me.zccshome.neuralNetwork.layerStructure;

public class HiddenLayerNode
{
	private double input;
	private double output[];
	private double weight[];
	private double bias;
	
	public HiddenLayerNode(double input, double weight[])
	{
		this.input = input;
		this.weight = weight;
	}
	public HiddenLayerNode(double weight[])
	{
		this.weight = weight;
	}
	public void setInput(double input)
	{
		this.input = input;
	}
	public double getInput()
	{
		return input;
	}
	public void setBias(double bias)
	{
		this.bias = bias;
	}
	public double getBias()
	{
		return bias;
	}
	public void setWeights(double weight[])
	{
		this.weight = weight;
	}
	public void setWeight(double weight, int index)
	{
		this.weight[index] = weight;
	}
	public double[] getWeights()
	{
		return weight;
	}
	public double getWeight(int index)
	{
		return weight[index];
	}
	public void setOutput(double[] output)
	{
		this.output = output;
	}
	public double[] getOutputs()
	{
		return output;
	}
	public double getOutput(int index)
	{
		return output[index];
	}
	public void calculateCertainOutput(int index)
	{
		output[index] = input * weight[index];
	}
	public void calculateOutput()
	{
		for(int i = 0; i < output.length; i++)
			calculateCertainOutput(i);
	}
}
