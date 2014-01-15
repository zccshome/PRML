package me.zccshome.regression.dataset;

import java.util.ArrayList;

import me.zccshome.regression.data.IData;
import me.zccshome.regression.data.NaiveLinearData;
import me.zccshome.regression.data.SimpleLinearData;

public class Dataset {
	private ArrayList<IData> dataList;

	public Dataset() {
		super();
		this.dataList = new ArrayList<IData>();
		
		// test data
//		this.dataList.add(new LinearData(1, 2.1));
//		this.dataList.add(new LinearData(2, 4.1));
//		this.dataList.add(new LinearData(3, 5.9));
//		this.dataList.add(new LinearData(4, 8));
//		this.dataList.add(new LinearData(5, 9.8));
//		this.dataList.add(new LinearData(6, 11.8));
//		this.dataList.add(new LinearData(7, 14));
//		this.dataList.add(new LinearData(8, 16));
//		this.dataList.add(new LinearData(9, 18.1));
//		this.dataList.add(new LinearData(10, 20));
		double[] test1 = {1,1};
		double[] test2 = {1,2};
		double[] test3 = {1,3};
		double[] test4 = {2,1};
		double[] test5 = {2,2};
		double[] test6 = {2,3};
		double[] test7 = {3,1};
		double[] test8 = {3,2};
		double[] test9 = {3,3};
		double[] test10 = {4,1};
		this.dataList.add(new SimpleLinearData(test1, 2.1));
		this.dataList.add(new SimpleLinearData(test2, 3));
		this.dataList.add(new SimpleLinearData(test3, 3.9));
		this.dataList.add(new SimpleLinearData(test4, 2.9));
		this.dataList.add(new SimpleLinearData(test5, 4));
		this.dataList.add(new SimpleLinearData(test6, 5.1));
		this.dataList.add(new SimpleLinearData(test7, 3.9));
		this.dataList.add(new SimpleLinearData(test8, 5));
		this.dataList.add(new SimpleLinearData(test9, 6));
		this.dataList.add(new SimpleLinearData(test10, 5.1));
		
		// test data
//		if(this.dataList.get(0) instanceof LinearData) {
//			System.out.println(((LinearData)this.dataList.get(0)).getX());
//		}
	}

	public Dataset(ArrayList<IData> dataList) {
		super();
		this.dataList = dataList;
	}

	public ArrayList<IData> getDataList() {
		return dataList;
	}

	public void setDataList(ArrayList<IData> dataList) {
		this.dataList = dataList;
	}
	public static ArrayList<NaiveLinearData> translateToLinearData(ArrayList<IData> dataList) {
		ArrayList<NaiveLinearData> retList = new ArrayList<NaiveLinearData>();
		if(!(dataList != null && dataList.size() > 0 && dataList.get(0) instanceof NaiveLinearData))
			return retList;
		for(IData d: dataList)
			retList.add((NaiveLinearData)d);
		return retList;
	}
	public static ArrayList<SimpleLinearData> translateToLinearDataModal(ArrayList<IData> dataList) {
		ArrayList<SimpleLinearData> retList = new ArrayList<SimpleLinearData>();
		if(!(dataList != null && dataList.size() > 0 && dataList.get(0) instanceof SimpleLinearData))
			return retList;
		for(IData d: dataList)
			retList.add((SimpleLinearData)d);
		return retList;
	}
}
