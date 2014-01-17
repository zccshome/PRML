package me.zccshome.knn;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class KNN {
	private List<Data> datasetList;
	
	public KNN() {
		Dataset dsDataset = new Dataset();
		datasetList = dsDataset.getDatasetList();
	}
	
	public void knn(int data_id, int k) {
		Data data = datasetList.get(data_id);
		List<Similarity> similarityList = new ArrayList<Similarity>();
		for(int i = 0; i < datasetList.size(); i++) {
			double sim_i = calculateSim(data, datasetList.get(i));
			Similarity similarity = new Similarity(datasetList.get(i), sim_i);
			similarityList.add(similarity);
		}
		//Collections.sort(similarityList);
		sort(similarityList, 0, similarityList.size()-1);
		for(int i = 0; i < Math.min(k, datasetList.size()); i++) {
			for(int f: similarityList.get(i).getData().getFeatureList())
			System.out.print(f+" ");
			System.out.println(" "+similarityList.get(i).getSimilarity());
		}
	}
	
	public double calculateSim(Data d1, Data d2) {
		double sim = 0;
		for(int i = 0; i < d1.getFeatureList().length; i++) {
			
			int f1 = d1.getFeatureList()[i];
			int f2 = d2.getFeatureList()[i];
			sim += Math.pow(f1 - f2, 2);
		}
		return Math.sqrt(sim);
	}
	
	private void sort(List<Similarity> list, int start, int end) {
		int middle;
		int i = start;
		int j = end;
		middle = (i + j) / 2;
		while(i < j) {
			while(list.get(i).compareTo(list.get(middle)) < 0 && (i < end))
				i++;
			while(list.get(j).compareTo(list.get(middle)) > 0 && (j > start))
				j--;
			if(i <= j) {
				Similarity temp = list.get(i);
				list.set(i, list.get(j));
				list.set(j, temp);
				i++;
				j--;
			}
		  }
		  if(start < j)
			  sort(list, start, j);
		  if(end > i)
			  sort(list, i, end);
	}
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		KNN knn = new KNN();
		knn.knn(0,3);
	}

}
