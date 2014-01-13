package me.zccshome.decisionTree.decisionTree;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import javax.swing.JFileChooser;

/**
 * This is the class of the whole procedure of the decision tree lab.
 * 
 * @author Zhu Chengchun
 *
 */
public class DecisionTree
{
	/**
	 * The decision tree defined earlier in other class.
	 */
	DecisionTreeNode DTN = new DecisionTreeNode();
	/**
	 * To determine what percentage of examples are used as exercise examples.
	 */
	public double exercisePercentage = 0.2;
	/**
	 * To determine what percentage of examples are used as test examples to build
	 * the decision tree.
	 */
	public double testPercentage = 0.8;
	/**
	 * The whole data list when read.
	 */
	ArrayList<int[]> testDataList = new ArrayList<int[]>();
	/**
	 * The number of wrong predicates.
	 */
	int makeDecisionButWrongNum = 0;
	/**
	 * The number of right predicates.
	 */
	int makeDecisionAndRightNum = 0;
	/**
	 * The number of the situation that no predicates could make.
	 */
	int notMakeDecisionButCanMake = 0;
	/**
	 * The number of the situation that no predicates could make because of opposites 
	 * examples(not occurred in this lab).
	 */
	int notMakeDecisionAndCannotMake = 0;
	
	/**
	 * The construct method to determine the percentage.
	 * @param percentage The percentage to divide all the examples.
	 */
	public DecisionTree(double percentage)
	{
		exercisePercentage = percentage;
		testPercentage = 1 - percentage;
	}
	
	/**
	 * Read the statistics from outside files.
	 */
	public void readStatistics()
	{
		DecisionTreeData rootData = new DecisionTreeData();
		try
		{
			JFileChooser jc = new JFileChooser();
			jc.setCurrentDirectory(new File("."));
			jc.showOpenDialog(null);
			File file = jc.getSelectedFile();
			FileReader fr = new FileReader(file);
			BufferedReader br = new BufferedReader(fr);
			String tempStringLine = "";
			while((tempStringLine = br.readLine()).contains("@attribute"))
			{
				String[] tempS = tempStringLine.split(" ");
				DecisionTreeDataExchange.nameString.add(tempS[1]);
				tempS = tempS[2].substring(1, tempS[2].length()-1).split(",");
				DecisionTreeDataExchange.attributeDivideString.add(tempS);
			}
			DecisionTreeDataExchange.DATA_NUM = DecisionTreeDataExchange.nameString.size();
			DecisionTreeDataExchange.ATTRIBUTE_DIVISION_NUM = new int[DecisionTreeDataExchange.DATA_NUM];
			for(int i = 0; i < DecisionTreeDataExchange.DATA_NUM; i++)
				DecisionTreeDataExchange.ATTRIBUTE_DIVISION_NUM[i] = DecisionTreeDataExchange.attributeDivideString.get(i).length;
			//if(br.readLine().equals("@data"))
			{
				while((tempStringLine = br.readLine()) != null)
				{
					String[] dataString = tempStringLine.split(", ");
					int offset[] = new int[DecisionTreeDataExchange.DATA_NUM];
					for(int i = 0; i < dataString.length; i++)
					{
						offset[i] = DecisionTreeDataExchange.setString(i, dataString[i]);
					}
					rootData.dataList.add(offset);
				}
				br.close();
			}
			int size = rootData.dataList.size();
			int exerciseNum = (int)(size * testPercentage);
			for(int i = 0; i < exerciseNum; i++)
			{
				/*int index = (int)(Math.random() * rootData.dataList.size());
				testDataList.add(rootData.dataList.remove(index));*/
				testDataList.add(rootData.dataList.remove(0));
			}
			DTN.addRoot(rootData);
		}
		catch(Exception e)
		{
		}
	}
	/**
	 * To get the best division attribute each time.
	 * @param tempDTD The node that need to use this method to divide further.
	 * @return The integer expression of the best division attribute.
	 */
	public int getBestDecision(DecisionTreeData tempDTD)
	{
		double IDecision = getIDecision(tempDTD);
		double[] EGain = new double[DecisionTreeDataExchange.DATA_NUM - 1];
		for(int i = 0; i < DecisionTreeDataExchange.DATA_NUM - 1; i++)
			EGain[i] = IDecision - getEAttribute(tempDTD, i);
		double max = -1; int attribute = -1;
		for(int i = 0; i < EGain.length; i++)
		{
			if(EGain[i] > max)
			{
				max = EGain[i];
				attribute = i;
			}
		}
		if(max == 0)
			attribute = -1;
		return attribute;
	}
	
	/**
	 * To calculate the I(decision).
	 * @param tempDTD The node that need to use this method to divide further.
	 * @return The I(decision).
	 */
	public double getIDecision(DecisionTreeData tempDTD)
	{
		double answer = 0;
		int[] decisionCount = new int[DecisionTreeDataExchange.ATTRIBUTE_DIVISION_NUM[DecisionTreeDataExchange.DATA_NUM-1]];
		for(int i = 0; i < tempDTD.dataList.size(); i++)
			decisionCount[tempDTD.getData(i, DecisionTreeDataExchange.DATA_NUM-1)]++;
		for(int tempDC: decisionCount)
		{
			if(tempDC == 0)
				continue;
			double tempPercentage = (double)tempDC / tempDTD.dataList.size();
			answer = answer - tempPercentage * Math.log10(tempPercentage) / Math.log10(2);
		}
		return answer;
	}
	
	/**
	 * To calculate the E(attribute).
	 * @param tempDTD The node that need to use this method to divide further.
	 * @param index The number of the attribute in integer mode.
	 * @return E(attribute).
	 */
	public double getEAttribute(DecisionTreeData tempDTD, int index)
	{
		double answer = 0;
		int[][] decisionCount = new int[DecisionTreeDataExchange.ATTRIBUTE_DIVISION_NUM[index]][DecisionTreeDataExchange.ATTRIBUTE_DIVISION_NUM[DecisionTreeDataExchange.DATA_NUM-1]];
		int totalSize = tempDTD.dataList.size();
		int[] percentageSize = new int[decisionCount.length];
		for(int i = 0; i < tempDTD.dataList.size(); i++)
		{
			decisionCount[tempDTD.getData(i, index)][tempDTD.getData(i, DecisionTreeDataExchange.DATA_NUM-1)]++;
			percentageSize[tempDTD.getData(i, index)]++;
		}
		for(int i = 0; i < percentageSize.length; i++)
		{
			double tempAnswer = 0;
			for(int j = 0; j < DecisionTreeDataExchange.ATTRIBUTE_DIVISION_NUM[DecisionTreeDataExchange.DATA_NUM-1]; j++)
			{
				if(decisionCount[i][j] == 0)
					continue;
				double tempPercentage = (double)decisionCount[i][j] / percentageSize[i];
				tempAnswer = tempAnswer - tempPercentage * Math.log10(tempPercentage) / Math.log10(2);
			}
			answer = answer + tempAnswer * percentageSize[i] / totalSize;
		}
		return answer;
	}
	
	/**
	 * To build up the decision tree by recursion.
	 * @param topData The current node that need to develop down.
	 */
	public void setUpTree(DecisionTreeData topData)
	{
		int attribute = getBestDecision(topData);
		if(attribute == -1)
			return;
		topData.setDivideAttribute(attribute);
		for(int i = 0; i < DecisionTreeDataExchange.ATTRIBUTE_DIVISION_NUM[attribute]; i++)
		{
			DecisionTreeData childData = new DecisionTreeData();
			childData.setAttribute(i);
			int decision = -1; boolean perfectMatch = true;
			for(int[] tempInt: topData.dataList)
			{
				if(tempInt[attribute] == i)
				{
					childData.setAllData(tempInt);
					if(decision == -1)
						decision = tempInt[DecisionTreeDataExchange.DATA_NUM-1];
					if(decision != tempInt[DecisionTreeDataExchange.DATA_NUM-1])
						perfectMatch = false;
				}
			}
			if(perfectMatch == true)
				childData.setDecision(decision);
			if(childData.dataList.size() > 0)
				topData.childList.add(childData);
		}
		for(DecisionTreeData child: topData.childList)
		{
			if(child.getDivideAttribute() == -1 && child.getDecision() == -1)
				setUpTree(child);
		}
	}
	
	/**
	 * To print the built tree by recursion.
	 * @param topData The current node.
	 * @param layer The layer of the node.
	 * @param lastDivision How we divide the node and its siblings.
	 */
	public void showTree(DecisionTreeData topData, int layer, int lastDivision)
	{
		System.out.println("Layer: " + layer + " Division attribute: " + 
			DecisionTreeDataExchange.getAttributeName(topData.getDivideAttribute()) + 
			" Attribute: " + 
			DecisionTreeDataExchange.getCertainAttributeName(lastDivision, topData.getAttribute()) + 
			" Decision: " + 
			DecisionTreeDataExchange.getString(DecisionTreeDataExchange.DATA_NUM-1, topData.getDecision()) + 
			" DataListSize: " + topData.dataList.size());
		for(DecisionTreeData t: topData.childList)
			showTree(t, layer+1, topData.getDivideAttribute());
	}
	
	/**
	 * To test the correctness of the built decision tree by recursion.
	 * @param topData The current node.
	 * @param tempInt The test example.
	 */
	public void testTree(DecisionTreeData topData, int[] tempInt)
	{
		int divideAttribute = topData.getDivideAttribute();
		if(divideAttribute != -1 && topData.getDecision() == -1)
		{
			boolean has = false;
			for(DecisionTreeData t: topData.childList)
				if(t.getAttribute() == tempInt[divideAttribute])
				{
					//�и��ӽڵ㣬�ɷ�
					testTree(t, tempInt);
					has = true;
				}
			//ľ�и��ӽڵ�
			if(has == false)
				notMakeDecisionButCanMake++;
				//System.out.println("�޷������ж�");
		}
		else if(divideAttribute == -1 && topData.getDecision() == -1)
		{
			//����㣵���ʵ���޷������ж�
			//System.out.println("�޷������ж�");
			notMakeDecisionAndCannotMake++;
		}
		else if(divideAttribute == -1 && topData.getDecision() != -1)
		{
			int decision = topData.getDecision();
			int actualDecision = tempInt[DecisionTreeDataExchange.DATA_NUM-1];
			//System.out.println(decision == actualDecision);
			if(decision == actualDecision)
				makeDecisionAndRightNum++;
			else
				makeDecisionButWrongNum++;
		}
		else if(divideAttribute != -1 && topData.getDecision() != -1)
		{
			//�������
		}
	}
	
	/**
	 * The run method to start the procedure of a decision tree building and testing.
	 */
	public void run()
	{
		readStatistics();
		setUpTree(DTN.root);
		//showTree(DT.DTN.root, 0, -1);
		for(int[] t: testDataList)
			testTree(DTN.root, t);
		System.out.println("��ȷ�ʣ� "+ (double)makeDecisionAndRightNum/(makeDecisionAndRightNum + makeDecisionButWrongNum));
		System.out.println("�ٻ��ʣ� "+ (double)makeDecisionAndRightNum/(makeDecisionAndRightNum + notMakeDecisionButCanMake));
	}
	
	/**
	 * The main method.
	 * @param args Some environment variables.
	 */
	public static void main(String[] args)
	{
		DecisionTree DT = new DecisionTree(0.7);
		DT.run();
	}
}
