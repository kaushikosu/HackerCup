package com.kaushik.HackerCup.progresspieproblem;


import java.io.File;
import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.util.Scanner;


public class ProgressPie {

	public static void main(String[] args){
		Scanner in = new Scanner(System.in);
		Inputpair[] points;
		int totalNumberOfPoints = 0;
		try {
			totalNumberOfPoints = in.nextInt();
			points = new Inputpair[totalNumberOfPoints];
			for (int i = 0; i < totalNumberOfPoints; i++){
				double percentage = in.nextDouble();
				double x = in.nextDouble();
				double y = in.nextDouble();
				points[i] = new Inputpair(percentage, new Point(x, y));
			}
		}
		finally{
			in.close();
		}
		
		File f = new File("output_progress_pie.txt");
		PrintWriter writer = null;
		FileOutputStream fos = null;
		try{
			fos = new FileOutputStream(f);
			writer = new PrintWriter(fos);
			for (int i = 0; i < totalNumberOfPoints; i++){
				printInputPairColor(i+1, points[i],writer);
			}
			writer.flush();
			fos.close();
		}
		catch(Exception e){
			System.out.println("Something went wrong with writing the output file");
		}
		finally{
			writer.close();
		}
	}
	
	private static void printInputPairColor(int i, Inputpair point,PrintWriter writer){
		if (isThePointBlack(point)){
			System.out.println("Case #"+i+": black");
			writer.write("Case #"+i+": black\n");
		}
		else{
			System.out.println("Case #"+i+": white");
			writer.write("Case #"+i+": white\n");
		}
	}
	
	private static boolean isThePointBlack(Inputpair inputPair){
		double radius = 50;
		return checkIfMagnitudeIsLessThanRadius(inputPair.getPoint(),radius) && checkIfPointAngleLessThanGivenAngle(inputPair.getAngle(),inputPair.getPoint());
	}
	
	private static  boolean checkIfMagnitudeIsLessThanRadius(Point point,double radius){
		Point pointVector = Coordinatefunctions.returnVector(point, Point.getOrigin());
		double modPointVector = Coordinatefunctions.mod(pointVector);
		if (modPointVector < radius){
			return true;
		}
		return false;
	}
	
	private static boolean checkIfPointAngleLessThanGivenAngle(Angle angle, Point point){
		Point pointVector = Coordinatefunctions.returnVector(point,Point.getOrigin());
		if (angle.getHalf() == Half.H1 && point.getHalf() == Half.H1){
			if (Coordinatefunctions.cosine(angle.getValue()) < Coordinatefunctions.cosineValue(pointVector, Point.getReferenceVector())){
				return true;
			}
			return false;
		}
		else if (angle.getHalf() == Half.H2 && point.getHalf() == Half.H2){
			if (Coordinatefunctions.cosine(angle.getValue()) < Coordinatefunctions.cosineValue(pointVector, Point.getReferenceVector())){
				return false;
			}
			return true;
		}
		else if (angle.getHalf() == Half.H1 && point.getHalf() == Half.H2){
			return false;
		}
		else{
			return true;
		}
	}
	
}
