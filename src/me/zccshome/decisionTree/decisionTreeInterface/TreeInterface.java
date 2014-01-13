package me.zccshome.decisionTree.decisionTreeInterface;

import java.util.ArrayList;

/**
 * This interface is used to describe the decision tree and some methods to operate the tree.
 * 
 * @author Zhu Chengchun
 *
 * @param <E> The type of the node.
 */

public interface TreeInterface<E>
{
	/**
	 * To add the root to the tree.
	 * @param tempNode The root.
	 */
	public void addRoot(E tempNode);
	/**
	 * To add a child to a certain node.
	 * @param topNode A certain node.
	 * @param childNode The child node.
	 */
	public void addChild(E topNode, E childNode);
	/**
	 * To get the root node.
	 * @return The root node.
	 */
	public E getRoot();
	/**
	 * To get all the children of a certain node.
	 * @param topNode The father node.
	 * @return All the children by an arrayList.
	 */
	public ArrayList<E> getAllChild(E topNode);
	/**
	 * To get a certain child of a father node.
	 * @param topNode The father node.
	 * @param index the offset of the child.
	 * @return The child node.
	 */
	public E getChild(E topNode, int index);
	/**
	 * To get the size of the tree.
	 * @return The size of the tree.
	 */
	public int getSize();
	/**
	 * To print the tree for debugging.
	 */
	public void print();
}
