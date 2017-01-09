package com.kaushik.HackerCup.progresspieproblem;


public class Point {

	private double x;
	private double y;
	private Half half;
	private static final Point center = new Point(50,50); 
	private static final Point referenceVector = new Point(0,50);
	
	public Point(double x,double y){
		this.x = x;
		this.y = y;
		this.half = computeHalf();
	}
	
	public Half getHalf() {
		return half;
	}

	private Half computeHalf(){
		if (x == 50){
			return Half.H1;
		}
		if (x > center.getX()){
			return Half.H1;
		}
		return Half.H2;
	}
	
	public double getX(){
		return x;
	}
	
	public double getY(){
		return y;
	}
	
	public static Point getOrigin(){
		return center;
	}
	
	public static Point getReferenceVector(){
		return referenceVector;
	}
}
