package com.clonex.classes;

public class Menus {

	public void mainMenu() {
		System.out.println("\u001B[32m-_-_-_ CLONE-X _-_-_-");
		System.out.println(" ___________________ ");
		System.out.println("|                   |");
		System.out.println("| 1- Register       |");
		System.out.println("| 2- Login          |");
		System.out.println("| 0- Exit           |");
		System.out.println("|___________________|\u001B[0m");
	}
	
	public void userMenu(String string) {
		System.out.println("\u001B[32m-_-_-_ "+string+" _-_-_-");
		System.out.println(" ___________________ ");
		System.out.println("|                   |");
		System.out.println("| 1- Tweet          |");
		System.out.println("| 2- Comment        |");
		System.out.println("| 3- Like Tweet     |");
		System.out.println("| 4- ReTweet        |");
		System.out.println("| 5- Follow         |");
		System.out.println("| 6- Unfollow       |");
		System.out.println("| 7- Delete Tweet   |");
		System.out.println("| 8- My Tweets      |");
		System.out.println("| 9- My Profile     |");
		System.out.println("| 10- Message       |");
		System.out.println("| 11- Inbox         |");
		System.out.println("| 12- Block         |");
		System.out.println("| 13- Unblock       |");
		System.out.println("| 14- List Blocked  |");
		System.out.println("| 0- Logout         |");
		System.out.println("|___________________|\u001B[0m");
	}
	
	public void adminMenu() {
		System.out.println("\u001B[32m-_-_-_ ADMIN _-_-_-");
		System.out.println(" ____________________");
		System.out.println("|                    |");
		System.out.println("| 1- List Users      |");
		System.out.println("| 2- List Profiles   |");
		System.out.println("| 3- Ban User        |");
		System.out.println("| 4- UnBan User      |");
		System.out.println("| 5- Restrict User   |");
		System.out.println("| 6- UnRestrict User |");
		System.out.println("| 0- Logout          |");
		System.out.println("|____________________|\u001B[0m");
	}
}
