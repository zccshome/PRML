package me.zccshome.geneticAlgorithm.knapsackProblem;

import me.zccshome.geneticAlgorithm.coreInterface.Individual;

/**
 * 
 * This class is used to preserve information of each selection, which is, 
 * the expression of selection E, and the fitness of the corresponding selection.
 *
 * @author Zhu Chengchun
 * 
 */
public class SingleSelection implements Individual<StringBuffer, Integer>
{
	/**
	 * A single expression of selection.
	 */
	StringBuffer selection;
	/**
	 * The fitness of the selection.
	 */
	int fitness;
	
	/**
	 * Initialize the SingleSelection class with two parameters.
	 * @param selection A single expression of selection.
	 * @param fitness The fitness of the selection.
	 */
	public SingleSelection(StringBuffer selection, int fitness)
	{
		setSelection(selection);
		setFitness(fitness);
	}
	
	/**
	 * (non-Javadoc)
	 * @see coreInterface.Individual#setSelection(java.lang.Object)
	 */
	@Override
	public void setSelection(StringBuffer selection)
	{
		this.selection = selection;
	}
	
	/**
	 * (non-Javadoc)
	 * @see coreInterface.Individual#setFitness(java.lang.Object)
	 */
	@Override
	public void setFitness(Integer fitness)
	{
		this.fitness = fitness;
	}
	
	/**
	 * (non-Javadoc)
	 * @see coreInterface.Individual#getSelection()
	 */
	@Override
	public StringBuffer getSelection()
	{
		return selection;
	}
	
	/**
	 * (non-Javadoc)
	 * @see coreInterface.Individual#getFitness()
	 */
	@Override
	public Integer getFitness()
	{
		return fitness;
	}
	
	@Override
	public String toString()
	{
		return "selection: " + selection.toString() + " fitness: " + fitness;
	}
}
