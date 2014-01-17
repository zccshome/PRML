package me.zccshome.knn;

import java.util.ArrayList;
import java.util.List;

public class Dataset {
	private List<Data> datasetList;

	public Dataset() {
		super();
		datasetList = new ArrayList<Data>();
		int[] data1 = {1,1,1,1,1,1};
		int[] data2 = {1,2,1,1,1,2};
		int[] data3 = {1,2,1,1,1,3};
		int[] data4 = {3,1,2,1,2,1};
		int[] data5 = {1,1,4,1,3,1};
		int[] data6 = {2,3,1,2,1,2};
		int[] data7 = {4,4,1,2,1,1};
		int[] data8 = {1,2,1,3,4,3};
		int[] data9 = {3,3,1,2,3,3};
//		int[] data10 = {1,1,1,1,1,1};
//		int[] data11 = {1,1,1,1,1,1};
//		int[] data12 = {1,1,1,1,1,1};
//		int[] data13 = {1,1,1,1,1,1};
//		int[] data14 = {1,1,1,1,1,1};
//		int[] data15 = {1,1,1,1,1,1};
//		int[] data16 = {1,1,1,1,1,1};
//		int[] data17 = {1,1,1,1,1,1};
//		int[] data18 = {1,1,1,1,1,1};
//		int[] data19 = {1,1,1,1,1,1};
//		int[] data20 = {1,1,1,1,1,1};
		Data d = new Data(data1);
		datasetList.add(d);
		d = new Data(data2);
		datasetList.add(d);
		d = new Data(data3);
		datasetList.add(d);
		d = new Data(data4);
		datasetList.add(d);
		d = new Data(data5);
		datasetList.add(d);
		d = new Data(data6);
		datasetList.add(d);
		d = new Data(data7);
		datasetList.add(d);
		d = new Data(data8);
		datasetList.add(d);
		d = new Data(data9);
		datasetList.add(d);
//		d = new Data(data10.length);
//		datasetList.add(d);
//		d = new Data(data11.length);
//		datasetList.add(d);
//		d = new Data(data12.length);
//		datasetList.add(d);
//		d = new Data(data13.length);
//		datasetList.add(d);
//		d = new Data(data14.length);
//		datasetList.add(d);
//		d = new Data(data15.length);
//		datasetList.add(d);
//		d = new Data(data16.length);
//		datasetList.add(d);
//		d = new Data(data17.length);
//		datasetList.add(d);
//		d = new Data(data18.length);
//		datasetList.add(d);
//		d = new Data(data19.length);
//		datasetList.add(d);
//		d = new Data(data20.length);
//		datasetList.add(d);
	}

	public List<Data> getDatasetList() {
		return datasetList;
	}

	public void setDatasetList(List<Data> datasetList) {
		this.datasetList = datasetList;
	}
}
