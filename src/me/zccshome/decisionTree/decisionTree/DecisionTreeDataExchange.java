package me.zcchome.decisionTree.decisionTree;

import java.util.ArrayList;

/**
 * Because I preserve the attribute information y integer, it is necessary to create a class
 * to convert integers to strings(if data is string) as well as strings to integer(I transform
 * the data and use a unique integer to represent it)
 * 
 * @author Zhu Chengchun
 *
 */

public class DecisionTreeDataExchange
{
	/**
	 * The number of attributes and decision.
	 */
	public static int DATA_NUM = -1;
	/**
	 * The length of each attribute choice as well as the decision.
	 */
	public static int[] ATTRIBUTE_DIVISION_NUM = {};
	/**
	 * All choices of attributes, represented by string[].
	 */
	public static ArrayList<String[]> attributeDivideString = new ArrayList<String[]>();
	/**
	 * All attribute names, represented by string.
	 */
	public static ArrayList<String> nameString = new ArrayList<String>();
	
	/**
	 * To get the string representation of the data's attribute choice of the index's 
	 * attribute/decision.
	 * @param index The number of attribute.
	 * @param data The i-th choice of an attribute.
	 * @return The string representation of the choice of an attribute.
	 */
	public static String getString(int index, int data)
	{
		if(index >= 0 && index < DATA_NUM && data >= 0 && data < ATTRIBUTE_DIVISION_NUM[index])
			return attributeDivideString.get(index)[data];
		else
			return "";
	}
	
	/**
	 * To convert the string of a choice of an attribute into an integer.
	 * @param index The number of attribute.
	 * @param data The i-th choice of an attribute.
	 * @return The integer representation of the choice of an attribute.
	 */
	public static int setString(int index, String data)
	{
		for(int i = 0; i < attributeDivideString.get(index).length; i++)
		{
			if(attributeDivideString.get(index)[i].equals(data) || data.contains(attributeDivideString.get(index)[i]))
			{
				return i;
			}
		}
		return -1;
	}
	
	/**
	 * To get the string representation of the index's attribute name.
	 * @param index The number of attribute.
	 * @return The string representation of an attribute name.
	 */
	public static String getAttributeName(int index)
	{
		if(index >= 0 && index < DATA_NUM)
			return nameString.get(index);
		else
			return "";
	}
	
	/**
	 * To get the attribute name and its choice.
	 * @param index The number of attribute.
	 * @param data The i-th choice of an attribute.
	 * @return A pretty output of the information of the whole attribute.
	 */
	public static String getCertainAttributeName(int index, int data)
	{
		if(index >= 0 && index < DATA_NUM)
			return nameString.get(index) + getString(index, data);
		else
			return "";
	}
}
