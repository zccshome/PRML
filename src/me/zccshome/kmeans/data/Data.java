package me.zccshome.kmeans.data;

import java.util.ArrayList;
import java.util.List;

public class Data {
	private List<Point> pointList;

	public Data() {
		pointList = new ArrayList<Point>();
		pointList.add(new Point(2, 1));
		pointList.add(new Point(3, 1));
		pointList.add(new Point(1, 1));
		pointList.add(new Point(1, 2));
		pointList.add(new Point(1, 3));
		pointList.add(new Point(2, 2));
		pointList.add(new Point(2, 3));
		pointList.add(new Point(3, 2));
		pointList.add(new Point(3, 3));
		
		pointList.add(new Point(20, 1));
		pointList.add(new Point(20, 2));
		pointList.add(new Point(20, 3));
		pointList.add(new Point(21, 1));
		pointList.add(new Point(21, 2));
		pointList.add(new Point(21, 3));
		pointList.add(new Point(22, 1));
		pointList.add(new Point(22, 2));
		pointList.add(new Point(22, 3));
	}
	
	public List<Point> getPointList() {
		return pointList;
	}

	public void setPointList(List<Point> pointList) {
		this.pointList = pointList;
	}
}
