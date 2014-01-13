package me.zcchome.decisionTree.decisionTree;

import java.util.ArrayList;
import me.zcchome.decisionTree.decisionTreeInterface.TreeInterface;

/**
 * To implement the tree node of the decision tree and supplementing some methods.
 * 
 * @author Zhu Chengchun
 *
 */

public class DecisionTreeNode implements TreeInterface<DecisionTreeData>
{
	/**
	 * The size of the tree.
	 */
	public int size = 0;
	/**
	 * The root of the tree.
	 */
	public DecisionTreeData root;
	
	/**
	 * (non-Javadoc)
	 * @see decisionTreeInterface.TreeInterface#addRoot(java.lang.Object)
	 */
	@Override
	public void addRoot(DecisionTreeData tempNode)
	{
		// TODO Auto-generated method stub
		root = tempNode;
		size++;
	}

	/**
	 * (non-Javadoc)
	 * @see decisionTreeInterface.TreeInterface#addChild(java.lang.Object, java.lang.Object)
	 */
	@Override
	public void addChild(DecisionTreeData topNode, DecisionTreeData childNode)
	{
		// TODO Auto-generated method stub
		topNode.childList.add(childNode);
		size++;
	}

	/**
	 * (non-Javadoc)
	 * @see decisionTreeInterface.TreeInterface#getRoot()
	 */
	@Override
	public DecisionTreeData getRoot()
	{
		// TODO Auto-generated method stub
		return root;
	}

	/**
	 * (non-Javadoc)
	 * @see decisionTreeInterface.TreeInterface#getAllChild(java.lang.Object)
	 */
	@Override
	public ArrayList<DecisionTreeData> getAllChild(DecisionTreeData topNode)
	{
		// TODO Auto-generated method stub
		return topNode.childList;
	}

	/**
	 * (non-Javadoc)
	 * @see decisionTreeInterface.TreeInterface#getChild(java.lang.Object, int)
	 */
	@Override
	public DecisionTreeData getChild(DecisionTreeData topNode, int index)
	{
		// TODO Auto-generated method stub
		return topNode.childList.get(index);
	}

	/**
	 * (non-Javadoc)
	 * @see decisionTreeInterface.TreeInterface#getSize()
	 */
	@Override
	public int getSize() {
		// TODO Auto-generated method stub
		return size;
	}

	/**
	 * (non-Javadoc)
	 * @see decisionTreeInterface.TreeInterface#print()
	 */
	@Override
	public void print()
	{
		// TODO Auto-generated method stub
		
	}
}
