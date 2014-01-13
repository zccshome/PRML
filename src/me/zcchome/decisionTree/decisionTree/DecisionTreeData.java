package me.zcchome.decisionTree.decisionTree;

import java.util.ArrayList;
import me.zcchome.decisionTree.decisionTreeInterface.DecisionTreeDataInterface;

/**
 * To implement the node data type of the decision tree.
 * 
 * @author Zhu Chengchun
 *
 */

public class DecisionTreeData implements DecisionTreeDataInterface
{
	/**
	 * The examples that fits the decision from root to the current node.
	 */
	public ArrayList<int[]> dataList = new ArrayList<int[]>();
	/**
	 * An arrayList of children.
	 */
	ArrayList<DecisionTreeData> childList = new ArrayList<DecisionTreeData>();
	/**
	 * To describe by which attribute we divide the example.
	 */
	int divideAttribute = -1;
	/**
	 * To describe what attribute it is from the father node's division.
	 */
	int decision = -1;
	/**
	 * To describe the decision of the node.
	 */
	int attribute = -1;
	
	/**
	 * (non-Javadoc)
	 * @see decisionTreeInterface.DecisionTreeDataInterface#setAllData(int[])
	 */
	@Override
	public void setAllData(int[] data)
	{
		// TODO Auto-generated method stub
		dataList.add(data);
	}

	/**
	 * (non-Javadoc)
	 * @see decisionTreeInterface.DecisionTreeDataInterface#setData(int, int, int)
	 */
	@Override
	public void setData(int data, int index, int offset)
	{
		// TODO Auto-generated method stub
		dataList.get(index)[offset] = data;
	}

	/**
	 * (non-Javadoc)
	 * @see decisionTreeInterface.DecisionTreeDataInterface#getAllData(int)
	 */
	@Override
	public int[] getAllData(int index)
	{
		// TODO Auto-generated method stub
		return dataList.get(index);
	}

	/**
	 * (non-Javadoc)
	 * @see decisionTreeInterface.DecisionTreeDataInterface#getData(int, int)
	 */
	@Override
	public int getData(int index, int offset)
	{
		// TODO Auto-generated method stub
		return dataList.get(index)[offset];
	}
	
	/**
	 * To print the data list of the node pretty.
	 */
	public String DataToString(int index)
	{
		StringBuffer returnString = new StringBuffer("");
		for(int i = 0; i < DecisionTreeDataExchange.DATA_NUM; i++)
		{
			returnString.append(DecisionTreeDataExchange.getString(i, dataList.get(index)[i]) + ", ");
		}
		return returnString.toString();
	}

	/**
	 * (non-Javadoc)
	 * @see decisionTreeInterface.DecisionTreeDataInterface#setDivideAttribute(int)
	 */
	@Override
	public void setDivideAttribute(int attribute)
	{
		// TODO Auto-generated method stub
		divideAttribute = attribute;
	}

	/**
	 * (non-Javadoc)
	 * @see decisionTreeInterface.DecisionTreeDataInterface#getDivideAttribute()
	 */
	@Override
	public int getDivideAttribute()
	{
		// TODO Auto-generated method stub
		return divideAttribute;
	}

	/**
	 * (non-Javadoc)
	 * @see decisionTreeInterface.DecisionTreeDataInterface#setDecision(int)
	 */
	@Override
	public void setDecision(int decision)
	{
		// TODO Auto-generated method stub
		this.decision = decision;
	}

	/**
	 * (non-Javadoc)
	 * @see decisionTreeInterface.DecisionTreeDataInterface#getDecision()
	 */
	@Override
	public int getDecision()
	{
		// TODO Auto-generated method stub
		return decision;
	}

	/**
	 * (non-Javadoc)
	 * @see decisionTreeInterface.DecisionTreeDataInterface#setAttribute(int)
	 */
	@Override
	public void setAttribute(int attribute) {
		// TODO Auto-generated method stub
		this.attribute = attribute;
	}

	/**
	 * (non-Javadoc)
	 * @see decisionTreeInterface.DecisionTreeDataInterface#getAttribute()
	 */
	@Override
	public int getAttribute() {
		// TODO Auto-generated method stub
		return attribute;
	}
}
