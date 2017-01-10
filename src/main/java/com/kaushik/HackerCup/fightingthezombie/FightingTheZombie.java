package com.kaushik.HackerCup.fightingthezombie;

import java.io.File;
import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;



public class FightingTheZombie {

	static Map<Key,Integer> table;

	public static void main(String[] args){
		Scanner in = new Scanner(System.in);
		Case[] cases;
		int totalNumberOfCases = 0;
		try {
			totalNumberOfCases = in.nextInt();
			cases = new Case[totalNumberOfCases];
			for (int i = 0; i < totalNumberOfCases; i++){
				int zombiePower  = in.nextInt();
				int numberOfSpells = in.nextInt();
				cases[i] = new Case(zombiePower,numberOfSpells);
				for (int j = 0; j < numberOfSpells ;j++ ){
					cases[i].addSpell(j, in.next());
				}
			}
		}
		finally{
			in.close();
		}
		
		
		
		File f = new File("output_fighting_the_zombie.txt");
		PrintWriter writer = null;
		FileOutputStream fos = null;
		try{
			fos = new FileOutputStream(f);
			writer = new PrintWriter(fos);
			for (int i = 0; i < totalNumberOfCases; i++){
				printProbabilityOfTheBestSpell(i+1, cases[i],writer);
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
	
	private static void printProbabilityOfTheBestSpell(int index, Case c,PrintWriter writer){
		double maxProbability = 0;
		for (int i = 0; i < c.getNumberOfSpells(); i ++){
			Spell spell = c.getSpells()[i];
			table = new HashMap<Key,Integer>();
			populateTheTableForASingleDie(spell.getM());
			
			double probablityForThisSpell = calculateTheProbabilityForASpell(c.getZombiePower(),spell);
			if (probablityForThisSpell > maxProbability){
				maxProbability = probablityForThisSpell;
			}
		}
		System.out.println("Case #"+index+": "+String.format("%.6f", maxProbability));
		writer.write("Case #"+index+": "+String.format("%.6f", maxProbability)+"\n");
	}
	
	private static double calculateTheProbabilityForASpell (int zombiePower, Spell spell){
		if (zombiePower > spell.getMax()){
			return 0;
		}
		if (zombiePower < spell.getMin()){
			return 1;
		}
		else{
			double totalPositiveCount = 0;
			for (int i = zombiePower; i <= spell.getMax(); i ++){
				int nextNumber = numberOfWaysOfGettingXUsingNMSidedDice(spell.getN(), spell.getM(), i-spell.getZ());
					totalPositiveCount += nextNumber;
			}
			double totalPossibleCount = totalNumberOfPossibilitiesForNMFacedDice(spell.getN(), spell.getM());
			return totalPositiveCount/totalPossibleCount;
		}
	}
	
	private static void populateTheTableForASingleDie(int m){
			for (int j= 1; j <=m ; j++){
				Key key = new Key(j,1);
				table.put(key, 1);
			}
	}
	
	private static int totalNumberOfPossibilitiesForNMFacedDice(int n,int m){
		return (int)Math.pow(m,n);
	}
	
	private static int numberOfWaysOfGettingXUsingNMSidedDice(int n, int m, int x){
		if (x < 0){
			return 0;
		} 
		Key key = new Key(x,n);
		if (table.containsKey(key)){
			return table.get(key);
		}
		else{
			if(n == 1){
				if (x >= m || x <= 1){
					table.put(key, 0);
				}
				else{
					table.put(key, 1);
				}
				return table.get(key);
			}
			else{
				for (int i = 1; i <= m; i++){
					updateValue(key,numberOfWaysOfGettingXUsingNMSidedDice(n-1, m, x-i),table);
				}
			return table.get(key);
			}
		}
	}
	
	private static void updateValue(Key key, int value, Map<Key,Integer> table){
		if (table.containsKey(key)){
			table.put(key, table.get(key) + value);
		}
		else{
			table.put(key, value);
		}
	}
	
}

class Spell {
	
	int m;
	int n;
	int z;

	public Spell(int m, int n, int z) {
		this.m = m;
		this.n = n;
		this.z = z;
	}

	public int getM() {
		return m;
	}
	
	public int getN() {
		return n;
	}
	
	public int getZ() {
		return z;
	}
	
	public int getMax(){
		return (n*m)+z;
	}
	
	public int getMin(){
		return (n*1)+z;
	}
}

class Key {
	
	int sum;
	int numberOfDice;
	
	public Key(int sum, int numberOfDice){
		this.sum = sum;
		this.numberOfDice = numberOfDice;
	}

	public int getSum() {
		return sum;
	}
	
	public int getNumberOfDice() {
		return numberOfDice;
	}

	public void setNumberOfDice(int numberOfDice) {
		this.numberOfDice = numberOfDice;
	}
	
	public boolean equals(Object o){
		if (this.getSum() == ((Key)o).getSum() && this.getNumberOfDice() == ((Key)o).getNumberOfDice()){
			return true;
		}
		else{
			return false;
		}
	}
	
	public int hashCode(){
		return numberOfDice+sum;
	}
}

class Case{
	
	int zombiePower;
	int numberOfSpells;
	Spell[] spells;
	
	public int getZombiePower() {
		return zombiePower;
	}

	public void setZombiePower(int zombiePower) {
		this.zombiePower = zombiePower;
	}
	
	public int getNumberOfSpells() {
		return numberOfSpells;
	}
	
	public void setNumberOfSpells(int numberOfSpells) {
		this.numberOfSpells = numberOfSpells;
	}
	
	public Spell[] getSpells() {
		return spells;
	}

	
	public Case(int zombiePower, int numberOfSpells) {
		this.zombiePower = zombiePower;
		this.numberOfSpells = numberOfSpells;
		this.spells = new Spell[numberOfSpells];
	}
	
	public void addSpell(int index, String spellString){
		String[] splitSpellString = spellString.split("d");
		int numberOfDice = Integer.parseInt(splitSpellString[0]);
		String [] splitSecondPart = null;
		int dieFaces = 0;
		int z = 0;
		if (splitSpellString[1].contains("-")){
			splitSecondPart = splitSpellString[1].split("-");
			dieFaces =Integer.parseInt(splitSecondPart[0]);
			if (splitSecondPart.length > 1){
				z = Integer.parseInt(splitSecondPart[1]);
				z *= -1;
			}
		}
		else if (splitSpellString[1].contains("+")){
			splitSecondPart = splitSpellString[1].split("\\W");
			dieFaces =Integer.parseInt(splitSecondPart[0]);
			if (splitSecondPart.length > 1){
				z = Integer.parseInt(splitSecondPart[1]);
			}
		}
		else{
			dieFaces = Integer.parseInt(splitSpellString[1]);
		}
		
		Spell spell = new Spell(dieFaces,numberOfDice,z);
		spells[index] = spell;
	}
	
	
}

