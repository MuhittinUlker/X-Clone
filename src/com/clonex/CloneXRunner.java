package com.clonex;

import java.util.Scanner;

import com.clonex.classes.Menus;
import com.clonex.classes.User;
import com.clonex.managers.AdminManager;
import com.clonex.managers.UserManager;
import com.clonex.utilities.Database;
import com.clonex.utilities.Utilities;

public class CloneXRunner {
	
	public static Database database = new Database();
	static UserManager userManager = new UserManager();
	static AdminManager adminManager = new AdminManager();
	static Scanner scanner = new Scanner(System.in);
	static Menus menu = new Menus();
	static Utilities util = new Utilities();
	static int intInput;
		
	public static void main(String[] args) {
		
		cloneX();
		
	}
	
	public static void cloneX() {
		do {
			
			menu.mainMenu();
			intInput=util.intFromUser("Seçiminiz: ");
			
			switch (intInput) {
			case 1:
				User registeredUser = userManager.register();
				if (!registeredUser.equals(null)) {
					database.userList.add(registeredUser);
				}else {
					System.out.println("\u001B[31mKayıt başarısız.\u001B[0m");
				}
				break;
			case 2:
				User loggedInUser = userManager.login();
				if (!loggedInUser.equals(null)) {
					if (loggedInUser.getUsername().equals("admin")) {
						adminManager.adminManager();
					}else {
						userManager.userManager(loggedInUser);
					}
				}else {
					System.out.println("\u001B[31mGiriş başarısız..\u001B[0m");
				}
				break;
			case 0:
				System.out.println("\u001B[35mÇıkış yapıldı..\u001B[0m");
				System.exit(0);
				break;
			default:
				System.out.println("\u001B[36mLütfen 0-2 arasında bir değer girin..\u001B[0m");
				break;
			}
		} while (true);
	}
	
}
