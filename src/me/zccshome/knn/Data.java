package me.zccshome.knn;

public class Data {
	private int[] featureList;

	public Data(int feature_num) {
		super();
		featureList = new int[feature_num];
	}
	
	public Data(int[] featureList) {
		super();
		this.featureList = featureList;
	}

	public int[] getFeatureList() {
		return featureList;
	}

	public void setFeatureList(int[] featureList) {
		this.featureList = featureList;
	}
	
}
