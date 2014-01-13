package me.zcchome.geneticAlgorithm.coreInterface;

/**
 * 
 * The interface is used to preserve each selection's attribute, that is, 
 * in the KnapsackProblem is the whole weight and value, 
 * in the TravelingSalesmanProblem is the route and the distance of the route. 
 * 
 * @author Zhu Chengchun
 *
 * @param <E> The type to preserve the expression of the selection.
 * @param <T> The type to preserve the fitness of the selection.
 */
public interface Individual<E,T>
{
	/**
	 * Set the expression of the selection.
	 * @param tempString The expression of the selection.
	 */
	public void setSelection(E selection);
	/**
	 * Set the fitness of the selection.
	 * @param fitness The fitness of the selection.
	 */
	public void setFitness(T fitness);
	/**
	 * Get the expression of the selection.
	 * @return The expression of the selection.
	 */
	public E getSelection();
	/**
	 * Get the fitness of the selection.
	 * @return The fitness of the selection.
	 */
	public T getFitness();
}
