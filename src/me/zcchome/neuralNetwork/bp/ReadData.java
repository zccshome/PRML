package me.zcchome.neuralNetwork.bp;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;

import javax.swing.JFileChooser;

public class ReadData
{
	public static ArrayList<String[]> inputData = new ArrayList<String[]>();
	public static ArrayList<String[]> outputData = new ArrayList<String[]>();
	public static int inputLayer = 0;
	public static int outputLayer = 0;
	
	public static void readFile(File file)
	{
		FileReader fr;
		BufferedReader br;
		try
		{
			fr = new FileReader(file);
			br = new BufferedReader(fr);
			String data;
			while((data = br.readLine()) != null)
			{
				String inputs = data.split(":")[1];
				String outputs = data.split(":")[0];
				String[] input = inputs.split(" ");
				String[] output = outputs.split(" ");
				inputData.add(input);
				outputData.add(output);
				inputLayer = input.length;
				outputLayer = output.length;
			}
			br.close();
			fr.close();
		}
		catch(Exception e)
		{
			System.out.println("zcc");
		}
	}
	public static File getFile(String path)
	{
		/*JFileChooser jc = new JFileChooser();
		jc.setCurrentDirectory(new File("."));
		jc.showOpenDialog(null);
		return jc.getSelectedFile();*/
		try
		{
			File file = new File(path);
			return file;
		}
		catch (Exception e)
		{
			
		}
		return null;
	}
}
