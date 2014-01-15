package me.zccshome.parameterEstimation;

import java.util.ArrayList;
import java.util.List;

public class EMMultivariateNormalMixtures {
	private Data data;
	private List<Double> pie;
	private List<Double> u;
	private List<Double> sigma;
	public static final int DISTRIBUTION_NUM = 3;
	private double[][] Zij;
	
	public EMMultivariateNormalMixtures() {
		super();
		data = new Data();
		pie = new ArrayList<Double>();
		u = new ArrayList<Double>();
		sigma = new ArrayList<Double>();
		Zij = new double[DISTRIBUTION_NUM][data.getData().size()];
//		for(int i = 0; i < DISTRIBUTION_NUM; i++) {
//			u.add(Math.random());
//			sigma.add(Math.random());
//			pie.add(Math.random());
//		}
		u.add(2.1);
		u.add(3.0);
		u.add(3.9);
		sigma.add(0.01);
		sigma.add(0.01);
		sigma.add(0.01);
		pie.add(0.2);
		pie.add(0.4);
		pie.add(0.4);
	}
	public void run() {
		int size = data.getData().size();
		for(int i = 0; i < 100; i++) {
			// E step
			calculateZ();
			// M step
			for(int j = 0; j < DISTRIBUTION_NUM; j++) {
//				System.out.println(calculateTi(j, 0) / size);
//				System.out.println(calculateTi(j, 1) / calculateTi(j, 0));
//				System.out.println((calculateTi(j, 2)-calculateTi(j, 1) * calculateTi(j, 1) / calculateTi(j, 0)) / calculateTi(j, 0));
//				System.out.println();
				pie.set(j, calculateTi(j, 0) / size);
				u.set(j, calculateTi(j, 1) / calculateTi(j, 0));
				sigma.set(j, (calculateTi(j, 2)-calculateTi(j, 1) * calculateTi(j, 1) / calculateTi(j, 0)) / calculateTi(j, 0));
			}
		}
		for(int j = 0; j < DISTRIBUTION_NUM; j++) {
			System.out.println("pie"+j+"="+pie.get(j));
			System.out.println("u"+j+"="+u.get(j));
			System.out.println("sigma"+j+"="+sigma.get(j));
		}
	}
	public void calculateZ() {
		for(int i = 0; i < DISTRIBUTION_NUM; i++)
			for(int j = 0; j < data.getData().size(); j++)
				Zij[i][j] = calculateZij(i,j);
	}
	public double calculateTi(int i, int pow) {
		double ans = 0;
		int size = data.getData().size();
		for(int k = 0; k < size; k++) {
			ans += Zij[i][k] * Math.pow(data.getData().get(k), pow);
		}
		return ans;
	}
	public double calculateZij(int i, int j) {
		double a = pie.get(i) * getProbability(data.getData().get(j), u.get(i), sigma.get(i));
		double b = 0;
		for(int k = 0; k < DISTRIBUTION_NUM; k++) {
			//System.out.println("pppppppppppppp"+getProbability(data.getData().get(k), u.get(i), sigma.get(i)));
			b += pie.get(k) * getProbability(data.getData().get(j), u.get(k), sigma.get(k));
		}
		//System.out.println("Z"+i+j+"="+a / b);
		return a / b;
	}
	
	public double getProbability(double value, double u, double sigma) {
		double ans = Math.pow(Math.sqrt(2 * Math.PI), -1) * sigma * Math.pow(Math.E, Math.pow(value - u, 2) / -2 / sigma / sigma);
		return ans;
	}
	
	public Data getData() {
		return data;
	}
	public void setData(Data data) {
		this.data = data;
	}
	public List<Double> getPie() {
		return pie;
	}
	public void setPie(List<Double> pie) {
		this.pie = pie;
	}
	public List<Double> getU() {
		return u;
	}
	public void setU(List<Double> u) {
		this.u = u;
	}
	public List<Double> getSigma() {
		return sigma;
	}
	public void setSigma(List<Double> sigma) {
		this.sigma = sigma;
	}
	public double[][] getZij() {
		return Zij;
	}

	public void setZij(double[][] zij) {
		Zij = zij;
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		EMMultivariateNormalMixtures em = new EMMultivariateNormalMixtures();
		em.run();
	}

}
