package me.zccshome.parameterEstimation;

public class MomentEstimation {
	private Data data;
	
	public MomentEstimation() {
		data = new Data();
	}
	
	public double estimationU() {
		// E[x] = sum / size
		double u = 0;
		int size = data.getData().size();
		for(int i = 0; i < size; i++)
			u += data.getData().get(i);
//		System.out.println(u / size);
		return u / size;
	}
	
	public double estimationSigma() {
		// D[x] = E[x^2] - E[x]^2
		double u = 0;
		int size = data.getData().size();
		for(int i = 0; i < size; i++)
			u += Math.pow(data.getData().get(i), 2);;
//		System.out.println(u / size - Math.pow(estimationU(), 2));
		return u / size - Math.pow(estimationU(), 2);
	}
	
	public Data getData() {
		return data;
	}
	public void setData(Data data) {
		this.data = data;
	}
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		MomentEstimation me = new MomentEstimation();
		me.estimationU();
		me.estimationSigma();
	}

}
