package me.zccshome.neuralNetwork.bp;

public class TestSin
{
	public static void main(String[] args)
	{
		ReadData.readFile(ReadData.getFile("testcase/neuralNetwork/testFile/SinLearning.txt"));
		BP bp = new BP();
		bp.initializeNetwork();
		int size = ReadData.inputData.size();
		for(int i = 0; i < size * 10000; i++)
		{
			double[] answer = bp.buildBPNetwork(i%size);
			bp.modifyWeight3(answer, i%size);
		}
		for(int i = 0; i < size; i++)
		{
			double[] answer = bp.buildBPNetwork(i);
			for(int j = 0; j < answer.length; j++)
			{
				double actual = Double.parseDouble(ReadData.outputData.get(i)[j]);
				System.out.print("��ȷֵ�� "+actual + " Ԥ��ֵ��" +answer[j]);
				System.out.print(" ƫ� "+(actual-answer[j])+" ƫ���ʣ�"+((actual-answer[j])/answer[j]));
			}
			System.out.println();
		}
	}
}
