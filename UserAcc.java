package ish;


import java.io.*;
import java.nio.file.Files;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.concurrent.atomic.AtomicInteger;

public class UserAcc extends Account implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -3881595250232811528L;
	static ArrayList<UserAcc> ulist = new ArrayList<>();
	static File accFile = new File("AccountList.txt");
	private ArrayList<String> checkedOutBooks = new ArrayList<>();
	private int aID;
	
	UserAcc() {
		username = null;
		password = null;
		
	}
	
	UserAcc(String username, String password) {
		this.username = username;
		this.password = password;
		aID = ulist.get(ulist.size() - 1).getId() + 1 ;
		checkedOutBooks = new ArrayList<String>();
		
	}
	public String getUsername() {
		return username;
	}
	
	public String getPassword() {
		return password;
	}
	
	public int getId() {
		return aID;
	}
	
	public void addBook(String title) {
		checkedOutBooks.add(title);
		serializeAcc(ulist);
	}
	public void removeBook(String title) {
		checkedOutBooks.remove(title);
		serializeAcc(ulist);
	}
	
	public void printBookIDs() {
		for(String i: checkedOutBooks) {
			System.out.println(i + " ");
		}
	}
	
	public void emergancyEmpty() {
		checkedOutBooks.clear();
	}
	public void checkInBook(String title) { 
		Scanner scan = new Scanner(System.in);
		
		if(checkedOutBooks.contains(title)){
			System.out.println("Would you like to check in " + title + "? (yes or no)");
			String yON = scan.nextLine();
			if(yON.equalsIgnoreCase("yes")) {
				for(int i = 0; i < Book.booklist.size(); i++) {
					if(title.equals(Book.booklist.get(i).getTitle()) == true){
						checkedOutBooks.remove(title);
						Book.booklist.get(i).setCheckedOut(false);
						serializeAcc(ulist);
						Book.serializeBooks(Book.booklist);
						System.out.println("Book Successfully Checked in!");
					}
				}
			}
		}
		else {
			System.out.println("That book doesn't seem to be checked out to you");
		}

	}
	
	public void printBooks() {
		for(int i = 0; i < checkedOutBooks.size(); i++) {
			String title = checkedOutBooks.get(i);
			for(int z = 0; z < Book.booklist.size(); z++) {
				if(Book.booklist.get(z).getTitle().equals(title) == true) {
					System.out.println(Book.booklist.get(z).toString());
				}
			}
		}
	}
	
	public static void createAcc(String u, String p) {
		UserAcc ua = new UserAcc(u, p);
		ulist.add(ua);
		System.out.println("User Account successfully created!");
		UserAcc.serializeAcc(ulist);
	}
	
	public static void deleteAcc(String u) {
		for(int i = 0; i < ulist.size(); i++) {
			if(ulist.get(i).getUsername().equals(u) == true){
				ulist.remove(i);
				System.out.println("User Successfully Deleted");
			}
		}
		serializeAcc(ulist);
	}
	
	public static void serializeAcc(ArrayList<UserAcc> list) {
		try {
			ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(accFile));
			oos.writeObject(list);
			}
		catch(IOException io) {
			io.printStackTrace();
		}
		
	}
	
	public static ArrayList<UserAcc> readAccs(ArrayList<UserAcc> list) {
		try {
			ObjectInputStream ois = new ObjectInputStream(new FileInputStream(accFile));
			ulist = (ArrayList<UserAcc>) ois.readObject();
			System.out.println("File Successfully Read");
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		return ulist;
	}
	
	public static void printList() {
		for(UserAcc ua: ulist) {
			System.out.println(ua.toString());
		}
	}
	
	public boolean equals(UserAcc me) {
        boolean isEqual=false;
        if(me!=null && me instanceof UserAcc) {
            isEqual=(this.username==me.username);
        }
        return isEqual;
    }
	
	public String toString() {
		return "Username: " + username +
				"\nAccount ID: " + aID;
		
	}

}
