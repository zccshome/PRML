package me.zccshome.geneticAlgorithm.knapsackProblem;


import java.io.*;
import java.util.ArrayList;

import javax.swing.JFileChooser;

import me.zccshome.geneticAlgorithm.coreInterface.GeneticAlgorithmInterface;


/**
 * 
 * This algorithm implements the genetic algorithm to solve KnapsackProblem.
 * 
 * @author Zhu Chengchun
 * 
 */
public class KnapsackProblem implements GeneticAlgorithmInterface<StringBuffer, Integer>
{
	/**
	 * The cycle of the algorithm.
	 */
	public int cycle = 800;
	/**
	 * The population of the algorithm.
	 */
	public int population = 300;
	/**
	 * The whole weight restraint of the bag.
	 */
	public int wholeWeight = 0;
	/**
	 * The total fitness of all populations.
	 */
	public int totalFitness = 0;
	/**
	 * The number of objects for chosen in the bag.
	 */
	public int objectNumber = 0;
	/**
	 * The crossover rate.
	 */
	public double crossoverRate = 0.1;
	/**
	 * The variation rate.
	 */
	public double variationRate = 0.05;
	/**
	 * The arrayList preserves all weight of objects separately.
	 */
	public ArrayList<Integer> singleWeight = new ArrayList<Integer>();
	/**
	 * The arrayList preserves all values of objects separately.
	 */
	public ArrayList<Integer> singleValue = new ArrayList<Integer>();
	/**
	 * The array preserves each selection's percentage.
	 */
	public double[] percentage = new double[population];
	/**
	 * The arrayList preserves all information of objects.
	 */
	public ArrayList<SingleObject> objects = new ArrayList<SingleObject>();
	/**
	 * The arrayList preserves all information of selections.
	 */
	public ArrayList<SingleSelection> generations = new ArrayList<SingleSelection>();
	
	/**
	 * Initialize the KnapsackProblem class with two parameters, which is,
	 * the cycle of the algorithm and the population of the algorithm.
	 * @param cycle The new cycle value passes in.
	 * @param population The new population value passes in.
	 */
	public KnapsackProblem(int cycle, int population)
	{
		this.cycle = cycle;
		this.population = population;
		//run();
	}
	
	/**
	 * Initialize the KnapsackProblem class with default parameters.
	 */
	public KnapsackProblem()
	{
		run();
	}
	
	/**
	 * Read statistics from files.
	 */
	public void readStatistics()
	{
		try
		{
			JFileChooser jc = new JFileChooser();
			jc.setCurrentDirectory(new File("."));
			jc.showOpenDialog(null);
			File file = jc.getSelectedFile();
			FileReader fr = new FileReader(file);
			BufferedReader br = new BufferedReader(fr);
			if(br.readLine().equals("in"))
			{
				String tempS = br.readLine();
				wholeWeight = Integer.parseInt(tempS.split(" ")[0]);
				objectNumber = Integer.parseInt(tempS.split(" ")[1]);
				tempS = br.readLine();
				while(!tempS.equals("out"))
				{
					int weight = Integer.parseInt(tempS.split(" ")[0]);
					int value = Integer.parseInt(tempS.split(" ")[1]);
					singleWeight.add(weight);
					singleValue.add(value);
					tempS = br.readLine();
				}
				br.close();
			}
		}
		catch(IOException e)
		{
			System.out.println("aaa");
		}
	}
	
	/**
	 * (non-Javadoc)
	 * @see coreInterface.GeneticAlgorithmInterface#initializePopulation()
	 */
	@Override
	public void initializePopulation()
	{
		// TODO Auto-generated method stub
		for(int i = 0; i < objectNumber; i++)
			objects.add(new SingleObject(singleWeight.get(i),singleValue.get(i)));
		for(int j = 0; j < population; j++)
		{
			StringBuffer tempString = new StringBuffer("");
			for(int k = 0; k < objectNumber; k++)
				tempString.append((Math.random() < 0.5) ? 0 : 1);
			if(checkWeight(tempString))
			{
				int fitness = evaluteFitness(tempString);
				generations.add(new SingleSelection(tempString, fitness));
				totalFitness += fitness;
			}
			else
			{
				while(checkWeight(tempString) == false)
				{
					int num = (int)(Math.random() * objectNumber);
					if(tempString.charAt(num) == '1')
					{
						tempString.deleteCharAt(num);
						tempString.insert(num,'0');
					}
				}
				int fitness = evaluteFitness(tempString);
				generations.add(new SingleSelection(tempString, fitness));
				totalFitness += fitness;
			}
		}
	}
	
	/**
	 * To get all members from a population in one cycle.
	 */
	public void checkPopulation()
	{
		for(SingleSelection temp: generations)
			System.out.println(temp);
	}
	
	/**
	 * To get the average fitness from a population in one cycle.
	 * @return The average fitness.
	 */
	public double getAvgPopulation()
	{
		changeTotalFitness();
		return totalFitness/population;
	}
	
	/**
	 * To get the best answer from a population in one cycle.
	 */
	public void getPopulation()
	{
		int max = -1;
		SingleSelection tempS = null;
		for(SingleSelection temp: generations)
		{
			if(temp.fitness > max)
			{
				tempS = temp;
				max = tempS.getFitness();
			}
		}
		System.out.println("best: " + tempS + " avg: " + getAvgPopulation());
	}
	
	/**
	 * Check if the bag fits our requirement of weight.
	 * @param tempString A single selection.
	 */
	public boolean checkWeight(StringBuffer tempString)
	{
		int tempWeight = 0;
		for(int i = 0; i < objectNumber; i++)
			tempWeight += (tempString.charAt(i)=='1') ? objects.get(i).weight : 0;
		return (tempWeight > wholeWeight) ? false : true;
	}
	
	/**
	 * (non-Javadoc)
	 * @see coreInterface.GeneticAlgorithmInterface#evaluteFitness(java.lang.Object)
	 */
	@Override
	public Integer evaluteFitness(StringBuffer tempString)
	{
		// TODO Auto-generated method stub
		int tempWeight = 0;
		for(int i = 0; i < objectNumber; i++)
			tempWeight += (tempString.charAt(i)=='1') ? objects.get(i).value : 0;
		return tempWeight;
	}

	/**
	 * (non-Javadoc)
	 * @see coreInterface.GeneticAlgorithmInterface#selection()
	 */
	@Override
	public void selection()
	{
		// TODO Auto-generated method stub
		percentage[0] = ((double)generations.get(0).getFitness()) / totalFitness;
		for(int i = 1; i < population-1; i++)
			percentage[i] = percentage[i-1] + ((double)generations.get(i).getFitness()) / totalFitness;
		percentage[population-1] = 1;
		ArrayList<SingleSelection> tempGenerations = new ArrayList<SingleSelection>();
		for(int j = 0; j < population; j++)
		{
			double tempDouble = Math.random();
			for(int k = 0; k < population; k++)
			{
				if(tempDouble < percentage[k])
				{
					tempGenerations.add(generations.get(k));
					break;
				}
			}
		}
		generations = tempGenerations;
	}
	
	/**
	 * (non-Javadoc)
	 * @see coreInterface.GeneticAlgorithmInterface#crossOver()
	 */
	@Override
	public void crossOver()
	{
		// TODO Auto-generated method stub
		ArrayList<SingleSelection> tempGenerations = new ArrayList<SingleSelection>();
		while(generations.size() > 1)
		{
			int a = (int)(Math.random() * generations.size());
			int b = (int)(Math.random() * generations.size());
			while(a == b)
				b = (int)(Math.random() * generations.size());
			SingleSelection aSelection = generations.get(a);
			SingleSelection bSelection = generations.get(b);
			generations.remove(aSelection);
			generations.remove(bSelection);
			for(int i = 0; i < objectNumber; i++)
			{
				if(Math.random() < crossoverRate)
				{
					StringBuffer tempaBuffer = aSelection.selection;
					StringBuffer tempbBuffer = bSelection.selection;
					StringBuffer aBuffer = new StringBuffer(tempaBuffer.subSequence(0, i)).append(tempbBuffer.subSequence(i, objectNumber));
					StringBuffer bBuffer = new StringBuffer(tempbBuffer.subSequence(0, i)).append(tempaBuffer.subSequence(i, objectNumber));
					
					if(checkWeight(aBuffer) && checkWeight(bBuffer))
					{
						aSelection.setSelection(aBuffer);
						bSelection.setSelection(bBuffer);
						aSelection.setFitness(evaluteFitness(aBuffer));
						bSelection.setFitness(evaluteFitness(bBuffer));
						break;
					}
				}
			}
			tempGenerations.add(aSelection);
			tempGenerations.add(bSelection);
		}
		if(generations.size() > 0)
			tempGenerations.add(generations.get(0));
		generations = tempGenerations;
	}

	/**
	 * (non-Javadoc)
	 * @see coreInterface.GeneticAlgorithmInterface#variation()
	 */
	@Override
	public void variation()
	{
		// TODO Auto-generated method stub
		for(SingleSelection tempS: generations)
		{
			StringBuffer select = new StringBuffer(tempS.getSelection());
			for(int i = 0; i < objectNumber; i++)
			{
				if(Math.random() < variationRate)
				{
					if(select.charAt(i)=='0')
					{
						select.deleteCharAt(i);
						select.insert(i, '1');
					}
					else if(select.charAt(i)=='1')
					{
						select.deleteCharAt(i);
						select.insert(i, '0');
					}
				}
			}
			if(checkWeight(select) && (evaluteFitness(select) > tempS.getFitness()))
			{
				tempS.setSelection(select);
				tempS.setFitness(evaluteFitness(select));
			}
		}
	}
	
	/**
	 * Recalculate the total fitness after crossover or variation functions execute.
	 */
	public void changeTotalFitness()
	{
		totalFitness = 0;
		for(SingleSelection temp: generations)
			totalFitness += temp.getFitness();
	}
	
	/**
	 * Begin the algorithm.
	 */
	public void run()
	{
		readStatistics();
		initializePopulation();
		for(int i = 0; i < cycle; i++)
		{
			//System.out.print("��"+i+"�Σ� ");
			selection();
			crossOver();
			variation();
			changeTotalFitness();
			//getPopulation();
		}
		getPopulation();
	}
	
	public static void main(String[] args)
	{
		GeneticAlgorithmInterface<StringBuffer, Integer> KP = new KnapsackProblem();
		//KP.checkPopulation();
		//KP.getPopulation();
	}
}
