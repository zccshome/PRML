package me.zcchome.decisionTree.decisionTreeInterface;

/**
 * This interface is used to determine the structure of each node in the decision tree
 * and some methods to deal with the data of the node.
 * 
 * @author Zhu Chengchun
 *
 */
public interface DecisionTreeDataInterface
{
	/**
	 * Set an example into a node, typically a series integers that determine all attributes
	 * and the corresponding decision.
	 * 
	 * @param data All attributes and the corresponding decision ,represented by int[].
	 */
	public void setAllData(int[] data);
	/**
	 * Set a datum of a certain example into the node.
	 * @param data Certain attribute or a decision.
	 * @param index To insert into which example.
	 * @param offset The offset in the example, that is, to insert into int[offset].
	 */
	public void setData(int data, int index, int offset);
	/**
	 * Get all the data of a certain example of the node.
	 * @param index The index of the example in the arrayList.
	 * @return All the attributes and the decision of the corresponding example.
	 */
	public int[] getAllData(int index);
	/**
	 * Get certain data of a certain example of the node.
	 * @param index The index of the example in the arrayList.
	 * @param offset The offset in the example.
	 * @return Certain data of a certain example of the node.
	 */
	public int getData(int index, int offset);
	/**
	 * To set the division attribute.
	 * @param attribute The division attribute.
	 */
	public void setDivideAttribute(int attribute);
	/**
	 * To get the division attribute.
	 * @return The division attribute.
	 */
	public int getDivideAttribute();
	/**
	 * To set the attribute.
	 * @param attribute The attribute.
	 */
	public void setAttribute(int attribute);
	/**
	 * To get the attribute.
	 * @return The attribute.
	 */
	public int getAttribute();
	/**
	 * To set the decision.
	 * @param decision The decision.
	 */
	public void setDecision(int decision);
	/**
	 * To get the decision.
	 * @return The decision.
	 */
	public int getDecision();
}
