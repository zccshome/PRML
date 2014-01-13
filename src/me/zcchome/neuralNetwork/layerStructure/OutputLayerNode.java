package me.zcchome.neuralNetwork.layerStructure;

public class OutputLayerNode
{
	private double input;
	private double output;
	private double bias;
	
	public OutputLayerNode(double input)
	{
		this.input = input;
	}
	public OutputLayerNode()
	{
	}
	public void setInput(double input)
	{
		this.input = input;
	}
	public void setOutput(double output)
	{
		this.output = output;
	}
	public void setBias(double bias)
	{
		this.bias = bias;
	}
	public double getBias()
	{
		return bias;
	}
	public double getOutput()
	{
		return output;
	}
	public void calculateOutput()
	{
		output = input;
	}
}
