package ish;

import java.util.List;
import java.util.Scanner;
import java.util.stream.Stream;

public class MainV2 {
	static int loggedIn;
	
	public static void main(String[] args) {
		/*
		 * 
		 *  x1. Admin Login
		 *  x1.1 Add Books
		 *  x1.2 Delete Books
		 *  x1.3 Add Users
		 *  x1.4 Delete Users
		 *  
		 *  x2. User Login
		 *  x2.1 Check out Book
		 *  x2.2 Check in Book
		 *  x2.3 Search Books
		 *  x2.4 View Checked out Books
		 *  
		 *  x3. User Register
		 *  x3.1Register A User
		 *  
		 *  x4. Exit
		 *  
		 *  Final Step: Serialize EVERYTHING
		*/
		
		Admin b = new Admin("admin", "admin");
		Admin.aList.add(b);
		UserAcc.createAcc("bill", "nye");
		Book.createBook("Bible", "Jesus", "Fiction");
		Book.createBook("Something", "Jesus", "Fiction");
		Book.createBook("Ass", "Jesus", "Fiction");
		menu();
		
		
		
	}
	
	public static void adminMenu() {
		Scanner sAM = new Scanner(System.in);
		Scanner sBooks = new Scanner(System.in);
		int n;
		int w = 0;
		
		do {
		System.out.println("Welcome to the Admin Menu"
				+ "\n1. Add a Book" +
				"\n2. Delete a Book" +
				"\n3. Add a User" +
				"\n4. Delete a User" +
				"\n5. Exit");
		n = sAM.nextInt();
		
		if(n == 1) {
			do {
			System.out.println("Enter the book title: ");
			String bTitle = sBooks.nextLine();
			System.out.println("Enter the Author: ");
			String bAuthor = sBooks.nextLine();
			System.out.println("Enter the Genre: (Fiction, Nonfiction, Drama, Mystery, Scifi, Adventure, Horror)");
			String bGenre = sBooks.nextLine();
			Book.createBook(bTitle, bAuthor, bGenre);	
			System.out.println("Would you like to create another book? (yes or no) ");
			String yON = sBooks.nextLine();
			
			if(yON.equalsIgnoreCase("yes")) {
				w = 1;
			}
			else {
				w = 0;
			}
			}while(w == 1);
			
		}
		
		if(n == 2) {
			System.out.println("Enter the title of the book you would like to delete: ");
			String bTitle = sBooks.nextLine();
			if(containsBook(Book.booklist, bTitle)) {
			for(int i = 0; i < Book.booklist.size(); i++) {
				if(Book.booklist.get(i).getTitle().equals(bTitle) == true) {
					Book.booklist.remove(i);
					System.out.println("Book Successfully Removed");
				}				
			}
			}
			else {
				System.out.println("Book not found in System");
			}
			
			
		}
		if(n == 3) { 
			System.out.println("Enter the Username for the User: ");
			String uName = sBooks.nextLine();
			System.out.println("Enter a Password for the User: ");
			String uPass = sBooks.nextLine();
			UserAcc.createAcc(uName, uPass);
		}
		if(n == 4) {
			System.out.println("Enter the Username of the Account you would like to delete: ");
			String uName = sBooks.nextLine();
			if(containsUser(UserAcc.ulist, uName)) {
				UserAcc.deleteAcc(uName);
			}
			else {
				System.out.println("User not found in System");
			}
		}
		
		}while(n != 5);
	}
	
	public static void userMenu() {
		Scanner sUM = new Scanner(System.in);
		Scanner scanText = new Scanner(System.in);
		int n;
		int w = 1;
		do {
		System.out.println("Welcome to the User Menu" + 
				"\n1. Check out Book" + 
				"\n2. Check in Book" +
				"\n3. Search Books" +
				"\n4. View my Books"+
				"\n5. Exit");
		n = sUM.nextInt();
		if(n == 1) {
			System.out.println("CheckoutShit");
			Scanner coScan = new Scanner(System.in);
			do {
			int checkOutID = -1;
			try{
				System.out.println("Enter the BookID of the book you would like to CheckOut: ");
			
			checkOutID = coScan.nextInt();
			}
			catch(Exception e) {
				System.out.println("Invalid input, please type integer value");
				break;
			}
			if(checkOutID == -1 == false) {
			for(int i = 0; i < Book.booklist.size(); i++) {
				if(Book.booklist.get(i).getID() == checkOutID) {
					System.out.println("The book you've selected is: " + Book.booklist.get(i).getTitle() +
							"\nBy the Author: " + Book.booklist.get(i).getAuthor() +
							"\nIs this correct? (Yes or no)");
					String yON = scanText.nextLine();
					if(yON.equalsIgnoreCase("yes")) {
						Boolean b = Book.booklist.get(i).getCheckedOut();
						if(b) {
							System.out.println("Sorry, the Book is Already Checked out");
						}
						else {
							Book.booklist.get(i).setCheckedOut(true);
							System.out.println("You have checked out the Book: " + Book.booklist.get(i).getTitle() + 
									"\nBy the Author: " + Book.booklist.get(i).getAuthor());
							UserAcc.ulist.get(loggedIn).addBook(checkOutID);
							w = 0;
							
						}
					}
				}
			}
		}
			}while(w == 1);
			
			
		}
		if(n == 2) {
			System.out.println("CheckinShit");
			System.out.println("Enter the Book ID of the book you would like to Check in: ");
			int chkInID = sUM.nextInt(); 
			UserAcc.ulist.get(loggedIn).checkInBook(chkInID);
			
		}
		if(n == 3) {
			Scanner searchScan = new Scanner(System.in);
			Scanner textScan = new Scanner(System.in);
			System.out.println("Search Shit");
			int searchN;
			do{
				System.out.println("Search by:" +
					"\n1. Title"
					+ "\n2. Author"
					+ "\n3. Genre"
					+"\n4. Exit");
			searchN = searchScan.nextInt();
			if(searchN == 1) {
				System.out.println("Search by title: ");
				String searchTitle = textScan.nextLine();
				if(containsBook(Book.booklist, searchTitle)) {
					for(int i = 0; i < Book.booklist.size(); i++) {
						if(Book.booklist.get(i).getTitle().equals(searchTitle) == true) {
							System.out.println("Search Results: ");
							System.out.println(Book.booklist.get(i).toString());
				}
			}
				}
				else {
					System.out.println("Book not found");
				}
			}
			if(searchN == 2) {
				System.out.println("Search by Author: (Type exit to quit)");
				String searchAuthor = textScan.nextLine();
				if(searchAuthor.equalsIgnoreCase("exit") == false) {
				if(containsAuthor(Book.booklist, searchAuthor)) {
					System.out.println("Search Results: ");
					for(int i = 0; i < Book.booklist.size(); i++) {
						if(Book.booklist.get(i).getAuthor().equals(searchAuthor) == true) {
							System.out.println(Book.booklist.get(i).toString());
						}
					}
				}
				else {
					System.out.println("Author not found");
				}
				}
				
			}
			if(searchN == 3) {
				System.out.println("Search by Genre: (Fiction, Nonfiction, Drama, Mystery, Scifi, Adventure, Horror, exit to quit)");
				String searchGenre = textScan.nextLine();
				if(searchGenre.equalsIgnoreCase("exit") == false) {
					if(containsGenre(Book.booklist, searchGenre)) {
						System.out.println("Search Results: ");
						for(int i = 0; i < Book.booklist.size(); i++) {
							if(Book.booklist.get(i).getGenre().equals(searchGenre) == true) {
								System.out.println(Book.booklist.get(i).genreFormat());
							}
						}
					}
					else {
						System.out.println("Something went wrong/Invalid genre");
					}
					
				}
					
			}
			
			}while(searchN != 4);
		}
		if(n == 4) {
			System.out.println("Your checked out Books: ");
			UserAcc.ulist.get(loggedIn).printBooks();
				}
			
		
		
		}while(n != 5);
	}
	
	
	
	public static void menu() {
		Scanner sMenu = new Scanner(System.in);
		int n;
		do {
		System.out.println("Enter the number to select the menu: ");
		System.out.println("1. Admin login"
				+ "\n2. User Login"
				+ "\n3. Register a User Account"
				+ "\n4. Exit");
		n = sMenu.nextInt();
		
		if(n == 1) {
			System.out.println("Admin Login");
			loginAdmin();
		}
		if(n == 2) {
			System.out.println("User Login");
			loginUser();
		}
		if(n == 3) {
			createUser();
		}
		if(n == 4) {
			System.out.println("Exit");
			System.exit(0);
		}
		
		} while(n != 4);
	}
	
	public static void loginAdmin() {
		Scanner login = new Scanner(System.in);
		System.out.println("Enter a Username:");
		String adminUse = login.nextLine();
		String adminPass; 
		
		for(int i = 0; i < Admin.aList.size(); i++) {
			if(Admin.aList.get(i).getUsername().equals(adminUse) == true) { 
				System.out.println("Enter a users password: ");
				adminPass = login.nextLine();
				if(Admin.aList.get(i).getPassword().equals(adminPass) == true) {
					System.out.println("you logged in idiot");
					adminMenu();
				}
				else {
					System.out.println("Incorrect Password");
				}
				
			}
		}
		
	}
	
	public static void createUser() {
		Scanner sCreateU = new Scanner(System.in);
		System.out.println("Create a Username: ");
		String uName = sCreateU.nextLine();
		System.out.println("Create a Password: ");
		String uPass = sCreateU.nextLine();
		UserAcc.createAcc(uName, uPass);
		System.out.println("Account Created!");
		UserAcc.printList();
	}
	public static void loginUser() {
		Scanner ulogin = new Scanner(System.in);
		System.out.println("Enter a Username:");
		String uName = ulogin.nextLine();
		String uPass; 
		
		if(containsUser(UserAcc.ulist, uName)) {
		for(int i = 0; i < UserAcc.ulist.size(); i++) {
			if(UserAcc.ulist.get(i).getUsername().equals(uName) == true) { 
				System.out.println("Enter a users password: ");
				uPass = ulogin.nextLine();
				if(UserAcc.ulist.get(i).getPassword().equals(uPass) == true) {
					System.out.println("you logged in idiot");
					loggedIn = i;
					System.out.println("Welcome " + UserAcc.ulist.get(loggedIn).getUsername());

					userMenu(); 
				}
				else {
					System.out.println("Incorrect Password");
				}
				
			}
		}
		}
		else {
			System.out.println("User not in System");
		}
		
	}
	
	public static boolean containsUser(final List<UserAcc> list, final String name) {
		return list.stream().filter(o -> o.getUsername().equals(name)).findFirst().isPresent();
		
	}
	
	public static boolean containsBook(final List<Book> list, final String name) {
		return list.stream().filter(o -> o.getTitle().equals(name)).findFirst().isPresent();
	}
	
	public static boolean containsAuthor(final List<Book> list, final String name) {
		return list.stream().filter(o -> o.getAuthor().equals(name)).findFirst().isPresent();
	}
	
	public static boolean containsGenre(final List<Book> list, final String name) {
		return list.stream().filter(o -> o.getGenre().equals(name)).findFirst().isPresent();
	}
	
}
