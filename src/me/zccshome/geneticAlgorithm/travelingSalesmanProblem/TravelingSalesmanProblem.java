package me.zcchome.geneticAlgorithm.travelingSalesmanProblem;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.JFileChooser;

import me.zcchome.geneticAlgorithm.coreInterface.GeneticAlgorithmInterface;

/**
 * 
 * This algorithm implements the genetic algorithm to solve TravelingSalesmanProblem.
 *
 * @author Zhu Chengchun
 * 
 */
public class TravelingSalesmanProblem implements GeneticAlgorithmInterface<Integer[], Double>
{
	/**
	 * The cycle of the algorithm.
	 */
	public int cycle = 300;
	/**
	 * The population of the algorithm.
	 */
	public int population = 300;
	/**
	 * The total fitness of all populations.
	 */
	public double totalFitness = 0;
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
	 * The arrayList preserves all X coordinate of points separately.
	 */
	public ArrayList<Integer> singleX = new ArrayList<Integer>();
	/**
	 * The arrayList preserves all Y coordinate of points separately.
	 */
	public ArrayList<Integer> singleY = new ArrayList<Integer>();
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
	 * 
	 * Initialize the TravelingSalesmanProblem class with two parameters, which is,
	 * the cycle of the algorithm and the population of the algorithm.
	 * @param cycle The new cycle value passes in.
	 * @param population The new population value passes in.
	 */
	public TravelingSalesmanProblem(int cycle, int population)
	{
		this.cycle = cycle;
		this.population = population;
		run();
	}
	
	/**
	 * Initialize the KnapsackProblem class with default parameters.
	 */
	public TravelingSalesmanProblem()
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
				while(!tempS.equals("out"))
				{
					objectNumber++;
					int tempX = Integer.parseInt(tempS.split(",")[0].substring(1));
					int tempY = Integer.parseInt(tempS.split(",")[1].substring(0,tempS.split(",")[1].length()-1));
					singleX.add(tempX);
					singleY.add(tempY);
					tempS = br.readLine();
				}
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
			objects.add(new SingleObject(singleX.get(i),singleY.get(i)));
		for(int j = 0; j < population; j++)
		{
			Integer[] tempInt = new Integer[objectNumber];
			for(int k = 0; k < objectNumber; k++)
				tempInt[k] = k;
			for(int l = 0; l < objectNumber; l++)
			{
				int a = (int)(Math.random() * objectNumber);
				int b = (int)(Math.random() * objectNumber);
				while(a == b)
					b = (int)(Math.random() * objectNumber);
				int temp = tempInt[a];
				tempInt[a] = tempInt[b];
				tempInt[b] = temp;
			}
			double fitness = evaluteFitness(tempInt);
			generations.add(new SingleSelection(tempInt, fitness));
			totalFitness += fitness;
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
		double max = 1000000;
		SingleSelection tempS = null;
		for(SingleSelection temp: generations)
		{
			if(temp.getFitness() < max)
			{
				tempS = temp;
				max = tempS.getFitness();
			}
		}
		System.out.println("best: " + tempS + " avg: " + getAvgPopulation());
	}
	
	/**
	 * (non-Javadoc)
	 * @see coreInterface.GeneticAlgorithmInterface#evaluteFitness(java.lang.Object)
	 */
	@Override
	public Double evaluteFitness(Integer[] tempString)
	{
		double fitness = 0;
		// TODO Auto-generated method stub
		for(int i = 0; i < objectNumber; i++)
		{
			int startX = objects.get(tempString[i%objectNumber]).getObject();
			int startY = objects.get(tempString[i%objectNumber]).getValue();
			int endX = objects.get(tempString[(i+1)%objectNumber]).getObject();
			int endY = objects.get(tempString[(i+1)%objectNumber]).getValue();
			fitness += Math.sqrt(Math.abs(endX-startX)*Math.abs(endX-startX)+Math.abs(endY-startY)*Math.abs(endY-startY));
		}
		return fitness;
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
		percentage[0] = (1- percentage[0])/(population-1);
		for(int i = 1; i <= population-1; i++)
		{
			percentage[i] = ((double)generations.get(i).getFitness()) / totalFitness;
			percentage[i] = percentage[i-1] + (1- percentage[i])/(population-1);
		}
		percentage[population-1] = 1;
		//for(double a: percentage)
			//System.out.println(a);
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
		int numm = population;
		while(numm > 1)
		{
			int a = (int)(Math.random() * numm);
			int b = (int)(Math.random() * numm);
			while(a == b)
				b = (int)(Math.random() * numm);
			SingleSelection aSelection = generations.get(a);
			SingleSelection bSelection = generations.get(b);
			generations.remove(aSelection);
			generations.remove(bSelection);
			if(Math.random() < crossoverRate)
			{
				Integer[] tempaBuffer = aSelection.getSelection();
				Integer[] tempbBuffer = bSelection.getSelection();
				int checkPointA = (int)(Math.random() * objectNumber);
				int checkPointB = (int)(Math.random() * objectNumber);
				while(checkPointA == checkPointB)
					checkPointB = (int)(Math.random() * objectNumber);
				if(checkPointA > checkPointB)
				{
					int t = checkPointA;
					checkPointA = checkPointB;
					checkPointB = t;
				}
				int num = checkPointB;
				
				Integer[] fullA = new Integer[objectNumber];
				Integer[] fullB = new Integer[objectNumber];
				for(int i = 0; i < objectNumber; i++)
				{
					fullA[i] = tempaBuffer[num % objectNumber];
					fullB[i] = tempbBuffer[num % objectNumber];
					num++;
				}
				for(int i = checkPointA; i < checkPointB; i++)
				{
					int tempA = position(tempbBuffer[i], fullA);
					fullA[tempA] = -1;
					int tempB = position(tempaBuffer[i], fullB);
					fullB[tempB] = -1;
				}
				
				Integer[] aBuffer = new Integer[objectNumber];
				Integer[] bBuffer = new Integer[objectNumber];
				int count1 = 0; int count2 = 0;
				for(int i = 0; i < objectNumber; i++)
				{
					if(i >= checkPointA && i < checkPointB)
					{
						bBuffer[i] = tempaBuffer[i];
						aBuffer[i] = tempbBuffer[i];
					}
					else
					{
						count1 = next(count1,fullA);
						count2 = next(count2,fullB);
						aBuffer[i] = Integer.parseInt(""+fullA[count1]);
						bBuffer[i] = Integer.parseInt(""+fullB[count2]);
						count1++;count2++;
					}
				}
				if(evaluteFitness(aBuffer) < evaluteFitness(tempaBuffer))
				{
					aSelection.setSelection(aBuffer);
					aSelection.setFitness(evaluteFitness(aBuffer));
				}
				if(evaluteFitness(bBuffer) < evaluteFitness(tempbBuffer))
				{
					bSelection.setSelection(bBuffer);
					bSelection.setFitness(evaluteFitness(bBuffer));
				}
			}
			tempGenerations.add(aSelection);
			tempGenerations.add(bSelection);
			numm -= 2;
		}
		if(numm > 0)
			tempGenerations.add(generations.get(0));
		
		generations = tempGenerations;
	}
	
	/**
	 * To get the next element that does not equals -1 in an array from a certain place a
	 * @param a The start place of the array.
	 * @param temp The array used for search.
	 * @return Return the place of the next suitable number.
	 */
	public int next(int a, Integer[] temp)
	{
		while(temp[a] == -1)
			a++;
		return a;
	}
	
	/**
	 * To get the place of the  element that equals a.
	 * @param a The number for search.
	 * @param temp The array for which to search in.
	 * @return The place of the number a. If not found, return -1.
	 */
	public int position(int a, Integer[] temp)
	{
		for(int i = 0; i < temp.length; i++)
		{
			if(temp[i] == a)
				return i;
		}
		return -1;
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
			if(Math.random() < variationRate)
			{
				Integer[] select = tempS.getSelection();
				Integer[] after = new Integer[objectNumber];
				int checkPointA = (int)(Math.random() * objectNumber);
				int checkPointB = (int)(Math.random() * objectNumber);
				while(checkPointA == checkPointB)
					checkPointB = (int)(Math.random() * objectNumber);
				if(checkPointA > checkPointB)
				{
					int t = checkPointA;
					checkPointA = checkPointB;
					checkPointB = t;
				}
				int j = 0;
				for(int i = 0; i < objectNumber; i++)
				{
					if(i >= checkPointA && i < checkPointB)
					{
						after[i] = select[checkPointB-1-j];
						j++;
					}
					else
						after[i] = select[i];
				}
				if(evaluteFitness(after) < evaluteFitness(select))
				{
					tempS.setSelection(after);
					tempS.setFitness(evaluteFitness(after));
				}
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
		//getPopulation();
		for(int i = 0; i < cycle; i++)
		{
			System.out.print("��"+i+"�Σ� ");
			selection();
			crossOver();
			variation();
			changeTotalFitness();
			getPopulation();
		}
		getPopulation();
		/*double max = 100000000;
		for(SingleSelection a: generations)
		{
			if(a.fitness < max)
				max = a.fitness;
		}
		System.out.println(max);*/
	}
	
	public static void main(String[] args)
	{
		GeneticAlgorithmInterface<Integer[], Double> KP = new TravelingSalesmanProblem();
	}
}
