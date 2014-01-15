package me.zccshome.parameterEstimation;

import java.util.ArrayList;
import java.util.List;

public class EMGMM {
	private Data data;
	private List<Double> pie;
	private List<Double> u;
	private List<Double> sigma;
	public static final int DISTRIBUTION_NUM = 3;
	private double[][] Zij;
	
	public EMGMM() {
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
		u.add(2.3);
		u.add(3.3);
		u.add(3.7);
		sigma.add(0.01);
		sigma.add(0.01);
		sigma.add(0.01);
		pie.add(0.2);
		pie.add(0.6);
		pie.add(0.2);
	}
	public void run() {
		for(int i = 0; i < 100; i++) {
			// E step
			calculateZ();
			// M step
			for(int j = 0; j < DISTRIBUTION_NUM; j++) {
				pie.set(j, calculatePie(j));
				u.set(j, calculateU(j));
				sigma.set(j, calculateSigma(j));
//				for(int k = 0; k < DISTRIBUTION_NUM; k++) {
//					System.out.println("pie"+k+"="+pie.get(k));
//					System.out.println("u"+k+"="+u.get(k));
//					System.out.println("sigma"+k+"="+sigma.get(k));
//				}
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
	public double calculatePie(int i) {
		double ans = 0;
		int size = data.getData().size();
		for(int k = 0; k < size; k++) {
			ans += Zij[i][k];
		}
		return ans / size;
	}
	public double calculateU(int i) {
		double ans1 = 0;
		double ans2 = 0;
		int size = data.getData().size();
		for(int k = 0; k < size; k++) {
			ans1 += Zij[i][k] * data.getData().get(k);
			ans2 += Zij[i][k];
		}
		return ans1 / ans2;
	}
	public double calculateSigma(int i) {
		double ans1 = 0;
		double ans2 = 0;
		int size = data.getData().size();
		for(int k = 0; k < size; k++) {
			ans1 += Zij[i][k] * Math.pow(data.getData().get(k) - u.get(i), 2);
			ans2 += Zij[i][k];
		}
		return Math.sqrt(ans1 / ans2);
	}
	public double calculateZij(int i, int j) {
		double a = pie.get(i) * getProbability(data.getData().get(j), u.get(i), sigma.get(i));
		double b = 0.0;
		for(int k = 0; k < DISTRIBUTION_NUM; k++) {
			b += pie.get(k) * getProbability(data.getData().get(j), u.get(k), sigma.get(k));
		}
		if(a==0) return 0;
		return b == 0 ? 1.0:a / b;
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
		EMGMM em = new EMGMM();
		em.run();
	}

}
