package com.clonex.utilities;

import java.util.Random;
import java.util.Scanner;

public class Utilities {

	static Random rnd = new Random();
	int rndInt;
	Scanner scanner = new Scanner(System.in);
	
	public int randomIntGenerator() {
		rndInt = rnd.nextInt(1000,10000);
		return rndInt;
	}
	
	public String stringFromUser(String string) {
		System.out.print("\u001B[33m"+string+"\u001B[0m");
		String stringInput = scanner.nextLine();
		return stringInput;
	}
	
	public int intFromUser(String string) {
		System.out.print("\u001B[33m"+string+"\u001B[0m");
		int intInput = scanner.nextInt();scanner.nextLine();
		return intInput;
	}
}
