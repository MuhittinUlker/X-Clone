package com.clonex.managers;

import com.clonex.CloneXRunner;
import com.clonex.classes.Menus;
import com.clonex.enums.EStatus;
import com.clonex.utilities.Utilities;

public class AdminManager {
	
	UserManager userManager = new UserManager();
	Menus menu = new Menus();
	Utilities util = new Utilities();
	int selection;
	int id;
	boolean isBanned = false;
	boolean isUnBanned = false;
	
	public void adminManager() {
		do {
			menu.adminMenu();
			selection=util.intFromUser("Seçiminiz: ");
			
			switch (selection) {
			case 1:
				CloneXRunner.database.getUsers();;
				break;
			case 2:
				CloneXRunner.database.getProfiles();
				break;
			case 3:
				banUser();
				break;
			case 4:
				unBanUser();
				break;
			case 5:
				restrictUser();
				break;
			case 6:
				unRestrictUser();
				break;
			case 0:
				CloneXRunner.cloneX();
			default:
				System.out.println("\u001B[36mLütfen 0-6 arasında bir değer girin..\u001B[0m");
				break;
			}
		} while (true);
		
	}

	public void unRestrictUser() {
		System.out.println("\u001B[32m*****UNRESTRICT USER*****\u001B[0m");
		CloneXRunner.database.getProfiles();
		id = util.intFromUser("Kısıtlanacak kullanıcı ID'si: ");
		for (int i = 0; i < CloneXRunner.database.userList.size(); i++) {
			if (CloneXRunner.database.userList.get(i).getProfile().getId()==id) {
				if (CloneXRunner.database.userList.get(i).getProfile().getStatus().name().equals("RESTRICTED")) {
					CloneXRunner.database.userList.get(i).getProfile().setStatus(EStatus.ACTIVE);
					isUnBanned=true;
				}else {
					System.out.println("Kullanıcı zaten aktif.");
				}
			}
		}
		if (!isUnBanned) {
			System.out.println("Girdiğiniz ID ile kayıtlı bir kullanıcı yok");
		}
	}

	public void restrictUser() {
		System.out.println("\u001B[32m*****RESTRICT USER*****\u001B[0m");
		CloneXRunner.database.getProfiles();
		id = util.intFromUser("Kısıtlanacak kullanıcı ID'si: ");
		for (int i = 0; i < CloneXRunner.database.userList.size(); i++) {
			if (CloneXRunner.database.userList.get(i).getProfile().getId()==id) {
				if (CloneXRunner.database.userList.get(i).getProfile().getStatus().name().equals("ACTIVE")) {
					CloneXRunner.database.userList.get(i).getProfile().setStatus(EStatus.RESTRICTED);
					isBanned=true;
				}else {
					System.out.println("Kullanıcı zaten kısıtlanmış.");
				}
			}
		}
		if (!isBanned) {
			System.out.println("Girilen ID ile kayıtlı bir kullanıcı bulunamadı.");
		}
	}

	public void unBanUser() {
		System.out.println("\u001B[32m*****UN-BAN USER*****\u001B[0m");
		CloneXRunner.database.getUsers();
		id = util.intFromUser("Banı kaldırılacak kullanıcı ID'si: ");
		for (int i = 0; i < CloneXRunner.database.userList.size(); i++) {
			if (CloneXRunner.database.userList.get(i).getProfile().getId()==id) {
				if (CloneXRunner.database.userList.get(i).getProfile().getStatus().name().equals("BANNED")) {
					CloneXRunner.database.userList.get(i).getProfile().setStatus(EStatus.ACTIVE);
					isUnBanned=true;
				}else {
					System.out.println("Kullanıcı zaten aktif.");
				}
			}
		}
		CloneXRunner.database.getUsers();
		if (!isUnBanned) {
			System.out.println("Girdiğiniz ID ile kayıtlı bir kullanıcı bulunmamaktadır.");
		}
	}

	public void banUser() {
		System.out.println("\u001B[32m*****BAN USER*****\u001B[0m");
		CloneXRunner.database.getUsers();
		int id = util.intFromUser("Banlanacak kullanıcı ID'si: ");
		for (int i = 0; i < CloneXRunner.database.userList.size(); i++) {
			if (CloneXRunner.database.userList.get(i).getProfile().getId()==id) {
				if (!(CloneXRunner.database.userList.get(i).getProfile().getStatus().name().equals("BANNED"))) {
					CloneXRunner.database.userList.get(i).getProfile().setStatus(EStatus.BANNED);
					isBanned=true;
				}else {
					System.out.println("Kullanıcı zaten banlı.");
				}
			}
		}
		CloneXRunner.database.getUsers();
		if (!isBanned) {
			System.out.println("Girdiğiniz ID ile kayıtlı bir kullanıcı bulunmamaktadır.");
		}
	}
	
	
}
