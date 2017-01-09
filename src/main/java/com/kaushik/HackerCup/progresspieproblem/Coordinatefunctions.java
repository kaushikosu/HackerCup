package com.kaushik.HackerCup.progresspieproblem;

public class Coordinatefunctions {

	public static double distance(Point a, Point b){
		double deltaX = a.getX() - b.getX();
		double deltaY = a.getY() - b.getY();
		return Math.sqrt((deltaX*deltaX) + (deltaY*deltaY));
	}
	
	public static double dotProduct(Point a, Point b){
		return ((a.getX()*b.getX()) + (a.getY()*b.getY()));
	}
	
	public static Point returnVector(Point a, Point b){
		return new Point (a.getX()-b.getX(), a.getY()-b.getY());
	}
	
	public static double mod(Point vectorA){
		return Math.sqrt((vectorA.getX()*vectorA.getX()) + (vectorA.getY()*vectorA.getY()));
	}
	
	public static double cosineValue(Point vectorA, Point vectorB){
		double dotProduct = dotProduct(vectorA, vectorB);
		double modA = mod(vectorA);
		double modB = mod(vectorB);
		if (modA == 0 || modB == 0 ){
			return 0;
		}
		return (dotProduct/(modA*modB));
	}
	
	public static double cosine(double angleValue){
		double radianValue = (angleValue * 2 * Math.PI/360);
		return Math.cos(radianValue);
	}
	
}
