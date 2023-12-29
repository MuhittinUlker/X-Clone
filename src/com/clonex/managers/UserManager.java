package com.clonex.managers;

import java.util.Map;

import com.clonex.CloneXRunner;
import com.clonex.classes.Comment;
import com.clonex.classes.Menus;
import com.clonex.classes.Message;
import com.clonex.classes.Tweet;
import com.clonex.classes.User;
import com.clonex.utilities.Utilities;

public class UserManager {

	Menus menu = new Menus();
	Utilities util = new Utilities();
	private int selection;
	boolean isUserFound = false;
	boolean isTweetFound = false;
	
	public void userManager(User user) {
		do {
			menu.userMenu(user.getUsername());
			selection=util.intFromUser("Seçiminiz: ");
			switch (selection) {
			case 1:
				tweet(user);
				break;
			case 2:
				comment();
				break;
			case 3:
				like();
				break;
			case 4:
				reTweet(user);
				break;
			case 5:
				follow(user);
				break;
			case 6:
				unFollow(user);
				break;
			case 7:
				deleteTweet(user);
				break;
			case 8:
				user.getProfile().getTweetsAsList();
				break;
			case 9:
				System.out.println("\u001B[34m*****PROFILE*****\u001B[0m");
				System.out.println(user.getProfile());
				break;
			case 10:
				sendMessage(user);
				break;
			case 11:
				System.out.println("\u001B[34m*****INBOX*****\u001B[0m");
				user.getProfile().getInbox().forEach((i,m)->System.out.println((i+1)+"-->"+m));
				break;
			case 12:
				blockUser(user);
				break;
			case 13:
				unBlockUser(user);
				break;
			case 14:
				listBlockedUsers(user);
				break;
			case 0:
				CloneXRunner.cloneX();
				break;
			default:
				System.out.println("\u001B[36mLütfen 0-14 arasında bir değer girin..\u001B[0m");
				break;
			}
		} while (true);
	}

	public void listBlockedUsers(User user) {
		System.out.println("\u001B[34m*****BLOCKED USERS*****\u001B[0m");
		System.out.println("USERNAME\tID\tSTATUS");
		if (user.getProfile().getBlockedList().size()==0) {
			System.out.println("\u001B[31mBloklu kullanıcı bulunamadı.\u001B[0m");
		}else {
			user.getProfile().getBlockedList().forEach((k,v)->System.out.println(v.getUsername()+"\t"+k+"\t"+v.getProfile().getStatus()));	
		}
		
	}

	public void unBlockUser(User user) {
		boolean isUnBlocked = false;
		System.out.println("\u001B[34m*****UNBLOCK*****\u001B[0m");
		System.out.println("USERNAME\tID\tSTATUS");
		for (Map.Entry<Integer,User> blockedUsers : user.getProfile().getBlockedList().entrySet()) {
				System.out.println(blockedUsers.getValue().getUsername()+"\t"+blockedUsers.getKey()+"\t"+blockedUsers.getValue().getProfile().getStatus());
		}
		int id = util.intFromUser("Blok kaldırılacak kullanıcı ID'sini girin: ");
		for (User user2 : CloneXRunner.database.userList) {
			if (user2.getProfile().getId()==id) {
				user.getProfile().getBlockedList().remove(id);
				System.out.println("Kullanıcı blok kaldırıldı.");
				isUnBlocked = true;
			}
		}
		if (!isUnBlocked) {
			System.out.println("\u001B[31mBu ID ile kayıtlı bir kullanıcı bulunamadı.\u001B[0m");
		}
	}

	public void blockUser(User user) {
		
		System.out.println("\u001B[34m*****BLOCK*****\u001B[0m");
		CloneXRunner.database.userList.stream().forEach(u->{
		if (!(u.getProfile().getBlockedList().containsKey(user.getProfile().getId())) &&
				!(u.getUsername().equals(user.getUsername()))&&
				!(u.getUsername().equalsIgnoreCase("Admin"))) {
			System.out.println(u.getUsername()+" "+u.getProfile().getId()+" "+u.getProfile().getStatus());
		}			
		});
		int id = util.intFromUser("Bloklanacak kullanıcı ID'sini girin: ");
		boolean isFound = false;
		for (User user2 : CloneXRunner.database.userList) {
			if (user2.getProfile().getId()==id) {
				isFound = true;
				user.getProfile().getBlockedList().put(id, user2);
				System.out.println("Kullanıcı bloklandı.");
			}
				
		}
		if (!isFound) {
			System.out.println("\u001B[31mBu ID ile kayıtlı bir kullanıcı bulunamadı.\u001B[0m");
		}		
	}

	public void sendMessage(User user) {
		boolean isFound = false;
		System.out.println("\u001B[34m*****SEND MESSAGE*****\u001B[0m");
		System.out.println("Username\tID");
		User userToSendMessage = new User();
		for (User users : CloneXRunner.database.userList) {
			if (!users.getUsername().equals(user.getUsername())&&!users.getUsername().equalsIgnoreCase("Admin")) {
				System.out.println(users.getUsername()+"\t"+users.getProfile().getId());
			}
		}
		int intInput = util.intFromUser("Mesaj göndermek istediğiniz kullanıcı ID'si: ");
		String message = util.stringFromUser("Mesajınız: ");
		for (User user2 : CloneXRunner.database.userList) {
			if (user2.getProfile().getId()==intInput) {
				userToSendMessage = user2;
				isFound = true;
			}
		}
		if (!isFound) {
			System.out.println("\u001B[31mBu ID ile kayıtlı bir kullanıcı bulunamadı..\u001B[0m");
		}else {
			user.getProfile().getMessages().add(new Message(userToSendMessage.getUsername(), message));
			userToSendMessage.getProfile().getInbox()
			.put(userToSendMessage.getProfile().getInbox().size(), new Message(user.getUsername(), message));
		}
	}

	public void deleteTweet(User user) {
		System.out.println("\u001B[34m*****DELETE TWEET*****\u001B[0m");
		user.getProfile().getTweetsAsList();
		if (!(user.getProfile().getTweetList().size()==0)) {
			int intInput = util.intFromUser("Silmek istediğiniz tweet ID'si: ");
			user.getProfile().getTweetList().remove(intInput);
			System.out.println("Tweet'iniz silindi.");
		}else {
			System.out.println("\u001B[31mTweetiniz bulunmamaktadır.\u001B[0m");
		}
		
	}

	public void unFollow(User user) {
		System.out.println("\u001B[34m*****UNFOLLOW*****\u001B[0m");
		System.out.println("Username\tID");
		for (User users : CloneXRunner.database.userList) {
			if (!users.getUsername().equals(user.getUsername())&&!users.getUsername().equalsIgnoreCase("Admin")) {
				System.out.println(users.getUsername()+"\t"+users.getProfile().getId());
			}
		}
		int userId = util.intFromUser("Takipten çıkmak istediğiniz profil ID'si: ");
		for (int i = 0; i <CloneXRunner.database.userList.size() ; i++) {
			if (CloneXRunner.database.userList.get(i).getProfile().getId()==userId) {
				isUserFound=true;
				if (!user.getProfile().getFollowingList().isEmpty() && user.getProfile().getFollowingList().containsKey(userId)) {
					user.getProfile().getFollowingList().remove(userId);
					System.out.println(CloneXRunner.database.userList.get(i).getUsername()+" adlı kullanıcı takip ediliyor.");
					user.getProfile().setFollowing(user.getProfile().getFollowing()-1);
					CloneXRunner.database.userList.get(i).getProfile().getFollowerList().remove(user.getProfile().getId());
					CloneXRunner.database.userList.get(i).getProfile().setFollowers(CloneXRunner.database.userList.get(i).getProfile().getFollowers()-1);
				}else {
					System.out.println("\u001B[31mKullanıcıyı zaten takip etmiyorsunuz..\u001B[0m");
				}
			}
		}
		if (!isUserFound) {
			System.out.println("\u001B[31mAradığınız ID ile bir kullanıcı yok..\u001B[0m");
		}
	}

	public void follow(User user) {
		System.out.println("\u001B[34m*****FOLLOW*****\\u001B[0m");
		System.out.println("Username\tID");
		for (User users : CloneXRunner.database.userList) {
			if (!users.getUsername().equals(user.getUsername())&&
					!users.getUsername().equalsIgnoreCase("Admin")) {
				System.out.println(users.getUsername()+"\t"+users.getProfile().getId());
			}
		}
		int userId = util.intFromUser("Takip etmek istediğiniz profil ID'si: ");
		for (int i = 0; i <CloneXRunner.database.userList.size() ; i++) {
			if (CloneXRunner.database.userList.get(i).getProfile().getId()==userId) {
				isUserFound=true;
				if (CloneXRunner.database.userList.get(i).getProfile().getBlockedList().containsKey(user.getProfile().getId())) {
					System.out.println("Kullanıcı sizi bloklamış..");
				}else {
					if (!user.getProfile().getFollowingList().containsKey(userId)) {
							user.getProfile().getFollowingList().put(userId, CloneXRunner.database.userList.get(i));
							System.out.println(CloneXRunner.database.userList.get(i).getUsername()+" adlı kullanıcı takip ediliyor.");
							user.getProfile().setFollowing(user.getProfile().getFollowing()+1);
							CloneXRunner.database.userList.get(i).getProfile().getFollowerList().put(user.getProfile().getId(), user);
							CloneXRunner.database.userList.get(i).getProfile().setFollowers(CloneXRunner.database.userList.get(i).getProfile().getFollowers()+1);
						}else {
							System.out.println("\u001B[31mKullanıcıyı zaten takip ediyorsunuz..\u001B[0m");
						}
				}
			}
		}
		if (!isUserFound) {
			System.out.println("\u001B[31mAradığınız ID ile bir kullanıcı yok..\u001B[0m");
		}
	}

	public void reTweet(User user) {
		System.out.println("\u001B[34m*****RE-TWEET*****\u001B[0m");
		CloneXRunner.database.getProfilesWithTweets();
		int userId = util.intFromUser("Hangi ID'li profildeki tweet'i reTweet edeceksiniz: ");
		int tweetId = util.intFromUser("Hangi ID'li tweet'i reTweet edeceksiniz: ");
		for (int i = 0; i < CloneXRunner.database.userList.size(); i++) {
			if (CloneXRunner.database.userList.get(i).getProfile().getId()==userId) {
				isUserFound = true;
				Tweet tweet = new Tweet(CloneXRunner.database.userList.get(i).getProfile().getTweetList().get(tweetId));
				user.getProfile().getTweetList().put(tweet.getId(),
					CloneXRunner.database.userList.get(i).getProfile().getTweetList().get(tweetId));
				System.out.println("ReTweet Edildi..");
				isTweetFound = true;
				break;
			}
		}
		if (!isTweetFound) {
			System.out.println("\u001B[31mBöyle bir tweet bulunamadı.\u001B[0m");
		}
		if (!isUserFound) {
			System.out.println("\u001B[31mBöyle bir kullanıcı bulunamadı.\u001B[0m");
		}
	}

	public void like() {
		System.out.println("\u001B[34m*****LIKE*****\u001B[0m");
		CloneXRunner.database.getProfilesWithTweets();
		int userId = util.intFromUser("Hangi ID'li profildeki tweet'i beğeneceksiniz: ");
		int tweetId = util.intFromUser("Hangi ID'li tweet'i beğeneceksiniz: ");
		for (int i = 0; i < CloneXRunner.database.userList.size(); i++) {
			if (CloneXRunner.database.userList.get(i).getProfile().getId()==userId) {
				isUserFound = true;
				for (int j = 0; j < CloneXRunner.database.userList.get(i).getProfile().getTweetList().size(); j++) {
					if (!(CloneXRunner.database.userList.get(i).getProfile().getTweetList().get(j).equals(null)) && 
							CloneXRunner.database.userList.get(i).getProfile().getTweetList().get(j).getId()==tweetId) {
						
						CloneXRunner.database.userList.get(i).getProfile().getTweetList()
						.get(j).setLikes((CloneXRunner.database.userList.get(i)
						.getProfile().getTweetList().get(j).getLikes()+1));
						System.out.println("Beğeniniz alınmıştır..");
						isTweetFound = true;
						break;
				}
				}
			}
		}
		if (!isTweetFound) {
			System.out.println("\u001B[31mBöyle bir tweet bulunamadı.\u001B[0m");
		}
		if (!isUserFound) {
			System.out.println("\u001B[31mBöyle bir kullanıcı bulunamadı.\u001B[0m");
		}
	}

	public void tweet(User user) {
		System.out.println("\u001B[34m*****TWEET*****\u001B[0m");
		for (int i = 0; i < CloneXRunner.database.hashTags.length; i++) {
			System.out.println(i+"-"+CloneXRunner.database.hashTags[i].name());
		}
		String tweetContent = util.stringFromUser("Tweet içeriğiniz: ");
		Tweet tweet = new Tweet(tweetContent);
		int hashTagAmount = util.intFromUser("Kaç HashTag Gireceksiniz? 0-4 arası girin: ");
		if (!(hashTagAmount==0)) {
			for (int i = 0; i < hashTagAmount; i++) {
				int selection = util.intFromUser((i+1)+". HashTaginiz: ");
				tweet.getHashTags().add(i, (CloneXRunner.database.hashTags[selection]));;
			}
		}		
		user.getProfile().getTweetList().put(tweet.getId(),tweet);
		user.getProfile().setTweets(user.getProfile().getTweets()+1);
		System.out.println("Tweet'iniz yayınlandı.");
	}

	public void comment() {
		System.out.println("\u001B[34m*****COMMENT*****\u001B[0m");
		CloneXRunner.database.getProfilesWithTweets();
		int userId = util.intFromUser("Hangi ID'li profildeki tweet'e yorum yapacaksınız: ");
		int tweetId = util.intFromUser("Hangi ID'li tweet'e yorum yapacaksınız: ");
		String commentContent = util.stringFromUser("Yorumunuz: ");
		for (int i = 0; i < CloneXRunner.database.userList.size(); i++) {
			if (CloneXRunner.database.userList.get(i).getProfile().getId()==userId) {
				isUserFound = true;
				for (int j = 0; j < CloneXRunner.database.userList.get(i).getProfile().getTweetList().size(); j++) {
					if (!(CloneXRunner.database.userList.get(i).getProfile().getTweetList().get(j).equals(null)) && 
							CloneXRunner.database.userList.get(i).getProfile().getTweetList().get(j).getId()==tweetId) {
						
						CloneXRunner.database.userList.get(i).getProfile().getTweetList()
						.get(j).getListOfComments().add(new Comment(commentContent));
						
						CloneXRunner.database.userList.get(i).getProfile().getTweetList()
						.get(j).setComments(CloneXRunner.database.userList.get(i)
						.getProfile().getTweetList().get(j).getComments()+1);
						System.out.println("Yorumunuz alınmıştır..");
						isTweetFound = true;
						break;
				}
				}
			}
		}
		if (!isTweetFound) {
			System.out.println("\u001B[31mBöyle bir tweet bulunamadı.\u001B[0m");
		}
		if (!isUserFound) {
			System.out.println("\u001B[31mBöyle bir kullanıcı bulunamadı.\u001B[0m");
		}
	}
	
	public User register() {
		System.out.println("\u001B[34m*****REGISTER*****\u001B[0m");
		do {
			String username = util.stringFromUser("Username: ");
			String password = util.stringFromUser("Password: ");
			if (password.length()>8) {
				String repassword = util.stringFromUser("re-Password: ");
				if (repassword.equals(password)) {
					String street = util.stringFromUser("Sokak: ");
					String city = util.stringFromUser("Şehir: ");
					String country = util.stringFromUser("Ülke: ");
					String postalCode = util.stringFromUser("Posta Kodu: ");
					String phone = util.stringFromUser("Phone Number: ");
					String bio = util.stringFromUser("Bio: ");
					User user = new User(username, password, phone);
					user.getProfile().setAddress(street,city,country,postalCode);
					user.getProfile().setBio(bio);
					System.out.println("Kayıt Başarılı.."+ username);
					return user;
				}else {
					System.out.println("\u001B[31mŞifreleriniz eşleşmiyor.\u001B[0m");
				}
			}else {
				System.out.println("\u001B[31mŞifreniz 8 karakterden uzun olmalıdır.\u001B[0m");
			}
			return null;
		} while (true);
	}
	
	public User login() {
		System.out.println("\u001B[34m*****LOGIN*****\u001B[0m");
		String username = util.stringFromUser("Username: ");
		String password = util.stringFromUser("Password: ");
		do {
			for (User user : CloneXRunner.database.userList) {
				if (user.getUsername().equals(username) && user.getPassword().equals(password)) {
						return user;
					}
				}
			System.out.println("\u001B[31mGiriş bilgileriniz hatalı...\u001B[0m");
			return null;
		} while (true);	
	}
	
}
