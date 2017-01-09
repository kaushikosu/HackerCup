package com.kaushik.HackerCup.progresspieproblem;

public class Angle {
	
	private double value;
	
	private Half half;

	public Angle(double value, Half half) {
		this.value = value;
		this.half = half;
	}

	public double getValue() {
		return value;
	}

	public void setValue(double value) {
		this.value = value;
	}

	public Half getHalf() {
		return half;
	}

	public void setQuadrant(Half half) {
		this.half = half;
	}
	
	
	
	
	
}
