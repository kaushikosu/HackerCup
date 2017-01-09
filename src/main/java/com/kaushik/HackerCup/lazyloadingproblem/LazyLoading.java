package com.kaushik.HackerCup.lazyloadingproblem;

import java.io.File;
import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.Scanner;

public class LazyLoading {
	public static void main(String[] args){
		Scanner in = new Scanner(System.in);
		Day[] days;
		int totalNumberOfDays = 0;
		try {
			totalNumberOfDays = in.nextInt();
			days = new Day[totalNumberOfDays];
			for (int i = 0; i < totalNumberOfDays; i++){
				int numberOfItems = in.nextInt();
				Day day = new Day(numberOfItems);
				for (int j = 0; j < numberOfItems; j++){
					day.addAnItem(j, in.nextInt());
				}
				if (i == 31){
					for(int item: day.getItems()){
					System.out.println("item "+item);
					}
				}
				days[i] = day;
				day.sortItems();
			}
		}
		finally{
			in.close();
		}
		
		File f = new File("output_lazy_loading.txt");
		PrintWriter writer = null;
		FileOutputStream fos = null;
		try{
			fos = new FileOutputStream(f);
			writer = new PrintWriter(fos);
			for (int i = 0; i < totalNumberOfDays; i++){
				printTotalNumberOfTrips(i+1, calculateMaxTrips(days[i],50), writer);
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
	
	private static int calculateMaxTrips(Day day, int weightLimit){
		int maxTrips = 0;
		int[] items = day.getItems();
		int minIndex = 0;
		int maxIndex = items.length - 1;

		while(minIndex <= maxIndex){
			int maxValue = items[maxIndex];
			if (maxValue >= weightLimit){
				maxTrips += 1;
				maxIndex -= 1;
			}
			else{
				if (elementsNeededToMakeMaxWeight(maxValue, weightLimit) < elementsLeftInArray(minIndex, maxIndex)){
					maxTrips += 1;
					minIndex += elementsNeededToMakeMaxWeight(items[maxIndex], weightLimit);
					maxIndex -= 1;
				}
				else if (maxTrips == 0){
					break;
				} 
				else{
					break;
				}
			}
		}
		return maxTrips;
		
	}
	
	private static int elementsNeededToMakeMaxWeight(int weight, int maxWeight){
		return maxWeight/weight;
	}
	
	private static int elementsLeftInArray(int minIndex, int maxIndex){
		return maxIndex-minIndex+1;
	}
	
	private static void printTotalNumberOfTrips(int i, int maxTrips, PrintWriter writer){
			System.out.println("Case #"+i+": "+maxTrips);
			writer.write("Case #"+i+": "+maxTrips+"\n");
	}
}

class Day{
	
	int numberOfItems;
	
	int[] items;
	
	public Day(int numberOfItems){
		items = new int[numberOfItems];
	}
	
	public boolean addAnItem(int index,int item){
		if (index < items.length){
			items[index] = item;
			return true;
		}
		return false;
	}
	
	public void sortItems(){
		Arrays.sort(items);
	}
	
	public int[] getItems(){
		return items;
	}
}
