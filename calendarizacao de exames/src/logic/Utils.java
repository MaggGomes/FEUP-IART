package logic;

import java.awt.List;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import algorithm.Population;

public class Utils {
	
	public static int byteToInt(Integer[] bits){
		int sum = 0;
		
		int mult = 1;
		
		for(int i = bits.length - 1; i > -1; i--){
			
			sum += mult * bits[i];
			mult *= 2;            //1 2 4 8 16 32 64 .... ou seja multiplicar sempre or 2 a come�ar em 1
		}

		return sum;	
	}
	
	
	public static ArrayList<Integer[]> splitChromossome(Integer[] bits, int days){
	
		ArrayList<Integer[]> exameDates = new ArrayList<Integer[]>();
		
		int size = bits.length/days;
		
		
		for (int i = 0; i < days; i++) {
		
			Integer[] date = new Integer[size];
			
			System.arraycopy(bits, i * size, date, 0, size);
		
			exameDates.add(date);
		}
			
		return exameDates;
		
	}
	
	public static int getNumberOfbitsNedded(int numberOfDays){
		return (int) Math.ceil(Math.log10(numberOfDays)/Math.log10(2));
	}
	

    public static HashMap<Integer, Integer> sortByValue(HashMap<Integer, Integer> unsortMap) {

    
    	
    	return null;	
    	
    }
}

