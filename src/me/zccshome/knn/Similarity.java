package me.zccshome.knn;

public class Similarity implements Comparable<Similarity>{
	private Data data;
	private double similarity;
	public Similarity(Data data, double similarity) {
		super();
		this.data = data;
		this.similarity = similarity;
	}
	public Data getData() {
		return data;
	}
	public void setData(Data data) {
		this.data = data;
	}
	public double getSimilarity() {
		return similarity;
	}
	public void setSimilarity(double similarity) {
		this.similarity = similarity;
	}
	@Override
	public int compareTo(Similarity o) {
		if(this.similarity < o.similarity)
			return 1;
		else if(this.similarity > o.similarity)
			return -1;
		return 0;
//		return this.similarity < o.similarity ? 1 : -1;
	}
	
}
