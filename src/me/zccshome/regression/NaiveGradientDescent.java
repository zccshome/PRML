package me.zccshome.regression;

import java.util.ArrayList;

import me.zccshome.data.NaiveLinearData;
import me.zccshome.dataset.Dataset;

public class NaiveGradientDescent {
	private ArrayList<NaiveLinearData> dataList;
	private double k;
	private static final double GAMMA = 0.0001;
	private static final double LAMDA = 0.0001;
	
	public NaiveGradientDescent() {
		super();
		Dataset dataset = new Dataset();
		this.dataList = Dataset.translateToLinearData(dataset.getDataList());
		k = Math.random();
	}
	
	public void linearRegressionModel() {
//		double loss = getLoss();
		double adjust = 0;
		for(int i = 0; i < 1000; i++) {
			for(NaiveLinearData data: dataList) {
				adjust += (k * Math.pow(data.getX(), 2) - data.getX() * data.getY());
			}
			adjust  = GAMMA * adjust + LAMDA * k;
//			System.out.println("adjust= "+adjust);
			k -= adjust;
		}
		System.out.println("k= "+k);
	}
	
	public double getLoss() {
		double loss = 0;
		for(NaiveLinearData data: dataList) {
			loss += Math.pow(data.getY() - k * data.getX(), 2);
		}
		
		return GAMMA * loss + LAMDA * Math.pow(k, 2);
	}
	
	public double getError() {
		double loss = 0;
		for(NaiveLinearData data: dataList) {
			loss += Math.pow(data.getY() - k * data.getX(), 1);
		}
		return loss;
	}

	public ArrayList<NaiveLinearData> getDataList() {
		return dataList;
	}

	public void setDataList(ArrayList<NaiveLinearData> dataList) {
		this.dataList = dataList;
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		NaiveGradientDescent gd = new NaiveGradientDescent();
		gd.linearRegressionModel();
//		for(LinearData d: mle.getDataList())
//			System.out.println(d);
	}

}
