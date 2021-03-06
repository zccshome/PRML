package me.zccshome.regression.data;

import java.util.ArrayList;
import java.util.List;

/**
 * y = w0 + w1 * x1 + w2 * x2 + ...
 * @author zccshome
 *
 */
public class SimpleLinearData implements IData{
	private List<Double> x;
	private double y;
	
	public SimpleLinearData(List<Double> x, double y) {
		super();
		this.x = x;
		this.y = y;
	}
	
	public SimpleLinearData(double[] x, double y) {
		super();
		this.x = new ArrayList<Double>();
		for(double xx: x)
			this.x.add(xx);
		this.y = y;
	}

	public List<Double> getX() {
		return x;
	}

	public void setX(List<Double> x) {
		this.x = x;
	}

	public double getY() {
		return y;
	}

	public void setY(double y) {
		this.y = y;
	}

	@Override
	public String toString() {
		return "LinearDataModal [x=" + x + ", y=" + y + "]";
	}
}
