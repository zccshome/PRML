package me.zcchome.regression;

import java.util.ArrayList;
import java.util.List;

import me.zcchome.data.SimpleLinearData;
import me.zcchome.dataset.Dataset;

public class SimpleGradientDescent {
	private List<SimpleLinearData> dataList;
	private List<Double> w;
	private static final double GAMMA = 0.0001;
	private static final double LAMDA = 0.0001;
	
	public SimpleGradientDescent() {
		super();
		Dataset dataset = new Dataset();
		this.dataList = Dataset.translateToLinearDataModal(dataset.getDataList());
		w = new ArrayList<Double>();
		for(int i = 0; i <= this.dataList.get(0).getX().size(); i++){
			double ki = Math.random();
			w.add(ki);
		}
	}
	
	public void linearRegressionModel() {
		double loss = getLoss();
//		System.out.println(loss);
		double adjust = 0;
		for(int i = 0; i < 10000; i++) {
			for(int j = 1; j < w.size(); j++) {
				for(SimpleLinearData data: dataList) {
					adjust += -data.getX().get(j-1);
				}
				adjust = GAMMA * getError() * adjust + LAMDA * w.get(j);
				w.set(j, w.get(j) - adjust);
//				System.out.println(getError());
			}
			w.set(0, GAMMA * getError() + LAMDA * w.get(0));
		}
		System.out.println("k= "+w);
	}
	
	public double getLoss() {
		double loss = 0;
		for(SimpleLinearData data: dataList) {
			double t = data.getY() - w.get(0);
			for(int i = 1; i <= data.getX().size(); i++) {
				t -= w.get(i) * data.getX().get(i-1);
			}
			loss += Math.pow(t, 2);
		}
		loss = GAMMA * loss;
		for(double k: w) 
			loss += LAMDA * Math.pow(k, 2);
		return loss;
	}
	
	public double getError() {
		double loss = 0;
		for(SimpleLinearData data: dataList) {
			double t = data.getY() - w.get(0);
			for(int i = 1; i <= data.getX().size(); i++) {
				t -= w.get(i) * data.getX().get(i-1);
			}
			loss += Math.pow(t, 1);
		}
		return loss;
	}

	public List<SimpleLinearData> getDataList() {
		return dataList;
	}

	public void setDataList(List<SimpleLinearData> dataList) {
		this.dataList = dataList;
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		SimpleGradientDescent gd = new SimpleGradientDescent();
		gd.linearRegressionModel();
//		for(LinearData d: mle.getDataList())
//			System.out.println(d);
	}

}
