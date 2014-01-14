package me.zccshome.parameterEstimation;

public class EM {
	private Data data;
	private double u;
	private double sigma;
	
	public EM() {
		data = new Data();
		u = Math.random();
		sigma = Math.random();
		//u = 2;
		//sigma = 1;
	}
	// sigma ^ 2 = (X - u)^2 / n
	public void run() {
		for(int i = 0; i < 100; i++) {
			// E step
			sigma = calculateNewSigma();
			// M step
			u = calculateNewU();
			//System.out.println("u= "+u+"; sigma= "+sigma);
			
		}
		System.out.println("u= "+u+"; sigma= "+sigma);
	}
	
	public double getProbability(double value) {
		double ans = Math.pow(Math.sqrt(2 * Math.PI), -1) * sigma * Math.pow(Math.E, Math.pow(value - u, 2) / -2 / sigma / sigma);
		return ans;
	}
	
	public double getMaximunLikelihoodEstimation() {
		double ans = 0;
		for(int i = 0; i < data.getData().size(); i++)
			ans *= getProbability(data.getData().get(i));
		return ans;
	}
	
	public double calculateNewSigma() {
		double ans = 0;
		for(int i = 0; i < data.getData().size(); i++)
			ans += Math.pow(data.getData().get(i) - u, 2);
		return Math.sqrt(ans / data.getData().size());
	}
	
	public double calculateNewU() {
		double d1 = 0, d2 = 0;
		int size = data.getData().size();
		for(int i = 0; i < size; i++) {
			d1 += data.getData().get(i);
			d2 += Math.pow(data.getData().get(i), 2);
		}
		double b24ac = d1*d1*-size*(d2-size*sigma*sigma);
		b24ac = b24ac < 0 ? 0 : b24ac;
		return (d1  - Math.sqrt(b24ac)) / size;
	}
	
	public Data getData() {
		return data;
	}
	
	public void setData(Data data) {
		this.data = data;
	}
	
	public double getU() {
		return u;
	}

	public void setU(double u) {
		this.u = u;
	}

	public double getSigma() {
		return sigma;
	}

	public void setSigma(double sigma) {
		this.sigma = sigma;
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		EM mle = new EM();
		mle.run();
	}

}
