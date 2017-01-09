package com.kaushik.HackerCup.progresspieproblem;

public class Inputpair {

	private Angle angle;
	private Point point;

	public Inputpair(double percentage, Point point) {
		this.angle = getAngleFromPercentage(percentage);
		this.point = point;
	}
	
	public Angle getAngleFromPercentage(double percentage){
		double value = (percentage/100) * 360;
		Half h = Half.H1;
		if (percentage > 50 ){
			h = Half.H2;
		}
		return new Angle(value,h);
	}
	
	public Angle getAngle() {
		return angle;
	}
	
	public void setAngle(Angle angle) {
		this.angle = angle;
	}
	
	public Point getPoint() {
		return point;
	}
	public void setPoint(Point point) {
		this.point = point;
	}
}
