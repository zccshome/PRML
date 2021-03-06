package me.zccshome.regression.data;

/**
 * y = kx
 * @author zccshome
 *
 */
public class NaiveLinearData implements IData{
	private double x;
	private double y;
	
	public NaiveLinearData(double x, double y) {
		this.x = x;
		this.y = y;
	}

	public double getX() {
		return x;
	}

	public void setX(double x) {
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
		return "LinearData [x=" + x + ", y=" + y + "]";
	}
}
