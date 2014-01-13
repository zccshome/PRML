package me.zccshome.geneticAlgorithm.knapsackProblem;

import me.zccshome.geneticAlgorithm.coreInterface.SingleObjectInterface;


/**
 * 
 * This class is used to preserve information of each selection, which is, 
 * the weight of the object, and the value of it.
 *
 * @author Zhu Chengchun
 * 
 */
public class SingleObject implements SingleObjectInterface<Integer, Integer>
{
	/**
	 * The weight of the bag.
	 */
	int weight;
	/**
	 * The value of the objects in the bag.
	 */
	int value;
	
	/**
	 * Initialize the SingleObject class with two parameters.
	 * @param object The weight of the bag.
	 * @param value The value of the objects in the bag.
	 */
	public SingleObject(int object, int value)
	{
		setObject(object);
		setValue(value);
	}
	
	/**
	 * (non-Javadoc)
	 * @see coreInterface.SingleObjectInterface#setObject(java.lang.Object)
	 */
	@Override
	public void setObject(Integer object)
	{
		// TODO Auto-generated method stub
		this.weight = object;
	}

	/**
	 * (non-Javadoc)
	 * @see coreInterface.SingleObjectInterface#setValue(java.lang.Object)
	 */
	@Override
	public void setValue(Integer value)
	{
		// TODO Auto-generated method stub
		this.value = value;
	}

	/**
	 * (non-Javadoc)
	 * @see coreInterface.SingleObjectInterface#getObject()
	 */
	@Override
	public Integer getObject()
	{
		// TODO Auto-generated method stub
		return weight;
	}

	/**
	 * (non-Javadoc)
	 * @see coreInterface.SingleObjectInterface#getValue()
	 */
	@Override
	public Integer getValue()
	{
		// TODO Auto-generated method stub
		return value;
	}
	
	/**
	 * (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString()  
    {  
      return "weight: "+weight+" value: "+value;
    }  
}
