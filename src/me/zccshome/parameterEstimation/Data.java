package me.zccshome.parameterEstimation;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Data {
	private List<Double> data;
	
	public Data() {
		data = new ArrayList<Double>();
		for(int i = 0; i < 1000; i++) {
			data.add(nextGaussion(2, 1));
		}
	}
	public double nextGaussion(double u, double sigma) {
		Random random = new Random();
		double num = random.nextGaussian();
//		System.out.println(num);
		return num * sigma + u;
	}
	
	public List<Double> getData() {
		return data;
	}

	public void setData(List<Double> data) {
		this.data = data;
	}
}
