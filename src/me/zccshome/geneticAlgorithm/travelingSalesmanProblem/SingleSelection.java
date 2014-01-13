package me.zccshome.geneticAlgorithm.travelingSalesmanProblem;

import me.zccshome.geneticAlgorithm.coreInterface.Individual;


/**
 * 
 * This class is used to preserve information of each selection, which is, 
 * the expression of route, and the total distance of the corresponding route. 
 *
 * @author Zhu Chengchun
 * 
 */
public class SingleSelection implements Individual<Integer[], Double>
{
	/**
	 * A single selection of route.
	 */
	Integer[] route;
	/**
	 * The distance of the route.
	 */
	double fitness;
	
	/**
	 * Initialize the SingleSelection class with two parameters.
	 * @param route A single selection of route.
	 * @param fitness The distance of the route.
	 */
	public SingleSelection(Integer[] route, double fitness)
	{
		setSelection(route);
		setFitness(fitness);
	}
	
	/**
	 * (non-Javadoc)
	 * @see coreInterface.Individual#setSelection(java.lang.Object)
	 */
	@Override
	public void setSelection(Integer[] selection)
	{
		// TODO Auto-generated method stub
		this.route = selection;
	}

	/**
	 * (non-Javadoc)
	 * @see coreInterface.Individual#setFitness(java.lang.Object)
	 */
	@Override
	public void setFitness(Double fitness)
	{
		// TODO Auto-generated method stub
		this.fitness = fitness;
	}

	/**
	 * (non-Javadoc)
	 * @see coreInterface.Individual#getSelection()
	 */
	@Override
	public Integer[] getSelection()
	{
		// TODO Auto-generated method stub
		return route;
	}

	/**
	 * (non-Javadoc)
	 * @see coreInterface.Individual#getFitness()
	 */
	@Override
	public Double getFitness()
	{
		// TODO Auto-generated method stub
		return fitness;
	}
	
	/**
	 * (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString()  
    {  
        StringBuffer temp = new StringBuffer("");
		for(Integer tempInt: route)
			temp.append(tempInt+" ");
		return "route: "+temp+" fitness: "+fitness;
    } 
}
