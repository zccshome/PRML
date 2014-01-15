package me.zccshome.kmeans;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import me.zccshome.kmeans.data.Data;
import me.zccshome.kmeans.data.Point;

public class KMeans {
	private Map<Point, Integer> classificationMap;
	private List<Point> centerList;
	
	public KMeans() {
		super();
		classificationMap = new HashMap<Point, Integer>();
		Data data = new Data();
		for(Point p: data.getPointList())
			classificationMap.put(p, -1);
		centerList = new ArrayList<Point>();
		centerList.add(new Point(Math.random(), Math.random()));
		centerList.add(new Point(Math.random(), Math.random()));
	}
	public void run() {
		// E step
		double[] distance = new double[centerList.size()];
		for(Point p: classificationMap.keySet()) {
			for(int i = 0; i < centerList.size(); i++) {
				distance[i] = distance(p, centerList.get(i));
			}
			int index = min(distance);
			classificationMap.put(p, index);
		}
		
		// M step
		double[] xs = new double[centerList.size()];
		double[] ys = new double[centerList.size()];
		double[] num = new double[centerList.size()];
		for(Point p: classificationMap.keySet()) {
			int classification = classificationMap.get(p);
			xs[classification] += p.getX();
			ys[classification] += p.getY();
			num[classification]++;
		}
		for(int i = 0; i < centerList.size(); i++)
			centerList.set(i, new Point(xs[i]/ num[i], ys[i]/ num[i]));
	}
	public double distance(Point a, Point b) {
		return Math.sqrt(Math.pow(a.getX() - b.getX(), 2) + Math.pow(a.getY() - b.getY(), 2));
	}
	public int min(double[] d) {
		int ans = -1;
		double minValue = -1;
		for(int i = 0; i < d.length; i++) {
			if(minValue == -1 || d[i] < minValue) {
				minValue = d[i];
				ans = i;
			}
		}
		return ans;
	}
	public Map<Point, Integer> getClassificationMap() {
		return classificationMap;
	}
	public void setClassificationMap(Map<Point, Integer> classificationMap) {
		this.classificationMap = classificationMap;
	}
	public List<Point> getCenterList() {
		return centerList;
	}
	public void setCenterList(List<Point> centerList) {
		this.centerList = centerList;
	}
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		KMeans kMeans = new KMeans();
		for(int i = 0; i < 100; i++)
			kMeans.run();
		for(int i = 0; i < kMeans.getCenterList().size(); i++)
			System.out.println(kMeans.getCenterList().get(i));
	}

}
