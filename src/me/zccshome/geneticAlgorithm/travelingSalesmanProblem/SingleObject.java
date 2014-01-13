package me.zccshome.geneticAlgorithm.travelingSalesmanProblem;

import me.zccshome.geneticAlgorithm.coreInterface.SingleObjectInterface;


/**
 * 
 * This class is used to preserve information of each selection, which is, 
 * the X coordinate of the point, and the Y coordinate of the point.
 *  
 * @author Zhu Chengchun
 * 
 */
public class SingleObject implements SingleObjectInterface<Integer, Integer>
{
	/**
	 * The X coordinate of the point.
	 */
	int startX;
	/**
	 * The Y coordinate of the point.
	 */
	int startY;

	/**
	 * Initialize the SingleObject class with two parameters.
	 * @param startX The X coordinate of the point.
	 * @param startY The Y coordinate of the point.
	 */
	public SingleObject(int startX, int startY)
	{
		setObject(startX);
		setValue(startY);
	}
	
	/**
	 * (non-Javadoc)
	 * @see coreInterface.SingleObjectInterface#setObject(java.lang.Object)
	 */
	@Override
	public void setObject(Integer object)
	{
		// TODO Auto-generated method stub
		startX = object;
	}

	/**
	 * (non-Javadoc)
	 * @see coreInterface.SingleObjectInterface#setValue(java.lang.Object)
	 */
	@Override
	public void setValue(Integer value)
	{
		// TODO Auto-generated method stub
		startY = value;
	}

	/**
	 * (non-Javadoc)
	 * @see coreInterface.SingleObjectInterface#getObject()
	 */
	@Override
	public Integer getObject()
	{
		// TODO Auto-generated method stub
		return startX;
	}

	/**
	 * (non-Javadoc)
	 * @see coreInterface.SingleObjectInterface#getValue()
	 */
	@Override
	public Integer getValue()
	{
		// TODO Auto-generated method stub
		return startY;
	}
	
	/**
	 * (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString()  
    {  
      return "startX: "+startX+" startY: "+startY;
    } 
}
