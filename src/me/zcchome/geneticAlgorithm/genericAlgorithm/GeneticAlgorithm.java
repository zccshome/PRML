package me.zcchome.geneticAlgorithm.genericAlgorithm;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class GeneticAlgorithm
{
	//08302010073
	//09302010084
	public int totalGeneration = 10;
	public int cycle = 10;
	public int length = 8;
	public ArrayList<String> generations = new ArrayList<String>();
	
	public static void main(String[] args)
	{
		//System.out.println("a");
		GeneticAlgorithm ps = new GeneticAlgorithm();
		ps.initialize();
		for(int i = 0; i < ps.cycle; i++)
		{
			ps.sort();
			ps.evaluteFitness();
			ps.exchangeBits();
			ps.variation();
			//System.out.println(ps.generations.get(0));
		}
		ps.sort();
		System.out.println(ps.generations.get(0));
	}
	public void initialize()
	{
		for(int i = 0; i < totalGeneration; i++)
		{
			StringBuffer tempString = new StringBuffer();
			for(int j = 0; j < length; j++)
			{
				tempString.append(Integer.toString(Math.random() < 0.5 ? 0 : 1));
			}
			generations.add(tempString.toString());
			//System.out.println(tempString);
		}
		//String a = "00111101";String b = "10110110";
		//System.out.println(compare(a,b));
	}
	public void sort()
	{
		Collections.sort(generations,new Compare());
		/*for(String temp: generations)
			System.out.println(temp);*/
	}
	public void evaluteFitness()
	{
		int firstClass = (int)(totalGeneration * 0.1);
		//int firstClassPercentage = 3;
		int secondClass = (int)(totalGeneration * 0.3);
		//int secondClassPercentage = 2;
		ArrayList<String> tempGenerations = new ArrayList<String>();
		for(int i = 0; i < totalGeneration; i++)
		{
			if(i < firstClass)
			{
				String temp = generations.get(i);
				tempGenerations.add(temp);
				tempGenerations.add(temp);
				tempGenerations.add(temp);
				continue;
			}
			else if(i < secondClass)
			{
				String temp = generations.get(i);
				tempGenerations.add(temp);
				tempGenerations.add(temp);
				continue;
			}
			else if(tempGenerations.size() < totalGeneration)
			{
				tempGenerations.add(generations.get(i));
				continue;
			}
		}
		generations = tempGenerations;
		/*for(String temp: generations)
			System.out.println(temp);*/
	}
	public void exchangeBits()
	{
		ArrayList<String> tempGenerations = new ArrayList<String>();
		for(int i = 0; i < totalGeneration / 2; i++)
		{
			String exchangeA = generations.get(i);
			String exchangeB = generations.get(totalGeneration-i-1);
			for(int j = 1; j <= length; j++)
			{
				boolean exchange = Math.random() < 0.1;
				if(exchange)
				{
					exchangeA = exchangeA.substring(0, j) + exchangeB.substring(j, length);
					exchangeB = exchangeB.substring(0, j) + exchangeA.substring(j, length);
					break;
				}
			}
			tempGenerations.add(exchangeA);
			tempGenerations.add(exchangeB);
		}
		generations = tempGenerations;
	}
	public void variation()
	{
		ArrayList<String> tempGenerations = new ArrayList<String>();
		for(String temp: generations)
		{
			StringBuffer tempB = new StringBuffer(temp);
			for(int i = 0; i < length; i++)
			{
				boolean variation = Math.random() < 0.05;
				if(variation)
				{
					if(tempB.charAt(i) == '1')
					{
						tempB.deleteCharAt(i);
						tempB.insert(i, '0');
					}
					else
					{
						tempB.deleteCharAt(i);
						tempB.insert(i, '1');
					}
				}
			}
			tempGenerations.add(tempB.toString());
		}
		generations = tempGenerations;
	}
	class Compare implements Comparator<String>
	{
		public int compare(String a, String b)
		{
			for(int i = 0; i < length; i++)
			{
				if(Integer.parseInt(a.substring(i,i+1)) > Integer.parseInt(b.substring(i,i+1)))
					return -1;
				else if(Integer.parseInt(a.substring(i,i+1)) < Integer.parseInt(b.substring(i,i+1)))
					return 1;
				else if(Integer.parseInt(a.substring(i,i+1)) == Integer.parseInt(b.substring(i,i+1)))
					continue;
			}
			return 0;
		}
	}
}
