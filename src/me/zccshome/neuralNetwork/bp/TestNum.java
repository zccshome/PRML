package me.zcchome.neuralNetwork.bp;

import java.util.ArrayList;

public class TestNum
{
	public static void main(String[] args)
	{
		ReadData.readFile(ReadData.getFile("testcase/neuralNetwork/testFile/NumberLearning.txt"));
		BP bp = new BP();
		bp.initializeNetwork();
		int size = ReadData.inputData.size();
		//System.out.println(size);
		for(int i = 0; i < size; i++)
		{
			double[] answer = bp.buildBPNetwork(i);
			bp.modifyWeight3(answer, i);
		}
		ReadData.inputData = new ArrayList<String[]>();
		ReadData.outputData = new ArrayList<String[]>();
		ReadData.readFile(ReadData.getFile("testcase/neuralNetwork/testFile/NumberTest.txt"));
		size = ReadData.inputData.size();
		//System.out.println(size);
		int correctNumber = 0;
		for(int i = 0; i < size; i++)
		{
			double[] answer = bp.buildBPNetwork(i);
			double max = -1;
			double guess = -1;
			for(int j = 0; j < answer.length; j++)
			{
				//System.out.print(answer[j] + " "+Double.parseDouble(ReadData.outputData.get(i)[j]));
				if(answer[j] > max)
				{
					max = answer[j];
					guess = j;
				}
			}
			max = -1;
			double actual = -1;
			for(int j = 0; j < ReadData.outputLayer; j++)
			{
				if(Double.parseDouble(ReadData.outputData.get(i)[j]) > max)
				{
					max = Double.parseDouble(ReadData.outputData.get(i)[j]);
					actual = j;
				}
			}
			if(guess == actual)
				correctNumber++;
		}
		System.out.println("��ȷԤ����Ŀ��"+correctNumber);
		System.out.println("��ȷԤ���ʣ�"+((double)correctNumber) / ReadData.inputData.size());
	}
}
