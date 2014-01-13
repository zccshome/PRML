package me.zccshome.geneticAlgorithm.coreInterface;

/**
 * 
 * Ths interface is the common procedures of the genetic algorithm.
 * 
 * @author Zhu Chengchun
 *
 * @param <E> The type to preserve the expression of the selection.
 * @param <T> The type to preserve the fitness of the selection.
 */
public interface GeneticAlgorithmInterface<E, T>
{
	/**
	 * Set the statistics read from I/O.
	 */
	public void initializePopulation();
	/**
	 * Get the fitness of the selection.
	 * @param tempString A single selection.
	 * @return The fitness.
	 */
	public T evaluteFitness(E tempString);
	/**
	 * Do the selection procedure.
	 */
	public void selection();
	/**
	 * Do the crossOver procedure.
	 */
	public void crossOver();
	/**
	 * Do the variation procedure.
	 */
	public void variation();
}
