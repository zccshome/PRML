package me.zccshome.geneticAlgorithm.coreInterface;

/**
 * 
 * The interface is used to preserve each object's attribute, that is, 
 * in the KnapsackProblem is the weight and value, 
 * in the TravelingSalesmanProblem is the X and Y coordinate. 
 * 
 * @author Zhu Chengchun
 *
 * @param <E> The type to preserve the first attribute of the object.
 * @param <T> The type to preserve the second attribute of the object.
 */
public interface SingleObjectInterface<E,T>
{
	/**
	 * Set the value of object.
	 * @param object The first attribute of the object.
	 */
	public void setObject(E object);
	/**
	 * Set the value of value.
	 * @param value The second attribute of the object.
	 */
	public void setValue(T value);
	/**
	 * Get the value of object.
	 * @return The value of object.
	 */
	public E getObject();
	/**
	 * Get the value of value.
	 * @return The value of value.
	 */
	public T getValue();
}
